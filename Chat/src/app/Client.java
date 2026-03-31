package app;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.SwingUtilities;
import model.StartChat;
import model.ReadMessage;
import model.SendMessage;

public class Client {
    private static final int PORT = 2222;
    
    private static SendMessage sendMessage;
    private static ReadMessage readMessage;
        
    public static void main(String[] args) {              
        try {
            Socket socket = new Socket("192.168.1.67", PORT);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            sendMessage = new SendMessage(out);
            readMessage = new ReadMessage(in);

            new StartChat(sendMessage, readMessage);

            System.out.println("Conectado al servidor.");
            //System.out.println("Conectado al servidor. Puedes chatear. ");
            //System.out.println("Escribe '/exit' para salir del chat.");
        } catch (IOException e) {
            System.out.println("Error de conexion: " + e.getMessage());
        }        
        
    }
}
