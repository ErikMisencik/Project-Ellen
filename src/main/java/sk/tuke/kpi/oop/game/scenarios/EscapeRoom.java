package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.gamelib.graphics.OverlayDrawing;
import sk.tuke.kpi.oop.game.Ventilator;
import sk.tuke.kpi.oop.game.behaviours.RandomlyMoving;
import sk.tuke.kpi.oop.game.characters.Alien;
import sk.tuke.kpi.oop.game.characters.AlienMother;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.controllers.ShooterController;
import sk.tuke.kpi.oop.game.items.AccessCard;
import sk.tuke.kpi.oop.game.items.Ammo;
import sk.tuke.kpi.oop.game.items.Energy;
import sk.tuke.kpi.oop.game.openables.Door;

public class EscapeRoom implements SceneListener {

   // private Ammo ammo;

    public static class Factory implements ActorFactory {


        @Override
        public @Nullable Actor create(@Nullable String type, @Nullable String name) {
            assert name != null;
            switch (name) {
                case "access card":
                    return new AccessCard();
                case "front door":
                    return new Door("vdoor", Door.Orientation.VERTICAL);
                case"back door":
                    return new Door("hdoor", Door.Orientation.HORIZONTAL);
                case"exit door":
                    return new Door("evdoor", Door.Orientation.VERTICAL);
                case "ellen":
                    return new Ripley();
                case "energy":
                    return new Energy();
//                    case "locker":
//                        return new Locker();
//                    case "ventilator":
//                        return new Ventilator();
                case "alien":
                    return new Alien(100, new RandomlyMoving());
                case "alien mother":
                    return new AlienMother(200, new RandomlyMoving());
                case "ammo":
                    return new Ammo();

                default:
                    return null;
            }
        }
    }

    @Override
    public void sceneInitialized(Scene scene) {
        //return first actor that has the given name
        Ripley ripley= scene.getFirstActorByType(Ripley.class);
        scene.follow(ripley);

        //inicializujeme ripley pohyb a jej funkcnost na zaklade stlacenych klaves
        Disposable movement=scene.getInput().registerListener(new MovableController(ripley));
        Disposable keypressed=scene.getInput().registerListener(new KeeperController(ripley));
        Disposable shooterCon = scene.getInput().registerListener(new ShooterController(ripley));

        //Ukazovatel obsahu batoha
        scene.getGame().pushActorContainer(ripley.getBackpack());

        AccessCard accessCard = new AccessCard();
        ripley.getBackpack().add(accessCard);


       // scene.getMessageBus().subscribe(Door.DOOR_OPENED, (Ripley) -> ripley.decreaseEnergy());
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> movement.dispose());
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> keypressed.dispose());
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> shooterCon.dispose());
        scene.getMessageBus().subscribe(Ventilator.VENTILATOR_REPAIRED, (Ripley) -> ripley.stopDecreasingEnergy().dispose());


    }
    @Override
    public void sceneUpdating(Scene scene) {
        //return first actor that has the given name
        Ripley ripley= scene.getFirstActorByType(Ripley.class);
        if(ripley!= null ) {
            ripley.showRipleyState();
        }
    }



}
