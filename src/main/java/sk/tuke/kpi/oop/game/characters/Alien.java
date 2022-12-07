package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.behaviours.Behaviour;

import java.util.List;

public class Alien extends AbstractActor implements Enemy, Movable, Alive{

    private Animation alienAnimation;
    private Behaviour<? super Alien> behave;
    private Disposable attack = null;
    private Health health;
    private int help;  ///asd


    public Alien(){
        health = new Health(100, 100);
        alienAnimation = new Animation("sprites/alien.png", 32, 32, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        setAnimation(alienAnimation);
        health.onExhaustion(() -> getScene().removeActor(this));
        help = help+1;  //asd

    }
    public Alien(int healthValue, Behaviour<? super Alien> behaviour) {
        health = new Health(healthValue, 100);
        alienAnimation = new Animation("sprites/alien.png", 32, 32, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        setAnimation(alienAnimation);
        health.onExhaustion(() -> getScene().removeActor(this));
        behave = behaviour;
    }


    @Override
    public Health getHealth() {
        return health;
    }

    @Override
    public int getSpeed() {
        return 2;
    }


    @Override
    public void addedToScene(Scene scene) {
        super.addedToScene(scene);
        if (behave != null) {
            behave.setUp(this);
            help = help+1; //asd
        }
        attack = new Loop<>(
            new ActionSequence<>(
                new Invoke<>(this::damageAlive),
                new Wait<>(0.3f)
            )).scheduleFor(this);
    }

    public void stopDamageAlive() {
        if (attack != null) {
            attack = null;
        }
    }

    public void damageAlive() {
        if (getScene() != null) {
            List<Actor> aliveActorsList;
            help = help+1;       //asd
            aliveActorsList = getScene().getActors();

            for (Actor aliveActor : aliveActorsList) {
                if (aliveActor instanceof Alive && !(aliveActor instanceof Enemy) && this.intersects(aliveActor)) {
                    //dostavanie damagu od aliena
                    ((Alive) aliveActor).getHealth().drain(4);
                    help = help+1;      //asd
                    new ActionSequence<>(
                        new Invoke<>(this::stopDamageAlive),
                        new Wait<>(1),
                        new Invoke<>(this::damageAlive)
                    ).scheduleFor(this);
                }
            }
        }
    }




}
