package com.tlglearning.battleship;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.tlglearning.battleship.Ship.Direction;
import org.junit.jupiter.api.Test;

public class BoardTest {

  public static Board BOARD_LENGTH_10 = new Board(10);
  public static final Position POSITION_1 = new Position(6, 9);
  public static final Position POSITION_2 = new Position(3, 3);
  public static final Position POSITION_3 = new Position(1, 1);

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
  public void placementCoordinatesInBoardBoundaries_for_CarrierPosition1_throwsIllegalArgumentException()
      throws IllegalArgumentException {
    Ship carrier = new Ship(ShipType.CARRIER, POSITION_1, Direction.VERTICAL);
    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> BOARD_LENGTH_10.placementCoordinatesInBoardBoundaries(carrier));

    String expectedMessage = "Ship cannot be placed here— the calculated space needed to place this ship goes out of bounds.";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  public void placementCoordinatesInBoardBoundaries_for_CarrierPosition2_returnsTrue() {
    Ship carrier = new Ship(ShipType.CARRIER, POSITION_2, Direction.VERTICAL);

    assertTrue(BOARD_LENGTH_10.placementCoordinatesInBoardBoundaries(carrier));
  }

  @Test
  public void placementCoordinatesAvailable_for_CarrierPosition2_throwsIllegalArgumentException() {
    BOARD_LENGTH_10.set('☐',POSITION_2);

    Ship carrier = new Ship(ShipType.CARRIER, POSITION_2, Direction.VERTICAL);

    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> BOARD_LENGTH_10.placementCoordinatesAvailable(carrier));

    String expectedMessage = "Ship cannot be placed here— the calculated space needed to place this ship is not all water.";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  public void placementCoordinatesAvailable_for_CarrierPosition2_returnsTrue() {
    Ship carrier = new Ship(ShipType.CARRIER, POSITION_2, Direction.VERTICAL);

    assertTrue(BOARD_LENGTH_10.placementCoordinatesInBoardBoundaries(carrier));
  }
}
