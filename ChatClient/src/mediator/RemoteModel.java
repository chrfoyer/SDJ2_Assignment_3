package mediator;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteModel extends Remote
{
  public void addMessage(String message, RemoteSender sender) throws
      RemoteException;

}
