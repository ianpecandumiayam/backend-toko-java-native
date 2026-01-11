import java.sql.*;
public class BackendStok{
public static void main(String[] args){
String url = "jdbc:sqlite:barang_septian.db";
String query = "select barang.kode, barang.namabar,barang.harga, kategori.nama_kategori " + "from barang " + "inner join kategori on barang.id_kategori = kategori.id";
System.out.println("sedang menghubungkan ke database...");
try (Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();
ResultSet rs = st.executeQuery(query)){

System.out.println("koneksi ke database terhubung! ini datanya:\n");
System.out.println("-------------------------------------------------");
System.out.println(String.format("| %-5s | %-15s | %-12s | %-12s |", "KODE", "NAMA", "HARGA", "KATEGORI"));
System.out.println("------------------------------------------------");

while (rs.next()){
String kode = rs.getString("kode");
String nama = rs.getString("namabar");
int harga = rs.getInt("harga");
String kategori = rs.getString("nama_kategori");

System.out.println(String.format("| %-5s | %-15s | Rp%-10d | %-12s |", kode, nama, harga, kategori));
}
System.out.println("-------------------------------------------------");

}catch(SQLException e){
System.out.println("ERROR PARAH: "+e.getMessage());
}
}
}
