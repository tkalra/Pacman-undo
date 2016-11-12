package ca.ubc.jpacman;

import java.util.ArrayDeque;
import java.util.Deque;

import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Game;
import org.jpacman.framework.model.Ghost;
/*
 * This class creates an instance of the Pacman game with the Undo fucntionality.
 * 
 * @author: Tushar Kalra and Aviral Garg
 */
public class UndoableGame extends Game {

	public Deque<UndoStack> undoList = new ArrayDeque<UndoStack>();

	@Override
	public void moveGhost(final Ghost theGhost, final Direction dir) {

		super.moveGhost(theGhost, dir);
	}

	@Override
	public void movePlayer(final Direction dir) {
		final UndoStack gameUndo = new UndoStack(this);
		this.undoList.push(gameUndo);

		super.movePlayer(dir);
	}

	public void undo() {
		// Make sure that the undolist is not empty that and the game is not won.

		if (this.undoList.size() >= 1) {
			final UndoStack lastState = this.undoList.pop();
			lastState.prevState(this);
			this.notifyViewers();

		}
	}

}
