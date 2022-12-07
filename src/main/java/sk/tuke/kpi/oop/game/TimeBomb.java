package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;


public class TimeBomb extends AbstractActor {

    private float time;
    private boolean activated=false;
    private Animation timeBombActivated;
    private Animation timeBombExploded;


    public TimeBomb(float time){

        this.time = time;
        Animation timeBombDefault = (new Animation("sprites/bomb.png"));
        this.timeBombActivated = (new Animation("sprites/bomb_activated.png", 16,16,this.time/7));
        this.timeBombExploded = (new Animation("sprites/small_explosion.png",32,32,0.1f,Animation.PlayMode.ONCE));
        setAnimation(timeBombDefault);
    }

    public void activate() {
        this.activated = true;
        setAnimation(this.timeBombActivated);

        new ActionSequence<>(
            new Wait<>(this.time),
            new Invoke<>(this::explosion)).scheduleFor(this);

    }

    public boolean isActivated(){
        return this.activated;
    }

    public void explosion(){
        //prepne sa animacia
        setAnimation(timeBombExploded);
        //prejde na vymazanie actora mm
        new When<>(
            () -> this.getAnimation().getCurrentFrameIndex() >= this.getAnimation().getFrameCount() -1,
            new Invoke<>(() -> this.getScene().removeActor(this))).scheduleFor(this);
    }

}
