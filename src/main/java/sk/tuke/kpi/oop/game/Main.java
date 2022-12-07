package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.gamelib.backends.lwjgl.LwjglBackend;
import sk.tuke.kpi.oop.game.scenarios.EscapeRoom;

public class Main {

    public static void main(String[] args) {

        WindowSetup windowSetup = new WindowSetup("Project Ellen", 800, 600);
        Game game = new GameApplication(windowSetup, new LwjglBackend());                                // v pripade Mac OS bude druhy parameter "new Lwjgl2Backend()"
        //Nastavena inštancia triedy MissionImpossible.Factory ako továren na aktérov pre scénu

        //Scene scene = new World("world",  "maps/mission-impossible.tmx", new MissionImpossible.Factory());
        Scene scene = new World("World", "maps/escape-room.tmx", new EscapeRoom.Factory());

        game.addScene(scene);                               // adding scene into the game
        //Vytvorená inštancia triedy scenára EscapeRoom
        EscapeRoom escapeRoom = new EscapeRoom();
        scene.addListener(escapeRoom);


//        FirstSteps firstSteps = new FirstSteps();         //Vytvorená inštancia triedy scenára FirstSteps
//        scene.addListener(firstSteps);                    //Pridaná pomocou metódy addListener(), metoda sa nachádza v interfaci Scene,  k vytvorenej scéne.


   //     MissionImpossible missionImpossible = new MissionImpossible();        //Vytvorená inštancia triedy scenára missionImpossible
   //     scene.addListener(missionImpossible);

        game.start();                                                                     // spustenie hry
        //ak chceme hru vypnuť stlačíme Escape button, ESC
        game.getInput().onKeyPressed(Input.Key.ESCAPE, game::stop);

    }




}
