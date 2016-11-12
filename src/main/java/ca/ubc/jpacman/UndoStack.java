package ca.ubc.jpacman;

import java.util.ArrayDeque;
import java.util.Deque;

import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Food;
import org.jpacman.framework.model.Ghost;
import org.jpacman.framework.model.Player;
import org.jpacman.framework.model.Tile;

/*
 * This class is used to store the state attributes of the game like player position, player points,
 * Ghost positions and food positions.
 * It also recreates the game's previous state whenever Undo function is invoked.
 * @author: Tushar Kalra and Aviral Garg.
 * 
 */
public class UndoStack {

	Tile pacmanPos;
	Direction curDir;
	int curPoints;

	Deque<Tile> ghostPos = new ArrayDeque<Tile>();

	public UndoStack(final UndoableGame game) {

		// Current position of the Pacman.
		this.pacmanPos = game.getPlayer().getTile();
		// Current Direction of the Pacman.
		this.curDir = game.getPlayer().getDirection();
		// Currents Points earned by the Pacman.
		this.curPoints = game.getPointManager().getFoodEaten();

		// Current Positions of the Ghosts.

		for (final Ghost ghosts : game.getGhosts()) {
			this.ghostPos.push(ghosts.getTile());

		}

	}

	// Recreates the last state of the game before undo was pressed
	public void prevState(final UndoableGame game) {

		final Player pacman = game.getPlayer();

		if (!pacman.isAlive()) {

			pacman.resurrect();
		}

		final int diff = this.curPoints - game.getPointManager().getFoodEaten();

		game.getPointManager().consumePointsOnBoard(pacman, diff);

		if (diff != 0) {

			final Food food = new Food();
			food.occupy(pacman.getTile());

		}

		// Restiore the postion of the pacman

		pacman.deoccupy();
		pacman.occupy(this.pacmanPos);
		pacman.setDirection(this.curDir);

		// Restore Ghost positions

		for (final Ghost ghosts : game.getGhosts()) {
			ghosts.deoccupy();
			ghosts.occupy(this.ghostPos.pop());

		}

	}

}
