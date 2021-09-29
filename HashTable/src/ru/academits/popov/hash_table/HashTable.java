package ru.academits.popov.hash_table;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    static private final int SIZE = 10;
    private final ArrayList<T>[] array;
    private int size;
    private int modCount;

    public HashTable() {
        //noinspection unchecked
        array = (ArrayList<T>[]) new ArrayList[SIZE];
    }

    public HashTable(int arrayLength) {
        if (arrayLength <= 0) {
            throw new IllegalArgumentException("Длина массива не может быть меньше или равен 0. arrayLength = " + arrayLength);
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

        for (T element : this) {
            items[i] = element;
            i++;
        }

        return items;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a == null) {
            throw new NullPointerException("Коллекция отсутствует.");
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
    public boolean add(T element) {
        int index = getIndex(element);

        if (array[index] == null) {
            array[index] = new ArrayList<>();
        }

        array[index].add(element);
        size++;
        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = getIndex(o);

        if (array[index].size() == 0) {
            array[index] = null;
        }

        if (array[index] != null) {
            size--;
            modCount++;

            return array[index].remove(o);
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

        for (T element : collection) {
            add(element);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("Коллекция = null");
        }

        if (size == 0) {
            return false;
        }

        boolean isDeleted = false;

        for (ArrayList<T> list : array) {
            if (list != null) {
                int initialSize = list.size();

                if (list.removeAll(collection)) {
                    size -= initialSize - list.size();
                    isDeleted = true;
                }
            }
        }

        if (isDeleted) {
            modCount++;
        }

        return isDeleted;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("Коллекция = null");
        }

        if (size == 0) {
            return false;
        }

        boolean isDeleted = false;

        for (ArrayList<T> list : array) {
            if (list != null) {
                int initialSize = list.size();

                if (!list.retainAll(collection)) {
                    size -= initialSize - list.size();
                    isDeleted = true;
                }
            }
        }

        if (isDeleted) {
            modCount++;
        }

        return isDeleted;
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

            while (hasNext()) {
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

            return null;
        }
    }
}
