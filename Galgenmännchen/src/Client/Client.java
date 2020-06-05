package Client;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.Naming;

//Auch Server Objekt, um Anfragen über das Netzwerk entgegen zu nehmen
public class Client extends UnicastRemoteObject implements ClientInterface {
	RunClient runClient;		//

	public Client() throws RemoteException {
	}

	//Konstruktor dem ein runClient Objekt übergeben wird
	public Client(RunClient runClient) throws RemoteException {
		this.runClient = runClient;
	}

	//Methode aus Interface
	@Override
	public void getMessage(String name, String message) {
		runClient.getMessage(name,message);
	}

}