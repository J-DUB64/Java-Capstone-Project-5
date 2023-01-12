package com.tlglearning.battleship.model;

public enum PositionStatus {
  CARRIER('C'),
  BATTLESHIP('B'),
  DESTROYER('D'),
  SUBMARINE('S'),
  PATROL_BOAT('P'),
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
