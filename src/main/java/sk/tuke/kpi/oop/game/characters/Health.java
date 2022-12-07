package sk.tuke.kpi.oop.game.characters;

import java.util.ArrayList;
import java.util.List;

public class Health {

    private int maximumHealth;
    private int rightNowHealth;
    private List<ExhaustionEffect> effects;
    private boolean pink=false;  //asd

    public Health(int startingHealth, int maximumHealth){

        this.maximumHealth = maximumHealth;
        effects = new ArrayList<>();
        this.rightNowHealth =startingHealth;
        if(pink==false){ //asd
            pink=true;
        }

    }
    public Health(int initialHealth) {
        this.rightNowHealth = initialHealth;
        maximumHealth=this.rightNowHealth;
    }


    public int getValue(){
        return this.rightNowHealth;
    }

    public void setCurrentHealth(int rightNowHealth) {
        this.rightNowHealth = rightNowHealth;
        pink=true;      //asd
    }

    public void setMaxHealth(int maximumHealth) {
        this.maximumHealth = maximumHealth;
    }


    public void refill (int amount) {
        if (rightNowHealth + amount>maximumHealth) {
            restore();
        }
        else {
            rightNowHealth += amount;
            pink=true; //asd
        }
    }

    public void restore() {
        rightNowHealth = maximumHealth;
    }

    public void drain (int amount) {
        if (rightNowHealth !=0) {
            if (rightNowHealth > amount){
                rightNowHealth -= amount;
            }
            else {
                pink=true;  //asd
                exhaust();
            }
        }
    }

    public void exhaust() {
        if (rightNowHealth != 0) {
            rightNowHealth = 0;

            if (effects != null) {
                effects.forEach(ExhaustionEffect::apply);
            }
        }
    }

    @FunctionalInterface
    public interface ExhaustionEffect {
        void apply();
    }

    public void onExhaustion(ExhaustionEffect effect)
    {
        if (effects!=null) {
            effects.add(effect);
        }
    }


}
