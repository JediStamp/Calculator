package userInput;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CalculatorGUI extends JPanel{
	private static final long serialVersionUID = 1L;
	JFrame frame;
	JButton quadButton, numConvButton, trigButton, logicButton, lcdButton;
	
	public CalculatorGUI() {
		super(new BorderLayout());
		frame = new JFrame("Tech Math Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addCalculations();
		// Create the calculations panel
		JPanel panelCalcList = addCalculations();

		frame.getContentPane().add(panelCalcList, BorderLayout.LINE_START);
		frame.setSize(450, 300);
		frame.setVisible(true);
	}

	private JPanel addCalculations() {
		JPanel choices = new JPanel(new GridLayout(3, 2));
		choices.add(new JButton ("Lowest Common Denominator"));
		choices.add(new JButton("Triangle Calculations"));
		choices.add(new JButton("Quadratic Equation"));
		numConvButton = new JButton("Number Systems Converter");
		choices.add(numConvButton);
		logicButton = new JButton("Calculate Truth Tables");
		choices.add(logicButton);
		
		return choices;
	}
}
