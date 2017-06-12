package src.main.java.main;

import src.main.java.control.Controller;
import src.main.java.model.Elevator;
import src.main.java.view.ControlPanelView;
import src.main.java.view.ElevatorView;

/**
 * Created by Alicja on 2017-06-12.
 */


public class Main {

    public static void main(String[] args) {
        Elevator e = new Elevator();
        ElevatorView v = new ElevatorView();
        ControlPanelView cp = new ControlPanelView();
        Controller c = new Controller(e, v, cp);
    }
}
