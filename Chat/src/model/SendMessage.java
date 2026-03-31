package model;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class SendMessage extends Thread{   
    private DataOutputStream out;
    private Scanner sc = new Scanner(System.in);
    private String msg;   
    
    public SendMessage(DataOutputStream out){
        this.out = out;
    }
        
    @Override
    public void run(){
        try {
            while(true){
                msg = sc.nextLine();
                out.writeUTF(msg);   
            }            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
