package sk.tuke.kpi.oop.game.weapons;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.characters.Alive;

public class Bullet extends AbstractActor implements Fireable {

    private int speed;
    private int damage = 35;

    public Bullet(){

        speed = 5;
        Animation flyingBullet = new Animation("sprites/bullet.png",16,16);
        setAnimation(flyingBullet);
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public void startedMoving(Direction direction) {
        if (direction != null && direction != Direction.NONE) {
            speed = 5;
            this.getAnimation().setRotation(direction.getAngle());
        }
    }

    @Override
    public void addedToScene(Scene scene) {
        super.addedToScene(scene);
        new Loop<>(
            new Invoke<>(this::shoot)
        ).scheduleFor(this);

    }

    private void shoot() {
        for (Actor actor : getScene().getActors()) {
            if (this.intersects(actor) && (actor instanceof Alive)) {
                ((Alive) actor).getHealth().drain(damage);
                speed = 5;
                collidedWithWall();
            }
        }
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }


    @Override
    public void collidedWithWall() {
        getScene().removeActor(this);
    }



}
