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
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.hit.controller.StringMatchingController;

public class RemoveView implements View {

	private JFrame removeFrame;
	private JTextField bookNameField;
	private JLabel welcomeRemoveLabel;
	private JLabel algoLabel;
	private JLabel bookLabel;
	private JButton removeButton;
	private JRadioButton naiveRadioButton;
	private JRadioButton kmpRadioButton;
	private ButtonGroup bg;

	public int algoSelection;
	public String bookNameString;
	public String action;
	public String resString;
	
	private String pathFile = null;
	static Path imgPath;

	public RemoveView() {
		this.action = "DELETE";
		this.algoSelection = 1;
		this.pathFile = "/src/main/resources/booksImg3.jpg";
		imgPath = Paths.get(System.getProperty("user.dir") + pathFile);
		initialize();
	}

	@Override
	public void initialize() {
		//Initializing the frame for REMOVE action
		removeFrame = new JFrame("REMOVE BOOK");
		removeFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		welcomeRemoveLabel = new JLabel("<html>Please enter the name of the book<br>you would like to remove:</html>");
		welcomeRemoveLabel.setBounds(10, 12, 350, 70);
		welcomeRemoveLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		welcomeRemoveLabel.setForeground(Color.BLACK);

		removeFrame.add(welcomeRemoveLabel);
		//removeFrame.pack();

		bookLabel = new JLabel("Book name:");  //Creating labels for book and serach algorithm
		bookLabel.setBounds(22, 101, 120, 29);
		bookLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bookLabel.setForeground(Color.BLACK);
		bookLabel.setOpaque(false);
		removeFrame.add(bookLabel);

		bookNameField = new JTextField("Enter name of book...", 30);
		bookNameField.setBounds(115, 101, 130, 29);
		bookNameField.setOpaque(true);
		removeFrame.add(bookNameField);

		algoLabel = new JLabel("<html>Please choose the algorithm<br/> for the search:</html>");
		algoLabel.setFont(new Font("Calibri", Font.BOLD, 17));
		algoLabel.setForeground(Color.BLACK);
		algoLabel.setBounds(15, 155, 300, 40);
		removeFrame.add(algoLabel);

		
		kmpRadioButton = new JRadioButton("1. KMP", true);  //Creating radio buttons for the algorithms choice
		kmpRadioButton.setBounds(20, 210, 100, 15);
		kmpRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		kmpRadioButton.setForeground(Color.BLACK);
		kmpRadioButton.setOpaque(false);
		
		naiveRadioButton = new JRadioButton("2. Naive");
		naiveRadioButton.setBounds(130, 210, 100, 15);
		naiveRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		naiveRadioButton.setForeground(Color.BLACK);
		naiveRadioButton.setOpaque(false);
		bg = new ButtonGroup();
		bg.add(kmpRadioButton);
		bg.add(naiveRadioButton);
		removeFrame.add(kmpRadioButton);
		removeFrame.add(naiveRadioButton);

		removeButton = new JButton("REMOVE"); // creating REMOVE button
		removeButton.setBounds(145, 250, 100, 40);
		removeButton.setFont(new Font("Calibri", Font.BOLD, 17));
		removeFrame.add(removeButton);
		
		try {  //Setting background image
			BufferedImage img = ImageIO.read(new File(imgPath.toAbsolutePath().toString()));
			JLabel background = new JLabel(new ImageIcon(img));
			background.setBounds(0, -30, 400, 355);
			removeFrame.add(background);
			//searchFrame.pack();
		} catch (IOException e) {
			e.printStackTrace();
		}

		removeFrame.setBounds(100, 100, 400, 355);
		removeFrame.setLayout(null);
		removeFrame.setResizable(false);
		removeFrame.setVisible(true);

		bookNameField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				bookNameField.setText("");
			}

			public void focusLost(FocusEvent e) {
			}

		});
    
		//What happens after REMOVE button is clicked
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				if (kmpRadioButton.isSelected()) {
					algoSelection = 1;
					//System.out.println(algoSelection);//
				} else { // naive selected
					algoSelection = 2;
					//System.out.println(algoSelection);//
				}
				
				bookNameString = bookNameField.getText().toString();
				
				if(bookNameString.equals("") || bookNameString.equals("Enter name of book..."))
				{//If the fields are empty, a warning message pops up
					String noSearchTermString = "Please enter name of book to remove!";
					JLabel msgLabel = new JLabel(noSearchTermString);
					msgLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
					msgLabel.setForeground(Color.BLACK);
					JOptionPane.showMessageDialog(removeFrame,msgLabel,"STOP",JOptionPane.WARNING_MESSAGE);
				}

				else {
					StringMatchingController stringMatchingController = new StringMatchingController(action, algoSelection, bookNameString);
					resString = stringMatchingController.getResponse();
					JLabel label = new JLabel(resString);
					label.setFont(new Font("Tahoma", Font.BOLD, 16));
					label.setForeground(Color.DARK_GRAY);
					JOptionPane.showMessageDialog(removeFrame, label,"REMOVE Response",JOptionPane.INFORMATION_MESSAGE);
					
					removeFrame.setVisible(false); 
				}

			}
		});

	}

}
