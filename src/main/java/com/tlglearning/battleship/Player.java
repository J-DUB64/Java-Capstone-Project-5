package com.tlglearning.battleship;

public class Player {

  private final String name;
  private final Board playerBoard;
  private int shipsRemaining;

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
}
