package com.tlglearning.battleship;

public class Ship {

    // Private variables to store the length, position, and direction of the ship
    private final ShipType shipType;
    private final Position position;
    private final Direction direction;

  // Enum to represent the possible directions that a ship can be placed on the board
    public enum Direction {
      HORIZONTAL, VERTICAL
    }

    // Constructor to initialize the length, position, and direction of the ship
    public Ship(ShipType shipType, Position position, Direction direction) {
      this.shipType = shipType;
      this.position = position;
      this.direction = direction;
    }

    // Public methods to allow other parts of the program to access the length, position, and direction of the ship

    public ShipType getShipType() {
    return shipType;
  }

    public Position getPosition() {
      return position;
    }

    public Direction getDirection() {
      return direction;
    }

    // Other methods for the Ship class
  }

