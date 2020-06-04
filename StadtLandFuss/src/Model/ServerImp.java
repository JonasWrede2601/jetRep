package Model;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class ServerImp extends UnicastRemoteObject implements ServerInterface {

	protected ServerImp() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	private ArrayList<ClientImp> clientList=new ArrayList<>();
	private ArrayList<String> categories= new ArrayList<>();

	@Override
	public boolean addClient(String clientname) throws RemoteException {
		if(clientList.size()<4) {
			clientList.add(new ClientImp(clientname));
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isAdmin() {
		if(clientList.size()<=1) {
			return true;
		}
		return false;
	}
	
	
	
	@Override
	public void initCategories(ArrayList<String>liste) {
		categories.clear();
		for(int i=0; i<liste.size();i++) {
			categories.add(liste.get(i));
			System.out.println(i+" : "+categories.get(i));
		}
	}

	@Override
	public ArrayList<String> getClientNames() throws RemoteException {
		ArrayList<String> clientNames=new ArrayList<>();
		for(int i=0; i<clientList.size();i++) {
			clientNames.add(clientList.get(i).getName());
		}
		
		return clientNames;
	}
	

}
