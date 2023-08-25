import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TitleFrame extends JFrame {
    JPanel panel;
    int start;

    TitleFrame(){
        panel = new JPanel();
        start = 0;
        Container caixa = this.getContentPane();
        caixa.setLayout(new GridLayout(2, 2));
        JButton botao = new JButton("Start");
        JLabel label = new JLabel("A Snake Game, timeless classic.");
        JLabel label2 = new JLabel("Use wasd to move snake.");
        JLabel label3 = new JLabel("Click button or press SPACE to begin");
        panel.add(botao);
        panel.add(label);
        panel.add(label2);
        panel.add(label3);
        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                
                GameFrame frame = new GameFrame();
            }
        });

        this.add(panel);
        this.setTitle("Snake Game");
        this.setResizable(false);
        this.setBackground(Color.white);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(200, 200, 400, 300);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    //public void run() {
        
    //}
}