import java.io.IOException;
import java.security.*;

public class Transaction {
    public Wallet sender;
    public String reciepientId;
    public String senderId;
    public int amount;
    public String signature;
    public boolean valid;
    private static int sequence = 0; // a rough count of how many transactions have been generated.
    public Transaction(Wallet sender,Peer reciepient, int amount) throws GeneralSecurityException, IOException {

        this.amount = amount;
        this.senderId = sender.publicKey;
        this.reciepientId = reciepient.w.publicKey;
        this.valid = false;
        if (sender.credit > amount) {
            sender.credit -= this.amount;
            reciepient.w.credit += this.amount;
            this.valid = true;
        }
        this.signature = generateSignature(sender.privateKey);

    }
    public Transaction(String senderId, String reciepientId, int amount) throws GeneralSecurityException, IOException {
        this.senderId = senderId;
        this.reciepientId = reciepientId;
        this.amount = amount;
        this.signature = generateSignature(senderId);
    }
    public Transaction(){}
    /**
     public boolean isgreaterthanAmount(){

     if(sender.credit > amount){
     this.valid= true;
     }
     return this.valid;
     }**/


    private String calulateHash() {
        Hashing h = new Hashing();
        sequence++;
        return h.hashing(senderId + reciepientId + Float.toString(amount) + sequence
        );

    }
    public String generateSignature(String privateKey) throws GeneralSecurityException, IOException {
        Hashing h = new Hashing();
        String data = senderId + reciepientId + Float.toString(amount)	;
        System.out.println(data);
        signature = h.hashing(senderId + reciepientId + Float.toString(amount) + privateKey);
        return signature;
        //signature = StringUtil.applyECDSASig(privateKey,data);
    }
    public String concatenate() {
        return senderId+"," +reciepientId + ","  +amount + ","+ signature;
    }
    public Transaction extract(String transactionString){
        String[] tran = transactionString.split(",");
        this.senderId = tran[0];
        this.reciepientId = tran[1];
        this.amount = Integer.parseInt(tran[2]);
        this.signature = tran[3];
        return this;
    }


}