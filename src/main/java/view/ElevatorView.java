package src.main.java.view;

/**
 * Created by Alicja on 2017-06-12.
 */
        import src.main.java.model.Elevator;
        import javax.swing.JFrame;
        import javax.swing.JPanel;
        import javax.swing.border.EmptyBorder;

        import java.awt.GridLayout;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Observable;
        import java.util.Observer;

public class ElevatorView extends JFrame implements Observer {

    private JPanel contentPane;

    private int activeLevel;
    private List<LevelView> levelViews;

    public ElevatorView() {
        setTitle("Elevator");
        levelViews = new ArrayList<>();
        activeLevel = -1;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(10, 10, 400, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(0, 1, 0, 0));
    }

    @Override
    public void update(Observable o, Object arg) {
        Elevator elevator;
        String msg = null;
        System.out.println("ElevatorView" + arg);
        if (o instanceof Elevator) {
            elevator = (Elevator) o;
        } else {
            return;
        }
        if (arg instanceof String) {
            msg = (String) arg;
        }

        switch (msg) {
            case "current level changed":

                if(activeLevel != -1 && levelViews.size() > activeLevel){
                    levelViews.get(activeLevel).setOnLevel(false);
                }
                activeLevel = elevator.getCurrentLevel();
                if(activeLevel != -1 && levelViews.size() > activeLevel){
                    levelViews.get(activeLevel).setOnLevel(true);
                }
                break;
            case "level changed":
                levelViews.clear();
                List<Integer> level = elevator.getLevel();
                setSize(210, level.size() * 100 + 50);
                getContentPane().removeAll();
                for (int i = level.size() - 1; i >= 0; i--) {
                    LevelView levelView = new LevelView(i);
                    levelViews.add(0,levelView);
                    getContentPane().add(levelView);
                }
                if(activeLevel != -1 && levelViews.size() > activeLevel){
                    levelViews.get(activeLevel).setOnLevel(true);
                }
                invalidate();
                repaint();
                setVisible(true);

                break;
            case "open door changed":
                if(elevator.isDoorOpen()){
                    levelViews.get(activeLevel).setDoorOpen();
                } else {
                    levelViews.get(activeLevel).setOnLevel(true);
                }
                break;
            default:

                break;
        }
    }

}

