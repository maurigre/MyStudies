package br.com.mgr.service;

import br.com.mgr.domain.client.dao.ClientDao;
import br.com.mgr.domain.client.entity.Client;

import java.sql.Connection;
import java.util.List;

public class ClientServiceImp implements ClienteService {

    private final ClientDao clientDao;

    public ClientServiceImp(Connection connection) {
        this.clientDao = new ClientDao(connection);
    }

    @Override
    public Client save(Client client) {
        return this.clientDao.save(client);
    }

    @Override
    public void update(String name, String status, Long id) {
        this.clientDao.update(name, status, id);
    }

    @Override
    public void delete(Long id) {
        this.clientDao.delete(id);
    }

    @Override
    public List<Client> findAll() {
        return this.clientDao.findAll();
    }
}
