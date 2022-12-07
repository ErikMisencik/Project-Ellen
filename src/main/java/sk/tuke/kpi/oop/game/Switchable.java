package sk.tuke.kpi.oop.game;

public interface Switchable {


    void turnOn();                        //- method will turn on controlled device
    void turnOff();                       //- method will turn off controlled device
    boolean isOn();                                       //- method will return value representing state of device (true - device is turned on, false - device is turned off)



}
