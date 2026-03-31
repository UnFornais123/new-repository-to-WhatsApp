package model;

public class StartChat {
    SendMessage sendMessage;
    ReadMessage readMessage;
    
    public StartChat(SendMessage sendMessage, ReadMessage readMessage){
        this.sendMessage = sendMessage;
        this.readMessage = readMessage;
        iniciarServidor();
    }
    
    public void iniciarServidor(){
        try {
            
            sendMessage.start();
            readMessage.start();
            
        } catch (Exception e) {
            System.out.println(e.getMessage() + ": Error al iniciar el chat");
        }
    }
}
