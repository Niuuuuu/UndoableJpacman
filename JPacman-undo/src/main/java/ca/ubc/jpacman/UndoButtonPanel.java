package ca.ubc.jpacman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.jpacman.framework.ui.ButtonPanel;

public class UndoButtonPanel extends ButtonPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5141812652904179089L;
	private JButton undoButton;

	@Override
	/**
	 * Actually create the buttons.
	 */
	public void initialize() {
		super.initialize();

		undoButton = new JButton("Undo");
		initializeUndoButton();

		addButton(undoButton);
	}

	/**
	 * Create the undo button.
	 */
	protected void initializeUndoButton() {

		undoButton.setEnabled(true);

		undoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getPacmanInteractor().undo();
				pause();

			}
		});
		undoButton.setName("jpacman.undo");
	}

	@Override
	public UndoInteraction getPacmanInteractor() {
		return (UndoInteraction) super.getPacmanInteractor();
	}

}
