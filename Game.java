import java.util.Scanner;

public class Game {
        // Two private variable: the board (string array) and the turn meter (integer)
        private String[][] board;
        private int turn;

        // Create a new board, set every entry to ".", and set the turn meter to 0
        public Game() {
                this.board = new String[6][7];
                for (int row = 0; row < 6; row++) {
                        for (int col = 0; col < 7; col++) {
                                this.board[row][col] = ".";
                        }
                }
                this.turn = 0;
        }

        public void playGame() {
                Scanner reader = new Scanner(System.in);
                System.out.print("Enter column number (1-7), or 0 to quit: ");
                int move = reader.nextInt();

                while (move != 0) {
                        // Check for an illegal move (illegal column number of full column)
                        if (detectIllegalColumnNumber(move)) {
                                System.out.println("Illegal column");
                        }
                        else if (detectFullColumn(move)) {
                                System.out.println("Column is full");
                        }
                        // If the move is not illegal, do it
                        else {
                                placePiece(move);
                        }
                        printBoard();
                        // Check for game-ending condition (win or draw) and print output message
                        if (detectRedWin()) {
                                System.out.println("Game over: red wins");
                                break;
                        }
                        else if (detectYellowWin()) {
                                System.out.println("Game over: yellow wins");
                                break;
                        }
                        else if (detectDraw()) {
                                System.out.println("Game over: draw");
                                break;
                        }
                        else {
                                System.out.print("Enter column number (1-7), or 0 to quit: ");
                                move = reader.nextInt();
                        }
                }

                // Check game-ending reason for output message
                if (move == 0) {
                        printBoard();
                        System.out.println("Game over: user quit");
                }
        }

        // Display the board
        public void printBoard() {
                System.out.println();
                for (int row = 0; row < 6; row++) {
                        for (int col = 0; col < 7; col++) {
                                System.out.print(this.board[row][col] + " ");
                        }
                        System.out.println();
                }
        }

        // Place the piece according the the user input (variable "move" in playGame method)
        public void placePiece(int usermove) {
                for (int row = 5; row >= 0; row--) {
                        // Check for empty entry
                        if (this.board[row][usermove-1].equals(".")) {
                                // Check whose turn it is
                                if (this.turn%2 == 0) {
                                        this.board[row][usermove-1] = "Y";
                                }
                                else {
                                        this.board[row][usermove-1] = "R";
                                }
                                break;
                        }
                }
                // Update turn meter
                this.turn ++;
        }
                
        // Detect an illegal column number --> returns true if detected
        public boolean detectIllegalColumnNumber(int usermove) {
                if (usermove < 0 || usermove > 7) {
                        return true;
                }
                else {
                        return false;
                }
        }

        // Detect a full column --> returns true if detected
        public boolean detectFullColumn(int usermove) {
                if (!this.board[0][usermove-1].equals(".")) {
                        return true;
                }
                else {
                        return false;
                }
        }

        // Detect Yellow win:
        // 1. 4 horizontal pieces
        public boolean detectYellowHorizontal() {
                for (int row = 0; row < 6; row++) {
                        for (int col = 0; col < 4; col++) {
                                if (this.board[row][col].equals("Y") && this.board[row][col+1].equals("Y") && this.board[row][col+2].equals("Y") && this.board[row][col+3].equals("Y")) {
                                        return true;
                                }
                        }
                }
                return false;
        }

        // 2. 4 vertical pieces
        public boolean detectYellowVertical() {
                for (int col = 0; col < 7; col++) {
                        for (int row = 0; row < 3; row++) {
                                if (this.board[row][col].equals("Y") && this.board[row+1][col].equals("Y") && this.board[row+2][col].equals("Y") && this.board[row+3][col].equals("Y")) {
                                        return true;
                                }
                        }
                }
                return false;
        }

        // 3. 4 diagonal pieces
        public boolean detectYellowDiagonal() {
                for (int col = 0; col < 4; col ++) {
                        // 3. 1. Backwards diagonal (\)
                        for (int row = 0; row < 3; row++) {
                                if (this.board[row][col].equals("Y") && this.board[row+1][col+1].equals("Y") && this.board[row+2][col+2].equals("Y") && this.board[row+3][col+3].equals("Y")) {
                                        return true;
                                }
                        }
                        // 3. 2. Forwards diagonal (/)
                        for (int row = 5; row > 2; row--) {
                                if (this.board[row][col].equals("Y") && this.board[row-1][col+1].equals("Y") && this.board[row-2][col+2].equals("Y") && this.board[row-3][col+3].equals("Y")) {
                                        return true;
                                }
                        }
                }
                return false;
        }

        // Detect Red win:
        // 1. 4 horizontal pieces
        public boolean detectRedHorizontal() {
                for (int row = 0; row < 6; row++) {
                        for (int col = 0; col < 4; col++) {
                                if (this.board[row][col].equals("R") && this.board[row][col+1].equals("R") && this.board[row][col+2].equals("R") && this.board[row][col+3].equals("R")) {
                                        return true;
                                }
                        }
                }
                return false;
        }

        // 2. 4 vertical pieces
        public boolean detectRedVertical() {
                for (int col = 0; col < 7; col++) {
                        for (int row = 0; row < 3; row++) {
                                if (this.board[row][col].equals("R") && this.board[row+1][col].equals("R") && this.board[row+2][col].equals("R") && this.board[row+3][col].equals("R")) {
                                        return true;
                                }
                        }
                }
                return false;
        }

        // 3. 4 diagonal pieces
        public boolean detectRedDiagonal() {
                for (int col = 0; col < 4; col ++) {
                        // 3. 1. Backwards diagonal (\)
                        for (int row = 0; row < 3; row++) {
                                if (this.board[row][col].equals("R") && this.board[row+1][col+1].equals("R") && this.board[row+2][col+2].equals("R") && this.board[row+3][col+3].equals("R")) {
                                        return true;
                                }
                        }
                        // 3. 2. Forwards diagonal (/)
                        for (int row = 5; row > 2; row--) {
                                if (this.board[row][col].equals("R") && this.board[row-1][col+1].equals("R") && this.board[row-2][col+2].equals("R") && this.board[row-3][col+3].equals("R")) {
                                        return true;
                                }
                        }
                }
                return false;
        }

        // Overall detect win methods (use previous methods)
        public boolean detectYellowWin() {
                if (detectYellowHorizontal() || detectYellowVertical() || detectYellowDiagonal()) {
                        return true;
                }
                else {
                        return false;
                }
        }

        public boolean detectRedWin() {
                if (detectRedHorizontal() || detectRedVertical() || detectRedDiagonal()) {
                        return true;
                }
                else {
                        return false;
                }
        }

        // Detect draw method
        public boolean detectDraw() {
                if (this.turn == 42) {
                        return true;
                }
                else {
                        return false;
                }
        }
}