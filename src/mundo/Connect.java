package mundo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    private String url = "jdbc:mysql://localhost:3306/tienda?serverTimezone=UTC";
    private String username = "root";
    private String password = "Sharito1977*";

    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url, username, password);
    }

}
