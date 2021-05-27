package com.hit.view;

import java.awt.Color;
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
import javax.sound.sampled.LineUnavailableException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.hit.controller.StringMatchingController;

public class AddView implements View {

	private JFrame addFrame;
	private JTextField bookNameField;
	private JTextField authorNameField;
	private JLabel welcomeAddLabel;
	private JLabel bookLabel;
	private JLabel authorLabel;
	private JButton addButton;

	public String bookNameString;
	public String authorNameString;
	public String action;
	public String resString;

	private String pathFile = null;
	static Path imgPath;

	public AddView() {
		this.action = "ADD";
		this.pathFile = "/src/main/resources/booksImg3.jpg";
		imgPath = Paths.get(System.getProperty("user.dir") + pathFile);
		initialize();
	}

	@Override
	public void initialize() {
		// Initializing the frame for ADD action

		addFrame = new JFrame("ADD Book");
		addFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		welcomeAddLabel = new JLabel("<html>Please enter the name of the book<br>you would like to add:</html>");
		welcomeAddLabel.setBounds(10, 12, 350, 70);
		welcomeAddLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		welcomeAddLabel.setForeground(Color.BLACK);
		addFrame.add(welcomeAddLabel);

		bookLabel = new JLabel("Book name:"); // Creating labels for book and author
		bookLabel.setBounds(25, 101, 130, 29);
		bookLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bookLabel.setForeground(Color.BLACK);
		bookLabel.setOpaque(false);
		addFrame.add(bookLabel);

		authorLabel = new JLabel("Author name:");
		authorLabel.setBounds(25, 161, 130, 29);
		authorLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		authorLabel.setForeground(Color.BLACK);
		authorLabel.setOpaque(false);
		addFrame.add(authorLabel);

		bookNameField = new JTextField("Enter name of book...", 30);
		bookNameField.setBounds(118, 101, 130, 29);
		addFrame.add(bookNameField);

		authorNameField = new JTextField("Enter author of book...", 30);
		authorNameField.setBounds(118, 161, 130, 29);
		addFrame.add(authorNameField);

		addButton = new JButton("ADD BOOK"); // creating ADD button
		addButton.setBounds(115, 233, 110, 50);
		addButton.setFont(new Font("Calibri", Font.BOLD, 15));
		addFrame.add(addButton);

		try { // Setting background image
			BufferedImage img = ImageIO.read(new File(imgPath.toAbsolutePath().toString()));
			JLabel background = new JLabel(new ImageIcon(img));
			background.setBounds(0, -30, 400, 355);
			addFrame.add(background);
		} catch (IOException e) {
			e.printStackTrace();
		}

		addFrame.setBounds(100, 100, 400, 355);
		addFrame.setLayout(null);
		addFrame.setResizable(false);
		addFrame.setVisible(true);

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

		// What happens after ADD button is clicked
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				bookNameString = bookNameField.getText().toString();
				authorNameString = authorNameField.getText().toString();

				if ((bookNameString.equals("") || bookNameString.equals("Enter name of book..."))
						|| (authorNameString.equals("") || authorNameString.equals("Enter name of book..."))) {
					// If the fields are empty, a warning message pops up
					String noAddTermString = "Please enter name of book & author to add!";
					JLabel msgLabel = new JLabel(noAddTermString);
					msgLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
					msgLabel.setForeground(Color.BLACK);
					JOptionPane.showMessageDialog(addFrame, msgLabel, "STOP", JOptionPane.WARNING_MESSAGE);
				}

				else {
					StringMatchingController stringMatchingController = new StringMatchingController(action, 1,
							bookNameString, authorNameString);
					resString = stringMatchingController.getResponse();
					JLabel label = new JLabel(resString);
					label.setFont(new Font("Tahoma", Font.BOLD, 15));
					label.setForeground(Color.DARK_GRAY);
					JOptionPane.showMessageDialog(addFrame, label, "ADD Response", JOptionPane.INFORMATION_MESSAGE);

					addFrame.setVisible(false);

				}

			}
		});

	}
}
