package Session;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SessionInterface extends Remote {

  public void sendMessage(String message, boolean gamemaker) throws RemoteException;

}