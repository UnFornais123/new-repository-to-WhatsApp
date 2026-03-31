package view;

import java.awt.Color;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ChatUserPanel extends JPanel{
    JLabel lblMsgUser1, lblMsgUser2;
    
    public ChatUserPanel() {
        configPanel();
        initComponents();
    }
    
    public void configPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(200,200,200));
    }
    
    public void initComponents() {
        lblMsgUser1 = new JLabel("Mensaje de prueba 1");
        lblMsgUser1.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        lblMsgUser1.setBackground(Color.red);
        lblMsgUser1.setOpaque(true);
        lblMsgUser2 = new JLabel("Mensaje prueba 2");
        lblMsgUser2.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
        
        add(Box.createVerticalStrut(60));
        add(lblMsgUser1);
        add(Box.createVerticalStrut(40));
        add(lblMsgUser2);
    }
    
    
}
