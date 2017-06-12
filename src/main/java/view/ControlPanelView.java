package src.main.java.view;

/**
 * Created by Alicja on 2017-06-12.
 */
        import src.main.java.control.Controller;
        import src.main.java.model.Elevator;

        import java.awt.Button;
        import java.awt.Color;
        import java.awt.GridLayout;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Observable;
        import java.util.Observer;

        import javax.swing.JFrame;
        import javax.swing.JPanel;
        import javax.swing.UIManager;
        import javax.swing.border.EmptyBorder;



public class ControlPanelView extends JFrame implements Observer, ActionListener{

    private JPanel contentPane;
    private List<Button> buttons;
    private Controller control;
    private Color defaultBg = UIManager.getColor ( "Panel.background" );

    public ControlPanelView() {
        buttons = new ArrayList<Button>();
        setTitle("ControlPanel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 300, 400, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(0, 1, 0, 0));
    }

    public void setControl(Controller control) {
        this.control = control;
    }

    @Override
    public void update(Observable o, Object arg) {
        Elevator elevator;
        String msg = null;
        System.out.println("ControlPanelView" + arg);
        if (o instanceof Elevator) {
            elevator = (Elevator) o;
        } else {
            return;
        }
        if (arg instanceof String) {
            msg = (String) arg;
        }

        switch (msg) {

            case "level changed":
                buttons.clear();
                List<Integer> level = elevator.getLevel();
                setSize(210, level.size() * 50 + 50);
                getContentPane().removeAll();
                for (int i = level.size() - 1; i >= 0; i--) {
                    Button b = new Button(String.valueOf(i));
                    b.addActionListener(this);
                    buttons.add(0, b);
                    getContentPane().add(b);
                }
                invalidate();
                repaint();
                setVisible(true);
                break;
            case "pushed level removed":
                for (int i = 0; i < buttons.size(); i++) {
                    if(elevator.getPushedLevel().contains(i)){
                        buttons.get(i).setBackground(Color.GREEN);
                    } else {
                        buttons.get(i).setBackground(defaultBg);
                    }
                }
                break;
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Button b = (Button) e.getSource();
        b.setBackground(Color.GREEN);
        int lvl = Integer.parseInt(b.getLabel());
        control.push(lvl);

    }

}

