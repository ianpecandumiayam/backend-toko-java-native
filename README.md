Backend API (Server Side)
Selain versi GUI Desktop, proyek ini sekarang sudah berevolusi memiliki **Backend Server** sendiri.
Dibuat menggunakan **Java Native (HttpServer)** tanpa framework, berjalan di Port 8000.

## Fitur Backend
- **REST API Manual:** Membuat endpoint API dari nol menggunakan `com.sun.net.httpserver`.
- **JSON Response:** Server merespon request dalam format JSON (bukan teks biasa).
- **Database Connection:** Terhubung ke SQLite yang sama, menggunakan Logic `JOIN` untuk relasi tabel Barang & Kategori.
- **Background Process:** Bisa dijalankan di server Linux menggunakan `nohup` (Daemon).

## Cara Tes API (Terminal linux)
1. Compile Server:
   `javac ServerStok.java`
2. Jalankan Server (Wajib bawa driver SQLite):
   `java -cp ".:sqlite-jdbc-3.51.1.0.jar" ServerStok`
3. Tembak API:
   `curl -v http://localhost:8000/api/stok`
**Dibuat oleh:** ianpecandumiayam
