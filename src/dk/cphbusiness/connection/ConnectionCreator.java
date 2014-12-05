package dk.cphbusiness.connection;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mads Heckmann
 */
public class ConnectionCreator {

    private final String USERNAME = "nicklas";
    private final String PASSWORD = "cph";
    private final String HOSTNAME = "datdb.cphbusiness.dk";
    
    private static ConnectionCreator instance = null;

    private ConnectionFactory factory;
    private Connection connection;
    
    private ConnectionCreator() {
        factory = new ConnectionFactory();
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
        factory.setHost(HOSTNAME);
        try {
            connection = factory.newConnection();
        } catch (IOException ex) {
            Logger.getLogger(ConnectionCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ConnectionCreator getInstance() {
        if (instance == null) {
            instance = new ConnectionCreator();
        }
        return instance;
    }

    public Channel createChannel() throws IOException {
        return connection.createChannel();
    }
}
