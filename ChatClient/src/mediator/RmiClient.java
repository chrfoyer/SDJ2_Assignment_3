package mediator;

import mediator.RemoteModel;
import mediator.RemoteSender;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RmiClient implements RemoteSender
{
  private RemoteModel server;

  public RmiClient()
  {
    try
    {
      server = (RemoteModel) Naming.lookup("rmi://localhost:1099/Case");
      UnicastRemoteObject.exportObject(this, 0);
      Naming.rebind("Case", this);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
  }

  public void send(String text) throws RemoteException
  {
    server.addMessage(text,this);
  }

  @Override public void replyMessage(String message) throws RemoteException
  {
    System.out.println(message);
  }
}

