package br.com.mgr.domain.client.dao;

import br.com.mgr.domain.client.entity.Client;
import br.com.mgr.exceptions.MyRuntimeException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {

    private final Connection connection;

    public ClientDao(Connection connection) {
        this.connection = connection;
    }

    public Client save(Client client) {
        try {
            String sql = "INSERT INTO CLIENT (NAME, STATUS) VALUES (?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, client.getName());
                pstm.setString(2, client.getStatus());
                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        client.setId(rst.getLong(1));
                    }
                }
            }
            return client;
        } catch (SQLException e) {
            throw new MyRuntimeException(e);
        }

    }

    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        try {
            String sql = "SELECT ID, NAME, STATUS FROM CLIENT";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();

                mapperResultSetToClient(clients, pstm);
            }
            return clients;
        } catch (SQLException e) {
            throw new MyRuntimeException(e);
        }
    }

    public List<Client> find(String filter) {
        List<Client> clients = new ArrayList<>();
        try {

            String sql = "SELECT ID, NAME, STATUS FROM CLIENT WHERE NAME RLIKE ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, filter);
                pstm.execute();

                mapperResultSetToClient(clients, pstm);
            }
            return clients;
        } catch (SQLException e) {
            throw new MyRuntimeException(e);
        }
    }

    public void delete(Long id) {
        try (PreparedStatement stm = connection.prepareStatement("DELETE FROM CLIENT WHERE ID = ?")) {
            stm.setLong(1, id);
            stm.execute();
        } catch (SQLException e) {
            throw new MyRuntimeException(e);
        }
    }

    public void update(String name, String status, Long id) {
        try (PreparedStatement stm = connection
                .prepareStatement("UPDATE CLIENT C SET C.NAME = ?, C.STATUS = ? WHERE ID = ?")) {
            stm.setString(1, name);
            stm.setString(2, status);
            stm.setLong(3, id);
            stm.execute();
        } catch (SQLException e) {
            throw new MyRuntimeException(e);
        }
    }

    private void mapperResultSetToClient(List<Client> clients, PreparedStatement pstm) throws SQLException {
        try (ResultSet rst = pstm.getResultSet()) {
            while (rst.next()) {
                Client client = new Client(rst.getLong(1), rst.getString(2), rst.getString(3));

                clients.add(client);
            }
        }
    }

}
