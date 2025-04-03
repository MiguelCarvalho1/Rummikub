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
        return color + " " + number;
    }
}
