package ru.academits.popov.hash_table;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private final ArrayList<T>[] array;
    private int size;
    private int modCount;

    public HashTable() {
        //noinspection unchecked
        array = (ArrayList<T>[]) new ArrayList[10];
    }

    public HashTable(int tableLength) {
        if (tableLength <= 0) {
            throw new IllegalArgumentException("Размер таблицы не может быть меньше или равен 0");
        }

        //noinspection unchecked
        array = (ArrayList<T>[]) new ArrayList[tableLength];
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
        int index = 0;

        for (T hashElement : this) {
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
    public boolean add(T t) {
        int index = getIndex(t);

        if (array[index] == null) {
            array[index] = new ArrayList<>();
        }

        array[index].add(t);
        size++;
        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = getIndex(o);

        if (!array[index].remove(o)) {
            return false;
        }

        size--;
        modCount++;

        return true;
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

        for (T object : collection) {
            add(object);
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

        for (ArrayList<T> a : array) {
            if (a != null) {
                int initialSize = a.size();

                if (a.removeAll(collection)) {
                    size -= initialSize - a.size();
                    modCount++;
                    isDeleted = true;
                }
            }
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

        for (Collection<T> c : array) {
            if (c != null) {
                int initialSize = c.size();

                if (!c.retainAll(collection)) {
                    size -= initialSize - c.size();
                    modCount++;
                    isDeleted = true;
                }


            }
        }

        return isDeleted;
    }

    @Override
    public void clear() {
        Arrays.fill(array, null);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");

        for (ArrayList<T> hashElement : array) {
            stringBuilder.append(hashElement);
            stringBuilder.append("; ");
        }

        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    public class MyIterator implements Iterator<T> {
        private int arrayIndex = 0;
        private int index = -1;
        private int hashIndex = -1;
        private final int initialModCount = modCount;

        @Override
        public boolean hasNext() {
            return index + 1 < size;
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
                    hashIndex++;

                    if (hashIndex == array[arrayIndex].size()) {
                        arrayIndex++;
                        hashIndex = -1;
                    } else {
                        index++;
                        return array[arrayIndex].get(hashIndex);
                    }
                }
            }

            return null;
        }
    }
}
