#  Aplikasi Manajemen Stok Barang 

Aplikasi desktop sederhana berbasis **Java Swing** dan **SQLite** untuk mengelola stok barang di toko. Proyek ini dibuat untuk belajar konsep **CRUD** (Create, Read, Update, Delete) dan database.

## Screenshots
--

##  Fitur Utama
*  **Input Barang:** Nambahin data barang baru (Kode, Nama, Harga).
*  **Dashboard:** Nampilin semua stok dalam bentuk Tabel yang rapi.
*  **Update Harga:** Revisi data kalau ada typo atau perubahan harga.
*  **Hapus Barang:** Bersih-bersih stok yang udah gak dijual (Bye-bye Etanol!).
*  **Database:** Data tersimpan aman di SQLite (gak ilang pas aplikasi ditutup).

## Teknologi yang Dipakai
* **Bahasa:** Java (JDK 8+)
* **GUI:** Java Swing (JFrame, JTable)
* **Database:** SQLite (JDBC Driver)
* **Editor:** VS Code / Text Editor di Linux Ubuntu

## Cara Menjalankan (Run)
1.  Pastikan sudah install Java dan download driver `sqlite-jdbc`.
2.  Clone repo ini:
    ```bash
    git clone [https://github.com/ianpecandumiayam/Proyek-Toko-Java.git](https://github.com/ianpecandumiayam/Proyek-Toko-Java.git)
    ```
3.  Compile dan Run file `dasboardBarang.java`.

---
---

# Update: Backend API (Server Side)

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
