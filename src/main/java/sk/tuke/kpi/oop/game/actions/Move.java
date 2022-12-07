package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.actions.Action;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;


public class Move <A extends Movable> implements Action<A> {

    private A actor;
    private float duration;
    private boolean done;
    private int firstTimeCalling;
    private Direction direction;
    private int nul=0;

    public Move(Direction direction, float duration) {
        this.direction = direction;
        this.duration = duration;
        firstTimeCalling = nul;
        done = false;
    }

    private Move(Direction direction) {
        this.direction = direction;
        firstTimeCalling = nul;
        done = false;

    }

    @Override
    @Nullable
    public A getActor() {
        return actor;
    }

    @Override
    public void setActor(@Nullable A movable) {
        this.actor = movable;
    }

    @Override
    public boolean isDone() {
        return done;
    }

    @Override
    public void execute(float deltaTime) {

        if (actor == null) {
            return;
        }
            duration = duration - deltaTime;
            if (!isDone()) {
                if (firstTimeCalling == nul) {
                    firstTimeCalling = firstTimeCalling + 1;
                    actor.startedMoving(direction);
                }
                if (duration > nul) {
                    firstTimeCalling = firstTimeCalling + 1; //asd
                    actor.setPosition(actor.getPosX() + (direction.getDx() * actor.getSpeed()), actor.getPosY() + (direction.getDy() * actor.getSpeed()));
                    if (getActor().getScene().getMap().intersectsWithWall(actor)) {
                        actor.setPosition(actor.getPosX() - (direction.getDx() * actor.getSpeed()), actor.getPosY() - (direction.getDy() * actor.getSpeed()));
                        actor.collidedWithWall();
                    }
                }
                else {
                    stop();
                }
            }
    }

    public void stop() {
        if(actor!=null) {
            actor.stoppedMoving();
            done = true;
        }
    }

    @Override
    public void reset() {
        actor.stoppedMoving();
        duration = nul;
        done = false;
        firstTimeCalling = nul;
    }
}
