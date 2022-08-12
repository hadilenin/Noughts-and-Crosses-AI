import java.util.ArrayList;

public class AI {

    public static int nextMove() {
        AI ai = new AI();
        return ai.findOptimalMove(GUI.board);
    }

    private int findOptimalMove(ArrayList<BoardElements> board) {
        int bestMoveScore = -1000;
        int bestMove = -1;

        int index = 0;
        while (index <= 8) {
            if (board.get(index) == BoardElements.Empty) {

                board.set(index, Players.getRival());

                int currentMoveScore = minimax(board, 0, true);
                board.set(index, BoardElements.Empty);                          //undo the move
                if (bestMoveScore < currentMoveScore) {
                    bestMoveScore = currentMoveScore;
                    bestMove = index;
                }
            }
            index++;
        }

        return bestMove;
    }

    private int minimax(ArrayList<BoardElements> board, int depth, boolean isMax) {
        int score = fnc_evaluate(board);
        if (score == 10 || score == -10)
            return score;
        if (Utility.fnc_isFull(board))
            return 0;
        if (isMax) {
            int bestScore = -1000;
            int counter = 0;
            while (counter <= 8) {
                if (board.get(counter) == BoardElements.Empty) {
                    board.set(counter, Players.getRival());

                    bestScore = Math.max(bestScore, minimax(board, depth + 1, !isMax));

                    board.set(counter, BoardElements.Empty);
                }
                counter++;
            }
            return bestScore;
        } else {
            int bestScore = 1000;
            int counter = 0;
            while (counter <= 8) {
                if (board.get(counter) == BoardElements.Empty) {
                    board.set(counter, Players.getPlayer());

                    bestScore = Math.min(bestScore, minimax(board, depth + 1, isMax));

                    board.set(counter, BoardElements.Empty);
                }
                counter++;
            }
            return bestScore;
        }
    }

    private int fnc_evaluate(ArrayList<BoardElements> board) {
        for (int i = 0; i <= 6; i += 3) {
            if (board.get(i) == board.get(i + 1) && board.get(i + 1) == board.get(i + 2)) {
                if (board.get(i) == Players.getPlayer())
                    return -10;
                if (board.get(i) == Players.getRival())
                    return 10;
            }
        }

        for (int i = 0; i <= 2; i++) {
            if (board.get(i) == board.get(i + 3) && board.get(i + 3) == board.get(i + 6)) {
                if (board.get(i) == Players.getPlayer())
                    return -10;
                if (board.get(i) == Players.getRival())
                    return 10;
            }
        }

        if (board.get(0) == board.get(4) && board.get(4) == board.get(8)) {
            if (board.get(0) == Players.getPlayer())
                return -10;
            if (board.get(0) == Players.getRival())
                return 10;
        }

        if (board.get(2) == board.get(4) && board.get(4) == board.get(6)) {
            if (board.get(2) == Players.getPlayer())
                return -10;
            if (board.get(2) == Players.getRival())
                return 10;
        }

        return 0;
    }


}
