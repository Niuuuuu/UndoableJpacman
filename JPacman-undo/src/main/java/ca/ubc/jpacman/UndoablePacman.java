package ca.ubc.jpacman;

import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.ui.MainUI;

public class UndoablePacman extends MainUI {

	private static final long serialVersionUID = 2322465764242967651L;
	public UndoableFactory undoGameFactory = new UndoableFactory();

	public void undo() {
		getGame().undo();

	}

	@Override
	public UndoableGame getGame() {
		return (UndoableGame) super.getGame();
	}

	public MainUI UndoableFactorySetup() {

		return withFactory(undoGameFactory);
	}

	public UndoablePacman() {
		super();
		withFactory(undoGameFactory);
		withButtonPanel(new UndoButtonPanel());
		withModelInteractor(new UndoInteraction());

	}

	public static void main(String[] args) throws FactoryException {
		new UndoablePacman().main();
	}

}
