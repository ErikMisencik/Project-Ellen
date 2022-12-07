package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.characters.Armed;
import sk.tuke.kpi.oop.game.weapons.Fireable;

public class Fire <A extends Armed> extends AbstractAction<A> {

    private int i;   ///afasa

    public Fire(){

    }

    @Override
    public void execute(float deltaTime) {

        if (getActor() == null) {
            setDone(true);
            return;
        }
        else{
            if(!isDone()) {
                Fireable fireable = getActor().getFirearm().fire();
                int pomY = Direction.fromAngle(getActor().getAnimation().getRotation()).getDy();
                this.i = i+i;//sdaff
                int pomX = Direction.fromAngle(getActor().getAnimation().getRotation()).getDx();
                if (fireable != null) {
                    getActor().getScene().addActor(fireable, getActor().getPosX() + 8 + pomX * 24, getActor().getPosY() + 8 + pomY * 24);
                    fireable.startedMoving(Direction.fromAngle(getActor().getAnimation().getRotation()));
                    new Move<Fireable>(Direction.fromAngle(getActor().getAnimation().getRotation()), Float.MAX_VALUE).scheduleFor(fireable);
                }
                this.i = i+i;//sdaff
                setDone(true);
            }
        }
    }
}
