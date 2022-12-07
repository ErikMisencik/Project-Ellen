package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.items.Backpack;
import sk.tuke.kpi.oop.game.weapons.Firearm;
import sk.tuke.kpi.oop.game.weapons.Gun;

public class Ripley extends AbstractActor implements Movable, Keeper, Alive, Armed {

    private Animation animationOfRipley;
    private int ammo;
    private Disposable dispose=null;
    private int speed;
    private Health health;
    private Firearm deagleGun;
    private Backpack backpack;
    public static final Topic<Ripley> RIPLEY_DIED = Topic.create("ripley died", Ripley.class);


    public Ripley() {
        super("Rippley");
        animationOfRipley = new Animation("sprites/player.png", 32, 32, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        setAnimation(animationOfRipley);
        health = new Health(100,100);
        speed =2;
        ammo=120;
        animationOfRipley.stop();
        deagleGun = new Gun(50,200);
        backpack = new Backpack("Carovne vrecusko",10);


        health.onExhaustion(() -> {
            Animation ripleyDie = new Animation("sprites/player_die.png", 32, 32, 0.1f, Animation.PlayMode.ONCE);
            setAnimation(ripleyDie);
            getScene().getMessageBus().publish(RIPLEY_DIED,this);
        });
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    @Override
    public void startedMoving(Direction direction) {
        animationOfRipley.setRotation(direction.getAngle());
        animationOfRipley.play();
    }

    @Override
    public void stoppedMoving() {
        animationOfRipley.stop();
    }

    public int getAmmo() {
        return this.ammo;
    }

    public void setAmmo(int ammo) {
            this.ammo = ammo;
    }

    @Override
    public Backpack getBackpack() {
        return backpack;
    }

    public void showRipleyState() {
        int windowHeight = getScene().getGame().getWindowSetup().getHeight();
        int yTextPos = windowHeight - GameApplication.STATUS_LINE_OFFSET;
        getScene().getGame().getOverlay().drawText("Health: " +health.getValue(), 120, yTextPos);
        getScene().getGame().getOverlay().drawText("Ammo " + this.getFirearm().getAmmo(), 280, yTextPos);
    }

    public void decreaseEnergy() {

        if (this.health.getValue() < 1) {
            Animation ripleyDie = new Animation("sprites/player_die.png", 32, 32, 0.1f, Animation.PlayMode.ONCE);
            setAnimation(ripleyDie);
            getScene().getMessageBus().publish(RIPLEY_DIED, this);
        }
        else {
            dispose = new Loop<>(
                new ActionSequence<>(
                    new Invoke<>(() -> {
                        if (this.health.getValue() < 1) {
                            Animation ripleyDie = new Animation("sprites/player_die.png", 32, 32, 0.1f, Animation.PlayMode.ONCE);
                            setAnimation(ripleyDie);
                            getScene().getMessageBus().publish(RIPLEY_DIED, this);
                            return;
                        }
                        else {
                            this.getHealth().drain(4);
                        }
                    }),
                    new Wait<>(1)
                )
            ).scheduleFor(this);
        }
    }

    public Disposable stopDecreasingEnergy() {
        return dispose;
    }


    @Override
    public Health getHealth() {
        return health;
    }

    @Override
    public Firearm getFirearm() {
        return deagleGun;
    }

    @Override
    public void setFirearm(Firearm weapon) {
        deagleGun = weapon;
    }


}
