package com.tlglearning.battleship.controller;

import com.tlglearning.battleship.model.Board;
import com.tlglearning.battleship.model.Player;
import com.tlglearning.battleship.model.Position;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class SessionController {

  private final PrintStream output;
  private final BufferedReader input;

  public SessionController(PrintStream output, BufferedReader input) {
    this.output = output;
    this.input = input;
  }

  public void run() throws IOException {
    String computerName = "Computer";

    do {
      // Create Boards for both players
      Board playerOneBoard_personal = new Board(10);
      Board playerOneBoard_for_trackingOpponent = new Board(10);
      Board computerBoard_personal = new Board(10);
      Board computerBoard_for_trackingOpponent = new Board(10);
      // create instance of player (not-Computer)
      String humanPlayerName = playerNameEntry();
      Player humanPlayer = new Player(humanPlayerName, playerOneBoard_personal, input);
      humanPlayer.setComputer(false);
      // create instance of player (Computer)
      Player computerPlayer = new Player(computerName, computerBoard_personal, input);
      computerPlayer.setComputer(true);
      // place ships
      humanPlayer.placePlayerShips(playerOneBoard_personal);
      humanPlayer.printBoard(playerOneBoard_personal);
      /* ToDo: do we need to add a field in Player 'isComputer'
          and if this field is true, then skip to an else statement that
          contains Math.Random() logic to set ship placement.
       */
      computerPlayer.placePlayerShips(computerBoard_personal);
      // run playerShoots for humanPlayer & take turns until playerShipInventory is empty for a player
      do {
        System.out.printf("%s's TURN TO SHOOT!", humanPlayer.getName());
        humanPlayer.printBoard(playerOneBoard_for_trackingOpponent);
        humanPlayer.playerShoots(playerOneBoard_for_trackingOpponent, computerPlayer, computerBoard_personal);
        humanPlayer.printBoard(playerOneBoard_for_trackingOpponent);
        System.out.println("-------------------------------------");
        if(!computerPlayer.getPlayerShipInventory().isEmpty()) {
          computerPlayer.playerShoots(computerBoard_for_trackingOpponent, humanPlayer,
              playerOneBoard_personal);
          humanPlayer.printBoard(playerOneBoard_personal);
          System.out.println("-------------------------------------");
        }
      } while (!humanPlayer.getPlayerShipInventory().isEmpty() | !computerPlayer.getPlayerShipInventory().isEmpty());

      if (humanPlayer.getPlayerShipInventory().isEmpty()) {
        System.out.print("You have lost to the Computer! Better luck next time, " + humanPlayerName + "! ");
      } else if (computerPlayer.getPlayerShipInventory().isEmpty()){
        System.out.print("Congratulations " + humanPlayerName + ", you've won! ");
      }

    } while (continuePlay());
  }

  private String playerNameEntry() throws IOException {
    System.out.print("Please enter your first name: ");
    String input = this.input
        .readLine()
        .strip()
        .toLowerCase();
    return input;
  }

  private boolean continuePlay() throws IOException {
    System.out.print("Do you want to play again (Y/n)? ");
    String input = this.input
        .readLine()
        .strip()
        .toLowerCase();
    return input.isEmpty() || (input.charAt(0) != 'n');
  }
}
