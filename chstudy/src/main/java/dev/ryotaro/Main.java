package dev.ryotaro;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.ClientIntelligence;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.jboss.logging.Logger;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        // ./bin/cli.sh user create admin -p secret
        var builder = new ConfigurationBuilder();
//        builder.addServer()
//                .host("127.0.0.1")
//                .port(11222)
//                .host("127.0.0.1").port(11322);

        builder.addServer()
                .host("127.0.0.1")
                .port(11222)
                .addServer()
                .host("127.0.0.1")
                .port(11322)
                .addServer()
                .host("127.0.0.1")
                .port(11422)
                .security()
                .authentication()
                .saslMechanism("SCRAM-SHA-512")
                .username("admin")
                .password("secret");

        // builder.uri("hotrod://admin:secret@localhost:11222");
        //builder.uri("hotrod://admin:secret@localhost:11222");
        // https://infinispan.org/docs/stable/titles/hotrod_java/hotrod_java.html#configuring-hotrod-java-clients_hotrod-client-configuration
        builder.clientIntelligence(ClientIntelligence.BASIC);
        try(RemoteCacheManager remoteCacheManager = new RemoteCacheManager(builder.build())) {
            LOGGER.infof("remoteCacheManager: %s", remoteCacheManager);
        var c = remoteCacheManager.getCache("myCache");
         c.put("foo", "bar");
         LOGGER.infof("a: %s", c.put("neko", "nyan"));
        }
    }
}