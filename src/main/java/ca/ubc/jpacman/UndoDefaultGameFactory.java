package ca.ubc.jpacman;

import org.jpacman.framework.factory.DefaultGameFactory;
/*
 * This class is used to override the getGame and makeGame methods 
 * to return an UndoableGame instance rather than Game instance.
 * @author: Tushar Kalra and Aviral Garg.
 */
public class UndoDefaultGameFactory extends DefaultGameFactory {

	private transient UndoableGame undoGame;

	@Override
	public UndoableGame getGame() {

		return this.undoGame;
	}

	@Override
	public UndoableGame makeGame() {

		this.undoGame = new UndoableGame();
		return this.undoGame;
	}

}
