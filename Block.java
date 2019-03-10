import java.util.Date;

public class Block {
    public String prev_hash;
    public String hash;
    private String data;
    private long timeStamp;
    private int nonce;
    public Transaction transaction;

    public Block(String prev_hash) {
        this.prev_hash = prev_hash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash() {
        Hashing h = new Hashing();
        String hash = h.hashing(prev_hash + Long.toString(timeStamp) + Integer.toString(nonce));
        return hash;
    }

    public void mine(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }

    public boolean addTransaction(Transaction transaction) {
        //process transaction and check if valid, unless block is genesis block then ignore.
        if (transaction == null) {
            return false;
        }
        if (transaction.valid) {
            this.transaction = transaction;
            System.out.println("Transaction added to Block");
            return true;
        }
        return false;
    }
}