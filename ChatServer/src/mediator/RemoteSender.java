package mediator;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteSender extends Remote
{
  public void replyMessage(String message) throws RemoteException;
}
