package ca.ubc.jpacman.accept;

import org.jpacman.framework.model.Tile;
import org.jpacman.test.framework.accept.AbstractAcceptanceTest;
import org.junit.Assert;
import org.junit.Test;

public class MovePlayerStoryTest extends AbstractAcceptanceTest {
	@Test
	public void test_S2_1_PlayerMoves() {
		final Tile emptyTile = this.tileAt(1, 0);
		// given
		this.getEngine().start();
		Assert.assertEquals(1, this.getPlayer().getTile().getY());
		// when
		this.getEngine().up();
		// then
		Assert.assertEquals(emptyTile, this.getPlayer().getTile());
	}

	@Test
	public void test_S2_2_PlayerEats() {
		// given
		this.getEngine().start();
		// when
		this.getEngine().left();
		// then
		Assert.assertTrue(this.getPlayer().getPoints() > 0);
	}

	@Test
	public void test_S2_3_PlayerDies() {
		// given
		this.getEngine().start();
		// when
		this.getEngine().right();
		// then
		Assert.assertFalse(this.getPlayer().isAlive());
	}

	@Test
	public void test_S2_4_PlayerWall() {
		final Tile nextToWall = this.tileAt(1, 0);
		// given
		this.getEngine().start();
		this.getEngine().up(); // move next to Wall
		Assert.assertEquals(nextToWall, this.getPlayer().getTile());
		// when
		this.getEngine().left();
		// then still next to wall.
		Assert.assertEquals(nextToWall, this.getPlayer().getTile());
	}

	@Test
	public void test_S2_5_PlayerWins() {
		// given
		this.getEngine().start();
		this.getEngine().left(); // eat first food
		this.getEngine().right(); // go back
		this.getEngine().up(); // move next to final food
		// when
		this.getEngine().right(); // eat final food
		// then
		Assert.assertTrue(this.getUI().getGame().getPointManager().allEaten());
	}
}
