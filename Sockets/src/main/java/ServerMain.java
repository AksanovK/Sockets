public class ServerMain {
    public static void main(String[] args) {
        SocketServer server = new SocketServer();
        server.start(7777);
    }
}
