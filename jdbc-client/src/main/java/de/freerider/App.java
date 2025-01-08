package de.freerider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    // DB connection information
    static final String DB_URL  = "jdbc:mysql://localhost/FREERIDER_DB";
    static final String USER    = "freerider";
    static final String PASS    = "free.ride";

    // DB Query
    static final String QUERY   = "SELECT * FROM CUSTOMER;";

    public static void main(String[] args) {
        System.out.println( "Hello FREERIDER_DB!" );

        // Open a connection
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {

            // create prepare-statement from connection
            Statement stmt = conn.createStatement();

            // send query to DB-server
            ResultSet rs = stmt.executeQuery(QUERY);

            // extract data from result set
            while(rs.next()) {
                // Retrieve by column name
                System.out.print("id: " + rs.getInt("ID"));
                System.out.print(", name: " + rs.getString("NAME"));
                System.out.print(", contact: " + rs.getString("CONTACT"));
                System.out.println(", status: " + rs.getString("STATUS"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}