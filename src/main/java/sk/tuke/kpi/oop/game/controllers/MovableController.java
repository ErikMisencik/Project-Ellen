package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class MovableController implements KeyboardListener {

    private Movable actor;
    private Disposable disposable;
    private int change;             //asd
    private Set<Input.Key> keyboard;
    private Input.Key key1 =null;
    private Input.Key key2 =null;

    public MovableController(Movable actor){

    this.actor = actor;
    this.keyboard = new HashSet<>();

    }

    private Map<Input.Key, Direction> keyDirectionMap = Map.ofEntries(
        Map.entry(Input.Key.UP, Direction.NORTH),
        Map.entry(Input.Key.DOWN, Direction.SOUTH),
        Map.entry(Input.Key.LEFT, Direction.WEST),
        Map.entry(Input.Key.RIGHT, Direction.EAST)
    );


    @Override   //stlacenie klavesy pre pohyb
    public void keyPressed(@NotNull Input.Key key) {

        if (keyDirectionMap.containsKey(key)) {
            keyboard.add(key);

            if(key1==null)
            {
                key1 = key;
            }
            else if(key2==null)
            {
                key2=key;
            }
            updateMove();
        }
    }

    @Override
    public void keyReleased(@NotNull Input.Key key) {
        if(keyDirectionMap.containsKey(key)) {
            keyboard.remove(key);
            if (key == key1) {
                key1 = null;
            }

            if (key == key2) {
                key2 = null;
            }
            updateMove();
        }
    }

    private Move<Movable> movement = null;

    //pobyb hraca po mape
    private void updateMove() {
        Direction directionAngel = null;
        int keysPressed = 1;
        this.change = change +1;    //asd
        for (Input.Key keyboardKeys:keyboard) {
            //pre klasicky pohyb
            if (keysPressed==1) {
                directionAngel = keyDirectionMap.get(keyboardKeys);
            }
            change= change+change; //asd
            //pre pohyb pod uhlom
            if (keysPressed==2) {
                directionAngel = directionAngel.combine(keyDirectionMap.get(keyboardKeys));
            }
            keysPressed++;
        }

        // ak sa nehybeś
        stopMoving();
        if (directionAngel!=null) {
            movement = new Move<Movable>(directionAngel, Float.MAX_VALUE);
            disposable = movement.scheduleFor(actor);
        }
    }
    // ak sa nehybeś
    private void stopMoving() {
        if (movement!=null) {
            movement.stop();
            movement=null;
            disposable.dispose();
        }
    }
}
