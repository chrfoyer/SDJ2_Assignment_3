package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ModelManager implements Model
{

  private PropertyChangeSupport property = new PropertyChangeSupport(this);
  private String username;
  private MessageList messageList;

  @Override public void setUsername(String userName)
  {
    this.username = userName;
    initializeChat();
    property.firePropertyChange("SET_USERNAME", true, username);
  }

  @Override public void initializeChat()
  {
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
    property.firePropertyChange("SEND_MESSAGE", message, username);
  }

  @Override public void addMessage(Message message)
  {
    messageList.addMessage(message);
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
}
