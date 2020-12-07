import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {
    private Socket client;
    private BufferedReader toServerS;

    public BufferedReader getToServerS() {
        return toServerS;
    }

    private PrintWriter toServer;
    private BufferedReader fromServer;

    public SocketClient(String host, int port) {
        try {
            client = new Socket(host, port);
            toServer = new PrintWriter(client.getOutputStream(), true);
            fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
            new Thread(receiverMessagesTask).start();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public SocketClient(Socket socket, BufferedReader reader) {
        toServerS = reader;
        try {
            client = socket;
            toServer = new PrintWriter(client.getOutputStream());
            fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
            new Thread(receiverMessagesTask).start();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void sendMessage(String message) {
        toServer.println(message);
        toServer.flush();
    }

    private Runnable receiverMessagesTask = () -> {
        while (true) {
            try {
                String messageFromServer = fromServer.readLine();
                if (messageFromServer != null) {
                    System.out.println(messageFromServer);
                }
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    };

}
