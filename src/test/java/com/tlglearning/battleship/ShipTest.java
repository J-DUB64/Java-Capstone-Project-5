package com.tlglearning.battleship;

import com.tlglearning.battleship.model.ShipType;
import com.tlglearning.battleship.model.Position;
import com.tlglearning.battleship.model.Ship;
import com.tlglearning.battleship.model.Ship.Direction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShipTest {

  @Test
  public void testGetLength() {
    ShipType expected = ShipType.SUBMARINE;
    Position position = new Position(2, 2);
    Direction direction = Direction.VERTICAL;
    Ship ship = new Ship(expected, position, direction);
    assertEquals(expected.getLength(), ship.getLength());
  }

  @Test
  public void testSetPosition() {
    Position expected = new Position(5,5);
    ShipType shipType = ShipType.BATTLESHIP;
    Direction direction = Direction.VERTICAL;
    Ship ship = new Ship(shipType, new Position(1,1), direction);
    ship.setPosition(expected);
    Position actual = ship.getPosition();
    assertEquals(expected, actual);
  }

  @Test
  public void testGetPosition() {
    Position expected = new Position(3,3);
    ShipType shipType = ShipType.DESTROYER;
    Direction direction = Direction.HORIZONTAL;
    Ship ship = new Ship(shipType, expected, direction);
    Position actual = ship.getPosition();
    assertEquals(expected, actual);
  }

  @Test
  public void testGetDirection() {
    Direction expected = Direction.HORIZONTAL;
    ShipType shipType = ShipType.PATROL_BOAT;
    Position position = new Position(1,1);
    Ship ship = new Ship(shipType, position, expected);
    Direction actual = ship.getDirection();
    assertEquals(expected, actual);
  }

  @Test
  public void testSetShipType() {
    ShipType expected = ShipType.CARRIER;
    Position position = new Position(1, 1);
    Direction direction = Direction.HORIZONTAL;
    Ship ship = new Ship(ShipType.BATTLESHIP, position, direction);
    ship.setShipType(expected);
    ShipType actual = ship.getShipType();
    assertEquals(expected, actual);
  }

  @Test
  public void testSetDirection() {
    Direction expected = Direction.VERTICAL;
    ShipType shipType = ShipType.SUBMARINE;
    Position position = new Position(1,1);
    Ship ship = new Ship(shipType, position, Direction.HORIZONTAL);
    ship.setDirection(expected);
    Direction actual = ship.getDirection();
    assertEquals(expected, actual);
  }
  @Test
  public void testDecrementHealthPoints() {
    ShipType shipType = ShipType.CARRIER;
    Position position = new Position(1,1);
    Direction direction = Direction.HORIZONTAL;
    int expected = shipType.getLength()-1;
    Ship ship = new Ship(shipType, position, direction);
    ship.decrementHealthPoints();
    int actual = ship.getHealthPoints();
    assertEquals(expected, actual);
  }

  @Test
  public void testGetHealthPoints() {
    ShipType shipType = ShipType.BATTLESHIP;
    Position position = new Position(1,1);
    Direction direction = Direction.VERTICAL;
    int expected = shipType.getLength();
    Ship ship = new Ship(shipType, position, direction);
    int actual = ship.getHealthPoints();
    assertEquals(expected, actual);
  }


  @Test
  public void testGetRowOffsetHorizontal() {
    int expected = 0;
    int actual = Direction.HORIZONTAL.getRowOffset();
    assertEquals(expected, actual);
  }

  @Test
  public void testGetRowOffsetVertical() {
    int expected = 1;
    int actual = Direction.VERTICAL.getRowOffset();
    assertEquals(expected, actual);
  }

  @Test
  public void testGetColumnOffsetHorizontal() {
    int expected = 1;
    int actual = Direction.HORIZONTAL.getColumnOffset();
    assertEquals(expected, actual);
  }

  @Test
  public void testGetColumnOffsetVertical() {
    int expected = 0;
    int actual = Direction.VERTICAL.getColumnOffset();
    assertEquals(expected, actual);
  }




}

