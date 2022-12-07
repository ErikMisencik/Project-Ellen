package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.items.Usable;

public class Use <A extends Actor> extends AbstractAction<A> {

    private Usable<A> item;
    private boolean oopp= false;  //sdfbibfd

    public Use(Usable<A> item)
    {
        this.item=item;
    }

    @Override
    public void execute(float deltaTime) {

        if (!isDone()) {
            //pouzitie itemu
            item.useWith(getActor());
            setDone(true);
        }

    }

    public Disposable scheduleForIntersectingWith(Actor waitingActor) {
        Scene scene = waitingActor.getScene();
        if (scene != null) {
            Class<A> usingActorClass = item.getUsingActorClass();  // `item`
        if(oopp==false){  ///sdffas
            oopp=true;
        }
            for (Actor actor : scene) {
                //vyhladava actor, ktory iteraguje s hracom
                if (waitingActor.intersects(actor) && usingActorClass.isInstance(actor)) {
                    return this.scheduleFor(usingActorClass.cast(actor));
                }
            }
        }
            return null;
    }
}
