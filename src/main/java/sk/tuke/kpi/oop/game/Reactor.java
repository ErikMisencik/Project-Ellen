package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.actions.PerpetualReactorHeating;

import java.util.HashSet;
import java.util.Set;

public class Reactor extends AbstractActor implements Switchable, Repairable {

    private int temperature;               //dd
    private int damage;
    private int temporaryDmg;
    private boolean condition ;
    private boolean brokenReactor;

    private Animation turnOffAnimation;
    private Animation normalAnimation;
    private Animation overheatedAnimation;
    private Animation brokenAnimation;
    private Animation extinguishAnimation;
    private Set<EnergyConsumer> devices;
    private EnergyConsumer device;

    public  Reactor(){
        this.temperature = 0;
        this.damage = 0;
        this.brokenReactor = false;
        this.condition = false;
        this.devices = new HashSet<>();



        this.turnOffAnimation = new Animation("sprites/reactor.png");

        this.normalAnimation = new Animation(
            "sprites/reactor_on.png",
            80,80, 0.1f -(float)(this.damage/120),
            Animation.PlayMode.LOOP_PINGPONG);

        this.overheatedAnimation = new Animation(
            "sprites/reactor_hot.png",
            80,80,  0.1f - (float)(this.damage/120),
            Animation.PlayMode.LOOP_PINGPONG);

        this.brokenAnimation = new Animation(
            "sprites/reactor_broken.png",
            80,80, 0.1f - (float)(this.damage/120),
            Animation.PlayMode.LOOP_PINGPONG);

            turnOff();
    }

    public void increaseTemperature(int increment){         //toto opravit
        updateAnimation();
        if(this.condition == false || increment <0 || this.damage ==100) {

          return;
        }
            int temp = increment;
            if (this.damage <= 66 && this.damage >= 33) {
                temp = (int) Math.ceil(temp * 1.5);
            }
            else if (this.damage > 66) {
                temp = (int) (temp * 2);
            }
            this.temperature += temp;

            if (this.temperature > 2000) {
                int dmg = (this.temperature - 2000);
                this.damage = (dmg*10)/400;
                if (this.damage > 100) {
                    this.damage = 100;
                    updateAnimation();
                }
            }
            updateAnimation();
    }

    public void decreaseTemperature(int decrement) {

        if(this.condition == true){
            if (this.damage < 50  && this.temperature>0 && decrement > 0) {
                    this.temperature -= decrement;
            }

            if (this.damage >= 50 && this.damage < 100 && this.temperature>0 && decrement > 0) {
                    this.temperature -= decrement * 0.5;
            }
        updateAnimation();
        }
    }

    private void updateAnimation(){

        if (this.temperature > 4000 ){
            setAnimation(overheatedAnimation);
        }
        else {
            setAnimation(normalAnimation);
        }

        if (this.temperature >= 4000 && this.temperature < 6000 && this.damage < 100 ) {
            setAnimation(overheatedAnimation);
        }
        else {
            setAnimation(normalAnimation);
        }

        if (this.temperature >= 6000 || this.damage == 100){

                setAnimation(brokenAnimation);
                brokenReactor = true;
                condition = false;
        }
    }

    public int getTemperature() {
        return  temperature;
    }

    public int getDamage() {
        return  damage;
    }

    public void addDevice(EnergyConsumer device){

        this.device = device;
        devices.add(this.device);

        if (this.isOn()== true) {
            device.setPowered(true);
        }
        else{
            device.setPowered(false);
        }

    }

    public void removeDevice(@NotNull EnergyConsumer device){
        this.device = device;
        devices.remove(this.device);
        device.setPowered(false);
    }

    @Override
    public boolean repair(){

            if (this.temperature<6000 && this.damage <100 && this.damage !=0) {
                if (this.damage <= 50) {

                    this.temporaryDmg = ((this.damage-50)*40)+2000;  //prevedenie dmg na teplotu podla vzorca
                    if(this.temperature > this.temporaryDmg){
                        this.temperature = this.temporaryDmg;       //Uprava teploty iba ak po oprave bola nižšia
                    }

                    this.damage = 0;
                    updateAnimation();
                }
                else {
                    this.temporaryDmg = ((this.damage-50)*40)+2000;  //prevedenie dmg na teplotu podla vzorca

                        if(this.temperature > this.temporaryDmg){
                            this.temperature = this.temporaryDmg;       //Uprava teploty iba ak po oprave bola nižšia
                        }

                    this.damage -= 50;
                    updateAnimation();

                }
                return true;
            }
        updateAnimation();
        return false;
    }

    public boolean extinguish(){

        if(brokenReactor == true){
             this.extinguishAnimation = new Animation("sprites/reactor_extinguished.png",80,80);
             setAnimation(extinguishAnimation);
             this.temperature = 4000;
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void turnOn() {
        if(brokenReactor == false) {
            this.condition = true;
            setAnimation(normalAnimation);

        }
    }

    @Override
    public void turnOff() {
        this.condition = false;
        if(brokenReactor == false) {
            setAnimation(turnOffAnimation);
        }
        else {
            setAnimation(brokenAnimation);
        }

    }

    @Override
    public boolean isOn(){
        return this.condition;
    }

    @Override
    public void addedToScene(Scene scene){
        super.addedToScene(scene);
        new PerpetualReactorHeating(1).scheduleFor(this);
    }

}

