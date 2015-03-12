package ca.ubc.jpacman;

import org.jpacman.framework.factory.DefaultGameFactory;
import org.jpacman.framework.model.Game;

public class UndoableFactory extends DefaultGameFactory {

	private transient UndoableGame UndoableGame;

	@Override
	public Game makeGame() {
		UndoableGame = new UndoableGame();
		return UndoableGame;
	}

	/**
	 * @return The game created by this factory.
	 */
	@Override
	protected Game getGame() {
		assert UndoableGame != null;
		return UndoableGame;
	}
}
