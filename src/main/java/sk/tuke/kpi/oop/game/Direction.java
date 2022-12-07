package sk.tuke.kpi.oop.game;

public enum Direction {
    NORTH     ( 0,  1),
    EAST      ( 1,  0),
    SOUTH     ( 0, -1),
    WEST      (-1,  0),
    NORTHEAST ( 1,  1),
    SOUTHEAST ( 1, -1),
    SOUTHWEST (-1, -1),
    NORTHWEST (-1,  1),
    NONE      ( 0,  0);

    private final int dx;
    private final int dy;
    private float angel;

    Direction(int dx,int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public float getAngle(){
        return angel;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }


    public static Direction fromAngle(float angle) {
        if (angle == 0)   {return NORTH;    }
        if (angle == 45)  {return NORTHWEST;}
        if (angle == 90)  {return WEST;     }
        if (angle == 135) {return SOUTHWEST;}
        if (angle == 180) {return SOUTH;    }
        if (angle == 225) {return SOUTHEAST;}
        if (angle == 270) {return EAST;     }
        return NORTHEAST;
    }

    static {
        NORTH.angel     =   0;
        NORTHWEST.angel =  45;
        WEST.angel      =  90;
        SOUTHWEST.angel = 135;
        SOUTH.angel     = 180;
        SOUTHEAST.angel = 225;
        EAST.angel      = 270;
        NORTHEAST.angel = 315;
    }



    public Direction combine(Direction other) {
        int newPositionX;
        int newPositionY;

        if (this==other){
            return this;
        }

        //pre x
        if (getDx()!=other.getDx()) {
            newPositionX = getDx() + other.getDx();
        }
        else {
            newPositionX = getDx();
        }

        //pre y
        if (getDy()!=other.getDy()) {
            newPositionY = getDy() + other.getDy();
        }
        else {
            newPositionY = getDy();
        }

        Direction direction=NONE;

        for (Direction value : Direction.values()) {
            if (value.getDx()==newPositionX && value.getDy()==newPositionY){
                direction=value;
            }
        }
        return direction;
    }



}
