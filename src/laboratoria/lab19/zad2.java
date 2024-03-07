package laboratoria.lab19;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//        Napisz program, który korzystając z bazy danych AdventureWorks wyświetli podstawowe dane 10. pierwszych osób (Person.Contact),
//        którzy mają na nazwisko „Anderson”.
//
//        Dokonaj modyfikacji powyższego programu, aby można było wyszukiwać osoby podając początek nazwiska.

public class zad2 {
    public static void main(String[] args) {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("morfeusz.wszib.edu.pl");
        ds.setUser("pedrys");
        ds.setPassword("");
        ds.setPortNumber(1433);
        ds.setDatabaseName("AdventureWorks");
        ds.setTrustServerCertificate(true);

        String sql = "SELECT FirstName, LastName FROM Person.Contact WHERE LastName LIKE ?";

        if (args.length != 0) {
            ResultSet rs = null;
            try (Connection con = ds.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {
                String beginning = args[0];
                ps.setString(1, beginning + "%");
                rs = ps.executeQuery();

                if (rs.next()) {
                    do {
                        System.out.println(rs.getString(1) + " " + rs.getString(2));
                    } while (rs.next());
                } else {
                    System.out.println("Brak osób o nazwisku zaczynającym się na " + beginning);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException e) {
                        System.out.println("Problem przy close dla ResultSet");
                    }
                }
            }
        } else {
            System.out.println("Należy w args podać początek nazwiska...");
        }
    }
}