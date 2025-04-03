import java.util.*;

public class Board {
    private List<Piece> playedPieces;
    private List<Player> players;

    public Board(int numPlayers) {
        this.playedPieces = new ArrayList<Piece>();
        this.players = new ArrayList<>();

        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player());
        }

    }

    private void initialBoard(){
        String[] colors = new String[]{"white", "black", "red", "yellow", "green", "blue"};

        for(String color : colors){
            for (int i = 1; i<=13; i++){
                playedPieces.add(new Piece(i, color));
            }
        }
    }

    private void displayBoard(){
        Random rand = new Random();

        for (Player player : players){
            int randomIndex = rand.nextInt(playedPieces.size());
            Piece piece = playedPieces.get(randomIndex);
            player.addPiece(piece);
            playedPieces.remove(randomIndex);
        }
    }

    public void addPieces(List<Piece> pieces){
        playedPieces.addAll(pieces);
    }

    public List<Piece> getPlayedPieces() {
        return playedPieces;
    }

    public List<Player> getPlayers() {
        return players;
    }



}
