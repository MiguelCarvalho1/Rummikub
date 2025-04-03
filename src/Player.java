import java.util.*;

public class Player {
    private List<Piece> pieces;

    public Player() {
        this.pieces = new ArrayList<>();
    }

    public void addPiece(Piece piece) {
        pieces.add(piece);
    }

    public boolean play(List<Piece> piecesPlayed, Board board) {
        if(isSequence(piecesPlayed) || isGroup(piecesPlayed)){
            board.addPieces(piecesPlayed);
            pieces.removeAll(piecesPlayed);
            return true;
        }
        return false;
    }


    public boolean isSequence(List<Piece> pieces){
        if(pieces.size() <3){
            return false;
        }
        pieces.sort(Comparator.comparingInt(Piece::getNumber));

        String color = pieces.get(0).getColor();
        for(Piece piece : pieces){
            if(!piece.getColor().equals(color)){
                return false;
            }
        }
        for(int i =0; i < pieces.size(); i++){
            if(pieces.get(i).getNumber() != pieces.get(i -1).getNumber() +1){
                return false;
            }
        }


        return true;
    }

    public boolean isGroup(List<Piece> pieces){
        if(pieces.size() <3){
            return false;
        }
        int number = pieces.get(0).getNumber();
        Set<String> colors = new HashSet<>();
        for(Piece piece : pieces){
            if(piece.getNumber() != number){
                return false;
            }
            colors.add(piece.getColor());
        }
        return colors.size() >= 3;
    }

    public List<Piece> getPieces() {
        return pieces;
    }
}
