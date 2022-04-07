import mediator.RmiServer;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class ServerMain {
    public static void main(String[] args) {
        try {
            RmiServer rmiServer = new RmiServer();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
