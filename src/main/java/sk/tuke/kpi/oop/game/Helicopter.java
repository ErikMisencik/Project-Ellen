//package sk.tuke.kpi.oop.game;
//
//import org.jetbrains.annotations.Nullable;
//import sk.tuke.kpi.gamelib.Actor;
//import sk.tuke.kpi.gamelib.Input;
//import sk.tuke.kpi.gamelib.Scene;
//import sk.tuke.kpi.gamelib.actions.Invoke;
//import sk.tuke.kpi.gamelib.framework.AbstractActor;
//import sk.tuke.kpi.gamelib.framework.Player;
//import sk.tuke.kpi.gamelib.framework.actions.Loop;
//import sk.tuke.kpi.gamelib.framework.actions.Rotate;
//import sk.tuke.kpi.gamelib.graphics.Animation;
//
//public class Helicopter extends AbstractActor {
//
//
//    private int heliX,heliY;
//    private int playerX,playerY;
//    private int step = 2;
//    private Animation heliAnimation;
//    private Player player;
//
//
//    public Helicopter(Player player){
//        this.player = player;
//        this.heliAnimation = new Animation("sprites/heli.png", 64, 64, 0.2f, Animation.PlayMode.LOOP_PINGPONG);
//        setAnimation(heliAnimation);
//    }
//
//    public void searchAndDestroy(){
//
//
//        //if(intersects(Player player){
//
//        getAnimation().play();
//
//   }
//
//
//    @Override
//    public void addedToScene(Scene scene){
//        super.addedToScene(scene);
//        new Loop<>(new Invoke<>(this::searchAndDestroy)).scheduleFor(this);
//    }
//
//    //x, y  heli aby sa mohla pohybovat za playerom
//
//    public void searchPlayer() {
//
//          @Nullable Scene scene = this.getScene();                  //zistit info
//          boolean moved = true;
//          getAnimation().play();                                  //nech sa heli pohybuje
//          float newRotation = getAnimation().getRotation();
//
//
//        int heliX = this.getPosX();             //position of helicopter
//        int heliY = this.getPosY();
//
//        int playerX = this.player.getPosX();    //position of player
//        int playerY = this.player.getPosY();
//
//        if (playerX > heliX) {
//            setPosition(this.getPosX() + step, this.getPosY());
//            newRotation = 270;
//          } else if (playerX < heliX) {
//              setPosition(this.getPosX() - step, this.getPosY());
//              newRotation = 90;
//          } else if (playerY > heliY) {
//              setPosition(this.getPosX(), this.getPosY() + step);
//              newRotation = 0;
//        } else if (playerY < heliY) {
//            setPosition(this.getPosX() , this.getPosY()- step);
//            newRotation = 180;
//        } else {
//            moved = false;
//            getAnimation().pause();
//        }
//
//        if (getAnimation().getRotation() != newRotation) {
//            new Rotate<>(newRotation, 0.1f).scheduleFor(this);
//        }
//
//        if (moved && scene.getMap().intersectsWithWall(this)) {
//            setPosition(heliX, heliY);
//        }
//    }
//
//
//
//
//}
