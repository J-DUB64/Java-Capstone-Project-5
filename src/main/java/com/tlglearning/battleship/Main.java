package com.tlglearning.battleship;

import com.tlglearning.battleship.controller.SessionController;
import com.tlglearning.battleship.model.Board;
import com.tlglearning.battleship.model.Player;
import com.tlglearning.battleship.model.Position;
import com.tlglearning.battleship.model.PositionStatus;
import com.tlglearning.battleship.model.Ship;
import com.tlglearning.battleship.model.ShipType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) {
    try {
      new SessionController(
         System.out,
         new BufferedReader(new InputStreamReader(System.in)))
          .run();
    } catch (IOException e) {
      System.err.println("Unable to read user input; unable to continue.");
    }
  }
}
