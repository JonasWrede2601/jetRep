package Session;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import Client.ClientInterface;
import Server.Server;

import java.rmi.Naming;


public class Session extends UnicastRemoteObject implements SessionInterface {
  Server server;
  String name;
  ClientInterface clientInt;
  boolean gamemaker;

  public Session() throws RemoteException {
  }

  public Session(Server server, String name, ClientInterface clientInt) throws RemoteException {
    this.server = server;
    this.name = name;
    this.clientInt = clientInt;
  }

  public void sendMessage(String message, boolean gamemaker) {
	  if(gamemaker) {
		  server.sendMessage(message, this);
	  }else {
		  server.sendAnswer(message, this);
	  }
    
  }

  public ClientInterface getClientInt() {
    return clientInt;
  }

  public String getName() {
    return name;
  }
  
}