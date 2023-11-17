package data;

import domain.Client;
import java.sql.*;
import java.util.*;

/**
 *
 * @author PC 4060TI
 */
public class ClientDaoJDBCImpl implements ClientDaoJDBC {

    @Override
    public List<Client> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Client client = null;
        List<Client> clients = new ArrayList<>();
        try {
            conn = ConnectionJDBC.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idCliente = rs.getInt("id_cliente");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                double saldo = rs.getDouble("saldo");

                client = new Client(idCliente, nombre, apellido, email, telefono, saldo);
                clients.add(client);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConnectionJDBC.close(rs);
            ConnectionJDBC.close(stmt);
            ConnectionJDBC.close(conn);
        }
        return clients;
    }

    @Override
    public Client encontrar(Client client) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectionJDBC.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, client.getIdCliente());
            rs = stmt.executeQuery();
            rs.absolute(1);

            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            String email = rs.getString("email");
            String telefono = rs.getString("telefono");
            double saldo = rs.getDouble("saldo");

            client.setNombre(nombre);
            client.setApellido(apellido);
            client.setEmail(email);
            client.setTelefono(telefono);
            client.setSaldo(saldo);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConnectionJDBC.close(rs);
            ConnectionJDBC.close(stmt);
            ConnectionJDBC.close(conn);
        }
        return client;
    }

    @Override
    public int insertar(Client client) {

        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ConnectionJDBC.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, client.getNombre());
            stmt.setString(2, client.getApellido());
            stmt.setString(3, client.getEmail());
            stmt.setString(4, client.getTelefono());
            stmt.setDouble(5, client.getSaldo());

            rows = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConnectionJDBC.close(stmt);
            ConnectionJDBC.close(conn);
        }
        return rows;
    }

    @Override
    public int actualizar(Client client) {
        
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ConnectionJDBC.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, client.getNombre());
            stmt.setString(2, client.getApellido());
            stmt.setString(3, client.getEmail());
            stmt.setString(4, client.getTelefono());
            stmt.setDouble(5, client.getSaldo());
            stmt.setInt(6, client.getIdCliente());

            rows = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConnectionJDBC.close(stmt);
            ConnectionJDBC.close(conn);
        }
        return rows;
    }

    @Override
    public int eliminar(Client client) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ConnectionJDBC.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, client.getIdCliente());

            rows = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConnectionJDBC.close(stmt);
            ConnectionJDBC.close(conn);
        }
        return rows;
    }
    
}
