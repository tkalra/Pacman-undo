package ca.ubc.jpacman.accept;

import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Tile;
import org.junit.Assert;
import org.junit.Test;

import ca.ubc.jpacman.UndoablePacman;

public class UndoStoryTest extends MovePlayerStoryTest {
	/**
	 * Override the AbstractAcceptanceTest.mainUI()Factory method that makes the UI to be tested.
	 *
	 * @return A new instance of UndoablePacman.
	 */

	public UndoablePacman undoUI;

	@Override
	public UndoablePacman getUI() {
		return this.undoUI;
	}

	@Override
	public UndoablePacman makeUI() {
		this.undoUI = new UndoablePacman();
		return this.undoUI;
	}

	@Test
	public void test_S7_1_UndoMove() {
		this.getEngine().start();
		final Tile tile = this.getPlayer().getTile();
		this.getEngine().left(); // moves
		this.getUI().getGame().undo(); // undo
		Assert.assertEquals(tile, this.getPlayer().getTile());

		this.getEngine().up(); // moves
		this.getUI().getGame().undo(); // undo
		Assert.assertEquals(tile, this.getPlayer().getTile());

		this.getEngine().down(); // moves
		this.getUI().getGame().undo(); // undo
		Assert.assertEquals(tile, this.getPlayer().getTile());
	}

	@Test
	public void test_S7_2_UndoEatingFood() {
		this.getEngine().start();
		final Tile lastTile = this.getPlayer().getTile();
		final int lastPoints = this.getPlayer().getPoints();
		this.getEngine().left(); // moves
		this.getUI().getGame().undo(); // undo
		Assert.assertEquals(lastTile, this.getPlayer().getTile());
		Assert.assertEquals(lastPoints, this.getPlayer().getPoints());
	}

	@Test
	public void test_S7_3_UndoPlayerDeath() {
		this.getEngine().start();
		final Tile ptile = this.getPlayer().getTile();
		final Tile gtile = this.theGhost().getTile();
		this.getEngine().right(); // moves
		Assert.assertFalse(this.getPlayer().isAlive());
		this.getUI().getGame().undo();
		Assert.assertEquals(ptile, this.getPlayer().getTile());
		Assert.assertEquals(gtile, this.theGhost().getTile());
	}

	@Test
	public void test_S7_4_UndoPlayerWin() {
		this.getEngine().start();
		this.getEngine().left(); // moves
		this.getEngine().right(); // moves
		this.getEngine().up(); // moves
		final Tile tile = this.getPlayer().getTile();
		this.getEngine().right(); // moves
		this.getUI().getGame().undo();
		Assert.assertEquals(tile, this.getPlayer().getTile());
	}

	@Test
	public void test_S7_5_UndoGhostMove() {
		this.getEngine().start();
		this.getUI().getGame().moveGhost(this.theGhost(), Direction.UP);
		final Tile tile = this.theGhost().getTile();
		this.getUI().getGame().undo();
		Assert.assertEquals(tile, this.theGhost().getTile());

	}
}
