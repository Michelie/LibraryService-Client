package com.hit.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.hit.controller.StringMatchingController;

public class SearchView implements View {

	private JFrame searchFrame;
	private JTextField bookNameField;
	private JTextField authorNameField;
	private JLabel welcomeSearchLabel;
	private JLabel algoLabel;
	private JButton searchButton;
	private JCheckBox authorJCheckBox;
	private JCheckBox bookJCheckBox;
	private JRadioButton naiveRadioButton;
	private JRadioButton kmpRadioButton;
	private ButtonGroup bg;

	public int algoSelection;
	public String bookNameString;
	public String authorNameString;
	public String action;
	public String resString;

	private String pathFile = null;
	static Path imgPath;

	public SearchView() {
		this.action = "SEARCH";
		this.bookNameString = null;
		this.authorNameString = null;
		this.pathFile = "/src/main/resources/booksImg3.jpg";
		imgPath = Paths.get(System.getProperty("user.dir") + pathFile);
		initialize();
	}

	@Override
	public void initialize() {
		// Initializing the frame for SEARCH action
		searchFrame = new JFrame("SEARCH BOOK");
		searchFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		welcomeSearchLabel = new JLabel("<html>Please enter the name of the book<br>or the name of the author:</html>");
		welcomeSearchLabel.setBounds(10, 12, 350, 70);
		welcomeSearchLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		welcomeSearchLabel.setForeground(Color.BLACK);
		searchFrame.add(welcomeSearchLabel);

		bookJCheckBox = new JCheckBox("Book name:"); // Creating checkboxes for book and author
		bookJCheckBox.setBounds(10, 81, 107, 29);
		bookJCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bookJCheckBox.setForeground(Color.BLACK);
		bookJCheckBox.setOpaque(false);
		searchFrame.add(bookJCheckBox);

		authorJCheckBox = new JCheckBox("Author name:");
		authorJCheckBox.setBounds(10, 121, 107, 29);
		authorJCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		authorJCheckBox.setForeground(Color.BLACK);
		authorJCheckBox.setOpaque(false);
		searchFrame.add(authorJCheckBox);

		bookNameField = new JTextField("Enter name of book...", 30); // Creating text fields for book and author
		bookNameField.setBounds(118, 81, 120, 29);
		searchFrame.add(bookNameField);

		authorNameField = new JTextField("Enter author of book...", 30);
		authorNameField.setBounds(118, 121, 120, 29);
		searchFrame.add(authorNameField);

		algoLabel = new JLabel("<html>Please choose the algorithm<br/> for the search:</html>");
		algoLabel.setFont(new Font("Calibri", Font.BOLD, 17));
		algoLabel.setForeground(Color.BLACK);
		algoLabel.setBounds(10, 170, 290, 40);
		searchFrame.add(algoLabel);

		kmpRadioButton = new JRadioButton("1. KMP", true); // Creating radio buttons for the algorithms choice
		kmpRadioButton.setBounds(20, 215, 100, 15);
		kmpRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		kmpRadioButton.setForeground(Color.BLACK);
		kmpRadioButton.setOpaque(false);

		naiveRadioButton = new JRadioButton("2. Naive");
		naiveRadioButton.setBounds(130, 215, 100, 15);
		naiveRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		naiveRadioButton.setForeground(Color.BLACK);
		naiveRadioButton.setOpaque(false);
		bg = new ButtonGroup();
		bg.add(kmpRadioButton);
		bg.add(naiveRadioButton);
		searchFrame.add(kmpRadioButton);
		searchFrame.add(naiveRadioButton);

		searchButton = new JButton("SEARCH"); // creating SEARCH button
		searchButton.setBounds(145, 250, 100, 40);
		searchButton.setFont(new Font("Calibri", Font.BOLD, 17));
		searchFrame.add(searchButton);

		try { // Setting background image
			BufferedImage img = ImageIO.read(new File(imgPath.toAbsolutePath().toString()));
			JLabel background = new JLabel(new ImageIcon(img));
			background.setBounds(0, -30, 400, 355);
			searchFrame.add(background);
		} catch (IOException e) {
			e.printStackTrace();
		}

		searchFrame.setBounds(100, 100, 400, 355);
		searchFrame.setLayout(null);
		searchFrame.setResizable(false);
		searchFrame.setVisible(true);

		bookNameField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				bookNameField.setText("");
			}

			public void focusLost(FocusEvent e) {
			}

		});

		authorNameField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				authorNameField.setText("");
			}

			public void focusLost(FocusEvent e) {
			}

		});

		// What happens after SEARCH button is clicked
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (kmpRadioButton.isSelected()) {
					algoSelection = 1;
					//System.out.println(algoSelection);
				} else { // naive selected
					algoSelection = 2;
					//System.out.println(algoSelection);
				}

				if (bookJCheckBox.isSelected() || authorJCheckBox.isSelected()) {
					if (bookJCheckBox.isSelected()) {
						bookNameString = bookNameField.getText().toString();
						// search by book name
					}
					if (authorJCheckBox.isSelected()) {
						authorNameString = authorNameField.getText().toString();
						// search by author name
					}

					StringMatchingController stringMatchingController = new StringMatchingController(action,
							algoSelection, bookNameString, authorNameString);
					resString = stringMatchingController.getResponse();
					JLabel label = new JLabel(resString);
					label.setFont(new Font("Tahoma", Font.BOLD, 17));
					label.setForeground(Color.BLACK);
					JOptionPane.showMessageDialog(searchFrame, label, "SEARCH Response",
							JOptionPane.INFORMATION_MESSAGE);
					searchFrame.setVisible(false);
				}

				else {
					// If the fields are empty, a warning message pops up
					String noSearchTermString = "Please select & fill in what would you like to search!";
					JLabel msgLabel = new JLabel(noSearchTermString);
					msgLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
					msgLabel.setForeground(Color.BLACK);
					JOptionPane.showMessageDialog(searchFrame, msgLabel, "STOP", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

	}

}
