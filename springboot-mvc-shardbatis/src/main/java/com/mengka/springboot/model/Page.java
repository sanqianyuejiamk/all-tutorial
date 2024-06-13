package com.mengka.springboot.model;

/**
 * @author huangyy
 * @description
 * @date 2017/04/30.
 */
public class Page {
    private int begin;
    private int end;
    private int length;
    private int count;
    private int current;
    private int total;

    public Page() {
    }

    public Page(int begin, int length) {
        this.begin = begin;
        this.length = length;
        this.end = this.begin + this.length;
        this.current = (int)Math.floor((double)this.begin * 1.0D / (double)this.length) + 1;
    }

    public Page(String begin, String length) {
        this.begin = Integer.parseInt(begin);
        this.length = Integer.parseInt(length);
        this.end = this.begin + this.length;
        this.current = (int)Math.floor((double)this.begin * 1.0D / (double)this.length) + 1;
    }

    public Page(int begin, int length, int count) {
        this(begin, length);
        this.count = count;
    }

    public int getBegin() {
        return this.begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return this.end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCurrent() {
        return this.current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}