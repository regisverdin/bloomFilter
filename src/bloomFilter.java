import java.util.BitSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by regisverdin on 7/19/15.
 */
public class BloomFilter<K> implements Set<K> {
    // use BitSet to store bits (need multiple per hash code)

    private BitSet filter;
    private int m; // Size of BitSet
    private int k; // Number of hash functions
    private int hash1seed;
    private int hash2seed;
    private int count;


    public BloomFilter(int filterSize, int numHashes){
        m = filterSize;
        k = numHashes;
        count = 0;

        filter = new BitSet(m);

        hash1seed = (int) System.currentTimeMillis();

        try {
            Thread.sleep(2);                 // Ensure that hash seeds are different
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        hash2seed = (int) System.currentTimeMillis();
    }

    private int hash1(CharSequence d){

        return MurmurHash3.murmurhash3_x86_32(d, 0, m, hash1seed);
    }

    private int hash2(CharSequence d){

        return MurmurHash3.murmurhash3_x86_32(d, 0, m, hash2seed);
    }

    private int hashI(int i, K data) {

        CharSequence d = (CharSequence) data;

        return hash1(d) + (i * hash2(d)) % m;

    }


    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return size()==0;
    }

    @Override
    public boolean contains(Object o) {

        K data = (K) o;

        if(size() < k) {

            for (int i = 0; i < k; k++) {
                int index = hashI(i, data);

                if (!filter.get(index)) return false;

            }
            return true;

        } else return false;
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException("Invalid operation for bloom filter");
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Invalid operation for bloom filter");
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Invalid operation for bloom filter");
    }

    @Override
    public boolean add(K inputData) {
        K data = inputData;
        for(int i = 0; i < k; k++) {
            int index = hashI(i, data);
            filter.set(index, true);
        }
        count++;

        return false;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Invalid operation for bloom filter");
    }

    @Override
    public boolean containsAll(Collection<?> c) {

        Iterator<?> itr = c.iterator();
        while (itr.hasNext()){
            K element = (K) itr.next();
            if (!contains(element)) return false;
         }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends K> c) {
        boolean addedSomething = false;

        Iterator<?> itr = c.iterator();
        while (itr.hasNext()){
            K element = (K) itr.next();
            add(element);
            addedSomething = true;
        }
        return addedSomething;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Invalid operation for bloom filter");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Invalid operation for bloom filter");
    }

    @Override
    public void clear() {
        count = 0;

        filter = new BitSet(m);

        hash1seed = (int) System.currentTimeMillis();

        try {
            Thread.sleep(2);                 // Ensure that hash seeds are different
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        hash2seed = (int) System.currentTimeMillis();
    }

}
