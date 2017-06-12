package src.main.java.model;

/**
 * Created by Alicja on 2017-06-12.
 */
        import java.util.ArrayList;
        import java.util.Collections;
        import java.util.List;
        import java.util.Observable;

        import com.sun.javafx.collections.UnmodifiableListSet;


public class Elevator extends Observable{

    private int currentLevel;
    private boolean doorOpen;
    private List<Integer> level;
    private List<Integer> pushedLevel;

    public Elevator(){
        currentLevel = -1;
        level = new ArrayList<>();
        pushedLevel = new ArrayList<>();
    }
    public synchronized void addLevel() {
        _addLevel();
        changed("level changed");
    }

    public synchronized void addLevel(int count) {
        for (int i = 0; i < count; i++) {
            _addLevel();
        }
        changed("level changed");
    }

    private void _addLevel() {
        if(level.size() == 0){
            level.add(0);
        } else {
            int lastLevel = level.get(level.size() - 1);
            level.add(lastLevel + 1);
        }
    }

    public synchronized void addPushedLevel(int objectToAdd){
        pushedLevel.add(objectToAdd);
        changed("pushed level added");
    }

    public void changed(String msg){
        setChanged();
        notifyObservers(msg);
    }

    public synchronized int getCurrentLevel() {
        return currentLevel;
    }

    public synchronized List<Integer> getLevel() {
        return Collections.unmodifiableList(level);
    }

    public synchronized List<Integer> getPushedLevel() {
        return Collections.unmodifiableList(pushedLevel);
    }

    public synchronized boolean isDoorOpen() {
        return doorOpen;
    }

    public synchronized void removePushedLevel(int objectToRemove){
        pushedLevel.remove((Object) objectToRemove);
        changed("pushed level removed");
    }

    public synchronized void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
        changed("current level changed");
    }

    public synchronized void setDoorOpen(boolean doorOpen) {
        this.doorOpen = doorOpen;
        changed("open door changed");
    }


}

