import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.tlglearning.battleship.Board;
import com.tlglearning.battleship.Position;
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
  public void testSpaceAvailable() {
    Board board = new Board(5);
    Ship ship = new Ship(ShipType.DESTROYER, new Position(0, 0), Direction.HORIZONTAL);
    Ship ship1 = new Ship(ShipType.CARRIER, new Position(0, 0), Direction.HORIZONTAL);
    Ship ship2 = new Ship(ShipType.CARRIER, new Position(0, 3), Direction.HORIZONTAL);
    Ship ship3 = new Ship(ShipType.BATTLESHIP, new Position(0, 0), Direction.VERTICAL);
    Ship ship4 = new Ship(ShipType.BATTLESHIP, new Position(2, 0), Direction.VERTICAL);
    Ship ship5 = new Ship(ShipType.DESTROYER, new Position(4, 4), Direction.HORIZONTAL);
    Ship ship6 = new Ship(ShipType.DESTROYER, new Position(4, 3), Direction.VERTICAL);
    assertTrue(board.spaceAvailable(ship1));
    assertTrue(board.spaceAvailable(ship2));
    assertTrue(board.spaceAvailable(ship3));
    assertTrue(board.spaceAvailable(ship4));
    assertThrows(IllegalArgumentException.class, () -> board.spaceAvailable(ship5));
    assertThrows(IllegalArgumentException.class, () -> board.spaceAvailable(ship6));//
  }
}


