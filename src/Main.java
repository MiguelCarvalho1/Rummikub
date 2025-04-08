import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Board board = new Board(2);

        while (true) {
            Player player = board.getCurrentPlayer();
            System.out.println("\nVez de " + player.getName());
            System.out.println("Mão: " + player.getPieces());

            if (board.isFirstMove()) {
                List<List<Piece>> valid = player.findValidInitialPlays();
                if (!valid.isEmpty()) {
                    for (List<Piece> play : valid) {
                        board.playMove(player, play);
                        System.out.println(player.getName() + " jogou: " + play);
                    }
                } else {
                    System.out.println("Sem jogada inicial. Pescando peça...");
                    board.drawPiece(player);
                    board.nextPlayer();
                    continue;
                }
            } else {
                List<Piece> hand = player.getPieces();
                if (hand.size() >= 3) {
                    List<Piece> tentativa = hand.subList(0, 3);
                    if (!board.playMove(player, new ArrayList<>(tentativa))) {
                        System.out.println("Jogada inválida. Pescando...");
                        board.drawPiece(player);
                    } else {
                        System.out.println(player.getName() + " jogou: " + tentativa);
                    }
                } else {
                    System.out.println("Poucas peças. Pescando...");
                    board.drawPiece(player);
                }
            }

            board.displayBoard();

            if (player.getPieces().isEmpty()) {
                System.out.println(player.getName() + " venceu!");
                break;
            }

            board.nextPlayer();
        }
    }
}