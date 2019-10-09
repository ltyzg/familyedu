package com.qishiyun.familyedu.util;

import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;

public class ListUtils {
    public static <T> List<List<T>> partition(List<T> list, int size) {
        return (list instanceof RandomAccess)
                ? new RandomAccessPartition<T>(list, size)
                : new Partition<T>(list, size);
    }

    private static class Partition<T> extends AbstractList<List<T>> {
        final List<T> list;
        final int size;

        Partition(List<T> list, int size) {
            this.list = list;
            this.size = size;
        }

        @Override
        public List<T> get(int index) {
            int start = index * size;
            int end = Math.min(start + size, list.size());
            return list.subList(start, end);
        }

        @Override
        public int size() {
            return divide(list.size(), size);
        }

        @Override
        public boolean isEmpty() {
            return list.isEmpty();
        }
    }

    public static int divide(int p, int q) {
        int div = p / q;
        // equal to p % q
        int rem = p - q * div;
        if (rem == 0) {
            return div;
        }
        int signum = 1 | ((p ^ q) >> (Integer.SIZE - 1));
        boolean increment = signum > 0;
        return increment ? div + signum : div;
    }

    private static class RandomAccessPartition<T> extends Partition<T> implements RandomAccess {
        RandomAccessPartition(List<T> list, int size) {
            super(list, size);
        }
    }
}
