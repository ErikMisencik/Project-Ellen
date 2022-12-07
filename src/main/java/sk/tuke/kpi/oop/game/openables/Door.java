package sk.tuke.kpi.oop.game.openables;


import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.map.MapTile;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.items.Usable;

public class Door extends AbstractActor implements Openable, Usable <Actor>{

    private boolean isOpenDoor;
    public static final Topic<Door> DOOR_OPENED = Topic.create("door opened", Door.class);
    private Orientation oriented;
    public enum  Orientation { VERTICAL, HORIZONTAL }
    private Animation openDoorAnimation;
    private Animation closeDoorAnimation;
    public static final Topic<Door> DOOR_CLOSED = Topic.create("door closed", Door.class);

    public Door(Orientation orientation){
        isOpenDoor = false;

        if (orientation == Orientation.VERTICAL) {
            oriented = Orientation.VERTICAL;
            setDoorAnimation();
        }
        else if (orientation == Orientation.HORIZONTAL) {
            oriented = Orientation.HORIZONTAL;
            setDoorAnimation();
        }
    }

    public Door (String name, Orientation orientation) {

        super(name);
        isOpenDoor = false;
        if (orientation == Orientation.VERTICAL) {
            oriented = Orientation.VERTICAL;
            setDoorAnimation();
        }
        else if (orientation == Orientation.HORIZONTAL) {
            oriented= Orientation.HORIZONTAL;
            setDoorAnimation();
        }
    }

    @Override
    public void open() {
        //dostaneme sa na scenu, do mapy a riesime poziciu dlazieb
        if(oriented==Orientation.VERTICAL) {
            getScene().getMap().getTile(getPosX() / 16, getPosY() / 16).setType(MapTile.Type.CLEAR);
            getScene().getMap().getTile(getPosX() / 16, getPosY() / 16 + 1).setType(MapTile.Type.CLEAR);
        }
        if(oriented==Orientation.HORIZONTAL) {
            getScene().getMap().getTile(getPosX() / 16, getPosY() / 16).setType(MapTile.Type.CLEAR);
            getScene().getMap().getTile(getPosX() / 16+1, getPosY() / 16).setType(MapTile.Type.CLEAR);
        }
        //zisti ci su otvorene dvere
        isOpenDoor = true;
        setAnimation(openDoorAnimation);
        getAnimation().play();
        getAnimation().stop();
        getScene().getMessageBus().publish(DOOR_OPENED, this);

    }

    @Override
    public void close() {
        //dostaneme sa na scenu, do mapy a riesime poziciu dlazieb
        if(oriented==Orientation.VERTICAL) {
            getScene().getMap().getTile(getPosX() / 16, getPosY() / 16).setType(MapTile.Type.WALL);
            getScene().getMap().getTile(getPosX() / 16, getPosY() / 16 + 1).setType(MapTile.Type.WALL);
        }
        if(oriented==Orientation.HORIZONTAL) {
            getScene().getMap().getTile(getPosX() / 16, getPosY() / 16).setType(MapTile.Type.WALL);
            getScene().getMap().getTile(getPosX() / 16+1, getPosY() / 16).setType(MapTile.Type.WALL);
        }
        isOpenDoor = false;
        setAnimation(closeDoorAnimation);
        getAnimation().play();
        getAnimation().stop();
        getScene().getMessageBus().publish(DOOR_CLOSED, this);
    }

    public void setDoorAnimation(){

        if(oriented==Orientation.VERTICAL) {
            openDoorAnimation = new Animation("sprites/vdoor.png", 16, 32, 0.1f, Animation.PlayMode.ONCE_REVERSED);
            closeDoorAnimation = new Animation("sprites/vdoor.png", 16, 32, 0.1f, Animation.PlayMode.ONCE);
            setAnimation(closeDoorAnimation);
            getAnimation().stop();
        }
        if(oriented==Orientation.HORIZONTAL) {
            openDoorAnimation = new Animation("sprites/hdoor.png", 32, 16, 0.1f, Animation.PlayMode.ONCE_REVERSED);
            closeDoorAnimation = new Animation("sprites/hdoor.png", 32, 16, 0.1f, Animation.PlayMode.ONCE);
            setAnimation(closeDoorAnimation);
            getAnimation().stop();
        }
    }

    @Override
    public boolean isOpen() {
        return isOpenDoor;
    }

    @Override
    public void useWith(Actor actor) {
        if(!isOpenDoor){
            open();
        }
        else{
            close();
        }
    }

    @Override
    public void addedToScene(Scene scene) {
        super.addedToScene(scene);
        //Aby dvere po zapnuti mapy boli zatvorene
        if(oriented==Orientation.VERTICAL) {
            getScene().getMap().getTile(getPosX() / 16, getPosY() / 16).setType(MapTile.Type.WALL);
            getScene().getMap().getTile(getPosX() / 16, getPosY() / 16 + 1).setType(MapTile.Type.WALL);
        }
        if(oriented==Orientation.HORIZONTAL) {
            getScene().getMap().getTile(getPosX() / 16, getPosY() / 16).setType(MapTile.Type.WALL);
            getScene().getMap().getTile(getPosX() / 16+1, getPosY() / 16).setType(MapTile.Type.WALL);
        }
    }



    @Override
    public Class<Actor> getUsingActorClass() {
        return Actor.class;
    }
}
