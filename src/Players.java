public class Players {
    private static BoardElements player;
    private static BoardElements rival;

    private static boolean isPlayerLocked;
    private static boolean isRivalLocked;

    public static BoardElements getPlayer(){
        return player;
    }

    public static BoardElements getRival(){
        return rival;
    }

    public static void setPlayer(BoardElements player){
        if(!isPlayerLocked){
            Players.player = player;
        } else {
            System.out.println("Players.player is immutable!");
        }
    }

    public static void setRival(BoardElements rival){
        if(!isRivalLocked){
            Players.rival = rival;
        } else {
            System.out.println("Players.rival is immutable!");
        }
    }

    public static void lockPlayer(boolean lock){
        isPlayerLocked = lock;
    }

    public static void lockRival(boolean lock){
        isRivalLocked = lock;
    }

}
