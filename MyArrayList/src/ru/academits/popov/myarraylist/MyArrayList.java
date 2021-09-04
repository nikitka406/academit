package ru.academits.popov.myarraylist;

import java.util.*;

public class MyArrayList<E> implements List<E> {
    private E[] items;
    private int size;
    private int modifyCount;

    public MyArrayList(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Размер списка не может быть ментьше нуля, size = " + size);
        }
        //noinspection unchecked
        items = (E[]) new Object[size];
        this.size = size;
        modifyCount = 0;
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
        if (o == null) {
            throw new IllegalArgumentException("Искомый элемент не должен быть null, o = " + o);
        }

        for (E element : items) {
            if (element.equals(o)) {
                return true;
            }
        }

        return false;
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
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        if (e == null) {
            throw new IllegalArgumentException("Добавляемый элемент не должен равняться null, e = " + e);
        }

        ensureCapacity(size + 1);

        items[size] = e;
        size++;
        modifyCount++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = (int) o;

        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Индекс выходит за границы списка, index = " + index);
        }

        if (size - 1 - index >= 0) System.arraycopy(items, index + 1, items, index, size - 1 - index);

        size--;
        modifyCount++;

        return true;
    }

    @Override
    public boolean containsAll(Collection<?> objects) {
        for (Object object : objects) {
            if (!contains(object)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> objects) {
        addAll(size, objects);

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> objects) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Индекс выходит за границы списка, index = " + index);
        }

        if (size + objects.size() >= items.length) {
            increaseCapacity();
        }

        int addCount = 0;
        for (E object : objects) {
            items[size + addCount] = object;
            addCount++;
            size++;
        }

        modifyCount++;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> objects) {
        boolean deleteIndicator = true;

        for (Object object : objects) {
            int index = indexOf(object);

            if (index == -1) {
                deleteIndicator = false;
            }

            while (index != -1) {
                remove(index);
                index = indexOf(object);
            }
        }

        return deleteIndicator;
    }

    @Override
    public boolean retainAll(Collection<?> objects) {
        int deletesCont = 0;

        for (int i = 0; i < size - deletesCont; ++i) {
            if (!objects.contains(items[i])) {
                remove(items[i]);
                deletesCont++;
            }
        }

        modifyCount++;

        return true;
    }

    @Override
    public void clear() {
        trimToSize(0);
        modifyCount++;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Индекс выходит за границы списка, index = " + index);
        }

        return items[index];
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Индекс выходит за границы списка, index = " + index);
        }

        E oldElement = items[index];
        items[index] = element;

        return oldElement;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Индекс выходит за границы списка, index = " + index);
        }

        E oldElement = items[index];
        ensureCapacity(size + 1);
        size++;
        modifyCount++;
        items[index] = element;

        for (int i = index + 1; i < size; ++i) {
            E temp = items[i];
            items[i] = oldElement;
            oldElement = temp;
        }
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Индекс выходит за границы списка, index = " + index);
        }

        E oldElement = items[index];

        if (size - 1 - index >= 0) System.arraycopy(items, index + 1, items, index, size - 1 - index);

        size--;
        modifyCount++;

        return oldElement;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; ++i) {
            if (items[i] == o) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; --i) {
            if (items[i] == o) {
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
        if (capacity < items.length) {
            return;
        }

        items = Arrays.copyOf(items, capacity);
    }

    public void trimToSize(int size) {
        if (size == this.size) {
            return;
        }

        if (size > this.size) {
            throw new IllegalArgumentException("Обрезаемый размер должен быть меньше существующего размера, size = " + size);
        }

        items = Arrays.copyOf(items, size);
        this.size = 0;
    }

    public class MyListIterator implements Iterator<E> {
        private int index = -1;
        private int modCount = modifyCount;

        @Override
        public boolean hasNext() {
            return index + 1 < size;
        }

        @Override
        public E next() {
            if (modCount != modifyCount) {
                throw new ConcurrentModificationException("Список был изменен.");
            }
            if (!hasNext()) {
                throw new NoSuchElementException("Список закончился, нет следующего элемента.");
            }

            index++;
            return items[index];
        }
    }

    public void increaseCapacity() {
        int newSize = 0;

        if (items.length == 0) {
            newSize = 1;
        } else {
            newSize = items.length * 2;
        }

        items = Arrays.copyOf(items, newSize);
    }
}
