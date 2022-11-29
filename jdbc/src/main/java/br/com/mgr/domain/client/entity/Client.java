package br.com.mgr.domain.client.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Client {

    @Setter
    private Long id;
    private String name;
    private String status;

    public Client() {
    }

    public Client(String name, String status) {
        this.name = name;
        this.status = status;
    }
}
