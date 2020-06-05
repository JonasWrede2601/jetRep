package Server;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.util.List;

import Client.ClientInterface;
import Session.SessionInterface;
import Session.Session;

import java.util.ArrayList;

public class Server extends UnicastRemoteObject implements ServerInterface {
	List sessionList = new ArrayList();
	String solution;
	String tmpSolution;
	int lifes=12;

	public Server() throws RemoteException {
	}
	
	public static void main(String args[]) {
		
		try {
			Registry reg=LocateRegistry.createRegistry(2344);
			reg.rebind("Galgenmann", new Server());
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}


	
	public void sendAnswer(String message, Session s) {
		Session tmpSession;
		String tmpSolutionSplit[];
		String tmpSolutionSave="";
		
		boolean found=false;
		
		for(int j=0; j < sessionList.size(); j++) {
			tmpSession = (Session)sessionList.get(j);
			try {
					tmpSession.getClientInt().getMessage(s.getName(),message);
			} catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
		}

		if(message.length()!=1) {
			
			if(message.equals(solution)) {
				for(int j=0; j < sessionList.size(); j++) {
					tmpSession = (Session)sessionList.get(j);
					try {
							tmpSession.getClientInt().getMessage("Answer-Bot",message+" ist das Lösungswort!");
					} catch(Exception ex) {
						System.out.println(ex.getMessage());
					}
				}
			}
			else {
				lifes-=1;
				for(int j=0; j < sessionList.size(); j++) {
					tmpSession = (Session)sessionList.get(j);
					try {
							tmpSession.getClientInt().getMessage("Answer-Bot",message+" ist falsch!");
							tmpSession.getClientInt().getMessage("Answer-Bot", "Noch "+ lifes+"Leben übrig.");
					} catch(Exception ex) {
						System.out.println(ex.getMessage());
					}
				}
			}
		}
		
		else if(message.length()==1) {
			char input=message.charAt(0);
			suche:
			for(int i=0; i<solution.length();i++) {
				if(input==solution.charAt(i)) {
					found=true;
					for(int j=0; j < sessionList.size(); j++) {
						tmpSession = (Session)sessionList.get(j);
						try {
							tmpSession.getClientInt().getMessage("Answer-Bot", message.charAt(0)+" ist im Lösungswort enthalten!");
						} catch(Exception ex) {
							System.out.println(ex.getMessage());
						}
					}
					break suche;
				}
			}
			
			
			if(found==true) {
				tmpSolutionSplit=tmpSolution.split(" ");
				for(int i=0; i<solution.length();i++) {
					if(input==solution.charAt(i)){
						tmpSolutionSplit[i]=String.valueOf(input);
					}
				}
				for(int k=0; k<tmpSolutionSplit.length;k++) {
					tmpSolutionSave+=tmpSolutionSplit[k]+" ";
				}
			}
			tmpSolution=tmpSolutionSave;
			}
			
			if(found==false) {
				lifes-=1;
				for(int j=0; j < sessionList.size(); j++) {
					tmpSession = (Session)sessionList.get(j);
					try {
						tmpSession.getClientInt().getMessage("Answer-Bot", "Falsch: "+ message.charAt(0)+" ! \n Nur noch "+lifes+" Fehlversuche übrig!");
					} catch(Exception ex) {

					}
				}
			}
			for(int j=0; j < sessionList.size(); j++) {
				tmpSession = (Session)sessionList.get(j);
				try {
					tmpSession.getClientInt().getMessage("Answer-Bot", tmpSolution+" wurde bisher erraten!");
				} catch(Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
		}



	public void sendMessage(String message, Session session) {
		Session tmpSession;
		for(int j=0; j < sessionList.size(); j++) {
			tmpSession = (Session)sessionList.get(j);
			try {
				tmpSession.getClientInt().getMessage(session.getName(), message);

			} catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
	
	public SessionInterface makeSession(String name, ClientInterface handle) throws RemoteException {
		System.out.println(name +" ist der Sitzung beigetreten.");
		SessionInterface sessionInt = new Session(this, name, handle);
		sessionList.add(sessionInt);
		return sessionInt;
	}



	@Override
	public int getSessionNumber() {
		return sessionList.size();
	}

	@Override
	public void setSolution(String solution) throws RemoteException {
		this.solution=solution;
		for (int i=0; i<solution.length();i++) {
			this.tmpSolution+="_ ";
		}
	}

	/*public void reset() {
		solution="";
		tmpSolution="";
		lifes=12;
	}*/

}