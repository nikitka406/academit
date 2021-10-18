package ru.academits.popov.hash_table;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private static final int DEFAULT_ARRAY_SIZE = 10;

    private final ArrayList<T>[] array;
    private int size;
    private int modCount;

    public HashTable() {
        //noinspection unchecked
        array = (ArrayList<T>[]) new ArrayList[DEFAULT_ARRAY_SIZE];
    }

    public HashTable(int arrayLength) {
        if (arrayLength <= 0) {
            throw new IllegalArgumentException("Длина массива не может быть меньше или равна 0. arrayLength = " + arrayLength);
        }

        //noinspection unchecked
        array = (ArrayList<T>[]) new ArrayList[arrayLength];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        int index = getIndex(o);

        return array[index] != null && array[index].contains(o);
    }

    private int getIndex(Object o) {
        if (o == null) {
            return 0;
        }

        return Math.abs(o.hashCode() % array.length);
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] items = new Object[size];
        int i = 0;

        for (T item : this) {
            items[i] = item;
            i++;
        }

        return items;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a == null) {
            throw new NullPointerException("Массив отсутствует.");
        }

        Object[] items = toArray();

        if (a.length < size) {
            //noinspection unchecked
            return (T1[]) Arrays.copyOf(items, size, a.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(T item) {
        int index = getIndex(item);

        if (array[index] == null) {
            array[index] = new ArrayList<>();
        }

        array[index].add(item);
        size++;
        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = getIndex(o);

        if (array[index] != null && array[index].remove(o)) {
            size--;
            modCount++;

            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("Коллекция = null");
        }

        for (Object object : collection) {
            if (!contains(object)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        if (collection == null) {
            throw new NullPointerException("Коллекция = null");
        }

        if (collection.size() == 0) {
            return false;
        }

        for (T item : collection) {
            add(item);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("Коллекция = null");
        }

        if (collection.size() == 0) {
            return false;
        }

        boolean isRemoved = false;

        for (ArrayList<T> list : array) {
            if (list != null) {
                int initialSize = list.size();

                if (list.removeAll(collection)) {
                    size -= initialSize - list.size();
                    isRemoved = true;
                }
            }
        }

        if (isRemoved) {
            modCount++;
        }

        return isRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        int initialSize = size;

        for (ArrayList<T> list : array) {
            if (list != null) {
                size -= list.size();
                list.retainAll(collection);
                size += list.size();
            }
        }

        if (initialSize != size) {
            modCount++;
            return true;
        }

        return false;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        Arrays.fill(array, null);

        size = 0;
        modCount++;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");

        for (ArrayList<T> elementsList : array) {
            stringBuilder.append(elementsList);
            stringBuilder.append("; ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    public class MyIterator implements Iterator<T> {
        private int arrayIndex = 0;
        private int tableIndex = -1;
        private int listIndex = -1;
        private final int initialModCount = modCount;

        @Override
        public boolean hasNext() {
            return tableIndex + 1 < size;
        }

        @Override
        public T next() {
            if (initialModCount != modCount) {
                throw new ConcurrentModificationException("Список был изменен.");
            }

            if (!hasNext()) {
                throw new NoSuchElementException("Список закончился, нет следующего элемента.");
            }

            while (true) {
                if (array[arrayIndex] == null) {
                    arrayIndex++;
                } else {
                    listIndex++;

                    if (listIndex == array[arrayIndex].size()) {
                        arrayIndex++;
                        listIndex = -1;
                    } else {
                        tableIndex++;
                        return array[arrayIndex].get(listIndex);
                    }
                }
            }
        }
    }
}
