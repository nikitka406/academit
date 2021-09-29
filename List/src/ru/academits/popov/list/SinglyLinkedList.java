package ru.academits.popov.list;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public int getCount() {
        return count;
    }

    private void checkListEmpty() {
        if (count == 0) {
            throw new NullPointerException("Односвязный список пуст.");
        }
    }

    private void checkIndex(int index) {
        if (index >= count && index <= 0) {
            throw new IndexOutOfBoundsException("Индекс выходит за границы списка, index = " + index);
        }
    }

    public T getFirst() {
        checkListEmpty();

        return head.getData();
    }

    private ListItem<T> getElementByIndex(int index) {
        checkIndex(index);
        ListItem<T> element = head;

        for (int i = 1; i < index; ++i) {
            element = element.getNext();
        }

        return element;
    }

    public T getDataByIndex(int index) {
        return getElementByIndex(index).getData();
    }

    public T setDataByIndex(int index, T data) {
        ListItem<T> element = getElementByIndex(index);

        T oldData = element.getData();
        element.setData(data);

        return oldData;
    }

    public T deleteFirstElement() {
        T oldData = head.getData();
        head = head.getNext();
        count--;

        return oldData;
    }

    public T deleteByIndex(int index) {
        if (index == 1) {
            return deleteFirstElement();
        }

        ListItem<T> prev = getElementByIndex(index - 1);

        if (index == count && count > 1) {
            prev.setNext(null);
            count--;
            return prev.getData();
        }

        ListItem<T> element = getElementByIndex(index);

        T oldData = element.getData();
        ListItem<T> nextElement = new ListItem<>(element.getNext().getData(), element.getNext().getNext());
        prev.setNext(nextElement);
        count--;

        return oldData;
    }

    public void addFirst(T newHead) {
        if (head == null) {
            head = new ListItem<>(newHead);
            head.setNext(null);
        }

        ListItem<T> oldHead = new ListItem<>(head.getData(), head.getNext());
        head.setData(newHead);
        head.setNext(oldHead);
        count++;
    }

    public void addByIndex(int index, T newData) {
        checkIndex(index);

        if (index == 1) {
            addFirst(newData);
            return;
        }

        ListItem<T> prev = getElementByIndex(index - 1);
        ListItem<T> oldElement = prev.getNext();

        prev.setNext(new ListItem<>(newData, oldElement));

        count++;
    }

    public boolean deleteByValues(T data) {
        boolean isDeleted = false;
        if (head.getData() == data) {
            deleteFirstElement();
        }

        for (ListItem<T> p = head.getNext(), prev = head; p != null; prev = p, p = p.getNext()) {
            if (p.getData() == data) {
                prev.setNext(p.getNext());
                isDeleted = true;
                count--;
                break;
            }
        }

        return isDeleted;
    }

    public SinglyLinkedList<T> copy(){
        SinglyLinkedList<T> result = new SinglyLinkedList<>();

        if (head == null) {
            return result;
        }

        ListItem<T> copyListItem = new ListItem<>(head.getData());

        result.head = copyListItem;
        ListItem<T> i = head.getNext();

        while (i != null) {
            ListItem<T> nextCopyListItem = new ListItem<>(i.getData());
            copyListItem.setNext(nextCopyListItem);
            copyListItem = nextCopyListItem;

            i = i.getNext();
        }

        result.count = count;

        return result;
    }

    public void reverse(){
        if (count == 0 || count == 1){
            return;
        }

        ListItem<T> element = head.getNext();
        for (int i = 2; i <= count; i++){
            T data = element.getData();
            deleteByIndex(i);
            addFirst(data);
            if (element.getNext() != null){
                element = element.getNext();
            }
        }
    }


    @Override
    public String toString() {
        if (count == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder("[");
        ListItem<T> element = head;

        for (int i = 0; i < count; ++i) {
            stringBuilder.append(element.getData());
            stringBuilder.append(", ");
            element = element.getNext();
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append("]");

        return stringBuilder.toString();
    }
}
