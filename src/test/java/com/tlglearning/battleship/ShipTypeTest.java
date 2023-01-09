package com.tlglearning.battleship;

import static org.junit.jupiter.api.Assertions.*;

import com.tlglearning.battleship.Ship.Direction;
import org.junit.jupiter.api.Test;

class ShipTypeTest {
  Position testPosition= new Position(2, 4);

  @Test
  void getLength_validCarrier() {
    Ship testShip = new Ship(ShipType.CARRIER, testPosition, Direction.HORIZONTAL);
    assertEquals(5, testShip.getShipType().getLength());
  }

  @Test
  void getLength_validBattleship() {
    Ship testShip = new Ship(ShipType.BATTLESHIP, testPosition, Direction.HORIZONTAL);
    assertEquals(4, testShip.getShipType().getLength());
  }


  @Test
  void getLength_validDestroyer() {
    Ship testShip = new Ship(ShipType.DESTROYER, testPosition, Direction.HORIZONTAL);
    assertEquals(3, testShip.getShipType().getLength());
  }

  @Test
  void getLength_validSubmarine() {
    Ship testShip = new Ship(ShipType.SUBMARINE, testPosition, Direction.HORIZONTAL);
    assertEquals(2, testShip.getShipType().getLength());
  }

  @Test
  void getLength_validPatrolBoat() {
    Ship testShip = new Ship(ShipType.PATROL_BOAT, testPosition, Direction.HORIZONTAL);
    assertEquals(1, testShip.getShipType().getLength());
  }
}