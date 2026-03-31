package app;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import model.ClientHandler;

public class Server {
    private static final int PORT = 2222;
    //private static final ArrayList<ClientHandler> clients = new ArrayList<>();        
    public static final Map<String, ClientHandler> clients = new HashMap<>(); //Key = nameClient - Value = ClientHandler(objeto)
    
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor iniciado en el puerto " + PORT + ". Esperando clientes...");
            
            while(true){ //Bucle infinito para que siempre este aceptando clientes
                Socket socket = serverSocket.accept();
                System.out.println("Nuevo cliente conectado: " + socket.getInetAddress());
                
                ClientHandler clientHandler = new ClientHandler(socket);
                //clients.add(clientHandler);
                clientHandler.start(); //Hilo en la clase ClientHandler
            }
            
        }catch(IOException e){
            System.out.println(e);
        }
    }        
    
    //Envia mensaje a todos los clientes excepto al que lo envio (sender)
    public static synchronized void broadcast(String message, ClientHandler sender) {
        for (ClientHandler client : clients.values()) {
            if (client != sender) {  // No reenviar al que lo envió
                client.sendMessage(message);
            }
        }
    }
    
    //Remover cliente cuando se desconecta
    public static synchronized void removeClient(ClientHandler client) {
        if(client.getNameClient() != null){
            clients.remove(client.getNameClient());
            System.out.println("Cliente desconectado: " + client.getNameClient()); //Este se muestra en Server
            broadcast(">>> " + client.getNameClient() + " ha abandonado el chat", null); //Este se muestra a todos los clientes
        }        
    }
    
    //Registrar un cliente después de que escriba su nombre
    public static synchronized void addClient(String name, ClientHandler client) {
        clients.put(name, client);
        System.out.println("Usuario conectado: " + name);
        broadcast(">>> " + name + " se ha unido al chat", null);
    }
    
    //Enviar mensaje privado a un usuario específico
    public static synchronized void sendPrivateMessage(String message, String targetName, ClientHandler sender) {
        ClientHandler target = clients.get(targetName);
        
        if(targetName.equals(sender.getNameClient())) {
            sender.sendMessage("Error: No puedes autoenviarte mensajes");
            return;
        }
        if (target != null) {
            String fullMsg = "[Privado de " + sender.getNameClient() + "]: " + message;
            target.sendMessage(fullMsg);
                        
            sender.sendMessage(">>> Mensaje privado enviado a " + targetName); //Mensaje de aviso de enviado
        } else {
            sender.sendMessage(">>> Error: Usuario '" + targetName + "' no encontrado.");
        }
    }
    
    //Obtener lista de usuarios conectados
    public static synchronized String getUsersList() {
        if (clients.isEmpty()) {
            return "No hay usuarios conectados.";
        }

        //StringBuilder para tener toda la lista en un solo string
        StringBuilder sb = new StringBuilder("Usuarios conectados (" + clients.size() + "):\n");
        for (String name : clients.keySet()) {
            sb.append(" - ").append(name).append("\n");
        }
        return sb.toString();
    }
        
    /*
    public static synchronized void showNameClient(String nameClient){
        System.out.println("Usuario conectado : " + nameClient);
    }
    */
}
