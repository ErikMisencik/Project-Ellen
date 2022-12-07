package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.actions.Loop;


import java.util.Random;

public class DefectiveLight extends Light implements Repairable{

    private boolean  repaired = false;
    private Disposable dispose;

    public DefectiveLight(){
        super();
    }

    private void defectiveLight(){
        Random rand = new Random();

        int randomNum = rand.nextInt((20) + 1) + 0;

        if(super.isAvailabilityOn()==true) {              //avaibility je setPowered

                if (randomNum == 1) {
                    super.toggle();
                }
            repaired = false;
        }
    }
 ///sxasdadada

    @Override
    public void addedToScene(Scene scene){
            super.addedToScene(scene);
            this.dispose = new Loop<>(new Invoke<>(this::defectiveLight)).scheduleFor(this);
    }

    @Override
    public boolean repair() {

        if(repaired){  //s
            return false;
        }

         else if(!repaired) {

              this.repaired = true;
              this.dispose.dispose();


        }
         turnOn();
         setPowered(true);
        this.dispose =
            new ActionSequence<>(
                new Wait<>(10),
                new Loop<>(new Invoke<>(this::defectiveLight))).scheduleFor(this);

        return true;
    }

}
