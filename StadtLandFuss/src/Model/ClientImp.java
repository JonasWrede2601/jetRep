package Model;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import View.AdminWindow;

public class ClientImp {
	private String name;
	private static ServerInterface in;
	
	
	public ClientImp(String name) {
		this.name=name;
	}
	
	public static void main(String args[]) {
		
		String input=JOptionPane.showInputDialog(null, "Nutzernamen eingeben");
	
		try {
			in=(ServerInterface) Naming.lookup("rmi://localhost:2344/serverInterface");
			in.addClient(input);
			
			if(in.isAdmin()) {
				AdminWindow aw=new AdminWindow("Runde vorbereiten");
			}else {
				JOptionPane.showMessageDialog(null, "Warten bis Admin die Runde startet...");
			}
			
			
			
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName() {
		this.name=name;
	}
	
	public static ServerInterface getServerInterface() {
		return in;
	}
	
}
