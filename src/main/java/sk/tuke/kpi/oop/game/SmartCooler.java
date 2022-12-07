package sk.tuke.kpi.oop.game;


public class SmartCooler extends Cooler {


    private boolean condition = false;

    private Reactor reactor;

    public SmartCooler(Reactor reactor) {
        super(reactor);
        this.reactor = reactor;

    }

    public void coolReactor(){

        if(this.reactor != null) {

            if(this.reactor.getTemperature()>2500){
                turnOn();
                this.condition =true;
            }
            if(this.condition==true && this.reactor.getTemperature()>=1500){
                super.coolReactor();
            }

            if(this.reactor.getTemperature()<1500) {
                turnOff();
            }
        }
        else{
            turnOff();
        }
    }





}
