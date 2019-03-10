
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Scanner;

public class Peer extends Thread{
    MulticastPublisher publisher;
    MulticastReceiver reciever;
    Wallet w = new Wallet();
    Blockchain b;
    String name;
    public Peer(String name){
        this.name = name;
    }

    public Peer() {

    }

    public void run(){
        publisher = new MulticastPublisher();
        reciever = new MulticastReceiver();
        reciever.start();
        Scanner s = new Scanner(System.in);
        Scanner n = new Scanner(System.in);
        Scanner f = new Scanner(System.in);
        int choice;
        String word;
        int amount;
        do {
            System.out.println("1) New Transaction \n 2)Peer's Copy \n 3)");
            choice = s.nextInt();
            if(choice == 1){
                System.out.println("enter reciepient Pk");
                word = f.nextLine();
                System.out.println("enter amount");
                amount = n.nextInt();
                Transaction t = null;
                try {
                    t = new Transaction(w.publicKey,word, amount );
                } catch (GeneralSecurityException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    publisher.multicast(t);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            else if(choice == 2){
                try {
                    publisher.requestLongestBlockchain("request");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }while(choice != 0);
    }
    public Blockchain peerCopy(Peer p ){
        return p.b;
    }


}
