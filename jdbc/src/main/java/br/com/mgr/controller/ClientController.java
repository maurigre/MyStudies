package br.com.mgr.controller;

import br.com.mgr.domain.client.entity.Client;
import br.com.mgr.factory.ConnectionFactory;
import br.com.mgr.service.ClientServiceImp;
import br.com.mgr.service.ClienteService;

import java.sql.Connection;
import java.util.List;


public class ClientController {

    private final ClienteService clienteService;

    public ClientController() {
        final Connection connection = new ConnectionFactory().getConnection();
        this.clienteService = new ClientServiceImp(connection);
    }

    public void delete(Long id) {
        this.clienteService.delete(id);
    }

    public Client save(Client client) {
        return this.clienteService.save(client);
    }

    public List<Client> findAll() {
        return this.clienteService.findAll();
    }

    public void update(String name, String status, Long id) {
        this.clienteService.update(name,status, id);
    }
}
