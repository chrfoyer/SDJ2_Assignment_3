package mediator;

import mediator.RemoteModel;
import mediator.RemoteSender;
import model.Message;
import model.MessageList;
import model.Model;
import model.ModelManager;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
public class RmiServer implements RemoteModel
{
  private Model model;
  private MessageList messageList;

  public RmiServer() throws RemoteException, MalformedURLException {
    model = new ModelManager();
    messageList = model.getAllMessagesForDay("7");
    startServer();
  }

  private void startRegistry() throws RemoteException
  {
    try{
      Registry reg = LocateRegistry.createRegistry(1099);
      System.out.println("Registry started....");
    }
    catch (java.rmi.server.ExportException e)
    {
      System.out.println("Registry already started? " + e.getMessage());
    }
  }

  private void startServer() throws RemoteException, MalformedURLException {
    startRegistry();
    UnicastRemoteObject.exportObject(this, 0);
    Naming.rebind("Case", this);
    System.out.println("server started...");
  }




  @Override public void addMessage(Message message, RemoteSender sender)
      throws RemoteException
  {
    messageList.addMessage(message);
    System.out.println(message);
    sender.replyMessage(message);
  }
}

