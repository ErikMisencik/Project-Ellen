package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;


public class Cooler extends AbstractActor implements Switchable{


    private Reactor reactor;
    private boolean condition = false;


    public Cooler(Reactor reactor) {

        this.reactor = reactor;
        Animation coolerOn = new Animation(
            "sprites/fan.png",
            32, 32, 0.2f,
            Animation.PlayMode.LOOP_PINGPONG);
            setAnimation(coolerOn);
            coolerOn.pause();

    }

    public void coolReactor(){

    if(isOn()){
       this.reactor.decreaseTemperature(1);
    }
    }

    @Override
    public void turnOn() {
        if (this.isOn() == false) {
            condition = true;
            getAnimation().play();
        }
    }

    @Override
    public void turnOff() {
            if (this.isOn()==true) {
                condition = false;
                getAnimation().pause();
            }
    }

    @Override
    public boolean isOn(){ return condition;}

    @Override
    public void addedToScene(Scene scene){
        if(this.reactor != null) {
            super.addedToScene(scene);
            new Loop<>(new Invoke<>(this::coolReactor)).scheduleFor(this);
        }
    }

}
