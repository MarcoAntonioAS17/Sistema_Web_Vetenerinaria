package Config;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    Connection con;
    
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/veterinaria";

    static final String USER = "root";
    static final String PASS = "2351043820";

    public Conexion() {
        try {
            Class.forName(JDBC_DRIVER);

            this.con = DriverManager.getConnection(DB_URL, USER,PASS);

        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    public Connection getConnection() {
        return this.con;
    }
    
}
