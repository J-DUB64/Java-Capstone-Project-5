package com.tlglearning.battleship;

public enum ShipType {
  CARRIER(5),
  BATTLESHIP(4),
  DESTROYER(3),
  SUBMARINE(2),
  PATROL_BOAT(1);

  private final int length;

  ShipType(int length) {
    this.length=length;
  }

  public int getLength() {
    return length;
  }
}
