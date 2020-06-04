package Model;

import java.rmi.RemoteException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server{

	
	protected Server() throws RemoteException {
		super();
	}

	public static void main(String args[]) throws RemoteException{
		try {
			Registry reg=LocateRegistry.createRegistry(2344);
			reg.rebind("serverInterface", new ServerImp());
			System.out.println("Server ready");
		
			
		}catch(RemoteException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	

}
