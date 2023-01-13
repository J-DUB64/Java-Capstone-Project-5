package com.tlglearning.battleship.model;

import com.tlglearning.battleship.model.Ship.Direction;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;

public class Player {

  private final String name;
  private final Board playerBoard;
  private final ArrayList<String> verticalVersusHorizontal;
  private ArrayList<Ship> playerShipInventory;
  private PrintStream output;
  private BufferedReader input;
  private boolean rowCoordinateEntryCompleted = false;
  private boolean columnCoordinateEntryCompleted = false;
  private boolean directionSelectionCompleted = false;
  private boolean isComputer;

  public Player(String name, Board playerBoard, BufferedReader input) {
    this.name = name;
    this.playerBoard = playerBoard;
    this.input = input;
    playerShipInventory = new ArrayList<>();
    playerShipInventory.add(new Ship(ShipType.PATROL_BOAT, null, null));
    playerShipInventory.add(new Ship(ShipType.SUBMARINE, null, null));
    playerShipInventory.add(new Ship(ShipType.DESTROYER, null, null));
    playerShipInventory.add(new Ship(ShipType.BATTLESHIP, null, null));
    playerShipInventory.add(new Ship(ShipType.CARRIER, null, null));
    verticalVersusHorizontal = new ArrayList<>();
    verticalVersusHorizontal.add("h");
    verticalVersusHorizontal.add("v");
  }


  public String getName() {
    return name;
  }

  public Board getPlayerBoard() {
    return playerBoard;
  }

  public ArrayList<Ship> getPlayerShipInventory() {
    return playerShipInventory;
  }

  public boolean isComputer() {
    return isComputer;
  }

  public void setComputer(boolean computer) {
    isComputer = computer;
  }

  public void decrementShip(ShipType ship) {
    Ship indexShip = new Ship(ship, null, null);
    int index = playerShipInventory.indexOf(indexShip);
    Ship decrementShip = playerShipInventory.get(index);
    decrementShip.decrementHealthPoints();
    playerShipInventory.removeIf(shipCheck -> shipCheck.getHealthPoints() == 0);
  }


  private String rowCoordinateEntry() throws IOException {
    return this.input
        .readLine()
        .strip()
        .toLowerCase();
  }

  private String columnCoordinateEntry() throws IOException {
    return this.input
        .readLine()
        .strip()
        .toLowerCase();
  }

  private String directionEntry() throws IOException {
    return this.input
        .readLine()
        .strip()
        .toLowerCase();
  }

  public void placePlayerShips(Board playerBoard) throws IOException {
    ArrayList<Ship> penaltyShips = new ArrayList<>();
    for (Ship ship : playerShipInventory) {
      int row = 0;
      int column = 0;
      String input = null;
      Random rand = new Random();
      if (!isComputer) {
        // Handling for row selection input
        printBoard(playerBoard);
        do {
          System.out.printf(
              "What row do you want the %s that is %d spaces long, to start on? (0-9): ",
              ship.getShipType(), ship.getLength());
          try {
            input = rowCoordinateEntry();
            row = Integer.parseInt(input);
            if (row >= 0 && row < playerBoard.length) {
              rowCoordinateEntryCompleted = true;
            }
          } catch (IllegalArgumentException e) {
            System.out.printf("Invalid input: %s%n", input);
          }
        } while (!rowCoordinateEntryCompleted);

        // Handling for direction selection input
        do {
          System.out.printf(
              "Do you want your %s to be Vertical or Horizontal? (V/h): ", ship.getShipType());
          try {
            input = directionEntry();
            if (input.charAt(0) == 'v') {
              ship.setDirection(Direction.VERTICAL);
              directionSelectionCompleted = true;
            } else if (input.charAt(0) == 'h') {
              ship.setDirection(Direction.HORIZONTAL);
              directionSelectionCompleted = true;
            } else{
              System.out.printf("Invalid input: %s%n", input);
              directionSelectionCompleted = false;
            }
          } catch (IllegalArgumentException e) {
            System.out.printf("Invalid input: %s%n", input);
          }
        } while (!directionSelectionCompleted);

        // Handling for column selection input
        do {
          System.out.printf(
              "What column do you want the %s that is %d spaces long, to start on? (0-9): ",
              ship.getShipType(), ship.getLength());
          try {
            input = columnCoordinateEntry();
            column = Integer.parseInt(input);
            if (column >= 0 && column < playerBoard.length) {
              columnCoordinateEntryCompleted = true;
            }
          } catch (IllegalArgumentException e) {
            System.out.printf("Invalid input: %s%n", input);
          }
        } while (!columnCoordinateEntryCompleted);

        Position placement = new Position(row, column);
        ship.setPosition(placement);

        // if spaceAvailable method passes, places ship & changes position status to match shipType
        if (
            playerBoard.placementCoordinatesInBoardBoundaries(ship)
                && playerBoard.placementCoordinatesAvailable(ship)) {
          switch (ship.getLength()) {
            case 1:
              playerBoard.setCharacterAtPosition(PositionStatus.PATROL_BOAT.getStatus(),
                  ship.getPosition());
              break;
            case 2:
              if (ship.getDirection() == Direction.HORIZONTAL) {
                for (int i = 0; i < ship.getLength(); i++) {
                  Position offset = new Position(ship.getPosition().getRow(),
                      ship.getPosition().getColumn() + i);
                  playerBoard.setCharacterAtPosition(PositionStatus.SUBMARINE.getStatus(), offset);
                }
              } else {
                for (int i = 0; i < ship.getLength(); i++) {
                  Position offset = new Position(ship.getPosition().getRow() + i,
                      ship.getPosition().getColumn());
                  playerBoard.setCharacterAtPosition(PositionStatus.SUBMARINE.getStatus(), offset);
                }
              }
              break;
            case 3:
              if (ship.getDirection() == Direction.HORIZONTAL) {
                for (int i = 0; i < ship.getLength(); i++) {
                  Position offset = new Position(ship.getPosition().getRow(),
                      ship.getPosition().getColumn() + i);
                  playerBoard.setCharacterAtPosition(PositionStatus.DESTROYER.getStatus(), offset);
                }
              } else {
                for (int i = 0; i < ship.getLength(); i++) {
                  Position offset = new Position(ship.getPosition().getRow() + i,
                      ship.getPosition().getColumn());
                  playerBoard.setCharacterAtPosition(PositionStatus.DESTROYER.getStatus(), offset);
                }
              }
              break;
            case 4:
              if (ship.getDirection() == Direction.HORIZONTAL) {
                for (int i = 0; i < ship.getLength(); i++) {
                  Position offset = new Position(ship.getPosition().getRow(),
                      ship.getPosition().getColumn() + i);
                  playerBoard.setCharacterAtPosition(PositionStatus.BATTLESHIP.getStatus(), offset);
                }
              } else {
                for (int i = 0; i < ship.getLength(); i++) {
                  Position offset = new Position(ship.getPosition().getRow() + i,
                      ship.getPosition().getColumn());
                  playerBoard.setCharacterAtPosition(PositionStatus.BATTLESHIP.getStatus(), offset);
                }
              }
              break;
            case 5:
              if (ship.getDirection() == Direction.HORIZONTAL) {
                for (int i = 0; i < ship.getLength(); i++) {
                  Position offset = new Position(ship.getPosition().getRow(),
                      ship.getPosition().getColumn() + i);
                  playerBoard.setCharacterAtPosition(PositionStatus.CARRIER.getStatus(), offset);
                }
              } else {
                for (int i = 0; i < ship.getLength(); i++) {
                  Position offset = new Position(ship.getPosition().getRow() + i,
                      ship.getPosition().getColumn());
                  playerBoard.setCharacterAtPosition(PositionStatus.CARRIER.getStatus(), offset);
                }
              }
          }
        } else {
          System.out.printf(
              "Penalty! You cannot place a ship out of bounds or on another ship. Your %s has been lost at sea!",
              ship.getShipType());
          penaltyShips.add(ship);
        }
        // THIS IS WHERE THE HUMAN PLAYER ENDS
      } else {
        // COMPUTER DATA -- START
        // Handling for row selection input
        do {
          try {
            row = rand.nextInt(10);
            if (row < playerBoard.length) {
              rowCoordinateEntryCompleted = true;
            }
          } catch (IllegalArgumentException e) {
            System.out.printf("Invalid input: %s%n", input);
          }
        } while (!rowCoordinateEntryCompleted);

        // Handling for direction selection input
        do {
          try {
            input = getRandomDirectionString(verticalVersusHorizontal);
            if (input.charAt(0) == 'v') {
              ship.setDirection(Direction.VERTICAL);
              directionSelectionCompleted = true;
            } else if (input.charAt(0) == 'h') {
              ship.setDirection(Direction.HORIZONTAL);
              directionSelectionCompleted = true;
            }
          } catch (IllegalArgumentException e) {
            System.out.printf("Invalid input: %s%n", input);
          }
        } while (!directionSelectionCompleted);

        // Handling for column selection input
        do {
          try {
            column = rand.nextInt(10);
            if (column < playerBoard.length) {
              columnCoordinateEntryCompleted = true;
            }
          } catch (IllegalArgumentException e) {
            System.out.printf("Invalid input: %s%n", input);
          }
        } while (!columnCoordinateEntryCompleted);

        Position placement = new Position(row, column);
        ship.setPosition(placement);

        // if spaceAvailable method passes, places ship & changes position status to match shipType
        if (
            playerBoard.placementCoordinatesInBoardBoundaries(ship)
                && playerBoard.placementCoordinatesAvailable(ship)) {
          switch (ship.getLength()) {
            case 1:
              playerBoard.setCharacterAtPosition(PositionStatus.PATROL_BOAT.getStatus(),
                  ship.getPosition());
              break;
            case 2:
              if (ship.getDirection() == Direction.HORIZONTAL) {
                for (int i = 0; i < ship.getLength(); i++) {
                  Position offset = new Position(ship.getPosition().getRow(),
                      ship.getPosition().getColumn() + i);
                  playerBoard.setCharacterAtPosition(PositionStatus.SUBMARINE.getStatus(), offset);
                }
              } else {
                for (int i = 0; i < ship.getLength(); i++) {
                  Position offset = new Position(ship.getPosition().getRow() + i,
                      ship.getPosition().getColumn());
                  playerBoard.setCharacterAtPosition(PositionStatus.SUBMARINE.getStatus(), offset);
                }
              }
              break;
            case 3:
              if (ship.getDirection() == Direction.HORIZONTAL) {
                for (int i = 0; i < ship.getLength(); i++) {
                  Position offset = new Position(ship.getPosition().getRow(),
                      ship.getPosition().getColumn() + i);
                  playerBoard.setCharacterAtPosition(PositionStatus.DESTROYER.getStatus(), offset);
                }
              } else {
                for (int i = 0; i < ship.getLength(); i++) {
                  Position offset = new Position(ship.getPosition().getRow() + i,
                      ship.getPosition().getColumn());
                  playerBoard.setCharacterAtPosition(PositionStatus.DESTROYER.getStatus(), offset);
                }
              }
              break;
            case 4:
              if (ship.getDirection() == Direction.HORIZONTAL) {
                for (int i = 0; i < ship.getLength(); i++) {
                  Position offset = new Position(ship.getPosition().getRow(),
                      ship.getPosition().getColumn() + i);
                  playerBoard.setCharacterAtPosition(PositionStatus.BATTLESHIP.getStatus(), offset);
                }
              } else {
                for (int i = 0; i < ship.getLength(); i++) {
                  Position offset = new Position(ship.getPosition().getRow() + i,
                      ship.getPosition().getColumn());
                  playerBoard.setCharacterAtPosition(PositionStatus.BATTLESHIP.getStatus(), offset);
                }
              }
              break;
            case 5:
              if (ship.getDirection() == Direction.HORIZONTAL) {
                for (int i = 0; i < ship.getLength(); i++) {
                  Position offset = new Position(ship.getPosition().getRow(),
                      ship.getPosition().getColumn() + i);
                  playerBoard.setCharacterAtPosition(PositionStatus.CARRIER.getStatus(), offset);
                }
              } else {
                for (int i = 0; i < ship.getLength(); i++) {
                  Position offset = new Position(ship.getPosition().getRow() + i,
                      ship.getPosition().getColumn());
                  playerBoard.setCharacterAtPosition(PositionStatus.CARRIER.getStatus(), offset);
                }
              }
          }
        } else {
          penaltyShips.add(ship);
        }
      }
    }
    for (Ship penaltyShip : penaltyShips) {
      getPlayerShipInventory().remove(penaltyShip);
    }
  }

  public void playerShoots(Board playerBoard, Player opponent, Board opponentBoard) {
    int row = 0;
    int column = 0;
    boolean rowCoordinateEntryCompleted = false;
    boolean columnCoordinateEntryCompleted = false;
    String input = null;
    Random rand = new Random();

    // Handling for row selection input
    if (!isComputer) {
      do {
        System.out.print(
            "What row do you want attack? 0-9: ");
        try {
          input = this.input.readLine().strip();
          row = Integer.parseInt(input);
          if (row >= 0 && row < getPlayerBoard().length) {
            rowCoordinateEntryCompleted = true;
          }
        } catch (IllegalArgumentException | IOException e) {
          System.out.printf("Invalid input: %s%n", input);
        }
      } while (!rowCoordinateEntryCompleted);

      // Handling for column selection input
      do {
        System.out.print(
            "What column do you want attack? 0-9: ");
        try {
          input = this.input.readLine().strip();
          column = Integer.parseInt(input);
          if (column >= 0 && column < getPlayerBoard().length) {
            columnCoordinateEntryCompleted = true;
          }
        } catch (IllegalArgumentException | IOException e) {
          System.out.printf("Invalid input: %s%n", input);
        }
      } while (!columnCoordinateEntryCompleted);

      Position position = new Position(row, column);

      if (opponentBoard.getCharacterAtPosition(position) == PositionStatus.PATROL_BOAT.getStatus()) {
        playerBoard.setCharacterAtPosition(PositionStatus.HIT.getStatus(), position);
        opponentBoard.setCharacterAtPosition(PositionStatus.HIT.getStatus(), position);
        opponent.decrementShip(ShipType.PATROL_BOAT);
        System.out.println("HIT!");
      } else if (opponentBoard.getCharacterAtPosition(position)
          == PositionStatus.SUBMARINE.getStatus()) {
        playerBoard.setCharacterAtPosition(PositionStatus.HIT.getStatus(), position);
        opponentBoard.setCharacterAtPosition(PositionStatus.HIT.getStatus(), position);
        opponent.decrementShip(ShipType.SUBMARINE);
        System.out.println("HIT!");
      } else if (opponentBoard.getCharacterAtPosition(position)
          == PositionStatus.DESTROYER.getStatus()) {
        playerBoard.setCharacterAtPosition(PositionStatus.HIT.getStatus(), position);
        opponentBoard.setCharacterAtPosition(PositionStatus.HIT.getStatus(), position);
        opponent.decrementShip(ShipType.DESTROYER);
        System.out.println("HIT!");
      } else if (opponentBoard.getCharacterAtPosition(position)
          == PositionStatus.BATTLESHIP.getStatus()) {
        playerBoard.setCharacterAtPosition(PositionStatus.HIT.getStatus(), position);
        opponentBoard.setCharacterAtPosition(PositionStatus.HIT.getStatus(), position);
        opponent.decrementShip(ShipType.BATTLESHIP);
        System.out.println("HIT!");
      } else if (opponentBoard.getCharacterAtPosition(position)
          == PositionStatus.CARRIER.getStatus()) {
        playerBoard.setCharacterAtPosition(PositionStatus.HIT.getStatus(), position);
        opponentBoard.setCharacterAtPosition(PositionStatus.HIT.getStatus(), position);
        opponent.decrementShip(ShipType.CARRIER);
        System.out.println("HIT!");
      } else if (opponentBoard.getCharacterAtPosition(position) == PositionStatus.WATER.getStatus()) {
        playerBoard.setCharacterAtPosition(PositionStatus.MISS.getStatus(), position);
        opponentBoard.setCharacterAtPosition(PositionStatus.MISS.getStatus(), position);
        System.out.println("MISS!");
      } else if (opponentBoard.getCharacterAtPosition(position) == PositionStatus.MISS.getStatus()
          || opponentBoard.getCharacterAtPosition(position) == PositionStatus.HIT.getStatus()) {
        System.out.println("Cannot fire at a space previously fired upon.");
      }
    } else {
      // COMPUTER-DATA SHOOTING LOGIC
      do {
        try {
          row = rand.nextInt(10);
          if (row < getPlayerBoard().length) {
            rowCoordinateEntryCompleted = true;
          }
        } catch (IllegalArgumentException e) {
          System.out.printf("Invalid input: %s%n", input);
        }
      } while (!rowCoordinateEntryCompleted);

      // Handling for column selection input
      do {
        try {
          column = rand.nextInt(10);
          if (column < getPlayerBoard().length) {
            columnCoordinateEntryCompleted = true;
          }
        } catch (IllegalArgumentException e) {
          System.out.printf("Invalid input: %s%n", input);
        }
      } while (!columnCoordinateEntryCompleted);

      Position position = new Position(row, column);

      if (opponentBoard.getCharacterAtPosition(position)
          == PositionStatus.PATROL_BOAT.getStatus()) {
        playerBoard.setCharacterAtPosition(PositionStatus.HIT.getStatus(), position);
        opponentBoard.setCharacterAtPosition(PositionStatus.HIT.getStatus(), position);
        opponent.decrementShip(ShipType.PATROL_BOAT);
        System.out.println("HIT!");
      } else if (opponentBoard.getCharacterAtPosition(position)
          == PositionStatus.SUBMARINE.getStatus()) {
        playerBoard.setCharacterAtPosition(PositionStatus.HIT.getStatus(), position);
        opponentBoard.setCharacterAtPosition(PositionStatus.HIT.getStatus(), position);
        opponent.decrementShip(ShipType.SUBMARINE);
        System.out.println("HIT!");
      } else if (opponentBoard.getCharacterAtPosition(position)
          == PositionStatus.DESTROYER.getStatus()) {
        playerBoard.setCharacterAtPosition(PositionStatus.HIT.getStatus(), position);
        opponentBoard.setCharacterAtPosition(PositionStatus.HIT.getStatus(), position);
        opponent.decrementShip(ShipType.DESTROYER);
        System.out.println("HIT!");
      } else if (opponentBoard.getCharacterAtPosition(position)
          == PositionStatus.BATTLESHIP.getStatus()) {
        playerBoard.setCharacterAtPosition(PositionStatus.HIT.getStatus(), position);
        opponentBoard.setCharacterAtPosition(PositionStatus.HIT.getStatus(), position);
        opponent.decrementShip(ShipType.BATTLESHIP);
        System.out.println("HIT!");
      } else if (opponentBoard.getCharacterAtPosition(position)
          == PositionStatus.CARRIER.getStatus()) {
        playerBoard.setCharacterAtPosition(PositionStatus.HIT.getStatus(), position);
        opponentBoard.setCharacterAtPosition(PositionStatus.HIT.getStatus(), position);
        opponent.decrementShip(ShipType.CARRIER);
        System.out.println("HIT!");
      } else if (opponentBoard.getCharacterAtPosition(position)
          == PositionStatus.WATER.getStatus()) {
        playerBoard.setCharacterAtPosition(PositionStatus.MISS.getStatus(), position);
        opponentBoard.setCharacterAtPosition(PositionStatus.MISS.getStatus(), position);
        System.out.println("MISS!");
      } else if (opponentBoard.getCharacterAtPosition(position) == PositionStatus.MISS.getStatus()
          || opponentBoard.getCharacterAtPosition(position) == PositionStatus.HIT.getStatus()) {
        System.out.println("Cannot fire at a space previously fired upon.");
      }
    }
  }

  public void printBoard(Board board) {
    try {
      System.out.println("\n");
      int length = board.length;
      StringBuilder str = new StringBuilder("|\t");
      System.out.println("    0 1 2 3 4 5 6 7 8 9");
      for (int i = 0; i < length; i++) {
        for (int j = 0; j < length; j++) {
          str.append(board.getCharacterAtPosition(new Position(i, j))).append("\t");
        }
        System.out.println(i + str.toString() + "|" + i);
        str = new StringBuilder("|\t");
      }
      System.out.println("    0 1 2 3 4 5 6 7 8 9");
    } catch (Exception e) {
      System.out.println("Empty Board being passed in!");
    }
  }

  public String getRandomDirectionString(ArrayList<String> directionStringList) {
    double randomIndex = Math.floor(Math.random() * directionStringList.size());
    return directionStringList.get((int) randomIndex);
  }

}
