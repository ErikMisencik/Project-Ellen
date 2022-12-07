package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Light extends AbstractActor implements Switchable, EnergyConsumer {          //errjn


    private boolean availability = false;    //oznacuje vypinac jeho stav zapnuty, vypnuty
    private boolean condition = false;      //pomocnik, oznaci cisa dodava elektrina
    private Animation light_on;
    private Animation light_off;

    public Light(){

        this.light_on= new Animation("sprites/light_on.png", 16, 16);
        this.light_off= new Animation("sprites/light_off.png", 16, 16);
        setAnimation(light_off);
    }

    public boolean isAvailabilityOn(){
        return this.availability;
    }

    @Override
    public void turnOn() {
             this.condition = true;     //switch
        if(isAvailabilityOn() && isOn()){
            setAnimation(light_on);
        }
        else{
            setAnimation(light_off);
        }
    }

    @Override
    public void turnOff() {

            this.condition = false;
            setAnimation(light_off);
    }

    @Override
    public boolean isOn() {
        return this.condition;
    }

    @Override
    public void setPowered(boolean availability){         // neprejde tu kondicia reaktora, pretože kod na funkčnosť reaktora   // mam v addLight mam set electricity, musim to spraviť inač

        if(availability==false){
            this.availability = false;                             //avaibility  switch  //condition je pre reactor
            setAnimation(light_off);
        }
        if(availability==true ){
            this.availability = true;
                                                    //dal tomu power
            if(condition == true){                 //switch
                setAnimation(light_on);
            }
        }
    }

    public void toggle(){

        if(this.condition==false){          //switch down
            condition=true;                                  //da switch up

        }
        else if(this.condition==true){
            condition=false;

        }
        if(isAvailabilityOn() && isOn()){

            setAnimation(light_on);
        }
        else{
            setAnimation(light_off);
        }
    }

}
