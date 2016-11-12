package ca.ubc.jpacman;

import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.ui.MainUI;
/*
 * This class creates the UI and links the UndoableGame class which has 
 * the Undo functionality.
 * @author: Tushar Kalra and Aviral Garg.
 */
public class UndoablePacman extends MainUI {

	private static final long serialVersionUID = -3372063235496322314L;

	// Main method to start the game and the interactions.
	public static void main(final String[] args) throws FactoryException {
		new UndoablePacman().main();
	}

	private final UndoDefaultGameFactory undoGameFac = new UndoDefaultGameFactory();

	/*
	 * Creates The Board with the Undo button.
	 */
	public UndoablePacman() {
		// Call the MainUI constructor
		super();

		// Add Undo features
		this.SetupUndoFactory();
		this.withButtonPanel(new UndoButton());
		this.withModelInteractor(new UndoInteractions());
	}

	@Override
	public UndoableGame getGame() {
		return this.undoGameFac.getGame();
	}

	//
	public MainUI SetupUndoFactory() {
		return this.withFactory(this.undoGameFac);
	}

}
