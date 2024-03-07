package laboratoria.lab19;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//Napisz program, który korzystając z bazy danych AdventureWorks
// wyświetli podstawowe dane 10. pierwszych osób (Person.Contact),
// którzy mają na nazwisko „Anderson”.
public class zad1 {
    public static void main(String[] args) {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("morfeusz.wszib.edu.pl");
        ds.setUser("pedrys");
        ds.setPassword("");
        ds.setPortNumber(1433);
        ds.setDatabaseName("AdventureWorks");
        ds.setTrustServerCertificate(true);
        String sql = "SELECT TOP 10 FirstName, LastName FROM Person.Contact WHERE LastName = 'Anderson'";

        try (Connection con = ds.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}