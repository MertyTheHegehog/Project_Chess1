public class Queen extends ChessPiece implements WalksTheLines {
    public Queen(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessPiece[][] board, int line, int column, int toLine, int toColumn) {
        if (notBeyondLimits(toLine, toColumn) && notSameTile(line, column, toLine, toColumn) &&
                noOneOnTheLine(board, line, column, toLine, toColumn)) {
            return toLine - line == toColumn - column || toLine - line == column - toColumn ||
                    line == toLine || column == toColumn;
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "Q";
    }
}
