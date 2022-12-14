package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.items.Hammer;
import sk.tuke.kpi.oop.game.items.Usable;

public class Locker  extends AbstractActor implements Usable <Ripley> {

    private boolean isLockerOpened;

    public Locker(){

        Animation littleLocker = new Animation("sprites/locker.png", 16, 16);
        setAnimation(littleLocker);
        isLockerOpened =false;

    }


    @Override
    public void useWith(Ripley actor) {

        if(!isLockerOpened){
            isLockerOpened=true;
            getScene().addActor(new Hammer(),getPosX()+6,getPosY());
        }
    }

    @Override
    public Class<Ripley> getUsingActorClass() {
        return Ripley.class;
    }
}
