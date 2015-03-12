package ca.ubc.jpacman;

import java.util.ArrayDeque;
import java.util.Deque;

import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Game;
import org.jpacman.framework.model.Ghost;

public class UndoableGame extends Game {

	public Deque<UndoGameStack> gameDeque = new ArrayDeque<UndoGameStack>();
	UndoButtonPanel undo;

	public void undo() {

		// check that the deque is not empty and that player has not won
		if (gameDeque.size() >= 1 && !this.won()) {
			UndoGameStack undogame = gameDeque.pop();
			undogame.LastStack(this);
			notifyViewers();

		}

	}

	@Override
	public void movePlayer(Direction dir) {
		// create current game's data ( pacman position, ghosts, points ...etc)
		UndoGameStack game = new UndoGameStack(this);
		gameDeque.push(game);

		super.movePlayer(dir);
	}

	@Override
	public void moveGhost(Ghost theGhost, Direction dir) {
		super.moveGhost(theGhost, dir);
	}

}
