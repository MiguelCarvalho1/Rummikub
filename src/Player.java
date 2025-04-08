import java.util.*;

public class Player {
    private String name;
    private List<Piece> pieces;

    public Player(String name) {
        this.name = name;
        this.pieces = new ArrayList<>();
    }

    public void addPiece(Piece piece) {
        pieces.add(piece);
    }

    public boolean isGroup(List<Piece> group) {
        if (group.size() < 3) return false;

        int number = -1;
        Set<String> colors = new HashSet<>();

        for (Piece piece : group) {
            if (piece.isJoker()) continue;
            if (number == -1) number = piece.getNumber();
            if (piece.getNumber() != number) return false;
            if (!colors.add(piece.getColor())) return false;
        }
        return colors.size() + (int) group.stream().filter(Piece::isJoker).count() >= 3;
    }

    public boolean isSequence(List<Piece> sequence) {
        if (sequence.size() < 3) return false;

        List<Piece> sorted = new ArrayList<>(sequence);
        sorted.removeIf(Piece::isJoker);
        sorted.sort(Comparator.comparingInt(Piece::getNumber));

        String color = sorted.isEmpty() ? null : sorted.get(0).getColor();
        for (Piece p : sorted) {
            if (!p.getColor().equals(color)) return false;
        }

        int jokers = (int) sequence.stream().filter(Piece::isJoker).count();

        for (int i = 1; i < sorted.size(); i++) {
            int diff = sorted.get(i).getNumber() - sorted.get(i - 1).getNumber();
            if (diff == 1) continue;
            jokers -= (diff - 1);
            if (jokers < 0) return false;
        }
        return true;
    }

    public List<List<Piece>> findValidInitialPlays() {
        List<List<Piece>> validPlays = new ArrayList<>();

        for (int i = 3; i <= pieces.size(); i++) {
            List<List<Piece>> comb = generateCombinations(pieces, i);
            for (List<Piece> combo : comb) {
                int sum = combo.stream().mapToInt(Piece::getNumber).sum();
                if ((isGroup(combo) || isSequence(combo)) && sum >= 30) {
                    validPlays.add(combo);
                }
            }
        }
        return validPlays;
    }

    private List<List<Piece>> generateCombinations(List<Piece> list, int size) {
        List<List<Piece>> result = new ArrayList<>();
        generateCombinationsHelper(list, size, 0, new ArrayList<>(), result);
        return result;
    }

    private void generateCombinationsHelper(List<Piece> list, int size, int index, List<Piece> current, List<List<Piece>> result) {
        if (current.size() == size) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = index; i < list.size(); i++) {
            current.add(list.get(i));
            generateCombinationsHelper(list, size, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }

    public String getName() {
        return name;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public boolean isValidPlay(List<Piece> play) {
        return isGroup(play) || isSequence(play);
    }
}

