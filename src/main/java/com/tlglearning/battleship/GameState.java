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

  public Board playerBoard = new Board(10);

  public void placeShips() throws IOException {
    for(ShipType ship : ShipType.values()){  //for loop to cycle through the enum shipType
      System.out.printf(  //User input that will select the row that the want to use
          "What row do you want to place the %s that is %d spaces long", ship.name(), ship.getLength());
      int row = Integer.parseInt(this.input.readLine().strip());

      System.out.printf(  //User input that will determine if the ship is horizontal or vertical
          "Do you want your %s to be Vertical or Horizontal? (v/h)", ship.name());
      String directionString = this.input.readLine().strip().toLowerCase();
      Direction direction;
      if(directionString.charAt(0)=='v'){ //TODO create a try catch for other character inputs
        direction = Direction.VERTICAL;
      }
      else{
        direction = Direction.HORIZONTAL;
      }

      System.out.printf(  //User input that will determine the column they want to use
          "What column do you want to place the %s that is %d spaces long", ship.name(), ship.getLength());
      int column = Integer.parseInt(this.input.readLine().strip());

      //Creates a new ship with the input data, from
      Ship shipPlace = new Ship(ship, new Position(row,column), direction);
      //tests if that space is available with the board's spaceAvailable method
      if(!playerBoard.spaceAvailable(shipPlace));{
        //TODO logic here needed to catch a bad placement and redirect the user to input new data
      }
//      if(){//TODO logic to determine if the spaces are already taken
//      }
//      else{
//      }
      switch(ship.getLength()){
        case 1:
          shipPlace.getPosition().setStatus(PositionStatus.SHIP);
          break;
        case 2:
          if(shipPlace.getDirection()==Direction.VERTICAL) {
            int y = shipPlace.getPosition().getColumn() + 1;
            new Position(shipPlace.getPosition().getRow(), y).setStatus(PositionStatus.SHIP);
          }
          else{
            int x = shipPlace.getPosition().getRow() + 1;
            new Position(shipPlace.getPosition().getRow(), x).setStatus(PositionStatus.SHIP);
          }
        case 3:
          //TODO will this logic work? if so I can just keep going

      }
    }
  }


  public void playerShoots(Position position){
    if(position.getStatus()==PositionStatus.SHIP){
      position.setStatus(PositionStatus.HIT);
      //TODO logic to take away a hit point from ship that is hit, Truly stumped on this
      //also need to print the board after each turn
      System.out.println("HIT!");
    }
    else if(position.getStatus()==PositionStatus.WATER){
      position.setStatus(PositionStatus.MISS);
      System.out.println("Miss!");
    }
  }

  public void computerShoots(Position position){
    if(position.getStatus()==PositionStatus.SHIP){
      position.setStatus(PositionStatus.HIT);
      //TODO logic to take away a hit point from ship that is hit
      System.out.println("HIT!");
    }
    else if(position.getStatus()==PositionStatus.WATER){
      position.setStatus(PositionStatus.MISS);
      System.out.println("Miss!");
    }
  }




  public void setGameOver(boolean gameOver) {
    this.gameOver = gameOver;
  }
}
