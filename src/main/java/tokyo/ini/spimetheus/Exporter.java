package tokyo.ini.spimetheus;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Gauge;
import io.prometheus.client.exporter.HTTPServer;

import java.io.IOException;

public class Exporter {

    private static final CollectorRegistry REGISTRY = new CollectorRegistry();

    private static final Exporter INSTANCE = new Exporter();

    private static final String APP_PREFIX = "spigot";

    private static final Gauge numOfPlayers = new Gauge.Builder()
            .name(APP_PREFIX+"_num_of_players")
            .help("number of players")
            .register(REGISTRY);

    private static final Gauge tps = new Gauge.Builder()
            .name(APP_PREFIX+"_tps")
            .help("recent tps")
            .labelNames("min")
            .register(REGISTRY);

    private static final Gauge numOfEntities = new Gauge.Builder()
            .name(APP_PREFIX+"_num_of_entities")
            .help("number of entities")
            .register(REGISTRY);

    private static final Gauge loadedChunks = new Gauge.Builder()
            .name(APP_PREFIX+"_num_of_loaded_chunks")
            .help("number of loaded chunks")
            .register(REGISTRY);

    private Exporter(){
        final int PORT = 9200;

        try {
            final HTTPServer internalServer = new HTTPServer.Builder()
                    .withPort(PORT)
                    .withRegistry(REGISTRY)
                    .build();
            System.out.println("Internal Server is Online. PORT:" +PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Exporter getInstance(){
        return INSTANCE;
    }

    public static void incNumOfPlayers() {
        numOfPlayers.inc();
    }

    public static void decNumOfPlayers() {
        numOfPlayers.dec();
    }

    public static void setNumOfPlayers(int x) {
        numOfPlayers.set(x);
    }

    public static void setTps(double[] x){
        tps.labels("1m").set(x[0]);
        tps.labels("5m").set(x[1]);
        tps.labels("15m").set(x[2]);
    }

    public static void setNumOfEntities(int x) {
        numOfEntities.set(x);
    }

    public static void setLoadedChunks(int x) {
        loadedChunks.set(x);
    }
}
