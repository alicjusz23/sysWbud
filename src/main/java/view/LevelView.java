package src.main.java.view;

/**
 * Created by Alicja on 2017-06-12.
 */
        import java.awt.Color;
        import java.awt.GridLayout;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;

        import javax.swing.*;


public class LevelView extends JPanel {

    private JPanel panel;

    public LevelView(int level) {
        setLayout(new GridLayout(1, 0, 0, 0));

        JLabel levelLbl = new JLabel("Piętro " + level);
        add(levelLbl);

        panel = new JPanel();
        panel.setSize(100, 100);
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

        ClockLabel clock = new ClockLabel();

        panel.add(clock);
        add(panel);
        setSize(200, 100);

    }

    public void setDoorOpen(){
        panel.setBackground(Color.GREEN);
    }

    public void setOnLevel(boolean onLevel){
        panel.setBackground(onLevel ? Color.ORANGE :Color.WHITE);
    }


}

class ClockLabel extends JLabel implements ActionListener {
    long start = System.currentTimeMillis();
    long stop;

    public ClockLabel() {
        Timer t = new Timer(1000, this);
        t.start();
    }

    public void actionPerformed(ActionEvent e) {
        setText(String.valueOf(start-stop));
    }

    public void setDoorOpen(){
        stop = System.currentTimeMillis();
    }
}
