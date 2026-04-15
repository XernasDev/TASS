package dev.xernas.tass;

import dev.xernas.photon.exceptions.PhotonException;

public class Launcher {

    public static void main(String[] args) throws PhotonException {
        Game game = new Game();
        game.launch();
    }

}
