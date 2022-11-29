package br.com.mgr;

import br.com.mgr.constants.StatusEnum;
import br.com.mgr.controller.ClientController;
import br.com.mgr.domain.client.entity.Client;

import java.sql.SQLException;
import java.util.List;

import static java.lang.System.out;

public class Main {

    public static void main(String[] args) throws SQLException {

        final ClientController clientController = new ClientController();
        final Client save = clientController.save( new Client("João Silva", StatusEnum.ACTIVE.name()));
        out.println("Client save: " + save);

        final Client save2 = clientController.save( new Client("Maria Silva", StatusEnum.ACTIVE.name()));
        out.println("Client save: " + save2);

        final List<Client> clients = clientController.findAll();
        clients.forEach(out::println);

    }
}