package src.main.java.control;

/**
 * Created by Alicja on 2017-06-12.
 */
import src.main.java.model.Elevator;
import src.main.java.view.ControlPanelView;
import src.main.java.view.ElevatorView;

import java.util.List;

public class Controller {

    private Elevator elevator;
    private ElevatorView elovatorView;
    private ControlPanelView controlPanel;
    private boolean running;

    public Controller(Elevator elevator, ElevatorView elovatorView,
                      ControlPanelView controlPanel) {
        super();
        this.elevator = elevator;
        this.elovatorView = elovatorView;
        this.controlPanel = controlPanel;
        this.controlPanel.setControl(this);
        this.elevator.addObserver(elovatorView);
        this.elevator.addObserver(controlPanel);
        this.elevator.addLevel(6);
        this.elevator.setCurrentLevel(0);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    public void push(int lvl) {
        elevator.addPushedLevel(lvl);
        if (!running) {
            new Thread(handlePush).start();
        }
    }

    Runnable handlePush = new Runnable() {

        @Override
        public void run(){
            running = true;
            List<Integer> pushedLevel = elevator.getPushedLevel();
            while (true) {
                int next;
                try {
                    next = pushedLevel.get(0);
                } catch (IndexOutOfBoundsException e1) {
                    break;
                }
                int currentLevel = elevator.getCurrentLevel();
                while (next != currentLevel) {
                    if (elevator.getPushedLevel().contains(currentLevel)) {
                        openAndCloseDoor(currentLevel);
                    }
                    if (next < currentLevel) {
                        currentLevel--;
                    } else {
                        currentLevel++;
                    }
                    elevator.setCurrentLevel(currentLevel);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
                openAndCloseDoor(currentLevel);
            }

            running = false;
        }
    };

    public void openAndCloseDoor(int currentLevel){
        elevator.setDoorOpen(true);
        elevator.removePushedLevel(currentLevel);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            return;
        }
        elevator.setDoorOpen(false);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            return;
        }
    }

}
