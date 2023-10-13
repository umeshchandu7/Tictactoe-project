package models;

import winningstrategies.BotPlayingStrategy;

import java.util.Scanner;

public class Bot extends Player{
    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public Bot(Symbol symbol, String name, BotDifficultyLevel botDifficultyLevel, BotPlayingStrategy botPlayingStrategy) {
        super(symbol, name, PlayerType.BOT);
        this.botDifficultyLevel = botDifficultyLevel;
        this.botPlayingStrategy = botPlayingStrategy;
    }
    public Move makeMove(Board board)
    {
        Move move = botPlayingStrategy.makeMove(this,board);
        return move;
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficutLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
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

    public BotPlayingStrategy getBotPlayingStrategy() {
        return botPlayingStrategy;
    }

    public void setBotPlayingStrategy(BotPlayingStrategy botPlayingStrategy) {
        this.botPlayingStrategy = botPlayingStrategy;
    }
}
