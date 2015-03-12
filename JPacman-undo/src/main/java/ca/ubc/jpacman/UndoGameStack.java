package ca.ubc.jpacman;

import java.util.ArrayDeque;
import java.util.Deque;

import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Food;
import org.jpacman.framework.model.Ghost;
import org.jpacman.framework.model.Player;
import org.jpacman.framework.model.Tile;

// this class store the info of player's position, ghosts' position, score, fruit whenever the
// pacman moves
public class UndoGameStack {
	Tile player;
	Direction dir;
	int point;
	Deque<Tile> ghost = new ArrayDeque<Tile>();

	public UndoGameStack(UndoableGame game) {
		// players current position, and its direction, and current points
		player = game.getPlayer().getTile();
		dir = game.getPlayer().getDirection();
		point = game.getPointManager().getFoodEaten();

		// saving 4 ghost’s current tile by pushing them into stacks
		for (Ghost g : game.getGhosts()) {
			ghost.push(g.getTile());
		}
	}

	public void LastStack(UndoableGame game)

	{

		Player player = game.getPlayer();
		// if pacman died, undo revive pacman
		if (!player.isAlive()) {
			player.resurrect();
		}

		// last stack's point - current point should be negative
		int difference = point - game.getPointManager().getFoodEaten();
		// restore the points
		game.getPointManager().consumePointsOnBoard(player, difference);
		// restore previously eaten food
		if (difference != 0) {
			Food f = new Food();
			f.occupy(player.getTile());
		}

		// set pacman to its last position
		player.deoccupy();
		player.occupy(this.player);
		player.setDirection(dir);

		// set ghosts to their last position
		for (Ghost g : game.getGhosts()) {
			g.deoccupy();
			g.occupy(ghost.pop());

		}
	}

}