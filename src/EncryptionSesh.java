import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class EncryptionSesh {
	JFrame frame = new JFrame("Text Editor"); 	
	JTextArea inputArea = new JTextArea();
	JTextArea pubKeyArea = new JTextArea();
	JTextArea priKeyArea = new JTextArea();
	JTextArea outputArea = new JTextArea();
	JTextArea nArea = new JTextArea();
	JButton encryptButton = new JButton("Encrypt");
	JButton decryptButton = new JButton("Decrypt");
	JLabel label = new JLabel("Select a file");		
	
	public static void main(String[] args) {
		EncryptionSesh te = new EncryptionSesh();
		te.setupView();
	}
	
	public void setupView () {
		//set up the frame, panel, and menus
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		
		//define the dimensions
		frame.setPreferredSize(new Dimension(1000, 600));
		label.setPreferredSize(new Dimension(800, 40));
		panel.setPreferredSize(new Dimension(800, 100));
		
		inputArea.setEditable(true);
		pubKeyArea.setEditable(true);
		priKeyArea.setEditable(true);
		nArea.setEditable(true);

		frame.add(BorderLayout.PAGE_END,panel);
		
		panel.add(BorderLayout.PAGE_END,label);
		
		//set the action listeners for the menu items
		encryptButton.addActionListener(new Listener());
		encryptButton.setActionCommand("encrypt");
		decryptButton.addActionListener(new Listener());
		decryptButton.setActionCommand("decrypt");
	
		frame.pack();
		frame.setVisible(true);
	}
	
	
	private class Listener implements ActionListener {
		public void actionPerformed (ActionEvent ae) {
			//depending on the action command, do something
			String ac = ae.getActionCommand();

	    	if (ac.equals("encrypt")){
	    		User u = new User();
	    		u.pubKey = Integer.parseInt(pubKeyArea.getText());
	    		u.n = Integer.parseInt(nArea.getText());
	    		int input = Integer.parseInt(inputArea.getText());
	    		int output = u.sendMessage(input);
	    		outputArea.setText(Integer.toString(output));
	    		
	    	} else if (ac.equals("decrypt")){
    			
	    		
	    	}
		}
	}
}