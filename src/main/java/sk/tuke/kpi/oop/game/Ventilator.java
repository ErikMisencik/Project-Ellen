package sk.tuke.kpi.oop.game;


import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;

public class Ventilator extends AbstractActor implements Repairable {

    public static final Topic<Ventilator> VENTILATOR_REPAIRED = Topic.create("ventilator repaired", Ventilator.class);
    private boolean brokenVent;

    public Ventilator(){

        brokenVent = true;
        Animation vent = new Animation("sprites/ventilator.png", 32, 32, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        setAnimation(vent);
        getAnimation().stop();
    }

    @Override
    public boolean repair() {
        if (brokenVent) {
            getAnimation().play();
            getScene().getMessageBus().publish(VENTILATOR_REPAIRED,this);
            brokenVent = false;
            return true;
        }
        else {
            return false;
        }
    }

}

