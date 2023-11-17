/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import domain.Client;
import java.util.List;

/**
 *
 * @author PC 4060TI
 */
public interface ClientDaoJDBC {
    static final String SQL_SELECT = "SELECT id_cliente, nombre, apellido, email, telefono, saldo "
            + "FROM cliente";
    
    static final String SQL_SELECT_BY_ID = "SELECT id_cliente, nombre, apellido, email, telefono, saldo "
            + "FROM cliente WHERE id_cliente = ?";
    
    static final String SQL_INSERT = "INSERT INTO cliente (nombre, apellido, email, telefono, saldo) "
            + " VALUES(?, ?, ?, ?, ?)";
    
    static final String SQL_UPDATE = "UPDATE cliente "
            + "SET nombre=?, apellido=?, email=?, telefono=?, saldo=? WHERE id_cliente = ?";
    
    static final String SQL_DELETE = "DELETE FROM cliente WHERE id_cliente = ?";
    
    public List<Client> listar();
    public Client encontrar(Client client);
    public int insertar(Client client);
    public int actualizar(Client client);
    public int eliminar(Client client);
}
