package sk.tuke.kpi.oop.game.scenarios;


import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.SceneListener;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.items.*;

public class FirstSteps implements SceneListener {

    private Ripley ripley;
    private Energy energy;
    private Ammo ammo;


    @Override
    public void sceneInitialized(Scene scene) {
        ripley = new Ripley();
        scene.addActor(ripley,0,0);
        MovableController movableController = new MovableController(ripley);
        scene.getInput().registerListener(movableController);

        Hammer hammer = new Hammer();
        Wrench wrench = new Wrench();
        FireExtinguisher fireExtinguisher = new FireExtinguisher();


        //Vytvorenie First Aid kitu na scene
        energy = new Energy();
        scene.addActor(energy,-150, 50);

        //Interakcia Ripley a First Aid Kitu
        new When<>(
            () -> ripley.intersects(energy),
            new Invoke<>(() -> energy.useWith(ripley))
        ).scheduleFor(ripley);

        //Vytvorenie Ammo na scene
        ammo = new Ammo();
        scene.addActor(ammo,150, 50);

        //Interakcia Ripley a Ammo
        new When<>(
            () -> ripley.intersects(ammo),
            new Invoke<>(() -> ammo.useWith(ripley))
        ).scheduleFor(ripley);

        scene.addActor(hammer, 100, -50);
        scene.addActor(wrench, -150, 200);
        scene.addActor(fireExtinguisher, 120, 40);

        ripley.getBackpack().add(hammer);
        ripley.getBackpack().add(wrench);
        ripley.getBackpack().add(fireExtinguisher);

        scene.getGame().pushActorContainer(ripley.getBackpack());
        ripley.getBackpack().shift();

        //pouźívanie kláves počas hry, SHIFT, TAKE, DROP
        KeeperController keeperController = new KeeperController(ripley);
        scene.getInput().registerListener(keeperController);


    }


    @Override
    public void sceneUpdating(Scene scene) {
        int windowHeight = scene.getGame().getWindowSetup().getHeight();
        int yTextPos = windowHeight - GameApplication.STATUS_LINE_OFFSET;
        scene.getGame().getOverlay().drawText("Energy: " +ripley.getHealth(), 120, yTextPos);
        scene.getGame().getOverlay().drawText("Ammo: " +ripley.getAmmo(), 320, yTextPos);
    }


}
