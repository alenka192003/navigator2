package com.example.navigator2;

import com.example.navigator2.Route;

import java.util.*;

public class Map<K, V> {
    private Hashtable<K, V> hashtable;

    public Map() {
        this.hashtable = new Hashtable<>();
    }

    public void put(K key, V value) {
        hashtable.put(key, value);
    }

    public V get(K key) {
        return hashtable.get(key);
    }

    public V remove(K key) {
        return hashtable.remove(key);
    }

    public int size() {
        return hashtable.size();
    }
    public boolean contains(K value) {
        return hashtable.containsKey(value);
    }
    public Collection<V> values() {
        return hashtable.values();
    }
}

class Hashtable<K, V> {
    private LinkedList<Entry<K, V>>[] buckets;
    private static final int DEFAULT_CAPACITY = 16;

    public Hashtable() {
        this(DEFAULT_CAPACITY);
    }

    public Hashtable(int capacity) {
        this.buckets = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    public void put(K key, V value) {
        int index = getIndex(key);
        LinkedList<Entry<K, V>> bucket = buckets[index];
        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }
        bucket.add(new Entry<>(key, value));
    }

    public V get(K key) {
        int index = getIndex(key);
        LinkedList<Entry<K, V>> bucket = buckets[index];
        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public V remove(K key) {
        int index = getIndex(key);
        LinkedList<Entry<K, V>> bucket = buckets[index];

        // Ищем элемент в связном списке
        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                bucket.remove(entry);  // Удаляем элемент из связного списка
                return entry.getValue();  // Возвращаем удаленное значение
            }
        }

        return null;  // Возвращаем null, если элемент не найден
    }

    public int size() {
        int count = 0;
        for (LinkedList<Entry<K, V>> bucket : buckets) {
            count += bucket.size();
        }
        return count;
    }

    private int getIndex(K key) {
        return Math.abs(key.hashCode() % buckets.length);
    }
    public boolean containsKey(K key) {
        return get(key) != null;
    }
    public Collection<V> values() {
        List<V> allValues = new ArrayList<>();
        for (LinkedList<Entry<K, V>> bucket : buckets) {
            for (Entry<K, V> entry : bucket) {
                allValues.add(entry.getValue());
            }
        }
        return allValues;
    }
}


  class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }


class LinkedList<T> implements Iterable<T> {
    private Node<T> head;
    private int size;

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        size++;
    }

    public boolean remove(T data) {
        if (head == null) {
            return false;
        }

        if (head.getData().equals(data)) {
            head = head.getNext();
            size--;
            return true;
        }

        Node<T> current = head;
        while (current.getNext() != null && !current.getNext().getData().equals(data)) {
            current = current.getNext();
        }

        if (current.getNext() != null) {
            current.setNext(current.getNext().getNext());
            size--;
            return true;
        }

        return false;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator<>(head);
    }

    private static class LinkedListIterator<T> implements Iterator<T> {
        private Node<T> current;

        public LinkedListIterator(Node<T> head) {
            this.current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = current.getData();
            current = current.getNext();
            return data;
        }
    }
}


class Node<T> {
    private T data;
    private Node<T> next;

    public Node(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}