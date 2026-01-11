import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.sql.*;

public class ServerStok {
    public static void main(String[] args) throws IOException {
        int port = 8000;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/api/stok", new StokHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("🚀 SERVER DATABASE NYALA! Cek: http://localhost:" + port + "/api/stok");
    }

    static class StokHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // 1. SIAPIN WADAH JSON
            StringBuilder jsonResult = new StringBuilder();
            jsonResult.append("["); 

            // 2. KONEK KE DB & AMBIL DATA (Logika Step 2)
            String url = "jdbc:sqlite:barang_septian.db";
            String query = "SELECT barang.kode, barang.namabar, barang.harga, kategori.nama_kategori " +
                           "FROM barang LEFT JOIN kategori ON barang.id_kategori = kategori.id";

            try (Connection conn = DriverManager.getConnection(url);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {

                boolean first = true;
                while (rs.next()) {
                    
                    if (!first) jsonResult.append(",");
                    first = false;

                    String nama = rs.getString("namabar");
                    int harga = rs.getInt("harga");
                    String kat = rs.getString("nama_kategori");

                    
                    jsonResult.append(String.format("{\"nama\": \"%s\", \"harga\": %d, \"kategori\": \"%s\"}", 
                                      nama, harga, kat != null ? kat : "Tanpa Kategori"));
                }

            } catch (SQLException e) {
                e.printStackTrace(); 
                String errorJson = "{\"status\": \"error\", \"pesan\": \"Database Meledak!\"}";
                kirimRespon(exchange, 500, errorJson);
                return;
            }

            jsonResult.append("]"); 
            
            // 3. KIRIM HASILNYA
            kirimRespon(exchange, 200, jsonResult.toString());
        }

        
        private void kirimRespon(HttpExchange exchange, int kode, String isi) throws IOException {
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(kode, isi.length());
            OutputStream os = exchange.getResponseBody();
            os.write(isi.getBytes());
            os.close();
        }
    }
}
