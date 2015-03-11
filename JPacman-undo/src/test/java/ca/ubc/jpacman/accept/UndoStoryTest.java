package ca.ubc.jpacman.accept;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Ghost;
import org.jpacman.framework.model.Tile;
import org.jpacman.framework.ui.MainUI;
import org.junit.Before;
import org.junit.Test;

import ca.ubc.jpacman.UndoablePacman;

public class UndoStoryTest extends MovePlayerStoryTest {

	private UndoablePacman theUndo;
	private Tile foodTile, ghostTile;

	@Override
	public MainUI makeUI() {
		theUndo = new UndoablePacman();
		theUndo.UndoableFactorySetup();// set up undoFactory
		return theUndo;
	}

	@Override
	protected MainUI getUI() {
		return theUndo;
	}

	@Override
	@Before
	public void setUp() throws FactoryException, InterruptedException {
		super.setUp();

		foodTile = tileAt(2, 0);
		ghostTile = tileAt(2, 1);
	}

	@Test
	public void test_S7_1_PlayerMovesBack() {
		// given
		getEngine().start();
		// and
		getEngine().up();

		Tile tile = getPlayer().getTile();

		getEngine().right();
		// when
		theUndo.undo();
		// then
		assertEquals(getPlayer().getTile(), tile);
	}

	@Test
	public void test_S7_2_PacmanPutsFoodBack() {
		// given
		getEngine().start();
		// and
		getEngine().up();
		getEngine().right();

		Tile oldTile = getPlayer().getTile();
		int points = getPlayer().getPoints();
		// when
		theUndo.undo();
		// then
		assertEquals(oldTile, foodTile);
		// and
		assertEquals(points - 10, 0);
	}

	@Test
	public void test_S7_3_GhostsMoveBack() {
		// given
		getEngine().start();
		// and
		Ghost ghost = getUI().getGame().getGhosts().get(0);
		Tile InitialGhostTile = getUI().getGame().getGhosts().get(0).getTile();

		getUI().getGame().moveGhost(ghost, Direction.UP);

		// when
		theUndo.undo();
		// then
		assertEquals(InitialGhostTile, ghostTile);

	}

	@Test
	public void test_S7_4_UndoFromFailedMove() {
		// given
		getEngine().start();
		// and
		getEngine().up();
		Tile tile = getPlayer().getTile();
		getEngine().up();
		// and
		Ghost ghost = getUI().getGame().getGhosts().get(0);
		Tile InitialGhostTile = getUI().getGame().getGhosts().get(0).getTile();

		getUI().getGame().moveGhost(ghost, Direction.UP);
		// when
		theUndo.undo();
		// then
		assertEquals(tile, getPlayer().getTile());
		assertEquals(InitialGhostTile, ghostTile);
	}

	@Test
	public void test_S7_5_UndoFromGameOver() {
		// given
		getEngine().start();
		// and
		getEngine().right();
		// when
		theUndo.undo();
		// then
		assertTrue(getPlayer().isAlive());
	}

	@Test
	public void test_S7_6_UndoFromWin() {
		// given
		getEngine().start();
		getEngine().left(); // eat first food
		getEngine().right(); // go back
		getEngine().up(); // move next to final food
		// when
		getEngine().right(); // eat final food
		// then

		// when
		theUndo.undo();
		// then
		assertTrue(getUI().getGame().getPointManager().allEaten());
	}

	@Test
	public void test_S7_7_UndoMultipleTimes() {
		// given
		getEngine().start();
		// and
		getEngine().up();

		Tile tile = getPlayer().getTile();

		getEngine().right();
		getEngine().left();
		// when
		theUndo.undo();
		theUndo.undo();
		// then
		assertEquals(getPlayer().getTile(), tile);
	}

}