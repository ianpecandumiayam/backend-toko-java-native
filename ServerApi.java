import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class ServerApi {
    public static void main(String[] args) throws IOException {
        // 1. TENTUIN NOMOR LAPAK (PORT)
        // Port 8000 itu kayak "Ruko Nomor 8000". Tempat kita jualan.
        int port = 8000;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        
        // 2. BIKIN MENU (ROUTE/ENDPOINT)
        // Kalau ada yang akses "/api/test", suruh "TestHandler" yang ngelayanin.
        server.createContext("/api/test", new TestHandler());
        
        // 3. BUKA WARUNG
        server.setExecutor(null); // Pake settingan default aja
        server.start();
        System.out.println("🚀 SERVER NYALA BOS! Cek di: http://localhost:" + port + "/api/test");
    }

    // INI "KOKI" YANG MASAK DATANYA
    static class TestHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // A. SIAPIN DATANYA (JSON)
            // Kita tulis manual string JSON-nya (Backslash \ biar tanda petiknya gak error)
            String respon = "{\"status\": \"sukses\", \"pesan\": \"Halo Ian, Server lu jalan!\", \"owner\": \"Septian\"}";
            
            // B. KIRIM HEADER (Kasih tau ini JSON & Kodenya 200 OK)
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, respon.length());
            
            // C. KIRIM ISI DATANYA (BODY)
            OutputStream os = exchange.getResponseBody();
            os.write(respon.getBytes());
            os.close();
        }
    }
}
