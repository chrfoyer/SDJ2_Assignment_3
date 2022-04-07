package mediator;

import model.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteSender extends Remote
{
  public void replyMessage(Message message) throws RemoteException;
}
