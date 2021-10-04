package ru.academits.popov.my_array_list;

import java.util.*;

public class MyArrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 10;

    private E[] items;
    private int size;
    private int modCount;

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Вместимость списка не может быть меньше нуля, capacity = " + capacity);
        }

        //noinspection unchecked
        items = (E[]) new Object[capacity];
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
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyListIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <E1> E1[] toArray(E1[] a) {
        if (a.length < size) {
            //noinspection unchecked
            return (E1[]) Arrays.copyOf(items, size, a.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(E e) {
        add(size, e);

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);

        if (index == -1) {
            return false;
        }

        remove(index);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object object : collection) {
            if (!contains(object)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        return addAll(size, collection);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        checkIndex(index, size + 1);
        ensureCapacity(size + collection.size());

        System.arraycopy(items, index, items, index + collection.size(), size - index);

        int i = index;

        for (E e : collection) {
            items[i] = e;
            i++;
        }

        size += collection.size();
        modCount++;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        if (size == 0) {
            return false;
        }

        int oldSize = size;

        for (int i = 0; i < size; i++) {
            if (collection.contains(items[i])) {
                remove(i);
                i--;
            }
        }

        return oldSize != size;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        int oldSize = size;

        for (int i = 0; i < size; ++i) {
            if (!collection.contains(items[i])) {
                remove(i);
                i--;
            }
        }

        return oldSize != size;
    }

    @Override
    public void clear() {
        if (!isEmpty()) {
            for (int i = 0; i < size; ++i) {
                items[i] = null;
            }

            size = 0;
            modCount++;
        }
    }

    @Override
    public E get(int index) {
        checkIndex(index, size);

        return items[index];
    }

    @Override
    public E set(int index, E item) {
        checkIndex(index, size);

        E oldItem = items[index];
        items[index] = item;

        return oldItem;
    }

    @Override
    public void add(int index, E item) {
        checkIndex(index, size + 1);

        if (size >= items.length) {
            increaseCapacity();
        }

        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = item;
        size++;
        modCount++;
    }

    @Override
    public E remove(int index) {
        checkIndex(index, size);

        E removedItem = items[index];

        System.arraycopy(items, index + 1, items, index, size - 1 - index);

        items[size - 1] = null;
        size--;
        modCount++;

        return removedItem;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; ++i) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; --i) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    public void ensureCapacity(int capacity) {
        if (capacity > items.length) {
            items = Arrays.copyOf(items, capacity);
        }
    }

    public void trimToSize() {
        if (size != items.length) {
            items = Arrays.copyOf(items, size);
        }
    }

    public class MyListIterator implements Iterator<E> {
        private int index = -1;
        private final int initialModCount = modCount;

        @Override
        public boolean hasNext() {
            return index + 1 < size;
        }

        @Override
        public E next() {
            if (initialModCount != modCount) {
                throw new ConcurrentModificationException("Список был изменен.");
            }
            if (!hasNext()) {
                throw new NoSuchElementException("Список закончился, нет следующего элемента.");
            }

            index++;
            return items[index];
        }
    }

    private void increaseCapacity() {
        items = Arrays.copyOf(items, items.length == 0 ? 1 : items.length * 2);
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder("[");

        for (int i = 0; i < size; ++i) {
            stringBuilder.append(items[i]);
            stringBuilder.append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    private static void checkIndex(int index, int futureSize) {
        if (index < 0 || index >= futureSize) {
            throw new IndexOutOfBoundsException("Индекс выходит за границы списка, index = " + index +
                    ". Допустимый диапазон от 0 до " + (futureSize - 1));
        }
    }
}
