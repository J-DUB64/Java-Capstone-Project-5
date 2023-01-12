package com.tlglearning.battleship;

import static org.junit.jupiter.api.Assertions.*;

import com.tlglearning.battleship.model.PositionStatus;
import com.tlglearning.battleship.model.ShipType;
import com.tlglearning.battleship.model.Board;
import com.tlglearning.battleship.model.Player;
import com.tlglearning.battleship.model.Position;
import com.tlglearning.battleship.model.Ship;
import com.tlglearning.battleship.model.Ship.Direction;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class PlayerTest {

  private static final Board PLAYER_1_BOARD = new Board(10);
  private static final Board PLAYER_2_BOARD = new Board(10);
  private static final Player PLAYER_1 = new Player("Evelyn", PLAYER_1_BOARD);
  private static final String PLAYER_1_NAME = "Evelyn";
  private static final Player PLAYER_2 = new Player("Bob", PLAYER_2_BOARD);
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
  void getPlayerShipInventory_returnsEmptyInitialArrayList() {
    assertEquals(
        EMPTY_SHIP_INVENTORY,
        PLAYER_1.getPlayerShipInventory());
  }

  @Test
  void decrementShip() {
    PLAYER_1.decrementShip(ShipType.CARRIER);
    Ship indexShip = new Ship(ShipType.CARRIER, null, null);
    int index = PLAYER_1.getPlayerShipInventory().indexOf(indexShip);
    assertEquals(4, PLAYER_1.getPlayerShipInventory().get(index).getHealthPoints());
  }

  @Test
  void playerShoots_valid_HIT() {
    Position HIT = new Position(2,2);
    PLAYER_2_BOARD.setCharacterAtPosition(PositionStatus.BATTLESHIP.getStatus(), HIT);

    PLAYER_1.playerShoots(HIT, PLAYER_2);

    assertEquals(PLAYER_2_BOARD.getCharacterAtPosition(HIT), PositionStatus.HIT.getStatus());
  }

  @Test
  void playerShoots_valid_MISS() {
    Position MISS = new Position(2,2);

    PLAYER_1.playerShoots(MISS, PLAYER_2);

    assertEquals(PLAYER_2_BOARD.getCharacterAtPosition(MISS), PositionStatus.MISS.getStatus());
  }

  @Test
  void playerShoots_invalid_previousHit() throws IllegalArgumentException{
    Position HIT = new Position(2,2);
    PLAYER_2_BOARD.setCharacterAtPosition(PositionStatus.HIT.getStatus(), HIT);

    assertThrows(IllegalArgumentException.class, () -> PLAYER_1.playerShoots(HIT, PLAYER_2));
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