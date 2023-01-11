package com.tlglearning.battleship;

import com.tlglearning.battleship.Ship.Direction;
import java.util.Arrays;
import com.tlglearning.battleship.Position;


public class Board {

  public final int length; //one variable for rows = columns = 10 [10x10 matrix]
  public char[][] board; // 2 Dimensional array called board
  public int numShips = 0; //counter for the number of ships

  // Constructors
  //constructor that initializes a new instance with a given length and creates a new board using the initBoard() method.
  public Board(
      int length) {  //public access modifier that defines the method named board by  specifying an int method named length method named board.
    this.length = length; //this. refers to the current instance of the class and assigns the value of the input parameter "length" to a field variable within the class.
    board = initBoard();  // assigns the value returned by the initBoard() method to the board field.
  }

  //constructor that initializes a new instance with a given matrix and sets the board field of the new Board instance to the given matrix. The length field is also set to the length of the matrix.
  public Board(
      char[][] matrix) { //public access modifier for the method Board that has a method signature char[][] which specifies the input parameters that the method expects named matrix.
    this.length = matrix.length; // assigns the length of the matrix array to a field (variable) within the class. "this" keyword refers to the class that is currently being operated in.
    board = matrix; //assigns the value of the input parameter "matrix" to the board class.
  }

  //Methods
  //private method that creates a new matrix of characters with dimensions equal to the length field of the Board instance and fills all elements of the matrix with the PositionStatus.WATER.getStatus() value, then returns the matrix.
  public char[][] initBoard() { //private access modifier that defines a return type method (char[][]) named initBoard().
    char[][] matrix = new char[length][length]; //declares a new two-dimensional array of characters called matrix which is initializes it specified dimensions through the length field
    for (char[] row : matrix) { //for loop that iterates over each row of the matrix array. char[]row will declare a new variable called row that will hold the current row of the array for each iteration of the for loop.
      Arrays.fill(row,
          PositionStatus.WATER.getStatus()); // fills the current row of the matrix with water constant.
    }
    return matrix; // returns the matrix array to the caller of the method.
  }

  // a getter for the length field, which returns the value of length as an int.
  public int getLength() { // method getLength() that gets an int value for the length
    return length; // return the value of length
  }

  //method that returns the value of the length field of the Board instance.
  public int getNumShips() { //method getNumShips() that gets an int value for the number of Ships
    return numShips; // return the number of ships value.
  }

  //method that returns the value of the board field of the Board instance.
  public char[][] getBoard() { //method getBoard that gets the value of the board
    return board; // returns the value of the board
  }

  //method called at that takes a Position object as an argument and returns the character at the position represented by the Position object in the board array.
  public char get(Position position) { //at method that allows other parts of the program to access specific elements of the board array based on the board position.
    return board[position.getRow()][position.getColumn()]; //returns the position of the board based on the getRow() and getColumn() values.
  }

  // method that sets the element of the board field at the specified row and column to the given status value and returns true. The row and column are specified through the position argument, which is an instance of the Position class.
  public boolean set(char status, Position position) { //method set() that has two arguments: char value called status
    board[position.getRow()][position.getColumn()] = status; //
    return true;
  }

  //method that checks whether there is enough space on the board for the given ship to be placed, based on the length of the ship and its Direction
  public boolean spaceAvailable(
      Ship ship) {  //thereIsSpace method return type boolean that takes a Ship object as an argument.
    int shipLength = ship.getLength(); //  local variable l and assigns it the length of the ship
    int row = ship.getPosition().getRow(); //assigns the row coordinate of the position of the ship
    int column = ship.getPosition().getColumn(); //assigns the column coordinate of the position of the ship object
    for (int i =0; i < shipLength; i++) {
      row = row + i * ship.getDirection().getRowOffset();
      column = column + i *ship.getDirection().getColumnOffset();
      if (row < 0 || row >= board.length || column < 0 || column >= board[0].length) {
        throw new IllegalArgumentException("Can not place here, position is out of bounds");
      }
      if (board[row][column] != PositionStatus.WATER.getStatus()) {
        throw new IllegalArgumentException("Can not place here, position already occupied");
      }
    }
    return true;
    }
  }
