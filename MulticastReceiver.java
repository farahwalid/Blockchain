import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class MulticastReceiver extends Thread {
    protected MulticastSocket socket = null;
    protected byte[] buf = new byte[3000];
    public Blockchain b = new Blockchain();
    public void run() {
        try {
            socket = new MulticastSocket(4446);
        } catch (IOException e) {
            e.printStackTrace();
        }
        InetAddress group = null;
        try {
            group = InetAddress.getByName("230.0.0.0");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        try {
            socket.joinGroup(group);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String received = new String(
                    packet.getData(), 0, packet.getLength());
            Transaction t = new Transaction();
            t.extract(received);
            System.out.println(received);
            b.addBlock(t);
            if ("end".equals(received)) {
                break;
            }
        }
        try {
            socket.leaveGroup(group);
        } catch (IOException e) {
            e.printStackTrace();
        }
        socket.close();
    }
    public Blockchain sendBlockchain(){
        return b;
    }
}

