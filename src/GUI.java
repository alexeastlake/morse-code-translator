import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * GUI class for the Morse Code Translator.
 *
 * @author alexeastlake
 */
class GUI {
	
	// Translator instance
	private Translator translator;
	
	// Primary GUI components
	private JFrame frame;
	private JPanel morsePanel;
	private JPanel normalTextPanel;
	
	// Menu bar components
	private JMenuBar menuBar;
	private JMenu saveMenu;
	private JMenuItem loadNormalTextMenuItem;
	private JMenuItem saveMorseMenuItem;
	private JMenuItem saveNormalTextMenuItem;
	
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
		
		// Menu bar setup
		menuBar = new JMenuBar();
		saveMenu = new JMenu("Save");
		loadNormalTextMenuItem = new JMenuItem("Load Normal Text");
		saveMorseMenuItem = new JMenuItem("Save Morse Code");
		saveNormalTextMenuItem = new JMenuItem("Save Normal Text");
		
		frame.setJMenuBar(menuBar);
		menuBar.add(saveMenu);
		saveMenu.add(loadNormalTextMenuItem);
		saveMenu.add(saveMorseMenuItem);
		saveMenu.add(saveNormalTextMenuItem);
		
		// Menu bar action listeners
		loadNormalTextMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadNormalText();
			}
		});
		
		saveMorseMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveMorseCode();
			}
		});
		
		saveNormalTextMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveNormalText();
			}
		});
		
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
					SwingUtilities.invokeLater(normalTextAreaToUpperCase());
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
				if (!normalTextArea.getText().equals("")) {
					morseTextArea.setText(translator.translateToMorse(normalTextArea.getText()));
				} else {
					morseTextArea.setText("");
				}
				
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
				if (!morseTextArea.getText().equals("")) {
					normalTextArea.setText(translator.translateFromMorse(morseTextArea.getText()));
				} else {
					normalTextArea.setText("");
				}
				
				updating = false;
			}
		};
		
		return doUpdate;
	}
	
	/**
	 * Creates a Runnable which makes all text in the normalTextArea upper case.
	 *
	 * @return Runnable
	 */
	private Runnable normalTextAreaToUpperCase() {
		updating = true;
		
		Runnable doUpperCase = new Runnable() {
			@Override
			public void run() {
				normalTextArea.setText(normalTextArea.getText().toUpperCase());
			}
		};
		
		return doUpperCase;
	}
	
	/**
	 * Loads a selected .txt file into the normalTextArea.
	 */
	private void loadNormalText() {
		JFileChooser fileChooser = new JFileChooser();
		int option = fileChooser.showOpenDialog(null);
		
		if (option == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			
			if (file.getName().contains(".txt")) {
				try {
					BufferedReader reader = new BufferedReader(new FileReader(file.getPath()));
					StringBuilder textBuilder = new StringBuilder();
					String line = reader.readLine();
					
					while (line != null) {
						textBuilder.append(line);
						line = reader.readLine();
					}
					
					reader.close();
					normalTextArea.setText(textBuilder.toString());
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Loading failed");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Unsupported file type. Use a .txt file");
			}
		}
	}
	
	/**
	 * Save the current content of the morseTextArea to a .txt file.
	 */
	private void saveMorseCode() {
		try {
			PrintWriter output = new PrintWriter("morse.txt");
			output.println(morseTextArea.getText());
			output.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Saving Failed");
		}
	}
	
	/**
	 * Save the current content of the normalTextArea to a .txt file.
	 */
	private void saveNormalText() {
		try {
			PrintWriter output = new PrintWriter("text.txt");
			output.println(normalTextArea.getText());
			output.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Saving Failed");
		}
	}
}