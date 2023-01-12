package com.tlglearning.battleship;

import com.sun.source.doctree.HiddenTree;

public class Position {

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
      int column) {
    this.row = row;
    this.column = column;
    this.status = PositionStatus.WATER;
  }


  public int getRow() {
    return row;
  }

  public int getColumn() {
    return column;
  }



  public char at(Position position) {
    return position.status.getStatus();

  }
}

