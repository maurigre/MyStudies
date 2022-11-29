package br.com.mgr.factory;

import br.com.mgr.exceptions.MyRuntimeException;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {

    private final DataSource dataSource;

    public ConnectionFactory() {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl(System.getenv().get("DATABASE_URL"));
        comboPooledDataSource.setUser(System.getenv().get("DATABASE_USER"));
        comboPooledDataSource.setPassword(System.getenv().get("DATABASE_PASSWORD"));
        comboPooledDataSource.setMaxPoolSize(15);
        this.dataSource = comboPooledDataSource;
    }

    public Connection getConnection()  {
        try {
            return this.dataSource.getConnection();
        } catch (SQLException e) {
            throw new MyRuntimeException(e);
        }
    }
}
