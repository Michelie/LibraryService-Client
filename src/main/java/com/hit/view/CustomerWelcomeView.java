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

public class CustomerWelcomeView implements View {
	private JFrame customerFrame;
	private JLabel welcomeCustomerLabel;
	private JButton searchButton;
	private String pathFile = null;
	static Path imgPath;

	public CustomerWelcomeView() {
		this.pathFile = "/src/main/resources/booksImg2.jpg";
		imgPath = Paths.get(System.getProperty("user.dir") + pathFile);
		initialize();
	}

	@Override
	public void initialize() {
		customerFrame = new JFrame("Customer");
		customerFrame.setLayout(null);
		customerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		welcomeCustomerLabel = new JLabel(
				"<html>Hello customer!<br>In order to search a book in the library please press SEARCH BOOK</html>");
		welcomeCustomerLabel.setFont(new Font("Calibri", Font.BOLD, 21));
		welcomeCustomerLabel.setForeground(Color.black);
		welcomeCustomerLabel.setBounds(10, 25, 390, 80);

		customerFrame.add(welcomeCustomerLabel);
		customerFrame.pack();

		searchButton = new JButton("SEARCH BOOK");// creating instance of JButton
		searchButton.setFont(new Font("Calibri", Font.BOLD, 16));
		searchButton.setBounds(105, 190, 140, 50);
		customerFrame.add(searchButton);

		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// f.setVisible(false);
				new SearchView();
			}
		});

		try {
			BufferedImage img = ImageIO.read(new File(imgPath.toAbsolutePath().toString()));
			JLabel background = new JLabel(new ImageIcon(img));
			background.setBounds(0, -20, 400, 355);
			customerFrame.add(background);
			customerFrame.pack();
		} catch (IOException e) {
			e.printStackTrace();
		}

		customerFrame.setBounds(100, 100, 400, 355);
		customerFrame.setLayout(null);
		customerFrame.setResizable(false);
		customerFrame.setVisible(true);

	}

}
