package com.neusoft.chatroom.client.vo;
import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;

public class Aloha extends JPanel {
    private static void createAndShowGui() throws MalformedURLException {
        ImageIcon icon = new ImageIcon(new URL("http://www.cppblog.com/images/cppblog_com/biao/Button.png"));
        JLabel label = new JLabel(icon);

        JFrame frame = new JFrame();
        frame.getContentPane().add(label);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGui();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
