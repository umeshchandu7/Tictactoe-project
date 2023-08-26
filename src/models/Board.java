package models;

import java.util.ArrayList;

public class Board {
    private int size;
    private ArrayList<ArrayList<Cell>> board;
    public Board(int size)
    {
        this.size = size;
        this.board = new ArrayList<>();
    for(int i=0;i<size;i++)
    {
        this.getBoard().add(new ArrayList<>());
        for(int j=0;j<size;j++)
        {
            this.getBoard().get(i).add(new Cell(i,j));
        }
    }
}
public void printBoard()
{
    for(int i=0;i<size;i++)
    {
        ArrayList<Cell> row = board.get(i);
        for(int j=0;j<size;j++)
        {
            row.get(j).display();
        }
        System.out.println();
    }
}

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ArrayList<ArrayList<Cell>> getBoard() {
        return board;
    }

    public void setBoard(ArrayList<ArrayList<Cell>> board) {
        this.board = board;
    }
}
