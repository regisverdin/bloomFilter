import java.util.BitSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by regisverdin on 7/19/15.
 */
public class bloomFilter<K> implements Set<K> {
    // use BitSet to store bits (need multiple per hash code)



    private BitSet s;

    // q: what size bitset to use?


    public bloomFilter(){


    }



    private int hash(int i) {


        return 0;
    }




    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(K k) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Invalid operation for bloom filter");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends K> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }
}
