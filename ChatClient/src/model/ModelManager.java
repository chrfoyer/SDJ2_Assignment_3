package model;

import mediator.RemoteModel;
import mediator.RmiClient;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ModelManager implements Model
{
  // todo make this a listener for the client so that it can be more independent from the mediator package

  private PropertyChangeSupport property = new PropertyChangeSupport(this);
  private String username;
  private MessageList messageList;
  private RmiClient client;

  @Override public void setUsername(String userName)
  {
    this.username = userName;
    initializeChat();
    property.firePropertyChange("SET_USERNAME", true, username);
  }

  @Override public void initializeChat()
  {
    client = new RmiClient();
    client.addListener(this);
    messageList = MessageList.getInstance(
        LocalDateTime.now().getDayOfMonth() + "");


  }

  @Override public MessageList getAllMessagesForDay(String day)
  {
    return MessageList.getInstance(day);
  }

  @Override
  public void sendMessage(Message message) {
    messageList.addMessage(message);
    // todo ignored, remove messagelist client side
    try {
      client.send(message);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
    //property.firePropertyChange("SEND_MESSAGE", message, username);
    System.out.println(message.toString());
  }

  @Override public void addMessage(Message message)
  {
    // messageList.addMessage(message);
    property.firePropertyChange("NEW_MESSAGE", message, username);
  }

  public String getUsername()
  {
    return username;
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    property.removePropertyChangeListener(listener);
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    if (evt.getPropertyName().equals("Log")) {
      addMessage((Message) evt.getNewValue());
    }
  }
}
