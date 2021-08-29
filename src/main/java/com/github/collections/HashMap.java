package com.github.collections;

import java.util.Iterator;

/**
 * @author 康盼Java开发工程师
 */
public class HashMap<K, V> implements Iterable<Entry<K, V>> {

    private Node<K, V>[] table;

    private int size;

    private int maxSize;

    public HashMap(int initialCapacity, int maxSize) {
        this.maxSize = maxSize;
        table = new Node[initialCapacity];
    }

    public V put(K key, V value) {
        ++size;
        if (size <= maxSize) {
            int index = getIndex(key);
            if (null == table[index]) {
                table[index] = new Node<>(index, key, value, null);
            } else {
                Node<K, V> node = table[index];
                push(node, new Node<>(index, key, value, null));
            }
        }
        return value;
    }

    private void push(Node node, Node next) {
        while (node != null) {
            Node<K, V> tab = node.next;
            if (tab == null) {
                node.next = next;
                break;
            }
            node = node.next;
        }
    }

    public V get(K key) {
        int index = getIndex(key);
        Node<K, V> node = table[index];
        while (node != null) {
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }


    public void clear() {
        if (table != null && size > 0) {
            size = 0;
            for (int i = 0; i < table.length; ++i) {
                table[i] = null;
            }
        }
    }

    public boolean containsKey(Object key) {
        int index = getIndex((K) key);
        Node<K, V> e = table[index];
        if (e == null) {
            return false;
        }
        while (e != null) {
            if (e.key.equals(key)) {
                return true;
            }
            e = e.next;
        }
        return false;
    }


    public int size() {
        return size;
    }


    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new HashIterator();
    }

    class HashIterator implements Iterator<Entry<K, V>> {
        Node<K, V> next;
        Node<K, V> current;
        int index;

        HashIterator() {
            Node<K, V>[] t = table;
            current = next = null;
            index = 0;
            if (t != null && size > 0) {
                do {
                } while (index < t.length && (next = t[index++]) == null);
            }
        }

        @Override
        public final boolean hasNext() {
            return next != null;
        }

        @Override
        public Entry<K, V> next() {
            Node<K, V>[] t;
            Node<K, V> e = next;
            if ((next = (current = e).next) == null && (t = table) != null) {
                do {
                } while (index < t.length && (next = t[index++]) == null);
            }
            return e;
        }
    }

    /**
     * 获取数组索引 (size - 1) & hash 运算, 索引范围在 0 ~ (size -1)
     *
     * @param key
     * @return
     */
    private final int getIndex(K key) {
        return (table.length - 1) & hash(key);
    }

    private final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    final class Node<K, V> implements Entry<K, V> {
        final K key;
        final int index;
        V value;
        Node<K, V> next;

        public Node(int index, K key, V value, Node<K, V> next) {
            this.index = index;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public boolean isEnd() {
            return next == null;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }
    }
}
