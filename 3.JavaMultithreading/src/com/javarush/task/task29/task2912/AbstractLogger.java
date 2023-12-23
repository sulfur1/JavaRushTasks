package com.javarush.task.task29.task2912;

public abstract class AbstractLogger implements Logger {
    public int level;
    public Logger next;


    public void setNext(Logger next) {
        this.next = next;
    }

    public void inform(String message, int level) {
        if (this.level <= level) {
            info(message);
        }
        if (next != null) {
            next.inform(message, level);
        }
    }
}
