package mediator;

import mediator.RemoteModel;
import mediator.RemoteSender;
import model.Message;
import model.Model;
import model.ModelManager;
import utility.observer.event.ObserverEvent;
import utility.observer.javaobserver.UnnamedPropertyChangeSubject;
import utility.observer.listener.RemoteListener;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RmiClient implements RemoteListener<Message, Message>, UnnamedPropertyChangeSubject
{
  private RemoteModel server;
  private Model model;
  private PropertyChangeSupport property;

  public RmiClient()
  {
    try
    {
      model = new ModelManager();
      property = new PropertyChangeSupport(this);
      server = (RemoteModel) Naming.lookup("rmi://localhost:1099/Case");
      UnicastRemoteObject.exportObject(this, 0);
      server.addListener(this);
      Naming.rebind("Case", this);
      System.out.println("Stub pulled");
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
  }

  public void send(Message message) throws RemoteException
  {
    server.addMessage(message);
  }

  public void setUsername(String username) {
    model.setUsername(username);
  }

  @Override public void propertyChange(ObserverEvent event)
      throws RemoteException
  {
    System.out.println("Property change detected: " + event.getPropertyName());
    System.out.println(event.getValue2());
    property.firePropertyChange("Log", null, event.getValue2());
  }

  @Override
  public void addListener(PropertyChangeListener listener) {
    property.addPropertyChangeListener(listener);
  }

  @Override
  public void removeListener(PropertyChangeListener listener) {
    property.removePropertyChangeListener(listener);
  }
}

