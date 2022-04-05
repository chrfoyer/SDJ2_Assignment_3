package model;

import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

public interface Model extends UnnamedPropertyChangeSubject
{
  public void setUsername(String userName);

  public void initializeChat();

  public MessageList getAllMessagesForDay(String day);
  public void sendMessage(Message message);
  public void addMessage(Message message);
  public String getUsername();

}
