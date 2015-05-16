package me.wwsun.sort;

public final class Sort {

    /**
     * Simple insertion sort
     *
     * @param a   an array of Comparable items
     * @param <E> the type of elements that will be sorted
     */
    public static <E extends Comparable<? super E>> void insertionSort(E[] a) {
        int j;

        for (int p = 0; p < a.length; p++) {
            E tmp = a[p];
            for (j = p; j > 0 && tmp.compareTo(a[j - 1]) < 0; j--) {
                a[j] = a[j - 1];
            }
            a[j] = tmp;
        }
    }


    /**
     * @param a   an array of Comparable items
     * @param <E> the type of element contained in the array
     */
    public static <E extends Comparable<? super E>> void heapsort(E[] a) {
        for (int i = a.length / 2; i >= 0; i--) {
            // build heap
            percDown(a, i, a.length);
        }

        for (int i = a.length - 1; i > 0; i--) {
            swapReferences(a, 0, i);  // delete max
            percDown(a, 0, i);
        }
    }


    /**
     * Internal method that makes recursive calls
     *
     * @param a        an array of Comparable items
     * @param tmpArray an array to place the merged result
     * @param left     the left-most index of the subarray
     * @param right    the right-most index of the subarray
     * @param <E>      the type of elements contained in the array
     */
    private static <E extends Comparable<? super E>> void mergeSort(E[] a, E[] tmpArray, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(a, tmpArray, left, center);
            mergeSort(a, tmpArray, center + 1, right);
            merge(a, tmpArray, left, center + 1, right);
        }
    }

    /**
     * Mergesort algorithm
     *
     * @param a   an array of Comparable items
     * @param <E> is the type of elements contained in the array
     */
    public static <E extends Comparable<? super E>> void mergeSort(E[] a) {
        E[] tmpArray = (E[]) new Comparable[a.length];
        mergeSort(a, tmpArray, 0, a.length - 1);
    }


    /**
     * Internal method that merges two sorted halves of a subarray
     *
     * @param a        an array of Comparable items
     * @param tmpArray an array to place the merged result
     * @param left     the left-most index of the subarray
     * @param right    the index of the start of the second half
     * @param rightEnd the right-most index of the subarray
     * @param <E>      the type of elements contined in this array
     */
    private static <E extends Comparable<? super E>> void merge(E[] a, E[] tmpArray, int left, int right, int rightEnd) {
        int leftEnd = right - 1;
        int tmpPos = left;
        int numElements = rightEnd - left + 1;


        // main loop
        while (left <= leftEnd && right <= rightEnd) {
            if (a[left].compareTo(a[right]) <= 0)
                tmpArray[tmpPos++] = a[left++];
            else
                tmpArray[tmpPos++] = a[right++];
        }

        while (left <= leftEnd)  // copy rest of first half
            tmpArray[tmpPos++] = a[left++];

        while (right <= rightEnd) // copy rest of right half
            tmpArray[tmpPos++] = a[right++];

        // copy tmpArray back
        for (int i = 0; i < numElements; i++, rightEnd--) {
            a[rightEnd] = tmpArray[rightEnd];
        }
    }

    /**
     * Quicksort algorithm
     *
     * @param a   an array of Comparable items
     * @param <E> the type of elements that contained in this array
     */
    public static <E extends Comparable<? super E>> void quicksort(E[] a) {

    }

    private static final int CUTOFF = 3;

    /**
     * Internal quicksort method that makes recursive calls
     * Uses median-of-three partitioning and a cutoff of 10
     *
     * @param a     an array of Comparable items
     * @param left  the left-most index of the subarray
     * @param right the right-most index of the subarray
     * @param <E>   the type of elements that contained in this array
     */
    private static <E extends Comparable<? super E>> void quicksort(E[] a, int left, int right) {
        if (left + CUTOFF <= right) {
            E pivot = median3(a, left, right);

            // begin partitioning
            int i = left, j = right - 1;
            for (; ; ) {
                while (a[++i].compareTo(pivot) < 0) {
                }
                while (a[--j].compareTo(pivot) > 0) {
                }
                if (i < j)
                    swapReferences(a, i, j);
                else
                    break;
            }

            swapReferences(a, i, right - 1); //restore pivot
            quicksort(a, left, i - 1);
            quicksort(a, i + 1, right);

        } else {
            insertionSort(a, left, right);
        }
    }

    /**
     * Internal insertion sort routine for subarrays that is uesd by quicksort
     *
     * @param a     an array of Comparable items
     * @param left  the left-most index of the subarray
     * @param right the right-most index of the subarray
     * @param <E>   the type of elements contained in the array
     */
    private static <E extends Comparable<? super E>> void insertionSort(E[] a, int left, int right) {
        for (int p = left + 1; p <= right; p++) {
            E tmp = a[p];
            int j;

            for (j = p; j > left && tmp.compareTo(a[j - 1]) < 0; j--) {
                a[j] = a[j - 1];

            }
            a[j] = tmp;
        }
    }

    /**
     * @return median of left, center, and right
     */
    private static <E extends Comparable<? super E>> E median3(E[] a, int left, int right) {
        int center = (left + right) / 2;
        if (a[center].compareTo(a[left]) < 0) swapReferences(a, left, center);
        if (a[right].compareTo(a[left]) < 0) swapReferences(a, left, right);
        if (a[right].compareTo(a[center]) < 0) swapReferences(a, center, right);

        // place pivot at position right-1
        swapReferences(a, center, right - 1);
        return a[right - 1];
    }


    /**
     * Internal method for heapsort that is used in deleteMax and buildHeap
     *
     * @param a   an array of Comparable items
     * @param i   the position from which to percolate down
     * @param n   the logic size of the binary heap
     * @param <E> the type of elements contained in the array
     */
    private static <E extends Comparable<? super E>> void percDown(E[] a, int i, int n) {
        int child;
        E tmp;

        for (tmp = a[i]; leftChild(i) < n; i = child) {
            child = leftChild(i);
            if (child != n - 1 && a[child].compareTo(a[child + 1]) < 0)
                child++;
            if (tmp.compareTo(a[child]) < 0)
                a[i] = a[child];
            else
                break;
        }

        a[i] = tmp;
    }

    /**
     * Internal method for heapsort
     *
     * @param i the index of an item in the heap
     * @return the index of the left child
     */
    private static int leftChild(int i) {
        return 2 * i + 1;
    }

    public static <E> void swapReferences(E[] a, int index1, int index2) {
        E tmp = a[index1];
        a[index1] = a[index2];
        a[index2] = tmp;
    }
}
