package ca.ubc.jpacman.accept;

import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.factory.IGameFactory;
import org.jpacman.framework.factory.MapParser;
import org.jpacman.test.framework.model.GameTest;

import ca.ubc.jpacman.UndoDefaultGameFactory;
import ca.ubc.jpacman.UndoableGame;

public class UndoableGameTest extends GameTest {

	private UndoableGame undoGame;

	/*
	 * (non-Javadoc)
	 * @see org.jpacman.test.framework.model.GameTest#makeFactory()
	 */
	@Override
	public IGameFactory makeFactory() {
		// TODO Auto-generated method stub
		return new UndoDefaultGameFactory();
	}

	/*
	 * (non-Javadoc)
	 * @see org.jpacman.test.framework.model.GameTest#makePlay(java.lang.String)
	 */
	@Override
	protected UndoableGame makePlay(final String singleRow) throws FactoryException {
		final MapParser p = new MapParser(this.makeFactory());
		this.undoGame = (UndoableGame) p.parseMap(new String[] { singleRow });
		return this.undoGame;
	}

}
