package mediator;

import mediator.RemoteModel;
import mediator.RemoteSender;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
public class RmiServer implements RemoteModel
{
  private ArrayList<String> messageList;

  public RmiServer() throws RemoteException
  {
    messageList = new ArrayList<>();
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

  private void startServer() throws RemoteException{
    startRegistry();
    System.out.println("server started...");
  }




  @Override public void addMessage(String message, RemoteSender sender)
      throws RemoteException
  {
    messageList.add(message);
    System.out.println(message);
    sender.replyMessage("Thank You");
  }
}

