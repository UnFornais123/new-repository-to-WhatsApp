package app;

import javax.swing.SwingUtilities;
import view.ChatGUI;

public class Main {        
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChatGUI view = new ChatGUI();
            
            view.setVisible(true);
        });
    }
}
