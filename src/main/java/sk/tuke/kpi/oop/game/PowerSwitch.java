package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class PowerSwitch extends AbstractActor {


    private Switchable actor;
    private boolean condition = false;


    public PowerSwitch(Switchable actor) {                           //sam som si vytvoril preto Light light
        this.actor = actor;                                      //členska premenna nazvana reactor, ktorá bude predstavovať referenciu na ovládaný reaktor.
        Animation powerSwitchAnimation = new Animation("sprites/switch.png", 16, 16);
        setAnimation(powerSwitchAnimation);
    }


    // REFERENCE TO ACTIVE ACTOR
    public Switchable getDevice(){
        return actor;
    }

    //SWITCH ON ACTOR
    public void switchOn(){

        //condition je definovana ako boolean pod public class PowerSwitch
        if (condition == false && this.actor != null) {
            condition = actor.isOn();
            actor.turnOn();
        }

    }
    //SWITCH OFF ACTOR
    public void switchOff(){

        if (this.actor != null) {
            condition = actor.isOn();
            actor.turnOff();
        }
    }

}
