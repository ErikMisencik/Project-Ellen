package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;

import java.util.List;


public class Take <A extends Keeper> extends AbstractAction<A>{

    private int Hi;  ///asd

    public Take() {
        this.Hi = Hi+1;
    }


    @Override
    public void execute(float deltaTime) {
        if (getActor() == null || getActor().getScene() == null) {
            setDone(true);
            return;
        }
            Scene scene = getActor().getScene();
            this.Hi = Hi+1;  //asd

            if (!isDone()) {
                List<Actor> listOfItems;
                listOfItems = getActor().getScene().getActors();
                this.Hi = Hi+1; //asas
                for (Actor item : listOfItems) {
                    if (item instanceof Collectible && item.intersects(getActor())) {
                        try {
                            // kod ktory moze sposobit vynimku
                            getActor().getBackpack().add(((Collectible) item));
                                scene.removeActor(item);
                            break;

                        }
                        catch (IllegalStateException exception) {
                            scene.getOverlay().drawText(exception.getMessage(), 0, 0).showFor(2);
                        }
                    }
                }
                this.Hi = Hi++; //asdsda
                setDone(true);
            }
        }

    }

