package sk.tuke.kpi.oop.game.scenarios;


import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.Scenario;
import sk.tuke.kpi.oop.game.Reactor;
import sk.tuke.kpi.oop.game.SmartCooler;
import sk.tuke.kpi.oop.game.items.Hammer;

public class TrainingGameplay extends Scenario{


    @Override
    public void setupPlay(Scene scene) {

        //IMPLEMENTACIA ACTOROV
        Reactor reactor = new Reactor();                           // vytvorenie instancie reaktora
        SmartCooler smartcooler = new SmartCooler(reactor);                       // vytvorenie instancie coolera
        Hammer hammer = new Hammer();                               // vytvorenie instancie hammera

        //PRIDANIE ACTOROV DO HRY, NA SCENU
        scene.addActor(smartcooler, 250, 140);                // pridanie coolera do sceny na poziciu x,y
        scene.addActor(reactor, 147, 95);                // pridanie reaktora do sceny na poziciu x,y
        scene.addActor(hammer, 200, 200);                // pridanie reaktora do sceny na poziciu x,y

//        //REACTOR TURN ON
          reactor.turnOn();
//
//        //COOLER COOL
//        new ActionSequence<>(
//            new Wait<>(5),                      //% sec delay pre spustenie coolera
//            new Invoke<>(Cooler::turnOn)               //dostaneme sa do Coolera a jeho funkcie turnOn
//        ).scheduleFor(cooler);                         //actor cooler v zatvorke, po vytvoreni instancie

    }
}
