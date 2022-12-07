package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.oop.game.Reactor;

public class Mjolnir extends Hammer {

    private int con;

    public Mjolnir(){

        super();
        super.setRemainingUses(4);

    }


    public void useWith(Reactor actor){

        super.useWith(actor);
        con = con+1;
    }
}
