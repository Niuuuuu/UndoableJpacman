package ca.ubc.jpacman.accept;

import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.factory.MapParser;
import org.jpacman.framework.model.Game;
import org.jpacman.test.framework.model.GameTest;

public class UndoableGameTest extends GameTest {

	// Override makePlay from superclass, return UndoableGame
	@Override
	protected Game makePlay(String singleRow) throws FactoryException {
		MapParser p = new MapParser(makeFactory());
		Game UndoableGame = p.parseMap(new String[] { singleRow });
		return UndoableGame;
	}

}
