package com.hit.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class StringMatchingView implements View {
	public JFrame f;
	private JLabel welcomeLabel;
	private JLabel userLabel;
	private String pathFile = null;
	static Path imgPath;

	public StringMatchingView() {
		this.pathFile = "/src/main/resources/booksImg1.jpg";
		imgPath = Paths.get(System.getProperty("user.dir") + pathFile);
		initialize();
	}

	public void initialize() {
		f = new JFrame("String Matching");// creating instance of JFrame
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(100, 100, 450, 300);

		welcomeLabel = new JLabel("<html>Welcome to Ben & Michelle's<br/> library managment service!</html>");
		welcomeLabel.setFont(new Font("Calibri", Font.BOLD, 22));
		welcomeLabel.setForeground(Color.BLACK);
		welcomeLabel.setBounds(11, 15, 390, 90);
		f.add(welcomeLabel);
		f.pack();

		userLabel = new JLabel("<html> Please choose a user:</html>");
		userLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		userLabel.setForeground(Color.BLACK);
		userLabel.setBounds(16, 135, 390, 90);
		f.add(userLabel);

		JButton librarianButton = new JButton("LIBRARIAN");
		librarianButton.setBounds(40, 225, 115, 50);
		librarianButton.setFont(new Font("Calibri", Font.BOLD, 15));
		JButton customerButton = new JButton("CUSTOMER");
		customerButton.setBounds(230, 225, 115, 50);
		customerButton.setFont(new Font("Calibri", Font.BOLD, 15));

		f.add(customerButton);
		f.add(librarianButton);

		librarianButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				new LibrarianActionView();
				f.setVisible(false);
			}
		});

		customerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				new CustomerWelcomeView();
				f.setVisible(false);
			}
		});

		try {
			BufferedImage img = ImageIO.read(new File(imgPath.toAbsolutePath().toString()));
			JLabel background = new JLabel(new ImageIcon(img));
			background.setBounds(0, 0, 400, 355);
			f.add(background);
			f.pack();
		} catch (IOException e) {
			e.printStackTrace();
		}

		f.setSize(400, 355);
		f.setLayout(null);
		f.setResizable(false);
		f.setVisible(true); // making the frame visible

	}

}
