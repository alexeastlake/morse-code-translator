import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

class GUI {
	
	// Translator instance
	private Translator translator;
	
	// Primary GUI components
	private JFrame frame;
	private JPanel morsePanel;
	private JPanel normalTextPanel;
	
	// MorsePanel components
	private JTextArea morseTextArea;
	
	// NormalTextPanel components
	private JTextArea normalTextArea;
	
	// Keeps track of if a text area is currently updating
	private boolean updating;
	
	/**
	 * Constructs a GUI instance with a given Translator.
	 *
	 * @param translator Translator instance to use
	 */
	public GUI(Translator translator) {
		this.translator = translator;
		
		// Primary GUI components setup
		frame = new JFrame();
		morsePanel = new JPanel();
		normalTextPanel = new JPanel();
		
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.LINE_AXIS));
		
		frame.getContentPane().add(morsePanel);
		frame.getContentPane().add(normalTextPanel);
		
		morsePanel.setLayout(new BoxLayout(morsePanel, BoxLayout.PAGE_AXIS));
		normalTextPanel.setLayout(new BoxLayout(normalTextPanel, BoxLayout.PAGE_AXIS));
		
		morsePanel.setPreferredSize(new Dimension(250, 300));
		normalTextPanel.setPreferredSize(new Dimension(250, 300));
		
		morsePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.black));
		normalTextPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.black));
		
		// Labels
		JLabel morseLabel = new JLabel("Morse Code");
		JLabel normalTextLabel = new JLabel("Text");
		
		morsePanel.add(morseLabel);
		normalTextPanel.add(normalTextLabel);
		
		morseLabel.setFont(new Font(morseLabel.getFont().getName(), Font.BOLD, morseLabel.getFont().getSize()));
		normalTextLabel.setFont(new Font(normalTextLabel.getFont().getName(), Font.BOLD, normalTextLabel.getFont().getSize()));
		
		morseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		normalTextLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		morseLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
		normalTextLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
		
		 // Text area setup
		morseTextArea = new JTextArea();
		normalTextArea = new JTextArea();
		
		morsePanel.add(morseTextArea);
		normalTextPanel.add(normalTextArea);
		
		morseTextArea.setLineWrap(true);
		normalTextArea.setLineWrap(true);
		
		// Document listener setups
		morseTextArea.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				if (!updating) {
					SwingUtilities.invokeLater(updateNormalTextArea());
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				if (!updating) {
					SwingUtilities.invokeLater(updateNormalTextArea());
				}
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {}
		});
		
		normalTextArea.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				if (!updating) {
					SwingUtilities.invokeLater(updateMorseTextArea());
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				if (!updating) {
					SwingUtilities.invokeLater(updateMorseTextArea());
				}
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {}
		});
		
		frame.pack();
		frame.setVisible(true);
		
		updating = false;
	}
	
	/**
	 * Creates a Runnable which updates the morseTextArea with a translation of the content of the normalTextArea.
	 *
	 * @return Runnable
	 */
	private Runnable updateMorseTextArea() {
		updating = true;
		
		Runnable doUpdate = new Runnable() {
			@Override
			public void run() {
				morseTextArea.setText(translator.translateToMorse(normalTextArea.getText()));
				updating = false;
			}
		};
		
		return doUpdate;
	}
	
	/**
	 * Creates a Runnable which updates the normalTextArea with a translation of the content of the normalTextArea.
	 *
	 * @return Runnable
	 */
	private Runnable updateNormalTextArea() {
		updating = true;
		
		Runnable doUpdate = new Runnable() {
			@Override
			public void run() {
				normalTextArea.setText(translator.translateFromMorse(morseTextArea.getText()));
				updating = false;
			}
		};
		
		return doUpdate;
	}
}