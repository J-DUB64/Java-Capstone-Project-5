package com.tlglearning.battleship;

public enum PositionStatus {
  CARRIER('\u2610'),
  BATTLESHIP('\u2610'),
  DESTROYER('\u2610'),
  SUBMARINE('\u2610'),
  PATROL_BOAT('\u2610'),
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

}
