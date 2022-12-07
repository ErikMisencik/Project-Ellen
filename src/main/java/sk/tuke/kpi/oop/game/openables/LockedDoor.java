package sk.tuke.kpi.oop.game.openables;

import sk.tuke.kpi.gamelib.Actor;

public class LockedDoor extends Door{

    private boolean isLockedDoor;

    public LockedDoor(){

        super(Orientation.VERTICAL);

        isLockedDoor = true;

    }

    @Override
    public void useWith(Actor actor) {
        if (!isLocked())
            super.useWith(actor);
    }

    public void lock(){
        this.close();
        isLockedDoor =true;

    }

    public void unlock(){
        this.open();
        isLockedDoor = false;
    }

    boolean isLocked(){
        return isLockedDoor;}
}
