package com.tlglearning.battleship;

  public class Position {  // Private variables to store the row and column coordinates of the position
    private final int row;
    private final int column;

    public Position(int row, int column) {  //// Constructor to initialize the row and column coordinates of the position
      this.row = row;
      this.column = column;
    }

    // Public methods to allow other parts of the program to access the row and column coordinates of the position
    public int getRow() {
      return row;
    }

    public int getColumn() {
      return column;
    }

    Position myPosition = new Position(10, 10);
  }

