import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.util.ArrayList;

public class FrameObj extends JFrame
	{
		private static final int WIDTH = 600;
		private static final int HEIGHT = 500;
		
		private JLabel pokemonL, locationL, ballTypeL, probabilityL;
		private JTextField pokemonTF, locationTF, ballTypeTF, probabilityTF;
		private JButton calculateB, exitB;
		
		private CalculateButtonHandler cbHandler;
		private ExitButtonHandler ebHandler;
		
		public FrameObj()
		{
			pokemonL = new JLabel("Enter the Pokemon's name in all Lowercase: ", SwingConstants.RIGHT);
			locationL = new JLabel("Enter your location in all lowercase: ", SwingConstants.RIGHT);
			ballTypeL = new JLabel("Enter the ball type (pokeball, great, or ultra)", SwingConstants.RIGHT);
			probabilityL = new JLabel("Probability of Capture: ", SwingConstants.RIGHT);
			
			pokemonTF = new JTextField(30);
			locationTF = new JTextField(30);
			ballTypeTF = new JTextField(30);
			probabilityTF = new JTextField(30);
						
			calculateB = new JButton("Calculate");
			cbHandler = new CalculateButtonHandler();
			calculateB.addActionListener(cbHandler);
			exitB = new JButton("Exit");
			ebHandler = new ExitButtonHandler();
			exitB.addActionListener(ebHandler);
			
			
			setTitle("Pokemon Catch Rate Calculator");
			
			Container pane = getContentPane();
			pane.setLayout(new GridLayout(5, 2));
			
			pane.add(pokemonL);
			pane.add(pokemonTF);
			pane.add(locationL);
			pane.add(locationTF);
			pane.add(ballTypeL);
			pane.add(ballTypeTF);
			pane.add(probabilityL);
			pane.add(probabilityTF);
			pane.add(calculateB);
			pane.add(exitB);
			
			setSize(WIDTH, HEIGHT);
			setVisible(true);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
		
		private class CalculateButtonHandler implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				//Do the Calculations here
				PokeList allP = new PokeList();
				ArrayList<Pokemon> allPoke = new ArrayList<Pokemon>();
				allPoke.addAll(allP.createList());
				ArrayList<Pokemon> notFound = new ArrayList<Pokemon>();
				notFound.addAll(allP.createNotFound());
				String ballTypeIn = ballTypeTF.getText();
				String pokemonIn = pokemonTF.getText();
				String locationIn = locationTF.getText();
				Algorithm algo = new Algorithm(notFound, allPoke, ballTypeIn, pokemonIn, locationIn);
				
				if(!algo.foundInWild())
				{
					probabilityTF.setText("Can't be found in wild!");
				}
				else if(!algo.marrowakGhost())
				{
					probabilityTF.setText("Can't be caught here!");
				}
				else if(algo.isMasterBall())
				{
					probabilityTF.setText("100% Chance!");
				}
				else
				{
					probabilityTF.setText("YOU GOT HERE!");
				}
				
			}
		}
		public class ExitButtonHandler implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		}
	}