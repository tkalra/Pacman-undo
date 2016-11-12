package ca.ubc.jpacman;

import org.jpacman.framework.ui.PacmanInteraction;
/*
 * This is class is used to map the invoking of the Undo button in the UI
 *  to the undo method in the UndoableGame class.
 * @author: Tushar Kalra and Aviral Garg.
 */
public class UndoInteractions extends PacmanInteraction {
	public void undo() {
		this.updateState();
		((UndoableGame) this.getGame()).undo();

	}
}
