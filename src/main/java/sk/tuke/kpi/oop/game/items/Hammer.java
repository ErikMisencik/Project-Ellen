package sk.tuke.kpi.oop.game.items;


import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Repairable;


public class Hammer extends BreakableTool <Repairable> implements Collectible{

    private int remainingUses;

    public Hammer() {
        super(1);
        Animation hammerAnimation = (new Animation("sprites/hammer.png"));
        setAnimation(hammerAnimation);
    }

    @Override
    public void useWith(Repairable actor) {

        if (actor != null) {
            if (actor.repair()) {
                super.useWith(actor);
            }
            //zbytocne else
            else {
                remainingUses = remainingUses + remainingUses;
            }
        }
    }

    @Override
    public Class<Repairable> getUsingActorClass() {
        return Repairable.class;
    }
}


