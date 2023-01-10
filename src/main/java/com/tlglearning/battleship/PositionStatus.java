package com.tlglearning.battleship;

public enum PositionStatus {
  SHIP('\u2610'),
  WATER('~'),
  HIT('\u2612'),
  MISS('\u2638');

  private final char status;

  PositionStatus(char status) {
    this.status=status;
  }

  public char getStatus() {
    return status;
  }

  public int getRow() {
    return status;
  }

  public int getColumn() {
    return status;
  }
}
