package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/* 
Построй дерево(1)
*/

public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    CustomTree.Entry<String> root;
    public CustomTree() {
        this.root = new CustomTree.Entry<String>("0");
    }

    static class Entry<T> implements Serializable {
        String elementName;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        public boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren | availableToAddRightChildren;
        }
    }

    public String getParent(String s) {
        Queue<Entry<String>> queue = new LinkedList<>();
        Entry<String> entry = root;
        if (entry.elementName.equals(s)) {
            return null;
        }
        queue.add(entry);
        while (!(entry=queue.poll()).isAvailableToAddChildren()) {
            if (entry.leftChild == null || entry.rightChild == null) {
                continue;
            }
            if (entry.leftChild.elementName.equals(s) || entry.rightChild.elementName.equals(s)) {
                return entry.elementName;
            } else {
                queue.add(entry.leftChild);
                queue.add(entry.rightChild);
            }
        }
        if ((entry.leftChild != null && entry.leftChild.elementName.equals(s)) || (entry.rightChild != null && entry.rightChild.elementName.equals(s))) {
            return entry.elementName;
        }


        return null;

    }

    @Override
    public boolean remove(Object o) {
        String removeString;
        if ((o instanceof String string)) {
            removeString = string;
        } else {
            throw new UnsupportedOperationException();
        }
        Queue<Entry<String>> queue = new LinkedList<>();
        Entry<String> entry = root;
        if (entry.elementName.equals(removeString)) {
            return false;
        }
        queue.add(entry);
        while (!(entry=queue.poll()).isAvailableToAddChildren()){
            if (entry.leftChild.elementName.equals(removeString)) {
                entry.leftChild = null;
                return true;
            } else if (entry.rightChild.elementName.equals(removeString)) {
                entry.rightChild = null;
                return true;
            } else {
                queue.add(entry.leftChild);
                queue.add(entry.rightChild);
            }
        }
        if ((entry.leftChild != null && entry.leftChild.elementName.equals(removeString))) {
            entry.leftChild = null;
            return true;
        } else if ((entry.rightChild != null && entry.rightChild.elementName.equals(removeString))) {
            entry.rightChild = null;
            return true;
        }

        return false;

    }

    @Override
    public boolean add(String s) {
        Entry<String> newEntry = new Entry<>(s);
        Queue<Entry<String>> queue = new LinkedList<>();
        Entry<String> entry = root;
        queue.add(entry);
        while (!(entry=queue.poll()).isAvailableToAddChildren()){
            queue.add(entry.leftChild);
            queue.add(entry.rightChild);
        }
        if (entry.availableToAddLeftChildren) {
            entry.leftChild = newEntry;
            entry.availableToAddLeftChildren = false;
        } else {
            entry.rightChild = newEntry;
            entry.availableToAddRightChildren = false;
        }
        newEntry.parent = entry;
        return true;
    }

    @Override
    public int size() {
        int count = 0;
        Queue<Entry<String>> queue = new LinkedList<>();
        Entry<String> entry = root;
        queue.add(entry);

        while (!(entry=queue.poll()).isAvailableToAddChildren()){
            queue.add(entry.leftChild);
            queue.add(entry.rightChild);
            count = count + 2;
        }
        if (entry.leftChild != null || entry.rightChild != null) {
            count++;
        }

        return count;
    }

    public String get(int index) {
        throw new UnsupportedOperationException();
    }
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
     }
}
