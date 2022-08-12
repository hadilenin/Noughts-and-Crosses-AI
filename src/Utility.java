import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.ArrayList;



public class Utility {
    public static GameStatus getGameStatus(ArrayList<BoardElements> board) {
        for (int i = 0; i <= 6; i += 3) {
            if (board.get(i) == board.get(i + 1) && board.get(i + 1) == board.get(i + 2)) {
                if (board.get(i) == Players.getPlayer())
                    return GameStatus.playerWon;
                if (board.get(i) == Players.getRival())
                    return GameStatus.rivalWon;
            }
        }

        for (int i = 0; i <= 2; i++) {
            if (board.get(i) == board.get(i + 3) && board.get(i + 3) == board.get(i + 6)) {
                if (board.get(i) == Players.getPlayer())
                    return GameStatus.playerWon;
                if (board.get(i) == Players.getRival())
                    return GameStatus.rivalWon;
            }
        }

        if (board.get(0) == board.get(4) && board.get(4) == board.get(8)) {
            if (board.get(0) == Players.getPlayer())
                return GameStatus.playerWon;
            if (board.get(0) == Players.getRival())
                return GameStatus.rivalWon;
        }

        if (board.get(2) == board.get(4) && board.get(4) == board.get(6)) {
            if (board.get(2) == Players.getPlayer())
                return GameStatus.playerWon;
            if (board.get(2) == Players.getRival())
                return GameStatus.rivalWon;
        }

        if(fnc_isFull(board))
            return GameStatus.draw;

        return GameStatus.onProgress;
    }

    public static boolean fnc_isFull(ArrayList<BoardElements> board) {
        int counter = 0;
        while (counter <= 8) {
            if (board.get(counter) == BoardElements.Empty)
                return false;
            counter++;
        }
        return true;
    }

    public static void playSound() {
        File soundFile = new File("/home/paddington/IdeaProjects/untitled/src/Cheer.wav");
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(soundFile));
            clip.start();
        } catch (Exception e) {
            System.out.println("Error with playing a sound!!");
            e.printStackTrace();
        }

    }

}
