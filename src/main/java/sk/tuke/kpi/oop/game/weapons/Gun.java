package sk.tuke.kpi.oop.game.weapons;

public class Gun extends Firearm {

    public Gun(int startingAmmo, int maximumAmmo) {
        super(startingAmmo, maximumAmmo);
    }

    @Override
    protected Fireable createBullet() {
        return new Bullet();
    }
}
