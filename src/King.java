public class King extends ChessPiece{
    public King(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessPiece[][] board, int line, int column, int toLine, int toColumn) {
        if (notBeyondLimits(toLine, toColumn)) {
            int difColumns = toColumn - column, difLines = toLine - line;
            return difColumns == 1 && (difLines == -1 || difLines == 0 || difLines == 1) ||//right
                    difColumns == -1 && (difLines == -1 || difLines == 0 || difLines == 1) ||//left
                    difColumns == 0 && (difLines == -1 || difLines == 1);//middle
        }
            return false;
    }

    @Override
    public String getSymbol() {
        return "K";
    }
}
