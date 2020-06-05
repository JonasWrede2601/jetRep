package Server;
import java.rmi.Remote;
import java.rmi.RemoteException;

import Client.ClientInterface;
import Session.SessionInterface;

public interface ServerInterface extends Remote {

  public SessionInterface makeSession(String name, ClientInterface clientInt) throws RemoteException;
  
  public int getSessionNumber() throws RemoteException;
  
  public void setSolution(String solution) throws RemoteException;
  
}

