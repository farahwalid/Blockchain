
import java.util.ArrayList;
import java.util.Date;

public class Blockchain {
    public ArrayList<Block> blockchain = new ArrayList<>();
    public static int difficulty=5;
    public Blockchain(){
        ArrayList<Block> blockchain = new ArrayList<>();
        blockchain.add(CreateGenesisBlock());
    }
    public Block CreateGenesisBlock()
    {
        return new Block(null );
    }
    public boolean isValid(){
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');
        for(int i=1 ; i < blockchain.size() ; i++){
            Block current = blockchain.get(i);
            Block prev = blockchain.get(i-1);
            if(! prev.hash.equals(current.prev_hash)){
                System.out.println("the prev hash is not equal to the current prev hash");
                return false;
            }
            if(! current.hash.equals(current.calculateHash())){
                System.out.println("current hash is not equal to the calculated one ");
                return false;
            }
            if(!current.hash.substring( 0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined") ;
                return false;
            }
        }
        return true;
    }
    public void addBlock(Transaction t){
        Block b;
        if(blockchain.size()==0){
            b = new Block(null);
        }
        else{
            b = new Block(blockchain.get(blockchain.size()-1).prev_hash);
            b.mine(difficulty);
            blockchain.add(b);
        }
    }


}
