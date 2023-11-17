package data;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class ConnectionJDBC {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/control_clientes?useSSL=false&serverTimezone=UTC";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "admin";

    public static DataSource getDataSource() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error loading MySQL JDBC driver", e);
        }

        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(JDBC_URL);
        ds.setUsername(JDBC_USER);
        ds.setPassword(JDBC_PASSWORD);
        ds.setInitialSize(50);
        return ds;

    }

    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();

    }

    public static void close(ResultSet rs) {
        try {
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(ConnectionJDBC.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void close(PreparedStatement stmt) {
        try {
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(ConnectionJDBC.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void close(Connection conn) {
        try {
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(ConnectionJDBC.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}
