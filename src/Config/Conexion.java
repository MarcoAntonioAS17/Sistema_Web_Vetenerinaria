package Config;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    Connection con;

    public Conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/veterinaria", "root", "2351043820");

        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    public Connection getConnection() {
        return this.con;
    }
    
}
