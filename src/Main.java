import java.util.Collection;

/**
 * Created by regisverdin on 7/20/15.
 */
public class Main {


    public static void main (String args[]){
        BloomFilter bf = new BloomFilter(10, 3);

        for(int i = 0; i<5; i+=2){
            bf.add(Integer.toString(i));
        }

        for(int i = 0; i<5; i+=2){
            System.out.print("Contains " + i + "?" + bf.contains(Integer.toString(i)));

        }
    }
}