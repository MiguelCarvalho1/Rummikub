import java.util.Objects;

public class Piece {
    private int number;
    private String color;

    public Piece(int number, String color) {
        this.number = number;
        this.color = color;
    }

    public int getNumber() {
        return number;
    }

    public String getColor() {
        return color;
    }

    public boolean isJoker() {
        return number == 0 && color.equals("joker");
    }

    @Override
    public String toString() {
        return isJoker() ? "JOKER" : color + " " + number;
    }
}
