package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Message implements Serializable
{
  private String userName;
  private LocalDateTime timeStamp;
  private String message;

  public Message(String message, String userName){
    this.message = message;
    this.userName = userName;
    timeStamp = LocalDateTime.now();
  }

  public String getUserName()
  {
    return userName;
  }

  public LocalDateTime getTimeStamp()
  {
    return timeStamp;
  }

  public String getMessage()
  {
    return message;
  }

  public String toString() {
    return userName + "> " + message;
  }
}
