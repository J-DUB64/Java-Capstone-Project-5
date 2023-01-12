package com.tlglearning.battleship;

import com.tlglearning.battleship.Ship.Direction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionTest {

  @Test
  public void testGetStatus() {
    Position position = new Position(0, 0);
    position.setStatus(PositionStatus.CARRIER);
    assertEquals(PositionStatus.CARRIER, position.getStatus());
  }
  @Test
  public void testSetStatus() {
    Position position = new Position(0, 0);
    position.setStatus(PositionStatus.BATTLESHIP);
    assertEquals(PositionStatus.BATTLESHIP, position.getStatus());
  }
  @Test
  public void testPositionConstructor() {
    Position position = new Position(1, 2);
    assertEquals(1, position.getRow());
    assertEquals(2, position.getColumn());
    assertEquals(PositionStatus.WATER, position.getStatus());
  }
  @Test
  public void testAt() {
    Position position = new Position(0, 0);
    position.setStatus(PositionStatus.SUBMARINE);
    assertEquals('S', position.at(position));
  }
  @Test
  public void testGetRow() {
    Position position = new Position(2, 3);
    assertEquals(2, position.getRow());
  }
  @Test
  public void testGetColumn() {
    Position position = new Position(2, 3);
    assertEquals(3, position.getColumn());
  }

}
