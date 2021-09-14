package ru.academits.popov.hash_table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class HashTable<T> implements Collection<T> {
    private final ArrayList<T>[] hashTable;

    public HashTable() {
        //noinspection unchecked
        hashTable = (ArrayList<T>[]) new ArrayList[10];
    }

    public HashTable(int tableLength) {
        if (tableLength <= 0) {
            throw new IllegalArgumentException("Размер таблицы не может быть меньше или равен 0");
        }

        //noinspection unchecked
        hashTable = (ArrayList<T>[]) new ArrayList[tableLength];
    }

    @Override
    public int size() {
        return hashTable.length;
    }

    @Override
    public boolean isEmpty() {
        return hashTable.length == 0;
    }

    @Override
    public boolean contains(Object o) {
        int index = getIndex(o);

        if (hashTable[index] != null) {
            return hashTable[index].contains(o);
        }

        return false;
    }

    public int getIndex(Object o) {
        if (o == null) {
            return 0;
        }

        return Math.abs(o.hashCode() % hashTable.length);
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        Object[] items = new Object[hashTable.length];
        int index = 0;

        for (ArrayList<T> hashElement : hashTable) {
            items[index] = hashElement;
            index++;
        }

        return items;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a == null) {
            throw new NullPointerException("Массив пуст.");
        }

        Object[] items = toArray();

        if (a.length < hashTable.length) {
            //noinspection unchecked
            return (T1[]) Arrays.copyOf(items, hashTable.length, a.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, a, 0, hashTable.length);

        if (a.length > hashTable.length) {
            a[hashTable.length] = null;
        }

        return a;
    }

    @Override
    public boolean add(T t) {
        int index = getIndex(t);

        if (hashTable[index] == null) {
            hashTable[index] = new ArrayList<T>();
        }

        hashTable[index].add(t);

        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (!contains(o)) {
            throw new IllegalArgumentException("Нет такого элемента в таблице, о = " + o);
        }

        int index = getIndex(o);
        hashTable[index].remove(o);

        if (hashTable[index].size() == 0) {
            hashTable[index] = null;
        }

        return true;
    }

    @Override
    public boolean containsAll(Collection<?> objects) {
        if (objects == null) {
            throw new IllegalArgumentException("Пустая коллекция");
        }

        for (Object object : objects) {
            if (!contains(object)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> objects) {
        if (objects == null) {
            throw new IllegalArgumentException("Нельзя добавить пустую коллекцию");
        }

        for (T object : objects) {
            add(object);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> objects) {
        if (objects == null) {
            throw new IllegalArgumentException("Нельзя удалить пустую коллекцию");
        }

        for (Object object : objects) {
            remove(object);
        }

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        if (collection == null) {
            throw new IllegalArgumentException("Переданная коллекция пустая");
        }

        for (Object c : collection) {
            int index = getIndex(c);

            if (!hashTable[index].contains(c)) {
                hashTable[index] = null;
            } else {
                hashTable[index].removeAll(hashTable[index]);
                hashTable[index].add((T) c);
            }
        }

        return true;
    }

    @Override
    public void clear() {
        Arrays.fill(hashTable, null);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");

        for (ArrayList<T> hashElement : hashTable) {
            stringBuilder.append(hashElement);
            stringBuilder.append("; ");
        }

        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append("]");

        return stringBuilder.toString();
    }
}
