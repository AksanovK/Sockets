import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SocketServer {

    private ArrayList<SocketClient> clients = new ArrayList<>();

    public void start(int port) {
        ServerSocket server;
        try {
            server = new ServerSocket(port);
            while (true) {
                Socket client = server.accept();
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
                SocketClient socketClient = new SocketClient(client, fromClient);
                clients.add(socketClient);
                new Thread(() -> {
                    while (true) {
                        try {
                            String messageFromClient = socketClient.getToServerS().readLine();
                            if (messageFromClient != null) {
                                sendMessageToAllClients(messageFromClient);
                            }
                        } catch (IOException e) {
                            throw new IllegalStateException(e);
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }



    public void sendMessageToAllClients(String message) {
        for (SocketClient client : clients) {
            client.sendMessage(message);
        }
    }
    public void removeClient(SocketClient client) {
        clients.remove(client);
    }
}
