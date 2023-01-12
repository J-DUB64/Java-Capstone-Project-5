package com.tlglearning.battleship;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class PositionStatusTest {

  @Test
  public void testCarrier() {
    assertEquals('C', PositionStatus.CARRIER.getStatus());
  }

  @Test
  public void testBattleship() {
    assertEquals('B', PositionStatus.BATTLESHIP.getStatus());
  }

  @Test
  public void testDestroyer() {
    assertEquals('D', PositionStatus.DESTROYER.getStatus());
  }

  @Test
  public void testSubmarine() {
    assertEquals('S', PositionStatus.SUBMARINE.getStatus());
  }

  @Test
  public void testPatrolBoat() {
    assertEquals('P', PositionStatus.PATROL_BOAT.getStatus());
  }

  @Test
  public void testWater() {
    assertEquals('~', PositionStatus.WATER.getStatus());
  }

  @Test
  public void testHit() {
    assertEquals('\u2612', PositionStatus.HIT.getStatus());
  }

  @Test
  public void testMiss() {
    assertEquals('\u2638', PositionStatus.MISS.getStatus());
  }

}
