
import java.util.Scanner;

public class TicTacToeEliana{
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}

class Game {
    private Board board;
    private Player player1;
    private Player player2;

    public Game() {
        board = new Board();
        player1 = new Player("Player 1", 'X');
        player2 = new Player("Player 2", 'O');
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            board.resetBoard();
            System.out.println("New game started!");

            Player currentPlayer = player1;
            boolean gameWon = false;

            while (!board.isFull()) {
                board.displayBoard();
                System.out.println(currentPlayer.getName() + "'s turn (" + currentPlayer.getSymbol() + ")");
                int row, col;

                while (true) {
                    System.out.print("Enter row (0-2): ");
                    row = scanner.nextInt();
                    System.out.print("Enter column (0-2): ");
                    col = scanner.nextInt();

                    if (board.isCellEmpty(row, col)) {
                        board.makeMove(row, col, currentPlayer.getSymbol());
                        break;
                    } else {
                        System.out.println("That cell is already taken! Try again.");
                    }
                }

                if (board.checkWinner(currentPlayer.getSymbol())) {
                    board.displayBoard();
                    System.out.println(currentPlayer.getName() + " wins!");
                    gameWon = true;
                    break;
                }

                currentPlayer = (currentPlayer == player1) ? player2 : player1;
            }

            if (!gameWon) {
                board.displayBoard();
                System.out.println("It's a draw!");
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String response = scanner.next();
            if (!response.equalsIgnoreCase("yes")) {
                System.out.println("Thanks for playing!");
                break;
            }
        }

        scanner.close();
    }
}

class Board {
    private char[][] grid;
    private final int SIZE = 3;

    public Board() {
        grid = new char[SIZE][SIZE];
        resetBoard();
    }

    public void resetBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = '-';
            }
        }
    }

    public void displayBoard() {
        System.out.println("  0 1 2");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean isCellEmpty(int row, int col) {
        return grid[row][col] == '-';
    }

    public void makeMove(int row, int col, char symbol) {
        grid[row][col] = symbol;
    }

    public boolean checkWinner(char symbol) {
        for (int i = 0; i < SIZE; i++) {
            if (grid[i][0] == symbol && grid[i][1] == symbol && grid[i][2] == symbol) return true;
            if (grid[0][i] == symbol && grid[1][i] == symbol && grid[2][i] == symbol) return true;
        }


        if (grid[0][0] == symbol && grid[1][1] == symbol && grid[2][2] == symbol) return true;
        if (grid[0][2] == symbol && grid[1][1] == symbol && grid[2][0] == symbol) return true;

        return false;
    }

    public boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == '-') return false;
            }
        }
        return true;
    }
}

class Player {
    private String name;
    private char symbol;

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }
}
