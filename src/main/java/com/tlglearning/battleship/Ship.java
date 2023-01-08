package com.tlglearning.battleship;

public class Ship {

    // Private variables to store the length, position, and direction of the ship
    private int length;
    private Position position;
    private Direction direction;

    // Enum to represent the possible directions that a ship can be placed on the board
    public enum Direction {
      HORIZONTAL, VERTICAL
    }

    // Constructor to initialize the length, position, and direction of the ship
    public Ship(int length, Position position, Direction direction) {
      this.length = length;
      this.position = position;
      this.direction = direction;
    }

    // Public methods to allow other parts of the program to access the length, position, and direction of the ship
    public int getLength() {
      return length;
    }

    public Position getPosition() {
      return position;
    }

    public Direction getDirection() {
      return direction;
    }

    // Other methods for the Ship class
  }

