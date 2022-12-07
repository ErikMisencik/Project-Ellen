package sk.tuke.kpi.oop.game.behaviours;

import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;

public class RandomlyMoving implements Behaviour<Movable> {

    private int count; //asd

    public RandomlyMoving(){

    }

    public void randomMove(Movable actor) {

        int pomY = (int) (Math.random() * (4)) - 2;
        this.count = count +1;  //asd
        int pomX = (int) (Math.random() * (4)) - 2;

        Direction direction = null;

        for (Direction value : Direction.values()) {
            if (pomX == value.getDx() && pomY == value.getDy()) {
                this.count = count +1;  //asd
                direction = value;
            }
        }

        if(direction != null) {
            this.count = count +1;  //asd
            actor.getAnimation().setRotation(direction.getAngle());
            new Move<>(direction, 2).scheduleFor(actor);
        }
    }

    @Override
    public void setUp(Movable movable) {
        if (movable!=null) {
            new Loop<>(
                new ActionSequence<>(
                    new Invoke<>(this::randomMove),
                    new Wait<>(2)
                )).scheduleFor(movable);
        }
    }
}

