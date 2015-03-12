package ca.ubc.jpacman;

import org.jpacman.framework.ui.PacmanInteraction;

public class UndoInteraction extends PacmanInteraction {

	public void undo() {
		updateState();
		((UndoableGame) this.getGame()).undo();

	}

}
