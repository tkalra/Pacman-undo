package ca.ubc.jpacman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.jpacman.framework.ui.ButtonPanel;
/*
 * This class initializes the ButtonPanel and adds the new Undo Button to it.
 */
public class UndoButton extends ButtonPanel {

	
	private static final long serialVersionUID = 5078677478811886963L;

	private JButton undoButton;

	/*
	 * Initialize the Start Stop and Undo Buttons.
	 */

	/**
	 * Obtain the handler capable of dealing with button events.
	 *
	 * @return The pacman interactor.
	 */
	@Override
	public UndoInteractions getPacmanInteractor() {
		return (UndoInteractions) super.getPacmanInteractor();
	}

	/*
	 * Enables the button and adds it to the Button Panel.
	 */

	@Override
	public void initialize() {

		// Setup and add the Start and stop button.
		super.initialize();

		this.undoButton = new JButton("Undo");

		this.initializeUndoButton();

		this.addButton(this.undoButton);

	}

	protected void initializeUndoButton() {

		this.undoButton.setEnabled(true);

		this.undoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {

				// Undo the pacman position,points,Ghosts positions
				UndoButton.this.getPacmanInteractor().undo();
				UndoButton.this.pause();
			}

		});

		this.undoButton.setName("jpacman.undo");

	}

}
