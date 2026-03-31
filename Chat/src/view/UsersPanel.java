package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class UsersPanel extends JPanel{
    JButton user1;
    
    public UsersPanel(){
        configPanel();
        initComponents();
    }
    
    public void configPanel(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(120,120,120));
    }
    
    public void initComponents(){
        user1 = new JButton("User1");
        
        add(Box.createVerticalStrut(60));    
        add(user1);
        add(Box.createVerticalStrut(40));   
    }
        
    public JButton createButton(String nameUser){
        JButton btn = new JButton(nameUser);
        btn.setFont(new Font("Arial", Font.BOLD, 15));
        btn.setForeground(Color.WHITE);
        //b.setIcon(new ImageIcon(getClass().getResource(rutaIcono)));
        
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        
        return btn;
    }
    
}
