package lab9;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  @author Your name here
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    private static final int DEFAULT_SIZE = 16;
    private static final double MAX_LF = 0.75;

    private ArrayMap<K, V>[] buckets;
    private int size;

    private int loadFactor() {
        return size / buckets.length;
    }

    public MyHashMap() {
        buckets = new ArrayMap[DEFAULT_SIZE];
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        this.size = 0;
        for (int i = 0; i < this.buckets.length; i += 1) {
            this.buckets[i] = new ArrayMap<>();
        }
    }

    /** Computes the hash function of the given key. Consists of
     *  computing the hashcode, followed by modding by the number of buckets.
     *  To handle negative numbers properly, uses floorMod instead of %.
     */
    private int hash(K key) {
        if (key == null) {
            return 0;
        }

        int numBuckets = buckets.length;
        return Math.floorMod(key.hashCode(), numBuckets);
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (buckets[hash(key)].containsKey(key)) {
            return buckets[hash(key)].get(key);
        }
        return null;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        if (key == null) {
            return;
        }
        if (!buckets[hash(key)].containsKey(key)) {
            size++;
        }
        buckets[hash(key)].put(key,value);
        if (loadFactor() > MAX_LF) {
            resize(buckets.length * 2);
        } /*else if (loadFactor() < 0.25 && buckets.length > 16) {
            resize(buckets.length / 2);
        }*/
    }
    /* Update bucket size after put*/
    private void resize(int size) {
        ArrayMap<K,V>[] temp = buckets;
        buckets = new ArrayMap[size];
        clear();
        for (ArrayMap<K,V> map : temp) {
            Set<K> keys = map.keySet();
            for (K k: keys) {
                put(k,map.get(k));
            }
        }
    }
    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (ArrayMap<K,V> map : buckets) {
            keys.addAll(map.keySet());
        }
        return keys;
    }

    /* Removes the mapping for the specified key from this map if exists.
     * Not required for this lab. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        V val = null;
        if (containsKey(key)) {
            val = buckets[hash(key)].get(key);
            buckets[hash(key)].remove(key);
        }
        return val;
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for this lab. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        V val = null;
        if (get(key) == value) {
            val = remove(key);
        }
        return val;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

}
