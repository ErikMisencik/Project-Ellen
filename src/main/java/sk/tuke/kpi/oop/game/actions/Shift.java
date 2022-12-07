package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;

public class Shift <A extends Keeper> extends AbstractAction<A> {

    private int oop=0; //asd

    public Shift(){

    }

    @Override
    public void execute(float deltaTime) {

        if (getActor()==null || isDone() || getActor().getBackpack().peek()==null) {
            setDone(true);
            if(oop==0){    //asd
                oop++;
            }
            return;
        }

        if (isDone()==false) {
            getActor().getBackpack().shift();
            setDone(true);
        }
    }
}
