package com.tlglearning.battleship.model;

import com.tlglearning.battleship.model.Ship.Direction;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

public class Player {

  private final String name;
  private final Board playerBoard;
  private ArrayList<Ship> playerShipInventory = new ArrayList<>();
  private PrintStream output;
  private BufferedReader input;
  private boolean rowCoordinateEntryCompleted = false;
  private boolean columnCoordinateEntryCompleted = false;
  private boolean directionSelectionCompleted = false;
  private boolean isComputer = false;

  public Player(String name, Board playerBoard) {
    this.name = name;
    this.playerBoard = playerBoard;
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

  public void decrementShip(ShipType ship){
    Ship indexShip = new Ship(ship, null, null);
    int index = playerShipInventory.indexOf(indexShip);
    Ship decrementShip = playerShipInventory.get(index);
    decrementShip.decrementHealthPoints();
    playerShipInventory.removeIf(shipCheck -> shipCheck.getHealthPoints() == 0);
  };

  public void placePlayerShips() throws IOException {
    for(ShipType ship : ShipType.values()){
      Ship shipPlace = new Ship(null , null, null);
      shipPlace.setShipType(ship);

      int row;
      int column;

      // Handling for row selection input
      do {
        System.out.printf(
            "What row do you want the %s that is %d spaces long, to start on?", ship.name(), ship.getLength());
        String input = null;
        try {
          input = this.input.readLine().strip();
          row = Integer.parseInt(input);
          // TODO: change the >= to just > after we handle user input handling to -1 in the logic to match matrix indexing
          if (row >= 0 && row < getPlayerBoard().length) {
            rowCoordinateEntryCompleted = true;
          }
        } catch (IllegalArgumentException e) {
          System.out.printf("Invalid input: %s%n", input);
        }
      } while (!rowCoordinateEntryCompleted);

      // Handling for direction selection input
      do {
        System.out.printf(
            "Do you want your %s to be Vertical or Horizontal? (V/h)", ship.name());
        String input = null;
        try {
          input = this.input.readLine().strip().toLowerCase();
          if (input.charAt(0) == 'v') {
            shipPlace.setDirection(Direction.VERTICAL);
            directionSelectionCompleted = true;
          }
          else if (input.charAt(0) == 'h'){
            shipPlace.setDirection(Direction.HORIZONTAL);
            directionSelectionCompleted = true;
          }
        } catch (IllegalArgumentException e) {
          System.out.printf("Invalid input: %s%n", input);
        }
      } while (!directionSelectionCompleted);

      // Handling for column selection input
      do {
        System.out.printf(
            "What column do you want the %s that is %d spaces long, to start on?", ship.name(), ship.getLength());
        String input = null;
        try {
          input = this.input.readLine().strip();
          column = Integer.parseInt(input);
          // TODO: change the >= to just > after we handle user input handling to -1 in the logic to match matrix indexing
          if (column >= 0 && column < getPlayerBoard().length) {
            columnCoordinateEntryCompleted = true;
          }
        } catch (IllegalArgumentException e) {
          System.out.printf("Invalid input: %s%n", input);
        }
      } while (!columnCoordinateEntryCompleted);

      // if spaceAvailable method passes, places ship & changes position status to match shipType
      if(
          getPlayerBoard().placementCoordinatesInBoardBoundaries(shipPlace)
              && getPlayerBoard().placementCoordinatesAvailable(shipPlace))
      {
        playerShipInventory.add(shipPlace);
        switch (ship.getLength()) {
          case 1:
            getPlayerBoard().setCharacterAtPosition(PositionStatus.PATROL_BOAT.getStatus(), shipPlace.getPosition());
            break;
          case 2:
            if (shipPlace.getDirection() == Direction.VERTICAL) {
              for(int i=0; i<ship.getLength(); i++){
                Position offset = new Position(shipPlace.getPosition().getRow(),
                    shipPlace.getPosition().getColumn()+i);
                getPlayerBoard().setCharacterAtPosition(PositionStatus.SUBMARINE.getStatus(), offset);
              }
            } else {
              for (int i = 0; i < ship.getLength(); i++) {
                Position offset = new Position(shipPlace.getPosition().getRow() + i,
                    shipPlace.getPosition().getColumn());
                getPlayerBoard().setCharacterAtPosition(PositionStatus.SUBMARINE.getStatus(), offset);
              }
            }
            break;
          case 3:
            if (shipPlace.getDirection() == Direction.VERTICAL) {
              for(int i=0; i<ship.getLength(); i++) {
                Position offset = new Position(shipPlace.getPosition().getRow(),
                    shipPlace.getPosition().getColumn() + i);
                getPlayerBoard().setCharacterAtPosition(PositionStatus.DESTROYER.getStatus(), offset);
              }
            }else{
              for (int i = 0; i < ship.getLength(); i++) {
                Position offset = new Position(shipPlace.getPosition().getRow() + i,
                    shipPlace.getPosition().getColumn());
                getPlayerBoard().setCharacterAtPosition(PositionStatus.DESTROYER.getStatus(), offset);
              }
            }
            break;
          case 4:
            if (shipPlace.getDirection() == Direction.VERTICAL) {
              for(int i=0; i<ship.getLength(); i++) {
                Position offset = new Position(shipPlace.getPosition().getRow(),
                    shipPlace.getPosition().getColumn() + i);
                getPlayerBoard().setCharacterAtPosition(PositionStatus.BATTLESHIP.getStatus(), offset);
              }
            }else{
              for (int i = 0; i < ship.getLength(); i++) {
                Position offset = new Position(shipPlace.getPosition().getRow() + i,
                    shipPlace.getPosition().getColumn());
                getPlayerBoard().setCharacterAtPosition(PositionStatus.BATTLESHIP.getStatus(), offset);
              }
            }
          case 5:
            if (shipPlace.getDirection() == Direction.VERTICAL) {
              for(int i=0; i<ship.getLength(); i++) {
                Position offset = new Position(shipPlace.getPosition().getRow(),
                    shipPlace.getPosition().getColumn() + i);
                getPlayerBoard().setCharacterAtPosition(PositionStatus.CARRIER.getStatus(), offset);
              }
            }else{
              for (int i = 0; i < ship.getLength(); i++) {
                Position offset = new Position(shipPlace.getPosition().getRow() + i,
                    shipPlace.getPosition().getColumn());
                getPlayerBoard().setCharacterAtPosition(PositionStatus.CARRIER.getStatus(), offset);
              }
            }
        }
      }
    }
  }

  public void playerShoots(Position position, Player opponent){
    if(opponent.getPlayerBoard().getCharacterAtPosition(position)==PositionStatus.PATROL_BOAT.getStatus()){
      getPlayerBoard().setCharacterAtPosition(PositionStatus.HIT.getStatus(), position);
      opponent.getPlayerBoard().setCharacterAtPosition(PositionStatus.HIT.getStatus(), position);
      opponent.decrementShip(ShipType.PATROL_BOAT);
      System.out.println("HIT!");
    }
    else if(opponent.getPlayerBoard().getCharacterAtPosition(position)==PositionStatus.SUBMARINE.getStatus()){
      getPlayerBoard().setCharacterAtPosition(PositionStatus.HIT.getStatus(), position);
      opponent.getPlayerBoard().setCharacterAtPosition(PositionStatus.HIT.getStatus(), position);
      opponent.decrementShip(ShipType.SUBMARINE);
      System.out.println("HIT!");
    }
    else if(opponent.getPlayerBoard().getCharacterAtPosition(position)==PositionStatus.DESTROYER.getStatus()){
      getPlayerBoard().setCharacterAtPosition(PositionStatus.HIT.getStatus(), position);
      opponent.getPlayerBoard().setCharacterAtPosition(PositionStatus.HIT.getStatus(), position);
      opponent.decrementShip(ShipType.DESTROYER);
      System.out.println("HIT!");
    }
    else if(opponent.getPlayerBoard().getCharacterAtPosition(position)==PositionStatus.BATTLESHIP.getStatus()){
      getPlayerBoard().setCharacterAtPosition(PositionStatus.HIT.getStatus(), position);
      opponent.getPlayerBoard().setCharacterAtPosition(PositionStatus.HIT.getStatus(), position);
      opponent.decrementShip(ShipType.BATTLESHIP);
      System.out.println("HIT!");
    }
    else if(opponent.getPlayerBoard().getCharacterAtPosition(position)==PositionStatus.CARRIER.getStatus()){
      getPlayerBoard().setCharacterAtPosition(PositionStatus.HIT.getStatus(), position);
      opponent.getPlayerBoard().setCharacterAtPosition(PositionStatus.HIT.getStatus(), position);
      opponent.decrementShip(ShipType.CARRIER);
      System.out.println("HIT!");
    }
    else if(opponent.getPlayerBoard().getCharacterAtPosition(position)==PositionStatus.WATER.getStatus()){
      getPlayerBoard().setCharacterAtPosition(PositionStatus.HIT.getStatus(), position);
      opponent.getPlayerBoard().setCharacterAtPosition(PositionStatus.HIT.getStatus(), position);
      System.out.println("Miss!");
    }
    else if(opponent.getPlayerBoard().getCharacterAtPosition(position)==PositionStatus.MISS.getStatus()
        || opponent.getPlayerBoard().getCharacterAtPosition(position)==PositionStatus.HIT.getStatus()){
      throw new IllegalArgumentException("Cannot fire at a space previously fired upon.");
    }
  }

}
