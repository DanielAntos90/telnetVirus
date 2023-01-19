import telnet.Telnet;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    static Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        if (args.length < 2) {
            logger.log(Level.INFO, "Program arguments not defined: Hostname and port number");
            System.exit(-1);
        }
        String host = args[0];
        int port = Integer.parseInt(args[1]);

        logger.log(Level.INFO, "Start new executor");

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(500);

        for (int i = 1; i <= 500; i++) {
            Telnet v = new Telnet(host, port);
            executor.execute(v);
        }

        shutdown(executor);
        logger.log(Level.INFO, "Done!");
    }

    static void shutdown(ExecutorService pool) {
        pool.shutdown();

        try {
            pool.shutdown();
        } catch (Exception ex) {
            logger.log(Level.WARNING,"Pool did not terminate");
            Thread.currentThread().interrupt();
        }
    }
}


