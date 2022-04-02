public class King extends ChessPiece {
    public King(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessPiece[][] board, int line, int column, int toLine, int toColumn) {
        if (conditionsArePassed(board, line, column, toLine, toColumn)) {
            if (this.isUnderAttack(board, toLine, toColumn)) return false;
            this.check = false;
            int difColumns = toColumn - column, difLines = toLine - line;
            if (difColumns == 1 && (difLines == -1 || difLines == 0 || difLines == 1) ||//right
                    difColumns == -1 && (difLines == -1 || difLines == 0 || difLines == 1) ||//left
                    difColumns == 0 && (difLines == -1 || difLines == 1)){ //middle
                this.check = false;
                return true;
            }
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "K";
    }
}
