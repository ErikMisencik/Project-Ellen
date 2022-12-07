package sk.tuke.kpi.oop.game.controllers;

import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.actions.Drop;
import sk.tuke.kpi.oop.game.actions.Shift;
import sk.tuke.kpi.oop.game.actions.Take;
import sk.tuke.kpi.oop.game.actions.Use;
import sk.tuke.kpi.oop.game.items.Usable;

public class KeeperController implements KeyboardListener {

    private Keeper keeper;

    public KeeperController(Keeper keeper) {
        this.keeper = keeper;
    }

    @Override  //funkcia pre stlacene klavesy na klavesnici
    public void keyPressed(Input. Key pressedKey) {
        if (pressedKey == Input.Key.ENTER) {
            new Take<>().scheduleFor(keeper);
        }
        if (pressedKey == Input.Key.BACKSPACE) {
            new Drop<>().scheduleFor(keeper);
        }
        if (pressedKey == Input.Key.S) {
            new Shift<>().scheduleFor(keeper);
        }
        if (pressedKey == Input.Key.B) {
            useItemFromBackpack();
        }
        if (pressedKey == Input.Key.U) {
            useUse();
        }

    }

    private void useUse() {
        //absolutny nonsens
        Usable<?> usable = keeper.getScene().getActors().stream().filter(Usable.class::isInstance).filter(keeper::intersects).map(Usable.class::cast).findFirst().orElse(null);
        if (usable != null) {
            new Use<>(usable).scheduleForIntersectingWith(keeper);
        }
    }
    private void useItemFromBackpack() {
            //pouzitie itemu z backpacku ak je v backpacku
        if (keeper.getBackpack().peek() instanceof Usable) {
            Use<?> use=new Use<>((Usable<?>)keeper.getBackpack().peek());
            use.scheduleForIntersectingWith(keeper);
        }
    }


}
