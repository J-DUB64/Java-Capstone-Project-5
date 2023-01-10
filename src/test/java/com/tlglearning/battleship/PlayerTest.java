package com.tlglearning.battleship;

import static org.junit.jupiter.api.Assertions.*;

import com.tlglearning.battleship.Ship.Direction;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class PlayerTest {

  private static final Board PLAYER_1_BOARD = new Board(10);
  private static final Player PLAYER_1 = new Player("Evelyn", PLAYER_1_BOARD);
  private static final String PLAYER_1_NAME = "Evelyn";
  private static final int PLAYER_1_BOARD_REMAINING_SHIPS = 0;
  public static final ArrayList<Ship> EMPTY_SHIP_INVENTORY = new ArrayList<>();
  public static final ArrayList<Ship> PLAYER_1_SHIP_INVENTORY = new ArrayList<>() {
    {
      add(new Ship(ShipType.CARRIER, new Position(1, 3), Direction.HORIZONTAL));
    }
  };


  @Test
  void getName_returnsPlayerName() {
    assertEquals(PLAYER_1_NAME, PLAYER_1.getName());
  }

  @Test
  void getPlayerBoard_returnsPlayerBoard() {
    assertEquals(PLAYER_1_BOARD, PLAYER_1.getPlayerBoard());
  }

  @Test
  void getShipsRemaining_returnsShipsRemaining() {
    assertEquals(PLAYER_1_BOARD_REMAINING_SHIPS, PLAYER_1.getShipsRemaining());
  }

  @Test
  void getPlayerShipInventory_returnsEmptyIntialArrayList() {
    assertEquals(
        EMPTY_SHIP_INVENTORY,
        PLAYER_1.getPlayerShipInventory());
  }

//  @Test
//  void getPlayerShipInventory_returnsArrayListWithBattleship() {
//    PLAYER_1.getPlayerShipInventory().add(
//        new Ship(ShipType.CARRIER, new Position(1, 3), Direction.HORIZONTAL)
//    );
//
//    assertEquals(
//        PLAYER_1_SHIP_INVENTORY,
//        PLAYER_1.getPlayerShipInventory()
//    );
//  }
}