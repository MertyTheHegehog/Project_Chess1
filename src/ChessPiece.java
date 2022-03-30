public abstract class ChessPiece {
    protected String color;
    boolean check = true;

    public ChessPiece(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    protected boolean notBeyondLimits(int toLine, int toColumn) {
        return toLine < 8 && toLine >= 0 && toColumn < 8 && toColumn >= 0;
    }

    protected boolean notSameTile(int line, int column, int toLine, int toColumn){
        return !(line==toLine&&column==toColumn);
    }

    public boolean isUnderAttack(ChessPiece[][] board, int line, int column){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null){
                    if (board[i][j].canMoveToPosition(board,i,j,line,column)&&
                            !board[i][j].getColor().equals(this.getColor()))
                        return true;
                }
            }
        }
        return false;
    }

    public abstract boolean canMoveToPosition(ChessPiece[][] board, int line,
                                              int column, int toLine, int toColumn);

    public abstract String getSymbol();
}
