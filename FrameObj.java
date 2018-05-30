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

@SuppressWarnings("serial")
public class FrameObj extends JFrame
	{
		private static final int WIDTH = 600;
		private static final int HEIGHT = 500;
		
		private JLabel pokemonL, locationL, ballTypeL, statusL, maxHPL, curHPL,probabilityL;
		private JTextField pokemonTF, locationTF, ballTypeTF, statusTF, maxHPTF, curHPTF, probabilityTF;
		private JButton calculateB, exitB;
		
		private CalculateButtonHandler cbHandler;
		private ExitButtonHandler ebHandler;
		
		public FrameObj()
		{
			pokemonL = new JLabel("Enter the Pokemon's name in all Lowercase: ", SwingConstants.RIGHT);
			locationL = new JLabel("Enter your location in all lowercase: ", SwingConstants.RIGHT);
			ballTypeL = new JLabel("Enter the ball type (pokeball, great, ultra, or master)", SwingConstants.RIGHT);
			statusL = new JLabel("Enter any status effects: ", SwingConstants.RIGHT);
			maxHPL = new JLabel("Enter the max HP of pokemon: ", SwingConstants.RIGHT);
			curHPL = new JLabel("Enter the current HP of pokemon: ", SwingConstants.RIGHT);
			probabilityL = new JLabel("Probability of Capture: ", SwingConstants.RIGHT);
			
			pokemonTF = new JTextField(30);
			locationTF = new JTextField(30);
			ballTypeTF = new JTextField(30);
			statusTF = new JTextField(30);
			maxHPTF = new JTextField(30);
			curHPTF = new JTextField(30);
			probabilityTF = new JTextField(30);
						
			calculateB = new JButton("Calculate");
			cbHandler = new CalculateButtonHandler();
			calculateB.addActionListener(cbHandler);
			exitB = new JButton("Exit");
			ebHandler = new ExitButtonHandler();
			exitB.addActionListener(ebHandler);
			
			
			setTitle("Pokemon Catch Rate Calculator");
			
			Container pane = getContentPane();
			pane.setLayout(new GridLayout(8, 2));
			
			pane.add(pokemonL);
			pane.add(pokemonTF);
			pane.add(locationL);
			pane.add(locationTF);
			pane.add(ballTypeL);
			pane.add(ballTypeTF);
			pane.add(statusL);
			pane.add(statusTF);
			pane.add(maxHPL);
			pane.add(maxHPTF);
			pane.add(curHPL);
			pane.add(curHPTF);
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
				String statusIn = statusTF.getText();
				int curHPIn = Integer.parseInt(curHPTF.getText());
				int maxHPIn = Integer.parseInt(maxHPTF.getText());
				Algorithm algo = new Algorithm(notFound, allPoke, ballTypeIn, pokemonIn, locationIn, statusIn, maxHPIn, curHPIn);
				
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
					int succesful = 0;
					@SuppressWarnings("unused")
					int faliure = 0;
					for(int i = 0; i < 100; i++)
					{
						//System.out.println("Trial #: " + i);
						int r1 = algo.genRandOne();
						//System.out.println("R ONE: " + r1);
						int s = algo.status();
						//System.out.println("S: " + s);
						int rPrime = algo.randomPrime();
						//System.out.println("R PRIME: " + rPrime);
						if(rPrime < 0)
						{
							succesful++;
						}
						else
						{
							int hpFact = algo.hitPointFactor();
							// System.out.println(" HP FACTOR: " + hpFact);
							if(algo.catchVersusRandPrime())
							{
								int r2 = algo.rand2();
								//System.out.println("R2: " + r2);
								if(r2 <= hpFact)
								{
									succesful ++;
								}
								else
								{
									faliure ++;
								}
							}
							else
							{
								faliure ++;
							}
						}
					}
					probabilityTF.setText("Probability of catch: " + succesful + "%");
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