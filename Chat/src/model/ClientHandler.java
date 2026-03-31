package model;

import app.Server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends Thread{
    private final Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String clientName;
    
    public ClientHandler(Socket socket) {
        this.socket = socket;
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            System.out.println("Error al crear streams");
        }
    }
    
    @Override
    public void run() {
        try {          
            // Pedir nombre al cliente al conectar
            out.writeUTF("Ingrese su nombre:");
            clientName = in.readUTF().trim();
            
            // Verificar que el nombre no esté en uso
            if (Server.clients.containsKey(clientName)) {
                out.writeUTF(">>> Nombre ya en uso. Conexión rechazada.");
                closeConnection();
                return;
            }

            Server.addClient(clientName, this);  // Guardar cliente en el HashMap
            
            out.writeUTF("Ya puedes chatear.");
            out.writeUTF("Escribe '/exit' para salir del chat.");
            out.writeUTF("Para mensaje privado usa: @nombre mensaje");            
            out.writeUTF(Server.getUsersList()); //Muestra la lista de usuarios conectados
            
            while (true) {//Bucle infinito para leer mensajes y enviarlos
                String message = in.readUTF();
                
                if (message.equalsIgnoreCase("/exit")) {
                    break; 
                }

                System.out.println("Mensaje recibido de " + clientName + ": " + message); //Avisa al servidor del mensaje enviado de algun cliente               
                //Server.broadcast(message, this);  // Reenviar a los demás (todos los clientes conectados)                
                if (message.startsWith("@")) {//Envia mensaje a un cliente especifico      
                    int spaceIndex = message.indexOf(" "); //Indice donde empieza el espacio en el mensaje privado                    
                    if (spaceIndex != -1) {
                        String targetName = message.substring(1, spaceIndex).trim(); //Cliente destinatario
                        String privateMsg = message.substring(spaceIndex + 1).trim(); //Texto del mensaje
                        
                        Server.sendPrivateMessage(privateMsg, targetName, this); 
                    } else {
                        sendMessage(">>> Formato incorrecto. Usa: @nombre mensaje");
                    }
                } else if(message.equalsIgnoreCase("/clients")){ //Muestra la lista de clientes conectados
                    out.writeUTF(Server.getUsersList());
                } else { //Envia mensaje a todos los clientes
                    String publicMsg = "[" + clientName + "]: " + message;
                    Server.broadcast(publicMsg, this);
                }
            }
        } catch (IOException e) {
            //System.out.println("Cliente desconectado - " + e.getMessage());
            System.out.println("Error: " + e.getMessage());
        } finally {
            closeConnection(); //Cuando un usuario escribe '/exit' termina el bucle while y se ejecuta el finally
        }
    }

    //Metodo para enviar mensaje a este cliente
    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
            out.flush();
        } catch (IOException e) {
            System.out.println("Error al enviar mensaje: " + e.getMessage());
        }
    }

    //Metodo para cerrar la conexion del cliente
    private void closeConnection() {
        try {
            Server.removeClient(this);
            if (in != null) in.close();
            if (out != null) out.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            System.out.println("Error al cerrar conexión: " + e.getMessage());
        }
    }
        
    public String getNameClient(){
        return clientName;
    }
}
