package src.main.java.view;

/**
 * Created by Alicja on 2017-06-12.
 */
        import java.awt.Color;
        import java.awt.GridLayout;

        import javax.swing.BorderFactory;
        import javax.swing.JLabel;
        import javax.swing.JPanel;


public class LevelView extends JPanel {

    private JPanel panel;

    public LevelView(int level) {
        setLayout(new GridLayout(1, 0, 0, 0));

        JLabel levelLbl = new JLabel("Piętro " + level);
        add(levelLbl);

        panel = new JPanel();
        panel.setSize(20, 20);
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        add(panel);
        setSize(50, 20);

    }

    public void setDoorOpen(){
        panel.setBackground(Color.GREEN);
    }

    public void setOnLevel(boolean onLevel){
        panel.setBackground(onLevel ? Color.ORANGE :Color.WHITE);
    }


}
