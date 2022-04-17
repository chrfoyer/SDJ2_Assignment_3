package model;

import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;

public interface Model extends UnnamedPropertyChangeSubject, PropertyChangeListener
{
  public void setUsername(String userName);

  public void initializeChat();

  public MessageList getAllMessagesForDay(String day);
  public void sendMessage(Message message);
  public void addMessage(Message message);
  public String getUsername();

}
