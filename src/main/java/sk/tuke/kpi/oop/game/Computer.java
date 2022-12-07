package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Computer extends AbstractActor implements EnergyConsumer {

    private boolean availability = false; //s
    private Animation computerOn;

    public Computer(){
        this.computerOn = new Animation("sprites/computer.png",
            80,48,0.2f,
            Animation.PlayMode.LOOP);
            setAnimation(computerOn);
            computerOn.pause();
    }

    public float add(float one, float two){

        if(this.availability){
            computerOn.play();
            return one+two;
        }
        else {
            computerOn.pause();
            return 0;
        }
    }

    public float sub(float one, float two){

        if(this.availability == true){
            computerOn.play();
            return one-two;
        }
        else{
            computerOn.pause();
            return 0;
        }
    }

    public int add(int one, int two){

        if(this.availability == true){
            computerOn.play();
            return one+two;
        }
        else{
            computerOn.pause();
            return 0;
        }
    }

    public int sub(int one, int two){

        if(this.availability == true){
            computerOn.play();
            return one-two;
        }
        else{
            computerOn.pause();
            return 0;
        }
    }

    @Override
    public void setPowered(boolean availability) {
        if(!availability){
            this.availability = false;                             //avaibility  switch  //condition je pre reactor
        }
        if(availability){
            this.availability = true;
        }
    }

}
