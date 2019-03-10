import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MulticastPublisher {
    private DatagramSocket socket;
    private InetAddress group;
    private byte[] buf;

    public void multicast(Transaction transaction) throws IOException {
        socket = new DatagramSocket();
        group = InetAddress.getByName("230.0.0.0");
        buf = transaction.concatenate().getBytes();


        DatagramPacket packet
                = new DatagramPacket(buf, buf.length, group, 4446);
        socket.send(packet);
        socket.close();
    }
    public void requestLongestBlockchain(String request) throws IOException {
        socket = new DatagramSocket();
        group = InetAddress.getByName("230.0.0.0");
        buf = request.getBytes();


        DatagramPacket packet
                = new DatagramPacket(buf, buf.length, group, 4446);
        socket.send(packet);
        socket.close();
    }


}
