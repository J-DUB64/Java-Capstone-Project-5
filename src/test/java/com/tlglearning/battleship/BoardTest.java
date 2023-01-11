package com.tlglearning.battleship;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.tlglearning.battleship.Board;
import com.tlglearning.battleship.Position;
import com.tlglearning.battleship.PositionStatus;
import com.tlglearning.battleship.Ship;
import com.tlglearning.battleship.Ship.Direction;
import com.tlglearning.battleship.ShipType;
import org.junit.jupiter.api.Test;

public class BoardTest {

  @Test
  public void testBoardConstructorWithLength() {
    int length = 5;
    Board board = new Board(length);
    assertEquals(length, board.length);
    assertNotNull(board.board);
  }

  @Test
  public void testBoardConstructorWithMatrix() {
    char[][] matrix = {{'X', 'O', 'X'}, {'O', 'X', 'O'}, {'X', 'O', 'X'}};
    Board board = new Board(matrix);
    assertEquals(matrix.length, board.length);
    assertArrayEquals(matrix, board.board);
  }

  @Test
  public void testInitBoardMethod() {
    int length = 3;
    Board board = new Board(length);
    char[][] expectedBoard = {{'~', '~', '~'}, {'~', '~', '~'}, {'~', '~', '~'}};
    assertArrayEquals(expectedBoard, board.initBoard());
  }

  @Test
  public void testGetLengthMethod() {
    int length = 3;
    Board board = new Board(length);
    assertEquals(length, board.getLength());
  }

  @Test
  public void testGetNumShipsMethod() {
    int numShips = 5;
    Board board = new Board(3);
    board.numShips = numShips;
    assertEquals(numShips, board.getNumShips());
  }

  @Test
  public void testGetBoardMethod() {
    char[][] matrix = {{'X', 'O', 'X'}, {'~', 'X', 'O'}, {'X', 'O', '~'}};
    Board board = new Board(matrix);
    assertArrayEquals(matrix, board.getBoard());
  }

  @Test
  public void testAtMethod() {
    char[][] matrix = {{'X', 'O', 'X'}, {'O', 'X', 'O'}, {'X', 'O', 'X'}};
    Board board = new Board(matrix);
    Position position = new Position(1, 1);
    assertEquals('X', board.get(position));
  }

  @Test
  public void testShipPlacement() {
    Board board = new Board(5);
    Position position1 = new Position(0, 0);
    Position position2 = new Position(0, 0);
    Position position3 = new Position(0, 3);
    Position position4 = new Position(0, 0);
    Position position5 = new Position(2, 0);
    Position position6 = new Position(4, 4);
    Position position7 = new Position(4, 3);
    Position position8 = new Position(0, 0);

    Ship ship1 = new Ship(ShipType.DESTROYER, position1, Direction.HORIZONTAL);
    Ship ship2 = new Ship(ShipType.CARRIER, position2, Direction.VERTICAL);
    Ship ship3 = new Ship(ShipType.CARRIER, position3, Direction.HORIZONTAL);
    Ship ship4 = new Ship(ShipType.BATTLESHIP, position4, Direction.VERTICAL);
    Ship ship5 = new Ship(ShipType.BATTLESHIP, position5, Direction.HORIZONTAL);
    Ship ship6 = new Ship(ShipType.DESTROYER, position6, Direction.VERTICAL);
    Ship ship7 = new Ship(ShipType.DESTROYER, position7, Direction.HORIZONTAL);
    Ship ship8 = new Ship(ShipType.DESTROYER, position8, Direction.HORIZONTAL);
    try {
      assertTrue(board.spaceAvailable(ship1));
      assertTrue(board.spaceAvailable(ship2));
      assertTrue(board.spaceAvailable(ship3));
      assertTrue(board.spaceAvailable(ship4));
      assertTrue(board.spaceAvailable(ship5));
      assertFalse(board.spaceAvailable(ship6));
      assertFalse(board.spaceAvailable(ship7));
    } catch (IllegalArgumentException e) {
      System.out.println("ship placement failed: " + e.getMessage());
    }
  }

  @Test
  public void testSpaceAvailable_placementNotValid() {
    Board board = new Board(10);
    Position position = new Position(3, 3);
    Ship ship1 = new Ship(ShipType.CARRIER, position, Direction.VERTICAL);
    board.spaceAvailable(ship1);
    Position position2 = new Position(3, 5);
    Ship ship2 = new Ship(ShipType.CARRIER, position2, Direction.VERTICAL);
    boolean spaceAvailable = board.spaceAvailable(ship2);
    assertFalse(spaceAvailable);
    try {
      assertTrue(board.spaceAvailable(ship1));
      assertTrue(board.spaceAvailable(ship2));
    } catch (IllegalArgumentException e) {
      System.out.println("ship placement failed: " + e.getMessage());
    }
  }
}
