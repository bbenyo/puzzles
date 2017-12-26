package puzzles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** 
 * Simple frame for showing an image with an Ok button
 * TODO: Automatic sizing.
 * @author Brett
 *
 */
public class ImageFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public ImageFrame(String imageName) {	   
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBackground(Color.white); 
	    ImageIcon icon = new ImageIcon(imageName); 
	    int height = icon.getIconHeight();
	    int width = icon.getIconWidth();
	    panel.setSize(width, height);

	    JLabel label = new JLabel(); 
	    label.setIcon(icon); 
	    panel.add(label, BorderLayout.CENTER);
	    
	    JButton okButton = new JButton("Ok");
	    okButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		setVisible(false);
	    		dispose();
	    	}
	    });
	    JPanel bPanel = new JPanel();
	    bPanel.add(okButton);
	    panel.add(bPanel, BorderLayout.SOUTH);
	    this.getContentPane().add(panel); 
	    setSize(width + 50, height + 50);  
	    setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		ImageFrame i1 = new ImageFrame(args.length == 0 ? "src/resources/ho-ho-no.png" : args[0]);
		i1.setVisible(true); 
	}
	
}
