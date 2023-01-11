package com.tlglearning.battleship;

public class Ship {


  // Private variables to store the length, position, and direction of the ship
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


    // Public methods to allow other parts of the program to access the length, position, and direction of the ship


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

  public void setHealthPoints(int healthPoints) {
    this.healthPoints = healthPoints;
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




