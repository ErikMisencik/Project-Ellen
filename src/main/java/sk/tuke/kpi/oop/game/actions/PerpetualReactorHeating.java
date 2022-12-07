package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Reactor;

public class PerpetualReactorHeating extends AbstractAction<Reactor> {

    private int action;

    public PerpetualReactorHeating(int action){

        this.action = action;

    }

    @Override
    public void execute(float deltaTime){

        if(getActor() != null) {
            getActor().increaseTemperature(action);
        }
        //ziskanie referencie na aktéra(REACTOR) s ktorym sa akcia vykonava
        //zvýšte jeho teplotu o hodnotu danú parametrom konštruktora akcie

    }

}
