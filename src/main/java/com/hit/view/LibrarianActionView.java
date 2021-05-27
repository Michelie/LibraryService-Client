package com.hit.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class LibrarianActionView implements View {
	private JFrame librarianFrame;
	private JLabel welcomeLibrarianLabel;
	private String pathFile = null;
	static Path imgPath;

	public LibrarianActionView() {
		this.pathFile = "/src/main/resources/booksImg2.jpg";
		imgPath = Paths.get(System.getProperty("user.dir") + pathFile);
		initialize();

	}

	@Override
	public void initialize() {
		librarianFrame = new JFrame("Librarian");
		librarianFrame.setLayout(null);
		librarianFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		welcomeLibrarianLabel = new JLabel(
				"<html>Hello librarian!<br>Please choose what would you<br/>like to do:</html>");
		welcomeLibrarianLabel.setFont(new Font("Calibri", Font.BOLD, 22));
		welcomeLibrarianLabel.setForeground(Color.BLACK);
		welcomeLibrarianLabel.setBounds(15, 15, 390, 80);

		librarianFrame.add(welcomeLibrarianLabel);
		librarianFrame.pack();

		JButton searchButton = new JButton("SEARCH BOOK");// creating buttons for SEARCH, REMOVE, ADD
		searchButton.setBounds(12, 160, 120, 50);
		searchButton.setFont(new Font("Calibri", Font.BOLD, 14));
		JButton removeButton = new JButton("REMOVE BOOK");
		removeButton.setBounds(75, 220, 122, 50);
		removeButton.setFont(new Font("Calibri", Font.BOLD, 13));
		JButton addButton = new JButton("ADD BOOK");
		addButton.setBounds(140, 160, 120, 50);
		addButton.setFont(new Font("Calibri", Font.BOLD, 15));

		librarianFrame.add(addButton);
		librarianFrame.add(removeButton);
		librarianFrame.add(searchButton);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				new SearchView();
			}
		});

		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				new AddView();
			}
		});

		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				new RemoveView();
			}
		});

		try {
			BufferedImage img = ImageIO.read(new File(imgPath.toAbsolutePath().toString()));
			JLabel background = new JLabel(new ImageIcon(img));
			background.setBounds(0, -20, 400, 355);
			librarianFrame.add(background);
			librarianFrame.pack();
		} catch (IOException e) {
			e.printStackTrace();
		}

		librarianFrame.setBounds(100, 100, 400, 355);
		librarianFrame.setLayout(null);
		librarianFrame.setResizable(false);
		librarianFrame.setVisible(true);

	}

}
