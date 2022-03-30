public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8]; // creating a field for game
    String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (checkPos(startLine) && checkPos(startColumn) &&
                nowPlayer.equals(board[startLine][startColumn].getColor()) &&
                (board[endLine][endColumn]==null||!board[endLine][endColumn].getColor().equals(nowPlayer))&&
                board[startLine][startColumn].canMoveToPosition(board,
                        startLine, startColumn, endLine, endColumn)&&
                !(board[startLine][startColumn].getSymbol().equals("K")
                        &&new King(nowPlayer).isUnderAttack(board,endLine,endColumn))) {
            if (board[startLine][startColumn].getSymbol().equals("K") ||  // check position for castling
                    board[startLine][startColumn].getSymbol().equals("R")) {
                board[startLine][startColumn].check = false;
            }

            board[endLine][endColumn] = board[startLine][startColumn]; // if piece can move, we moved a piece
            board[startLine][startColumn] = null; // set null to previous cell
            nowPlayer = nowPlayer.equals("White") ? "Black" : "White";

            return true;
        }
        return false;
    }

    public void printBoard() {  //print board in console
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");
        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }

    public boolean castling0() {//Исходный код. Не менял
        if (nowPlayer.equals("White")) {
            if (board[0][0] == null || board[0][4] == null) return false;
            if (board[0][0].getSymbol().equals("R") && board[0][4].getSymbol().equals("K") && // check that King and Rook
                    board[0][1] == null && board[0][2] == null && board[0][3] == null) {              // never moved
                if (board[0][0].getColor().equals("White") && board[0][4].getColor().equals("White") &&
                        board[0][0].check && board[0][4].check &&
                        !new King("White").isUnderAttack(board, 0, 2)) { // check that position not in under attack
                    board[0][4] = null;
                    board[0][2] = new King("White");   // move King
                    board[0][2].check = false;
                    board[0][0] = null;
                    board[0][3] = new Rook("White");   // move Rook
                    board[0][3].check = false;
                    nowPlayer = "Black";  // next turn
                    return true;
                } else return false;
            } else return false;
        } else {
            if (board[7][0] == null || board[7][4] == null) return false;
            if (board[7][0].getSymbol().equals("R") && board[7][4].getSymbol().equals("K") && // check that King and Rook
                    board[7][1] == null && board[7][2] == null && board[7][3] == null) {              // never moved
                if (board[7][0].getColor().equals("Black") && board[7][4].getColor().equals("Black") &&
                        board[7][0].check && board[7][4].check &&
                        !new King("Black").isUnderAttack(board, 7, 2)) { // check that position not in under attack
                    board[7][4] = null;
                    board[7][2] = new King("Black");   // move King
                    board[7][2].check = false;
                    board[7][0] = null;
                    board[7][3] = new Rook("Black");   // move Rook
                    board[7][3].check = false;
                    nowPlayer = "White";  // next turn
                    return true;
                } else return false;
            } else return false;
        }
    }

    public boolean castling7() {
        return castling7IsPossible(nowPlayer.equals("White") ? 0 : 7);
    }

    private boolean castling7IsPossible(int line) {
        if (board[line][4] != null && board[line][7] != null &&
                board[line][7].getSymbol().equals("R") && board[line][7].getColor().equals(nowPlayer) &&
                board[line][4].getSymbol().equals("K") && board[line][4].getColor().equals(nowPlayer) &&
                board[line][4].check && board[line][7].check &&
                board[line][5] == null && board[line][6] == null &&
                !new King(nowPlayer).isUnderAttack(board, line, 6)) {
            board[line][7] = board[line][4] = null;
            board[line][5] = new Rook(nowPlayer);
            board[line][6] = new King(nowPlayer);
            board[line][6].check = board[line][5].check = false;
            nowPlayer = nowPlayer.equals("White") ? "Black" : "White";
            return true;
        }
        return false;
    }
}
