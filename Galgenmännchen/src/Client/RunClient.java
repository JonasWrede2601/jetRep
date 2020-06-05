package Client;
import java.rmi.*;
import javax.swing.*;

import Server.ServerInterface;
import Session.SessionInterface;

import java.awt.*;
import java.awt.event.*;

/*RunClient ist die Klasse die der Spieler ausführt. Sie erbt zur Verinfachung der Oberfläche von JFrame
 * 
 * */
 
public class RunClient extends JFrame {

	boolean gamemaker;		
	ClientInterface clientInt;
	SessionInterface sessionInt;
	ServerInterface serverInt;

	JTextArea textArea=new JTextArea();
	JTextField textField=new JTextField();
	//JLabel textLabel=new JLabel("Lösungswort");
	
	public static void main(String[] args) {
	
		try {
			String name = JOptionPane.showInputDialog(null, "Name eingeben:");
			if(name != null) {
				RunClient runClient = new RunClient(name);
				runClient.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "Weil du versuchst ohne Namen zu spielen wird die Anwendung geschlossen :P.");     
				System.exit(0);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			System.exit(0);
		}
	}

	public RunClient(String name) throws Exception {
		serverInt = (ServerInterface)Naming.lookup("rmi://localhost:2344/Galgenmann");
		clientInt = new Client(this);
		sessionInt = serverInt.makeSession(name, clientInt);


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		textArea.setEditable(false);
		JScrollPane scroller = new JScrollPane();
		scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroller.getViewport().setView(textArea);

		JButton btnClose = new JButton("schließen");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});

		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					sendMessage(textField.getText());
					textField.setText("");     
				} catch(RemoteException ex) {
					ex.printStackTrace();
				}
			}
		});


		getContentPane().add(scroller, BorderLayout.CENTER);
		getContentPane().add(textField, BorderLayout.NORTH);
		//getContentPane().add(textLabel, BorderLayout.SOUTH);
		getContentPane().add(btnClose, BorderLayout.SOUTH);

		setSize(400, 600);


		if(serverInt.getSessionNumber()<=1) {
			gamemaker=true;
		}else {
			gamemaker=false;
		}

		newGame(gamemaker);
	}

	public void getMessage(String name, String message) {
		textArea.append(name+": "+message+"\n");
		textArea.setCaretPosition(textArea.getText().length()-1);
	}

	public void close() {
		System.exit(0);
	}

	public void sendMessage(String message) throws RemoteException {
		if(gamemaker) {
			serverInt.setSolution(message);
			sessionInt.sendMessage("Das Lösungswort steht fest! Los gehts!",gamemaker);
			textField.setVisible(false);
		}else {
			sessionInt.sendMessage(message,gamemaker);
		}
	}


	public void newGame(boolean gamemaker) {

		if(gamemaker==true) {
			textArea.setText("Hallo \n Du bist dran! Gebe ins obige Textfeld den Begriff ein,\n der von den anderen erraten werden soll! \n");
		}else {
			textArea.setText("Warte auf Spielbeginn... \n");
		}
	}

	
}