package Client;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {
	
	//Interface für die Client Klasse, notwendig um alle Clients "Nachrichten" entfangen zu lassen

	public void getMessage(String name, String message) throws RemoteException;

}