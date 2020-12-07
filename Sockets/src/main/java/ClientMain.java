import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) {
       SocketClient client = new SocketClient("localhost", 7777);
        Scanner scanner = new Scanner(System.in);
       while (true) {
            String message = scanner.nextLine();
            client.sendMessage(message);
        }
    }

}
