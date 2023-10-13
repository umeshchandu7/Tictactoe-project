import controller.GameController;
import models.*;
import winningstrategies.BotPlayingStrategyFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TicToeGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GameController gameController = new GameController();

        System.out.println("Please enter the dimension of the game");
        int dimension = sc.nextInt();

        System.out.println("Will there be any bot in the game ? Y/N");
        String isBotPresent = sc.next();

        List<Player> players = new ArrayList<>();
        int iteratorNumber = dimension-1;

        if(isBotPresent.equals("Y")){
            iteratorNumber = dimension-2;
        }

        for(int i=0;i<iteratorNumber;i++){
            //TODO : validate if no one passes a duplicate symbol
            System.out.println("What is the name of the player number : " + (i+1));
            String playerName = sc.next();

            System.out.println("What is the character symbol of the player number : " + (i+1));
            String characterSymbol = sc.next();

            players.add(new Player(new Symbol(characterSymbol.charAt(0)), playerName, PlayerType.HUMAN ));
        }

        if(isBotPresent.equals("Y")){
            System.out.println("What is the name of the BOT");
            String botName = sc.next();

            System.out.println("What is the character symbol of the BOT");
            String characterSymbol = sc.next();

            //TODO: take user input for bot difficulty level and create the object accordingly
            BotDifficultyLevel difficultyLevel = BotDifficultyLevel.EASY;

            Bot bot = new Bot(new Symbol(characterSymbol.charAt(0)),
                    botName,
                    difficultyLevel,
                    BotPlayingStrategyFactory.getBotPlayingStrategyForDifficultyLevel(BotDifficultyLevel.EASY));

            players.add(bot);
        }
        // randomizes the players in the list
        Collections.shuffle(players);


        Game game = gameController.createGame(dimension, players);
        int playerIndex = 0;
        //TODO : optimise the while loop and handle exception gracefully
        while(game.getGameState().equals(GameState.IN_PROGRESS)){
            System.out.println("Current board status");
            gameController.displayBoard(game);
            playerIndex++;
            playerIndex = playerIndex % players.size();
            Move movePlayed = gameController.executeMove(game, players.get(playerIndex));
            gameController.displayBoard(game);
            System.out.println("if u want to undo the move enter Y/N");
            String c = sc.next();
            if(c.equals("Y"))
              movePlayed =  gameController.undoTheMove(movePlayed,game);
            Player winner = gameController.checkWinner(game, movePlayed);
            if(winner != null){
                gameController.displayBoard(game);
                System.out.println("Winner is : " + winner.getName());
                break;
            }
        }
    }
}
