package mediator;

import model.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteModel extends Remote
{
  public void addMessage(Message message, RemoteSender sender) throws
      RemoteException;

}
