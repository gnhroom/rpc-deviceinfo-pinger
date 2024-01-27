import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class NetworkManagementImpl extends UnicastRemoteObject implements NetworkManagement {

    protected NetworkManagementImpl() throws RemoteException {
        super();
    }

    @Override
    public String getDeviceInfo(String ipAddress) throws RemoteException {
        try {
            // Mendapatkan informasi dari alamat IP
            InetAddress inetAddress = InetAddress.getByName(ipAddress);

            // Mendapatkan nama host dari alamat IP
            String hostname = inetAddress.getHostName();

            // Mendapatkan hasil ping, nslookup, dan tracert
            String pingResult = performPing(ipAddress);
            String nslookupResult = performNslookup(ipAddress);
            String tracertResult = performTracert(ipAddress);

            // Mengembalikan hasil dengan format yang lebih rapi
            return "Informasi perangkat untuk " + ipAddress + " (" + hostname + "):\n"
                    + "Hostname: " + hostname + "\n\n"
                    + "Ping Result:\n" + pingResult + "\n\n"
                    + "Nslookup Result:\n" + nslookupResult + "\n\n"
                    + "Tracert Result:\n" + tracertResult;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public String performAction(String ipAddress, String action) throws RemoteException {
        // Implementasi untuk melakukan tindakan pada perangkat dari alamat IP
        // (Anda perlu mengganti ini dengan logika yang sesuai untuk kasus Anda)
        return "Aksi berhasil dilakukan pada perangkat " + ipAddress + ": " + action;
    }

    private String performPing(String ipAddress) {
        try {
            String os = System.getProperty("os.name").toLowerCase();

            ProcessBuilder processBuilder;
            if (os.contains("win")) {
                // Windows
                processBuilder = new ProcessBuilder("ping", "-n", "4", ipAddress);
            } else {
                // Non-Windows (Linux/Mac)
                processBuilder = new ProcessBuilder("ping", "-c", "4", ipAddress);
            }

            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                // Baca output dari proses dan kembalikan sebagai hasil
                java.util.Scanner s = new java.util.Scanner(process.getInputStream()).useDelimiter("\\A");
                return s.hasNext() ? s.next() : "";
            } else {
                return "Failed";
            }
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    private String performNslookup(String ipAddress) {
        try {
            Process process = new ProcessBuilder("nslookup", ipAddress).start();
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                // Baca output dari proses dan kembalikan sebagai hasil
                java.util.Scanner s = new java.util.Scanner(process.getInputStream()).useDelimiter("\\A");
                return s.hasNext() ? s.next() : "";
            } else {
                return "Failed";
            }
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    private String performTracert(String ipAddress) {
        try {
            Process process = new ProcessBuilder("tracert", ipAddress).start();
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                // Baca output dari proses dan kembalikan sebagai hasil
                java.util.Scanner s = new java.util.Scanner(process.getInputStream()).useDelimiter("\\A");
                return s.hasNext() ? s.next() : "";
            } else {
                return "Failed";
            }
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    public static void main(String[] args) {
        try {
            // Inisialisasi dan daftarkan objek server ke RMI Registry
            NetworkManagement networkManagement = new NetworkManagementImpl();
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            java.rmi.Naming.rebind("NetworkManagementService", networkManagement);
            System.out.println("Server manajemen jaringan siap menerima panggilan...");
        } catch (Exception e) {
            System.err.println("Error pada server: " + e.toString());
            e.printStackTrace();
        }
    }
}
