import java.rmi.Remote;
import java.rmi.RemoteException;

public interface NetworkManagement extends Remote {
    String getDeviceInfo(String ipAddress) throws RemoteException;
    String performAction(String ipAddress, String action) throws RemoteException;
}
