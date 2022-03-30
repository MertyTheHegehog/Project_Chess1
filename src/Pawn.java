public class Pawn extends ChessPiece {

    public Pawn(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessPiece[][] board, int line, int column, int toLine, int toColumn) {
        if (notBeyondLimits(toLine, toColumn)) {
            if (board[toLine][toColumn] == null){
                if (column == toColumn) {
                    int difference = toLine - line;
                    if (color.equals("White")) {
                        return difference == 1 || (line == 1 && toLine == 3);
                    } else
                        return difference == -1 || (line == 6 && toLine == 4);
                }
            }else
                return canAttackPosition(board, line,column, toLine, toColumn);
        }
        return false;
    }

    private boolean canAttackPosition(ChessPiece[][] board, int line,
                                     int column, int toLine, int toColumn) {
        int difLine = toLine - line, difColumn = toColumn - column;
        return !board[toLine][toColumn].getColor().equals(this.color) &&
                (difLine == difColumn || difLine == -difColumn) &&
                (this.color.equals("White") && difLine == 1 || this.color.equals("Black") && difLine == -1);
    }

    @Override
    public String getSymbol() {
        return "P";
    }
}
