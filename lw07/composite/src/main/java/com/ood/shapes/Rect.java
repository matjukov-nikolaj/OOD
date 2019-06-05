package com.ood.shapes;

public class Rect<T> {

    private T left;

    private T top;

    private T width;

    private T height;

    public Rect(T left, T top, T width, T height) {
        this.left = left;
        this.top = top;
        this.height = height;
        this.width = width;
    }

    public T getTop() {
        return top;
    }

    public void setTop(T top) {
        this.top = top;
    }

    public T getLeft() {
        return left;
    }

    public void setLeft(T left) {
        this.left = left;
    }

    public T getHeight() {
        return height;
    }

    public void setHeight(T height) {
        this.height = height;
    }

    public T getWidth() {
        return width;
    }

    public void setWidth(T width) {
        this.width = width;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rect that = (Rect) o;
        return getTop().equals(that.getTop())
                && getLeft().equals(that.getLeft())
                && getWidth().equals(that.getWidth())
                && getHeight().equals(that.getHeight());
    }

    @Override
    public int hashCode() {
        return getTop().hashCode() + getLeft().hashCode() + getWidth().hashCode() + getHeight().hashCode();
    }

}
