 import java.util.UUID;

public class Wallet {
    public String privateKey;
    public String publicKey;
    public int credit;
    public Wallet(){
        generateKeyPair();
    }

    public void generateKeyPair() {
        UUID idOne = UUID.randomUUID();
        UUID idTwo = UUID.randomUUID();
        //log("private: " + idOne);
        //log("public: " + idTwo);
        privateKey = idOne.toString();
        publicKey = idTwo.toString();
    }
    private static void log(Object object){
        System.out.println( String.valueOf(object) );
    }
}