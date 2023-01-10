package com.tlglearning.battleship;

import com.sun.source.doctree.HiddenTree;

public class Position {  // Private variables to store the row and column coordinates of the position

  private final int row;
  private final int column;
  private PositionStatus status;

  public PositionStatus getStatus() {
    return status;
  }

  public void setStatus(PositionStatus status) {
    this.status = status;
  }

  public Position(int row,
      int column) {  // Constructor to initialize the row and column coordinates of the position
    this.row = row;
    this.column = column;
    this.status = PositionStatus.WATER; // Initialize the status field
  }

  // Public methods to allow other parts of the program to access the row and column coordinates of the position
  public int getRow() {
    return row;
  }

  public int getColumn() {
    return column;
  }


  // Modify the at method to return the status field of the position object
  public char at(Position position) {
    return position.status.getStatus();

  }
}

