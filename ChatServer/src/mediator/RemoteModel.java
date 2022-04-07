package mediator;

import java.rmi.RemoteException;

public interface RemoteModel
{
  public void addMessage(String message, RemoteSender sender) throws
      RemoteException;

}
