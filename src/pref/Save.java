package pref;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import logic.Game;

/**
 *
 * @author André Heinen
 */
public class Save implements Serializable {
    
    private final int saveNumber;
    
    public Save(int number) {
        this.saveNumber = number;
    }
    
    public void saveGame(Game game) throws IOException {
        File sFile = new File("save" + saveNumber);

        try (FileOutputStream fos = new FileOutputStream(sFile);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(game);
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException bei saveGame()");
        }
    }

    public Game loadGame() throws IOException {
        Game game = null;
        File lFile = new File("save" + saveNumber + ".sav");

        try (FileInputStream fis = new FileInputStream(lFile);
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            game = (Game) ois.readObject();
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException in loadGame()");
            System.exit(0);
        }
        return game;
    }
}