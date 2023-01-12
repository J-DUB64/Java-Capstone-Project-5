package com.tlglearning.battleship.model;

public class Ship {

  // fields
    private ShipType shipType;
    private Position position;
    private Direction direction;
    private int healthPoints;

  public Ship(ShipType shipType, Position position, Direction direction) {
    this.shipType = shipType;
    this.position = position;
    this.direction = direction;
    this.healthPoints = shipType.getLength();
  }

  // getters & setters
    public ShipType getShipType() {
    return shipType;
  }

  public int getLength() {
    return shipType.getLength();
  }

  public Position getPosition() {
      return position;
    }

  public Direction getDirection() {
    return direction;
  }

  public void setShipType(ShipType shipType) {
    this.shipType = shipType;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  public void setDirection(Direction direction) {
    this.direction = direction;
  }

  public void decrementHealthPoints() {
    this.healthPoints = healthPoints-1;
  }

  public int getHealthPoints() {
    return healthPoints;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Ship ship = (Ship) obj;
    return shipType == ship.shipType;
  }

  // Other methods for the Ship class

    // Enum to represent the possible directions that a ship can be placed on the board
    public enum Direction {
      HORIZONTAL(0, 1),
      VERTICAL(1, 0);

      private final int rowOffset;
      private final int columnOffset;

      Direction(int rowOffset, int columnOffset) {
        this.rowOffset = rowOffset;
        this.columnOffset = columnOffset;
      }

      public int getRowOffset() {
        return rowOffset;
      }

      public int getColumnOffset() {
        return columnOffset;
      }

    }
}




