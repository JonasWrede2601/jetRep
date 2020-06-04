package Model;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;


public interface ServerInterface extends Remote{
	
	public boolean addClient(String text) throws RemoteException;
	
	public boolean isAdmin() throws RemoteException;
	
	public ArrayList<String> getClientNames() throws RemoteException;
	
	public void initCategories(ArrayList<String> list) throws RemoteException;
	

}
