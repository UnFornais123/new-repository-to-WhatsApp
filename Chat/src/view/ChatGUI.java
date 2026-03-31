package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChatGUI extends JFrame{
    UsersPanel usersPanel;
    ChatUserPanel chatUserPanel;
    
    public ChatGUI(){
        configFrame();
        initComponents();
    }
    
    public void configFrame(){
        setTitle("MessageApp");
        setIconImage(new ImageIcon(getClass().getResource("/resources/musica.png")).getImage());
        setExtendedState(MAXIMIZED_BOTH);        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());                
    }
    
    public void initComponents(){
         usersPanel = new UsersPanel();
         chatUserPanel = new ChatUserPanel();
         
         add(usersPanel, BorderLayout.WEST);
         add(chatUserPanel, BorderLayout.CENTER);
    }
    
    
}
