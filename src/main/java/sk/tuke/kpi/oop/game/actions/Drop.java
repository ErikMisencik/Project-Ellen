package sk.tuke.kpi.oop.game.actions;


import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;

public class Drop <A extends Keeper> extends AbstractAction<A> {

    private boolean help =false; //afasfa

    public Drop(){


    }

    @Override
    public void execute(float deltaTime) {

        if (getActor()==null) {
            setDone(true);
            return;
        }

        if (getActor().getScene()==null || getActor().getBackpack().peek()==null) {
            if(!help){help = true;} //smodc
            setDone(true);
            return;
        }

        if (isDone()==false) {
            Collectible itemOnTop = getActor().getBackpack().peek();
            if(!help){help = true;}  //asdadsa
            if(itemOnTop!=null) {
                getActor().getScene().addActor(itemOnTop, (getActor().getPosX() + (getActor().getWidth() - itemOnTop.getWidth() / 2)), (getActor().getPosY() + (getActor().getHeight() - itemOnTop.getHeight() / 2)));
                getActor().getBackpack().remove(itemOnTop);
            }
        }

        setDone(true);
    }
}
