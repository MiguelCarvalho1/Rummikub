import java.util.*;

public class Board {
    private List<List<Piece>> playedGroups;
    private List<Piece> stock;
    private List<Player> players;
    private Player currentPlayer;
    private boolean firstMove;
    private int currentPlayerIndex;

    public Board(int numPlayers) {
        this.playedGroups = new ArrayList<>();
        this.stock = new ArrayList<>();
        this.players = new ArrayList<>();
        this.firstMove = true;

        initializePieces();
        initializePlayers(numPlayers);
        dealPieces();
        currentPlayer = players.get(0);
        currentPlayerIndex = 0;
    }

    private void initializePieces() {
        String[] colors = {"red", "blue", "yellow", "black"};
        for (String color : colors) {
            for (int i = 1; i <= 13; i++) {
                stock.add(new Piece(i, color));
                stock.add(new Piece(i, color));
            }
        }
        stock.add(new Piece(0, "joker"));
        stock.add(new Piece(0, "joker"));
        Collections.shuffle(stock);
    }

    private void initializePlayers(int n) {
        for (int i = 1; i <= n; i++) {
            players.add(new Player("Jogador " + i));
        }
    }

    private void dealPieces() {
        for (Player player : players) {
            for (int i = 0; i < 14; i++) {
                player.addPiece(stock.remove(0));
            }
        }
    }

    public boolean playMove(Player player, List<Piece> move) {
        if (player != currentPlayer) {
            System.out.println("Não é sua vez!");
            return false;
        }

        if (firstMove) {
            int sum = move.stream().mapToInt(Piece::getNumber).sum();
            if (sum < 30) {
                System.out.println("Jogada inicial inválida. Deve ter no mínimo 30 pontos.");
                return false;
            }
            firstMove = false;
        }

        if (player.isValidPlay(move)) {
            playedGroups.add(new ArrayList<>(move));
            player.getPieces().removeAll(move);
            return true;
        }

        return false;
    }

    public void drawPiece(Player player) {
        if (!stock.isEmpty()) {
            player.addPiece(stock.remove(0));
        }
    }

    public void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        currentPlayer = players.get(currentPlayerIndex);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isFirstMove() {
        return firstMove;
    }

    public void displayBoard() {
        System.out.println("\n==== Tabuleiro ====");
        for (List<Piece> group : playedGroups) {
            System.out.println(group);
        }
        System.out.println("Peças restantes no estoque: " + stock.size());
    }
}
