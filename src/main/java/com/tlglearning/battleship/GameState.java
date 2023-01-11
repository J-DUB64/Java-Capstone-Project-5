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

  public void placePlayerShips(Player player) throws IOException {
    for(ShipType ship : ShipType.values()){  //for loop to cycle through the enum shipType
      Ship shipPlace = new Ship(null , null, null);

      shipPlace.setShipType(ship);

      System.out.printf(  //User input that will select the row that the want to use
          "What row do you want the %s that is %d spaces long, to start on?", ship.name(), ship.getLength());

      int row = Integer.parseInt(this.input.readLine().strip());

      System.out.printf(  //User input that will determine if the ship is horizontal or vertical
          "Do you want your %s to be Vertical or Horizontal? (V/h)", ship.name());
      String directionString = this.input.readLine().strip().toLowerCase();

      if(directionString.charAt(0)=='v'){ //TODO create a try catch for other character inputs
        shipPlace.setDirection(Direction.VERTICAL);
      }
      else{
        shipPlace.setDirection(Direction.HORIZONTAL);
      }

      System.out.printf(  //User input that will determine the column they want to use
          "What column do you want the %s that is %d spaces long to end on?", ship.name(), ship.getLength());
      int column = Integer.parseInt(this.input.readLine().strip());

      shipPlace.setPosition(new Position(row, column));

      //tests if that space is available with the board's spaceAvailable method
      if(player.getPlayerBoard().spaceAvailable(shipPlace))
      {
        //adding the ship the board by changing the position status of the positions
        switch (ship.getLength()) {
          case 1:
            player.getPlayerBoard().set(PositionStatus.PATROL_BOAT.getStatus(), shipPlace.getPosition());
            break;
          case 2:
            if (shipPlace.getDirection() == Direction.VERTICAL) {
              int columnOffset = shipPlace.getPosition().getColumn() + 1;
              Position offset = new Position(shipPlace.getPosition().getRow(), columnOffset);
              player.getPlayerBoard().set(PositionStatus.SUBMARINE.getStatus(), shipPlace.getPosition());
              player.getPlayerBoard().set(PositionStatus.SUBMARINE.getStatus(), offset);
            } else {
              int rowOffset = shipPlace.getPosition().getRow() + 1;
              Position offset = new Position(shipPlace.getPosition().getRow(), rowOffset);
              player.getPlayerBoard().set(PositionStatus.SUBMARINE.getStatus(), shipPlace.getPosition());
              player.getPlayerBoard().set(PositionStatus.SUBMARINE.getStatus(), offset);
            }
            break;
          case 3:
            if (shipPlace.getDirection() == Direction.VERTICAL) {
              int columnOffset = shipPlace.getPosition().getColumn() + 1;
              Position offset = new Position(shipPlace.getPosition().getRow(), columnOffset);
              Position offset2 = new Position(shipPlace.getPosition().getRow(), columnOffset+1);
              player.getPlayerBoard().set(PositionStatus.DESTROYER.getStatus(), shipPlace.getPosition());
              player.getPlayerBoard().set(PositionStatus.DESTROYER.getStatus(), offset);
              player.getPlayerBoard().set(PositionStatus.DESTROYER.getStatus(), offset2);
            }else{
              int rowOffset = shipPlace.getPosition().getRow() + 1;
              Position offset = new Position(shipPlace.getPosition().getRow(), rowOffset);
              Position offset2 = new Position(shipPlace.getPosition().getRow(), rowOffset+1);
              player.getPlayerBoard().set(PositionStatus.DESTROYER.getStatus(), shipPlace.getPosition());
              player.getPlayerBoard().set(PositionStatus.DESTROYER.getStatus(), offset);
              player.getPlayerBoard().set(PositionStatus.DESTROYER.getStatus(), offset2);
            }
            break;
          case 4:
            if (shipPlace.getDirection() == Direction.VERTICAL) {
              int columnOffset = shipPlace.getPosition().getColumn() + 1;
              Position offset = new Position(shipPlace.getPosition().getRow(), columnOffset);
              Position offset2 = new Position(shipPlace.getPosition().getRow(), columnOffset+1);
              Position offset3 = new Position(shipPlace.getPosition().getRow(), columnOffset+2);
              player.getPlayerBoard().set(PositionStatus.BATTLESHIP.getStatus(), shipPlace.getPosition());
              player.getPlayerBoard().set(PositionStatus.BATTLESHIP.getStatus(), offset);
              player.getPlayerBoard().set(PositionStatus.BATTLESHIP.getStatus(), offset2);
              player.getPlayerBoard().set(PositionStatus.BATTLESHIP.getStatus(), offset3);
            }else{
              int rowOffset = shipPlace.getPosition().getRow() + 1;
              Position offset = new Position(shipPlace.getPosition().getRow(), rowOffset);
              Position offset2 = new Position(shipPlace.getPosition().getRow(), rowOffset+1);
              Position offset3 = new Position(shipPlace.getPosition().getRow(), rowOffset+2);
              player.getPlayerBoard().set(PositionStatus.BATTLESHIP.getStatus(), shipPlace.getPosition());
              player.getPlayerBoard().set(PositionStatus.BATTLESHIP.getStatus(), offset);
              player.getPlayerBoard().set(PositionStatus.BATTLESHIP.getStatus(), offset2);
              player.getPlayerBoard().set(PositionStatus.BATTLESHIP.getStatus(), offset3);
            }
          case 5:
            if (shipPlace.getDirection() == Direction.VERTICAL) {
              int columnOffset = shipPlace.getPosition().getColumn() + 1;
              Position offset = new Position(shipPlace.getPosition().getRow(), columnOffset);
              Position offset2 = new Position(shipPlace.getPosition().getRow(), columnOffset+1);
              Position offset3 = new Position(shipPlace.getPosition().getRow(), columnOffset+2);
              Position offset4 = new Position(shipPlace.getPosition().getRow(), columnOffset+3);
              player.getPlayerBoard().set(PositionStatus.CARRIER.getStatus(), shipPlace.getPosition());
              player.getPlayerBoard().set(PositionStatus.CARRIER.getStatus(), offset);
              player.getPlayerBoard().set(PositionStatus.CARRIER.getStatus(), offset2);
              player.getPlayerBoard().set(PositionStatus.CARRIER.getStatus(), offset3);
              player.getPlayerBoard().set(PositionStatus.CARRIER.getStatus(), offset4);
            }else{
              int rowOffset = shipPlace.getPosition().getRow() + 1;
              Position offset = new Position(shipPlace.getPosition().getRow(), rowOffset);
              Position offset2 = new Position(shipPlace.getPosition().getRow(), rowOffset+1);
              Position offset3 = new Position(shipPlace.getPosition().getRow(), rowOffset+2);
              Position offset4 = new Position(shipPlace.getPosition().getRow(), rowOffset+3);
              player.getPlayerBoard().set(PositionStatus.CARRIER.getStatus(), shipPlace.getPosition());
              player.getPlayerBoard().set(PositionStatus.CARRIER.getStatus(), offset);
              player.getPlayerBoard().set(PositionStatus.CARRIER.getStatus(), offset2);
              player.getPlayerBoard().set(PositionStatus.CARRIER.getStatus(), offset3);
              player.getPlayerBoard().set(PositionStatus.CARRIER.getStatus(), offset4);
            }

        }

      }
    }
  }


//  public void playerShoots(Position position){
//    if(computerBoard.get(position)==PositionStatus.PATROL_BOAT.getStatus()){
//      playerBoard.set(PositionStatus.HIT.getStatus(), position);
//      computerBoard.set(PositionStatus.HIT.getStatus(), position);
//      //TODO logic to take away a hit point from ship that is hit, Truly stumped on this
//      //also need to print the board after each turn
//      System.out.println("HIT!");
//    }
//    else if(computerBoard.get(position)==PositionStatus.SUBMARINE.getStatus()){
//      playerBoard.set(PositionStatus.HIT.getStatus(), position);
//      computerBoard.set(PositionStatus.HIT.getStatus(), position);
//      //TODO logic to take away a hit point from ship that is hit, Truly stumped on this
//      //also need to print the board after each turn
//      System.out.println("HIT!");
//    }
//    else if(computerBoard.get(position)==PositionStatus.DESTROYER.getStatus()){
//      playerBoard.set(PositionStatus.HIT.getStatus(), position);
//      computerBoard.set(PositionStatus.HIT.getStatus(), position);
//      //TODO logic to take away a hit point from ship that is hit, Truly stumped on this
//      //also need to print the board after each turn
//      System.out.println("HIT!");
//    }
//    else if(computerBoard.get(position)==PositionStatus.BATTLESHIP.getStatus()){
//      playerBoard.set(PositionStatus.HIT.getStatus(), position);
//      computerBoard.set(PositionStatus.HIT.getStatus(), position);
//      //TODO logic to take away a hit point from ship that is hit, Truly stumped on this
//      //also need to print the board after each turn
//      System.out.println("HIT!");
//    }
//    else if(computerBoard.get(position)==PositionStatus.CARRIER.getStatus()){
//      playerBoard.set(PositionStatus.HIT.getStatus(), position);
//      computerBoard.set(PositionStatus.HIT.getStatus(), position);
//      //TODO logic to take away a hit point from ship that is hit, Truly stumped on this
//      //also need to print the board after each turn
//      System.out.println("HIT!");
//    }
//    else if(computerBoard.get(position)==PositionStatus.WATER.getStatus()){
//      playerBoard.set(PositionStatus.MISS.getStatus(), position);
//      computerBoard.set(PositionStatus.MISS.getStatus(), position);
//      System.out.println("Miss!");
//    }
//    else if(computerBoard.get(position)==PositionStatus.MISS.getStatus()
//        || computerBoard.get(position)==PositionStatus.HIT.getStatus()){
//      throw new IllegalArgumentException("Cannot fire at a space previously fired upon.");
//    }
//
//  }

  public void computerShoots(Position position){
    //TODO copy over logic from playerShoots
  }




  public void setGameOver(boolean gameOver) {
    this.gameOver = gameOver;
  }
}
