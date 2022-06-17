package mediator;

import model.Message;
import model.MessageList;
import model.Model;
import model.ModelManager;
import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeHandler;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RmiServer implements RemoteModel
{
  private Model model;
  private MessageList messageList;
  private PropertyChangeHandler<Message, Message> property;

  public RmiServer() throws RemoteException, MalformedURLException {
    this.property = new PropertyChangeHandler<>(this, true);
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

  @Override public void addMessage(Message message, RemoteSender remoteSender)
      throws RemoteException
  {
    messageList.addMessage(message);
    System.out.println(message);
    remoteSender.replyMessage(message);
    property.firePropertyChange("Spoke", null, message);
  }

  @Override
  public boolean addListener(GeneralListener listener, String... propertyNames) throws RemoteException {
    return property.addListener(listener, propertyNames);
  }

  @Override
  public boolean removeListener(GeneralListener listener, String... propertyNames) throws RemoteException {
    return property.removeListener(listener, propertyNames);
  }
}

