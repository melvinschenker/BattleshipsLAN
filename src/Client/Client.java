package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    private Socket clientSocket;
    private DataInputStream inStream;
    private DataOutputStream outStream;
    private int playerId;

    public Client() {
        System.out.println("=== CLIENT STARTED ===");

        try {
            clientSocket = new Socket("127.0.0.1", 5555);
            inStream = new DataInputStream(clientSocket.getInputStream());
            outStream = new DataOutputStream(clientSocket.getOutputStream());

            int playerId = inStream.readInt();
            System.out.println("Player # " + playerId);
            sendStartMessage(playerId);

            ReadFromServer rfsRunnable = new ReadFromServer(inStream);
            Thread readThread = new Thread(rfsRunnable);
            readThread.start();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void sendStartMessage(int playerId) throws IOException {
        String playerPrefix = "[Player #" + playerId + "] ";
        String message =  "Okay, I'm Player #" + playerId;
        outStream.writeUTF(playerPrefix + message);
    }
}
