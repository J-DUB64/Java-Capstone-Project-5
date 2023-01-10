package com.tlglearning.battleship;

import java.util.ArrayList;

public class Player {

  private final String name;
  private final Board playerBoard;
  private int shipsRemaining;
  private ArrayList<Ship> playerShipInventory = new ArrayList<>();

  public Player(String name, Board playerBoard) {
    this.name = name;
    this.playerBoard = playerBoard;
    this.shipsRemaining = playerBoard.getNumShips();
  }

  public String getName() {
    return name;
  }

  public Board getPlayerBoard() {
    return playerBoard;
  }

  public int getShipsRemaining() {
    return shipsRemaining;
  }

  public ArrayList<Ship> getPlayerShipInventory() {
    return playerShipInventory;
  }
}
