package com.tlglearning.battleship;

import com.tlglearning.battleship.Ship.Direction;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class GameState {
  private boolean gameOver;
  private final PrintStream output;
  private final BufferedReader input;

  public GameState(PrintStream output, BufferedReader input){
    this.input=input;
    this.output=output;
  }

  public Board playerBoard = new Board(10); //temporarily using 10 to get to MVP
  public Board playerGameBoard = new Board(10);
  public Board computerBoard = new Board(10);

  public void placePlayerShips() throws IOException {
    for(ShipType ship : ShipType.values()){  //for loop to cycle through the enum shipType
      System.out.printf(  //User input that will select the row that the want to use
          "What row do you want the %s that is %d spaces long, to start on?", ship.name(), ship.getLength());
      int row = Integer.parseInt(this.input.readLine().strip());

      System.out.printf(  //User input that will determine if the ship is horizontal or vertical
          "Do you want your %s to be Vertical or Horizontal? (V/h)", ship.name());
      String directionString = this.input.readLine().strip().toLowerCase();
      Direction direction;
      if(directionString.charAt(0)=='v'){ //TODO create a try catch for other character inputs
        direction = Direction.VERTICAL;
      }
      else{
        direction = Direction.HORIZONTAL;
      }

      System.out.printf(  //User input that will determine the column they want to use
          "What column do you want the %s that is %d spaces long to end on?", ship.name(), ship.getLength());
      int column = Integer.parseInt(this.input.readLine().strip());

      //Creates a new ship with the input data, from
      Ship shipPlace = new Ship(ship, new Position(row,column), direction);
      //tests if that space is available with the board's spaceAvailable method
      if(playerBoard.spaceAvailable(shipPlace));
      {
        //adding the ship the board by changing the position status of the positions
        switch (ship.getLength()) {
          case 1:
            playerBoard.set(PositionStatus.PATROL_BOAT.getStatus(), shipPlace.getPosition());
            break;
          case 2:
            if (shipPlace.getDirection() == Direction.VERTICAL) {
              int columnOffset = shipPlace.getPosition().getColumn() + 1;
              Position offset = new Position(shipPlace.getPosition().getRow(), columnOffset);
              playerBoard.set(PositionStatus.SUBMARINE.getStatus(), shipPlace.getPosition());
              playerBoard.set(PositionStatus.SUBMARINE.getStatus(), offset);
            } else {
              int rowOffset = shipPlace.getPosition().getRow() + 1;
              Position offset = new Position(shipPlace.getPosition().getRow(), rowOffset);
              playerBoard.set(PositionStatus.SUBMARINE.getStatus(), shipPlace.getPosition());
              playerBoard.set(PositionStatus.SUBMARINE.getStatus(), offset);
            }
            break;
          case 3:
            if (shipPlace.getDirection() == Direction.VERTICAL) {
              int columnOffset = shipPlace.getPosition().getColumn() + 1;
              Position offset = new Position(shipPlace.getPosition().getRow(), columnOffset);
              Position offset2 = new Position(shipPlace.getPosition().getRow(), columnOffset+1);
              playerBoard.set(PositionStatus.DESTROYER.getStatus(), shipPlace.getPosition());
              playerBoard.set(PositionStatus.DESTROYER.getStatus(), offset);
              playerBoard.set(PositionStatus.DESTROYER.getStatus(), offset2);
            }else{
              int rowOffset = shipPlace.getPosition().getRow() + 1;
              Position offset = new Position(shipPlace.getPosition().getRow(), rowOffset);
              Position offset2 = new Position(shipPlace.getPosition().getRow(), rowOffset+1);
              playerBoard.set(PositionStatus.DESTROYER.getStatus(), shipPlace.getPosition());
              playerBoard.set(PositionStatus.DESTROYER.getStatus(), offset);
              playerBoard.set(PositionStatus.DESTROYER.getStatus(), offset2);
            }
            break;
          case 4:
            if (shipPlace.getDirection() == Direction.VERTICAL) {
              int columnOffset = shipPlace.getPosition().getColumn() + 1;
              Position offset = new Position(shipPlace.getPosition().getRow(), columnOffset);
              Position offset2 = new Position(shipPlace.getPosition().getRow(), columnOffset+1);
              Position offset3 = new Position(shipPlace.getPosition().getRow(), columnOffset+2);
              playerBoard.set(PositionStatus.BATTLESHIP.getStatus(), shipPlace.getPosition());
              playerBoard.set(PositionStatus.BATTLESHIP.getStatus(), offset);
              playerBoard.set(PositionStatus.BATTLESHIP.getStatus(), offset2);
              playerBoard.set(PositionStatus.BATTLESHIP.getStatus(), offset3);
            }else{
              int rowOffset = shipPlace.getPosition().getRow() + 1;
              Position offset = new Position(shipPlace.getPosition().getRow(), rowOffset);
              Position offset2 = new Position(shipPlace.getPosition().getRow(), rowOffset+1);
              Position offset3 = new Position(shipPlace.getPosition().getRow(), rowOffset+2);
              playerBoard.set(PositionStatus.BATTLESHIP.getStatus(), shipPlace.getPosition());
              playerBoard.set(PositionStatus.BATTLESHIP.getStatus(), offset);
              playerBoard.set(PositionStatus.BATTLESHIP.getStatus(), offset2);
              playerBoard.set(PositionStatus.BATTLESHIP.getStatus(), offset3);
            }
          case 5:
            if (shipPlace.getDirection() == Direction.VERTICAL) {
              int columnOffset = shipPlace.getPosition().getColumn() + 1;
              Position offset = new Position(shipPlace.getPosition().getRow(), columnOffset);
              Position offset2 = new Position(shipPlace.getPosition().getRow(), columnOffset+1);
              Position offset3 = new Position(shipPlace.getPosition().getRow(), columnOffset+2);
              Position offset4 = new Position(shipPlace.getPosition().getRow(), columnOffset+3);
              playerBoard.set(PositionStatus.CARRIER.getStatus(), shipPlace.getPosition());
              playerBoard.set(PositionStatus.CARRIER.getStatus(), offset);
              playerBoard.set(PositionStatus.CARRIER.getStatus(), offset2);
              playerBoard.set(PositionStatus.CARRIER.getStatus(), offset3);
              playerBoard.set(PositionStatus.CARRIER.getStatus(), offset4);
            }else{
              int rowOffset = shipPlace.getPosition().getRow() + 1;
              Position offset = new Position(shipPlace.getPosition().getRow(), rowOffset);
              Position offset2 = new Position(shipPlace.getPosition().getRow(), rowOffset+1);
              Position offset3 = new Position(shipPlace.getPosition().getRow(), rowOffset+2);
              Position offset4 = new Position(shipPlace.getPosition().getRow(), rowOffset+3);
              playerBoard.set(PositionStatus.CARRIER.getStatus(), shipPlace.getPosition());
              playerBoard.set(PositionStatus.CARRIER.getStatus(), offset);
              playerBoard.set(PositionStatus.CARRIER.getStatus(), offset2);
              playerBoard.set(PositionStatus.CARRIER.getStatus(), offset3);
              playerBoard.set(PositionStatus.CARRIER.getStatus(), offset4);
            }

        }
      }
    }
  }


  public void playerShoots(Position position){
    if(computerBoard.at(position)==PositionStatus.PATROL_BOAT.getStatus()){
      playerBoard.set(PositionStatus.HIT.getStatus(), position);
      computerBoard.set(PositionStatus.HIT.getStatus(), position);
      //TODO logic to take away a hit point from ship that is hit, Truly stumped on this
      //also need to print the board after each turn
      System.out.println("HIT!");
    }
    else if(computerBoard.at(position)==PositionStatus.SUBMARINE.getStatus()){
      playerBoard.set(PositionStatus.HIT.getStatus(), position);
      computerBoard.set(PositionStatus.HIT.getStatus(), position);
      //TODO logic to take away a hit point from ship that is hit, Truly stumped on this
      //also need to print the board after each turn
      System.out.println("HIT!");
    }
    else if(computerBoard.at(position)==PositionStatus.DESTROYER.getStatus()){
      playerBoard.set(PositionStatus.HIT.getStatus(), position);
      computerBoard.set(PositionStatus.HIT.getStatus(), position);
      //TODO logic to take away a hit point from ship that is hit, Truly stumped on this
      //also need to print the board after each turn
      System.out.println("HIT!");
    }
    else if(computerBoard.at(position)==PositionStatus.BATTLESHIP.getStatus()){
      playerBoard.set(PositionStatus.HIT.getStatus(), position);
      computerBoard.set(PositionStatus.HIT.getStatus(), position);
      //TODO logic to take away a hit point from ship that is hit, Truly stumped on this
      //also need to print the board after each turn
      System.out.println("HIT!");
    }
    else if(computerBoard.at(position)==PositionStatus.CARRIER.getStatus()){
      playerBoard.set(PositionStatus.HIT.getStatus(), position);
      computerBoard.set(PositionStatus.HIT.getStatus(), position);
      //TODO logic to take away a hit point from ship that is hit, Truly stumped on this
      //also need to print the board after each turn
      System.out.println("HIT!");
    }
    else if(computerBoard.at(position)==PositionStatus.WATER.getStatus()){
      playerBoard.set(PositionStatus.MISS.getStatus(), position);
      computerBoard.set(PositionStatus.MISS.getStatus(), position);
      System.out.println("Miss!");
    }
    else if(computerBoard.at(position)==PositionStatus.MISS.getStatus()
        || computerBoard.at(position)==PositionStatus.HIT.getStatus()){
      throw new IllegalArgumentException("Cannot fire at a space previously fired upon.");
    }

  }

  public void computerShoots(Position position){
    //TODO copy over logic from playerShoots
  }




  public void setGameOver(boolean gameOver) {
    this.gameOver = gameOver;
  }
}
