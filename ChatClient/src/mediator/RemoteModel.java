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
}
