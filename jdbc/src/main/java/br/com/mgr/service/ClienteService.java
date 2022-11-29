package br.com.mgr.service;

import br.com.mgr.domain.client.entity.Client;

import java.util.List;

public interface ClienteService {

    Client save(Client client);

    void update(String name, String status, Long id);

    void delete(Long id);

    List<Client> findAll();

}
