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

public class Servidor {
    private static final int PORT = 2222;    
    private static SendMessage sendMessage;
    private static ReadMessage readMessage;
        
    public static void main(String[] args) {  
        try {            
            System.out.println("Servidor iniciado. Esperando cliente..");
            ServerSocket server = new ServerSocket(PORT);
            Socket socket = server.accept();
            System.out.println("Cliente conectado");

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            sendMessage = new SendMessage(out);
            readMessage = new ReadMessage(in);

            new StartChat(sendMessage, readMessage);
            
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
