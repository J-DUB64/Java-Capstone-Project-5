package com.tlglearning.battleship;

import com.tlglearning.battleship.Ship.Direction;
import java.util.Arrays;
import javax.swing.text.Position;

public class Board {

  private final int length; //one variable for rows = columns = 10 [10x10 matrix]
  private char[][] board; // 2 Dimensional array called board
  private int numShips = 0; //counter for the number of ships

  // Constructors
  public Board(
      int length) {  //public access modifier that defines the method named board by  specifying an int method named length method named board.
    this.length = length; //this. refers to the current instance of the class and assigns the value of the input parameter "length" to a field variable within the class.
    board = initBoard();  // assigns the value returned by the initBoard() method to the board field.
  }

  public Board(
      char[][] matrix) { //public access modifier for the method Board that has a method signature char[][] which specifies the input parameters that the method expects named matrix.
    this.length = matrix.length; // assigns the length of the matrix array to a field (variable) within the class. "this" keyword refers to the class that is currently being operated in.
    board = matrix; //assigns the value of the input parameter "matrix" to the board class.
  }

  //Methods
  private char[][] initBoard() { //private access modifier that defines a return type method (char[][]) named initBoard().
    char[][] matrix = new char[length][length]; //declares a new two-dimensional array of characters called matrix which is initializes it specified dimensions through the length field
    for (char[] row : matrix) { //for loop that iterates over each row of the matrix array. char[]row will declare a new variable called row that will hold the current row of the array for each iteration of the for loop.
      Arrays.fill(row, PositionStatus.WATER.getStatus()); // fills the current row of the matrix with water constant.
    }
    return matrix; // returns the matrix array to the caller of he method.
  }

  public int getLength() { // method getLength() that gets an int value for the length
    return length; // return the value of length
  }

  public int getNumShips() { //method getNumShips() that gets an int value for the number of Ships
    return numShips; // return the number of ships value.
  }

  public char[][] getBoard() { //method getBoard  that gets the value of the board
    return board; // returns the value of the board
  }

  public char at(
      com.tlglearning.battleship.Position position) { //at method that allows other parts of the program to access specific elements of the board array based on the board position.
    return board[position.getRow()][position.getColumn()]; //returns the position of the board based on the getRow() and getColumn() values.
  }

  public boolean set(char status,
      com.tlglearning.battleship.Position position) { //method set() that has two arguments: char value called status
    board[position.getRow()][position.getColumn()] = status;
    return true;
  }


  public boolean thereIsSpace(Ship ship) {
    int l = ship.getShipType().getLength();
    int x = ship.getPosition().getRow();
    int y = ship.getPosition().getColumn();
    if (ship.getDirection() == Direction.HORIZONTAL)
      return (length - y + 1) > l;
    else
      return (length - x + 1) > l; // this is a not so I can Push again
  }

}