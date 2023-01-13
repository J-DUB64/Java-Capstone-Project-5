package com.tlglearning.battleship.model;

import java.util.Arrays;


public class Board {

  public final int length;
  public char[][] board;
  public int numShips = 0;

  // Constructors
  public Board(
      int length) {
    this.length = length;
    board = initBoard();
  }

  public Board(
      char[][] matrix) {
    this.length = matrix.length;
    board = matrix;
  }

  // methods
  public int getLength() {
    return length;
  }


  public int getNumShips() {
    return numShips;
  }

  public char[][] getBoard() {
    return board;
  }

  public char getCharacterAtPosition(
      Position position) {
    return board[position.getRow()][position.getColumn()];
  }

  public boolean setCharacterAtPosition(char status,
      Position position) {
    board[position.getRow()][position.getColumn()] = status;
    return true;
  }

  public char[][] initBoard() {
    char[][] matrix = new char[length][length];
    for (char[] row : matrix) {
      Arrays.fill(row,
          PositionStatus.WATER.getStatus());
    }
    return matrix;
  }

  public boolean placementCoordinatesInBoardBoundaries(Ship ship) {
    int shipLength = ship.getLength();
    int row = ship.getPosition().getRow();
    int column = ship.getPosition().getColumn();
    for (int i = 0; i < shipLength; i++) {
      int checkRow = row + i * ship.getDirection().getRowOffset();
      int checkColumn = column + i * ship.getDirection().getColumnOffset();
      boolean coordinatesOutOfBounds = (checkRow < 0 || checkRow >= board.length || checkColumn < 0
          || checkColumn >= board[0].length);
      if (coordinatesOutOfBounds) {
        return false;
      }
    }
    return true;
  }

  public boolean placementCoordinatesAvailable(Ship ship) {
    int shipLength = ship.getLength();
    int row = ship.getPosition().getRow();
    int column = ship.getPosition().getColumn();
    for (int i = 0; i < shipLength; i++) {
      int checkRow = row + i * ship.getDirection().getRowOffset();
      int checkColumn = column + i * ship.getDirection().getColumnOffset();
      boolean positionStatusNotWater =  board[checkRow][checkColumn] != PositionStatus.WATER.getStatus();
      if (positionStatusNotWater) {
        return false;
      }
    }
    return true;
  }
}
