package Client;

import java.io.DataInputStream;
import java.io.IOException;

public class ReadFromServer implements Runnable {
    private final DataInputStream inStream;

    public ReadFromServer(DataInputStream inStream){
        this.inStream = inStream;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println(inStream.readUTF());
            }
        } catch (IOException ex) {
            System.out.println("IOException from ReadFromServer run(),");
        }
    }
}
