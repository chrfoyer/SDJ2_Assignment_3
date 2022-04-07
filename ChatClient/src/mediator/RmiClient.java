package mediator;

import mediator.RemoteModel;
import mediator.RemoteSender;
import model.Message;
import model.Model;
import model.ModelManager;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.RemoteListener;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RmiClient implements RemoteSender, RemoteListener
{
  private RemoteModel server;
  private Model model;

  public RmiClient()
  {
    try
    {
      model = new ModelManager();
      server = (RemoteModel) Naming.lookup("rmi://localhost:1099/Case");
      UnicastRemoteObject.exportObject(this, 0);
      Naming.rebind("Case", this);
      System.out.println("Stub pulled");
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
  }

  public void send(String text) throws RemoteException
  {
    server.addMessage(new Message(text, model.getUsername()),this);
  }

  @Override
  public void replyMessage(Message message) throws RemoteException {
    System.out.println(message);
  }

  public void setUsername(String username) {
    model.setUsername(username);
  }

  @Override public void propertyChange(ObserverEvent event)
      throws RemoteException
  {

  }
}

