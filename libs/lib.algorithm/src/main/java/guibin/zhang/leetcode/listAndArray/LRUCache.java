package guibin.zhang.leetcode.listAndArray;

import java.util.HashMap;
import java.util.Map;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. 
 * It should support the following operations: get and set.
 * 
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * set(key, value) - Set or insert the value if the key is not already present. 
 * When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class LRUCache {
    
    ListNode head;
    ListNode tail;
    int capacity;
    int len;
    Map<Integer, ListNode> map = new HashMap<>();
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        len = 0;
    }
    
    public int get(int key) {
        if (map.containsKey(key)) {
            ListNode node = map.get(key);
            //Move the node to the head of the list
            remove(node);
            add(node);
            return node.val;
        }
        return -1;
    }
    
    public void set(int key, int value) {
        if (map.containsKey(key)) {
            ListNode old = map.get(key);
            //Replace the old node with new value, and move it to head
            remove(old);
            old.val = value;
            add(old);
        } else {
            ListNode node = new ListNode(key, value);
            //Create the node, add to head of the list if we have the capacity.
            if (len < capacity) {
                add(node);
                map.put(key, node);
                len ++;
            } else {
                //remove the tail, add the new node to the head of the list.
                map.remove(tail.key);
                tail = tail.prev;
                if (tail != null) {
                    tail.next = null;
                }
                add(node);
                map.put(node.key, node);
            }
        }
    }
    
    /**
     * Doubly linked list
     */
    public class ListNode {
        int val;
        int key;
        ListNode next;
        ListNode prev;
        
        public ListNode(int k, int v) {
            this.val = v;
            this.key = k;
        }
    }
    
    /**
     * Remove the node from the doubly linked list.
     * @param node 
     */
    public void remove (ListNode node) {
        
        ListNode prv = node.prev;
        ListNode nxt = node.next;
        
        if (prv != null) {
            prv.next = nxt;
        } else {
            head = nxt;
        }
        if (nxt != null) {
            nxt.prev = prv;
        } else {
            tail = prv;
        }
    }
    
    /**
     * Add the node to the head of the doubly linked list.
     * @param node 
     */
    public void add(ListNode node) {
        //Connect node to head
        node.next = head;
        node.prev = null;
        //Connect head to node
        if (head != null) {
            head.prev = node;
        }
        //Reset head
        head = node;
        //Check the tail
        if (tail == null) {
            tail = node;
        }
    }
}
