package models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Game {
private List<Player> players;
private Board board;
private List<Move> moves;
private Player winner;
private GameState gameState;
private List<WinningStrategy> winningStrategies;

    public Game(List<Player> players, Board board, Player winner) {
        this.players = players;
        this.board = board;
        this.winner = winner;
        this.moves = new List<Move>();
        this.gameState = GameState.IN_PROGRESS;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Board getBoard() {
        return board;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public Player getWinner() {
        return winner;
    }

    public GameState getGameState() {
        return gameState;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }
    public static Builder builder()
    {
        return new Builder();
    }
    public static class Builder
    {
        private List<Player> players;
        private List<WiningStrategy> winningStrategies;
        private int dimension;
        private Builder()
        {
            this.players = new ArrayList<Player>();
            this.winningStrategies = new ArrayList<WinningStrategy>();
            this.dimension = 0;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategies(List<WiningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }
        public void addPlayer(Player player)
        {
            players.add(player);
        }
        public void addWinningStrategy(WinningStrategy winningStrategy)
        {
            winningStrategies.add(winningStrategy);
        }
        private void validateBotCounts()
        {
            int botCount = 0;
            for(Player player: players)
            {
                if(player.getPlayerType().equals(PlayerType.BOT))
                {
                    botCount++;
                }
            }
            if(botCount>1)
            {
                throw new InvalidBotCountException("Bot count has exceeded 1");
            }
        }
        private void validateDimension()
        {
            if(dimension<3||dimension>10)
            {
                throw new InvalidDimensionException("Dimension can either be more than 2 or less than 11");
            }
        }
        private void validateNumberofPlayers()
        {
            if(players.size()!=dimension-1)
            {
                throw new InvalidNumbersOfPlayersException("Players should be 1 less than dimension");
            }
        }
        private void validateUniqueSymbolForAllPlayers()
        {
            HashSet<Character> set = new HashSet<>();
            for(Player player: players)
            {
                set.add(player.getSymbol().getSymbolChar());
            }
            if(set.size()!=players.size())
            {
                throw new DuplicateSymbolExcepion("Every plaer should have unique symbol");
            }
        }
        public void validate()
        {
            validateBotCounts();
            validateDimension();
            validateNumberofPlayers();
            validateUniqueSymbolForAllPlayers();

        }
        public Game build()
        {
            validate();
            return new Game(players, new Board(dimension),winningStrategies);
        }
    }

}
