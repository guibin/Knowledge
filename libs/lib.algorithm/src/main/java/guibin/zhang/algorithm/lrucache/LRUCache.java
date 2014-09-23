package guibin.zhang.algorithm.lrucache;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class LRUCache<K, V> {
    
    int maxSize;
    int currentSize = 0;
    
    Map<K, ValueHolder<K, V>> map;
    LinkedList<K> queue;
    
    public LRUCache(int maxSize) {
        this.maxSize = maxSize;
        map = new HashMap<>();
        queue = new LinkedList<>();
    }
    
    private void freeSpace() {
        
        K k = queue.remove();
        map.remove(k);
        currentSize --;
    }
    
    public void put(K key, V val) {
        
        while (currentSize >= maxSize) {
            freeSpace();
        }
        
        if (map.containsKey(key)) {
            ValueHolder<K, V> vh = map.get(key);
            queue.remove(vh.locInQueue);
        }
        
        ListNode<K> node = queue.add(key);
        ValueHolder<K, V> vh = new ValueHolder<>(val, node);
        map.put(key, vh);
        currentSize++;
    }
    
    public V get(K key) {
        
        ValueHolder<K, V> vh = map.get(key);
        
        if (vh != null) {
            queue.remove(vh.locInQueue);
            
            ListNode<K> node = queue.add(key);
            vh.locInQueue = node;
            return vh.value;
        }
        
        return null;
    }
}
