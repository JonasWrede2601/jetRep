package View;

import java.awt.BorderLayout;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Model.ClientImp;
import Model.ServerInterface;

public class AdminWindow {
	
	
 // View uses Swing framework to display UI to user
	private JFrame frame;
	private JLabel kategorie1Label;
	private JLabel kategorie2Label;
	private JLabel kategorie3Label;
	private JLabel kategorie4Label;
	private JLabel kategorie5Label;

	private JTextField kategorie1TextField;
	private JTextField kategorie2TextField;
	private JTextField kategorie3TextField;
	private JTextField kategorie4TextField;
	private JTextField kategorie5TextField;

	private JButton saveButton;

	public AdminWindow(String title) throws Exception{
		
		
		frame = new JFrame(title);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);


		// Create UI elements
		kategorie1Label = new JLabel("Kategorie 1: ");
		kategorie2Label = new JLabel("Kategorie 2: ");
		kategorie3Label = new JLabel("Kategorie 3: ");
		kategorie4Label = new JLabel("Kategorie 4: ");
		kategorie5Label = new JLabel("Kategorie 5: ");

		kategorie1TextField = new JTextField();
		kategorie1TextField.setText("");
		kategorie2TextField = new JTextField();
		kategorie3TextField = new JTextField();
		kategorie4TextField = new JTextField();
		kategorie5TextField = new JTextField();

		saveButton = new JButton("Save");
		saveButton.addActionListener(e -> save());


		// Add UI element to frame
		GroupLayout layout = new GroupLayout(frame.getContentPane());

		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(kategorie1Label)
						.addComponent(kategorie2Label).addComponent(kategorie3Label).addComponent(kategorie4Label).addComponent(kategorie5Label))

				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(kategorie1TextField)
						.addComponent(kategorie2TextField).addComponent(kategorie3TextField).addComponent(kategorie4TextField).addComponent(kategorie5TextField))

				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(saveButton)));

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(kategorie1Label).addComponent(kategorie1TextField))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(kategorie2Label).addComponent(kategorie2TextField))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(kategorie3Label).addComponent(kategorie3TextField))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(kategorie4Label).addComponent(kategorie4TextField))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(kategorie5Label).addComponent(kategorie5TextField))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(saveButton)));

		layout.linkSize(SwingConstants.HORIZONTAL, saveButton);
		frame.getContentPane().setLayout(layout);
	}


	public JFrame getFrame() {
		return frame;
	}
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	public JLabel getKategorie1Label() {
		return kategorie1Label;
	}
	public JLabel getKategorie2Label() {
		return kategorie2Label;
	}
	public JLabel getKategorie3Label() {
		return kategorie3Label;
	}
	public JLabel getKategorie4Label() {
		return kategorie4Label;
	}
	public JLabel getKategorie5Label() {
		return kategorie5Label;
	}
	public void setKategorie1Label(JLabel kategorie1Label) {
		this.kategorie1Label = kategorie1Label;
	}
	
	public void setKategorie2Label(JLabel kategorie2Label) {
		this.kategorie2Label = kategorie2Label;
	}
	
	public void setKategorie3Label(JLabel kategorie3Label) {
		this.kategorie3Label = kategorie3Label;
	}
	
	public void setKategorie4Label(JLabel kategorie4Label) {
		this.kategorie4Label = kategorie4Label;
	}
	
	public void setKategorie5Label(JLabel kategorie5Label) {
		this.kategorie5Label = kategorie5Label;
	}
	
	public String getKategorie1Text() {
		return kategorie1TextField.getText();
	}
	public String getKategorie2Text() {
		return kategorie2TextField.getText();
	}
	public String getKategorie3Text() {
		return kategorie3TextField.getText();
	}
	public String getKategorie4Text() {
		return kategorie4TextField.getText();
	}
	public String getKategorie5Text() {
		return kategorie5TextField.getText();
	}
	public JButton getSaveButton() {
		return saveButton;
	}
	public void setSaveButton(JButton saveButton) {
		this.saveButton = saveButton;
	}


	private void save() {
		ArrayList<String> categories=new ArrayList<>();
		if(!this.getKategorie1Text().equals("")&&!this.getKategorie1Text().equals(" ")) {
			categories.add(this.getKategorie1Text());
		}
		if(!this.getKategorie2Text().equals("")&&!this.getKategorie2Text().equals(" ")) {
			categories.add(this.getKategorie2Text());
		}
		if(!this.getKategorie3Text().equals("")&&!this.getKategorie3Text().equals(" ")) {
			categories.add(this.getKategorie3Text());
		}
		if(!this.getKategorie4Text().equals("")&&!this.getKategorie4Text().equals(" ")) {
			categories.add(this.getKategorie4Text());
		}
		if(!this.getKategorie5Text().equals("")&&!this.getKategorie5Text().equals(" ")) {
			categories.add(this.getKategorie5Text());
		}
		
		try {
			ClientImp.getServerInterface().initCategories(categories);
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}