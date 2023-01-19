package telnet;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Telnet extends Thread {
    private String host = "";
    private int port = -1;
    static AtomicInteger counter = new AtomicInteger();
    static Logger logger = Logger.getLogger(Telnet.class.getName());
    public Telnet(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() {
        int id = counter.incrementAndGet();

        try {
            Socket socket = new Socket(host, port);
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();

            output.write("GET / HTTP/1.0\r\n\r\n".getBytes());
            output.flush();
            while (input.read(new byte[2000]) != -1) {
            }
            socket.close();
        } catch (IOException e) {
            logger.log(Level.INFO, String.format("%d", id));
        }
    }
}