package mediator;

import model.Message;
import utility.observer.listener.RemoteListener;
import utility.observer.subject.RemoteSubject;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteModel extends RemoteSubject<Message, Message>
{
  public void addMessage(Message message) throws
      RemoteException;

  // An event is fired here in the process of a message being received so that the other clients can see the message
}
