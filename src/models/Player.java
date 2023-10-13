package models;

import java.util.Scanner;

public class Player {
    private static int idCounter = 0;
    private int id;
    private Symbol symbol;
    private  String name;
    private PlayerType playerType;
    private Scanner scanner;

    public Player( Symbol symbol, String name, PlayerType playerType) {

        this.id = idCounter++;
        this.symbol = symbol;
        this.name = name;
        this.playerType = playerType;
        this.scanner = new Scanner(System.in);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public Move makeMove(Board board)
   {
       System.out.println(this.getName() + ", please enter the row for the move");
       int row = scanner.nextInt();
       System.out.println(this.getName()+ ", please enter the coloumn for the move");
       int col = scanner.nextInt();
   //

   //
       board.getBoard().get(row).get(col).setPlayer(this);
       board.getBoard().get(row).get(col).setCellState(CellState.FILLED);
       return new  Move(new Cell(row,col,this),this);
   }
public Move undoMove(Board board,Move move)
{
    Cell cell = move.getCell();
    int r = cell.getRow();
    int c = cell.getCol();
    board.getBoard().get(r).get(c).setPlayer(null);
    board.getBoard().get(r).get(c).setCellState(CellState.EMPTY);
    return makeMove(board);
}
}