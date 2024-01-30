package com.example.navigator2;


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
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.5;
    private MyList<Entry<K, V>>[] table;
    private int size;

    public Hashtable() {
        this(DEFAULT_CAPACITY);
    }

    public Hashtable(int capacity) {
        this.table = new MyList[capacity];
        this.size = 0;
        for (int i = 0; i < capacity; i++) {
            table[i] = new MyList<>();
        }
    }

    public void put(K key, V value) {
        if ((double) size / table.length >= LOAD_FACTOR) {
            resizeAndRehash();
        }

        int index = findIndex(key);
        MyList<Entry<K, V>> bucket = table[index];
        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }
        System.out.println(new Entry<>(key, value).hashCode());
        bucket.add(new Entry<>(key, value));
        size++;
    }

    private void resizeAndRehash() {
        int newCapacity = table.length * 2;
        MyList<Entry<K, V>>[] newTable = new MyList[newCapacity];
        for (int i = 0; i < newCapacity; i++) {
            newTable[i] = new MyList<>();
        }

        for (MyList<Entry<K, V>> bucket : table) {
            for (Entry<K, V> entry : bucket) {
                int index = findIndex(entry.getKey(), newTable);
                newTable[index].add(entry);
            }
        }

        table = newTable;
    }

    public V get(K key) {
        int index = findIndex(key);
        MyList<Entry<K, V>> bucket = table[index];
        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public V remove(K key) {
        int index = findIndex(key);
        MyList<Entry<K, V>> bucket = table[index];

        // Итерируем по элементам корзины
        Iterator<Entry<K, V>> iterator = bucket.iterator();
        while (iterator.hasNext()) {
            Entry<K, V> entry = iterator.next();

            // Проверяем соответствие ключа
            if (entry.getKey().equals(key)) {
                iterator.remove(); // Используем метод iterator.remove() для безопасного удаления
                size--;
                return entry.getValue();
            }
        }

        return null;
    }


    public int size() {
        return size;
    }

    private int findIndex(K key) {
        return findIndex(key, table);
    }

    private int findIndex(K key, MyList<Entry<K, V>>[] array) {
        int index = Math.abs(key.hashCode()) % array.length;
        int i = 1;
        while (array[index].size() > 0) {
            for (Entry<K, V> entry : array[index]) {
                if (entry.getKey().equals(key)) {
                    return index;
                }
            }
            index = (index + i * i) % array.length;
            i++;
        }
        return index;
    }
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public Collection<V> values() {
        List<V> allValues = new ArrayList<>();
        for (MyList<Entry<K, V>> bucket : table) {
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

class MyList<T> implements Iterable<T> {
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
        return new MyListIterator<>(head);
    }

    private static class MyListIterator<T> implements Iterator<T> {
        private Node<T> current;

        public MyListIterator(Node<T> head) {
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