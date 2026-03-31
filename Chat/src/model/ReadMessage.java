package model;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Scanner;

public class ReadMessage extends Thread {
    private Scanner sc = new Scanner(System.in);
    private DataInputStream in;
    private String msg;
    
    public ReadMessage(DataInputStream in){
        this.in = in;
    }
    
    @Override
    public void run(){
        try {
            msg = in.readUTF();
            while(true){
                System.out.println(msg);
                msg = in.readUTF();                
            }
        } catch (IOException e) {
            System.out.println(e.getMessage() + ": Cliente se ha desconectado:");
        }
    }
}
