import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class ServerApi {
    public static void main(String[] args) throws IOException {
        int port = 8000;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/api/test", new TestHandler());
        server.setExecutor(null); 
        server.start();
        System.out.println("🚀 SERVER NYALA BOS! Cek di: http://localhost:" + port + "/api/test");
    }
    static class TestHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String respon = "{\"status\": \"sukses\", \"pesan\": \"Halo Ian, Server lu jalan!\", \"owner\": \"Septian\"}";
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, respon.length());
            OutputStream os = exchange.getResponseBody();
            os.write(respon.getBytes());
            os.close();
        }
    }
}
