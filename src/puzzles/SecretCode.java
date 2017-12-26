package puzzles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * GUI for entering a secret code.  Correct answer brings up a yes picture,
 *   wrong answer brings up a no picture.
 * Code is a string separated by commas.
 * ShowCorrect flag controls whether we display feedback for each component of the code in color
 * 
 * Intended use is for puzzles for kids, entering the correct code can bring up a picture showing the 
 *   location of a prize.
 * 
 * @author bbenyo
 *
 */
public class SecretCode {

	static private String Code="24:28:130:450:48:79";		
	static private String NoPic = "resources/ho-ho-no.png";  // Show on incorrect answer
	static private String YesPic = "resources/Prize.png";    // Show on correct answer
	
	protected String[] codeList;
	protected JTextField[] codeInput;
	
	boolean showCorrect = true;
	boolean debug = false;
	
	// Parameter is the code, separated by commas.
	public SecretCode(String code) {
		codeList = code.split(":");
		jInit();		
	}
	
	// TODO: Multiple rows for the text boxes if there are enough
	//    Right now there's only a single row
	private void jInit() {
		final JFrame mainFrame = new JFrame();
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		JPanel codePanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JLabel topLabel = new JLabel("Enter the code:");
		topLabel.setHorizontalAlignment(SwingConstants.CENTER);
		topLabel.setFont(new Font("Helvetica", Font.BOLD, 30));
		
		//codePanel.setLayout(new FlowLayout());
		codeInput = new JTextField[codeList.length];
		for (int i=0; i<codeList.length; ++i) {
			codeInput[i] = new JTextField("");
			codeInput[i].setBackground(Color.lightGray);
			codeInput[i].setForeground(Color.blue);
			codeInput[i].setFont(new Font("Arial", Font.BOLD, 120));
			codeInput[i].setPreferredSize(new Dimension(200,225));
			final int myIndex = i;
			codeInput[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String val = codeInput[myIndex].getText();
					if (val.trim().equalsIgnoreCase(codeList[myIndex])) {
						if (showCorrect) {
							codeInput[myIndex].setForeground(Color.green);
						}
						//checkAll();
					} else {
						if (showCorrect) {
							codeInput[myIndex].setForeground(Color.red);
						}
						if (debug) {
							System.out.println("Got: "+val+" Expected: "+codeList[myIndex]);
						}
					}
				}
			});
			codePanel.add(codeInput[i]);
			JSeparator s1 = new JSeparator();
			s1.setBackground(Color.black);
			codePanel.add(s1);
		}
		
		mainPanel.add(topLabel, BorderLayout.NORTH);
		mainPanel.add(codePanel, BorderLayout.CENTER);
		JButton quitButton = new JButton("Quit");
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		buttonPanel.add(quitButton);
		
		JButton submitButton = new JButton("Check Answer");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!checkAll()) {
					ImageFrame noFrame = new ImageFrame(NoPic);
					noFrame.setLocationRelativeTo(mainFrame);
					noFrame.setVisible(true);
				} else {
					ImageFrame yesFrame = new ImageFrame(YesPic);
					yesFrame.setLocationRelativeTo(mainFrame);
					yesFrame.setVisible(true);
				}
			}
		});
		buttonPanel.add(submitButton);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		mainFrame.setContentPane(mainPanel);
		mainFrame.setSize(new Dimension(50 + (codeList.length * 225), 400));
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}
	
	/**
	 * Iterate through the text box list and check each result versus the code
	 * @return
	 */
	protected boolean checkAll() {
		boolean wrong = false;
		for (int i=0; i<codeList.length; ++i) {
			String iVal = codeInput[i].getText();
			String eVal = codeList[i];
			if (!iVal.trim().equalsIgnoreCase(eVal)) {
				if (debug) {
					System.err.println(i+" expected "+eVal+" got "+iVal);
				}
				if (showCorrect) {
					codeInput[i].setForeground(Color.red);
				}
				wrong = true;
			} else {
				if (showCorrect) {
					codeInput[i].setForeground(Color.green);
				}
			}
		}
		return !wrong;
	}
	
	public static void main(String[] args) {
		new SecretCode(Code);
	}
	
}
