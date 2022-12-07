package sk.tuke.kpi.oop.game.weapons;

public abstract class Firearm {

    private int maximumAmmo;
    private int rightNowAmmo;

    public Firearm(int startingAmmo, int maximumAmmo){
        this.rightNowAmmo = startingAmmo;
        this.maximumAmmo = maximumAmmo;
    }

    public Firearm(int initialHealth) {
        this.rightNowAmmo = initialHealth;
        maximumAmmo = this.rightNowAmmo;
    }

    public int getAmmo(){
        return this.rightNowAmmo;
    }

    public int getMaximumAmmo() {
        return maximumAmmo;
    }

    public void reload (int newAmmo){
        if (rightNowAmmo + newAmmo>maximumAmmo) {
            rightNowAmmo = maximumAmmo;
        }
        else {
            rightNowAmmo += newAmmo;
        }
    }

    protected abstract Fireable createBullet();

    public Fireable fire() {
        if (rightNowAmmo != 0) {
            rightNowAmmo--;
            return createBullet();
        }
        else {
            return null;
        }
    }


}
