package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.oop.game.Locker;
import sk.tuke.kpi.oop.game.Ventilator;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.items.AccessCard;
import sk.tuke.kpi.oop.game.items.Energy;
import sk.tuke.kpi.oop.game.openables.Door;
import sk.tuke.kpi.oop.game.openables.LockedDoor;

public class MissionImpossible implements SceneListener {

    public static class Factory implements ActorFactory {


        @Override
        public @Nullable Actor create(@Nullable String type, @Nullable String name) {

            if(name != null){
                switch (name)
                {

                    case "access card":
                        return new AccessCard();
                    case "door":
                        return new LockedDoor();
                    case "ellen":
                        return new Ripley();
                    case "energy":
                        return new Energy();
                    case "locker":
                        return new Locker();
                    case "ventilator":
                        return new Ventilator();
                    default: return null;
                }
            }
            return null;
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

        //Ukazovatel obsahu batoha
        scene.getGame().pushActorContainer(ripley.getBackpack());

        scene.getMessageBus().subscribe(Door.DOOR_OPENED, (Ripley) -> ripley.decreaseEnergy());
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> movement.dispose());
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> keypressed.dispose());
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
