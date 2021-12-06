package ch.zhaw.ads;

import java.util.*;
import java.util.function.*;

public class SortServer implements CommandExecutor {
    private final int DATARANGE = 10000000;  
    public int dataElems; // number of data
    public int insertion_threshold;

    public void swap(int[] a, int i, int j) {
        int h = a[i];
        a[i] = a[j];
        a[j] = h;
    }
  
    public void bubbleSort(int[] a) {
        for (int k = a.length - 1; k > 0; k--){
            for (int i = 0; i < k; i++) {
                if (a[i] > a[i+1]) swap (a, i, i+1);
            }
        }
    }

    public void insertionSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int j = i;
            while (j > 0 && a[j] < a[j - 1]) {
                swap(a, j, j - 1);
                j--;
            }
        }
    }

    public void quickerSort(int[] a){
        quickerSort(a, 0, a.length - 1);
    }

    private void quickerSort(int[] arr, int left, int right) {
        if (arr.length <= insertion_threshold) {
            insertionSort(arr);
        } else if (left < right) {
            int mid = this.partition(arr, left, right);
            quickerSort(arr, left, mid - 1);
            quickerSort(arr, mid, right);
        }
    }

    private int partition (int[] arr, int left, int right) {
        int pivot = arr[(left + right) / 2];
        while (left <= right) {
            while (arr[left] < pivot) { left++; }
            while (arr[right] > pivot) { right--; }
            if (left <= right) {
                swap(arr,left,right);
                left++;
                right--;
            }
        }
        return left;
    }

    public void selectionSort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[min]) min = j;
            }
            swap(a, i, min);
        }
    }

    public void streamSort(int[] a) {
        // zum Vergleichen (falls Sie Zeit und Lust haben)
        int[] b = Arrays.stream(a).sorted().toArray();
        System.arraycopy(b, 0, a, 0, a.length);
    }
    public boolean isSorted(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public int[] randomData() {
        int[] a = new int[dataElems];
        Random r = new Random();
        for (int i = 0; i < dataElems; i++) {
            a[i] = r.nextInt(DATARANGE);
        }
        return a;
    }

    public int[] ascendingData() {
        int[] a = new int[dataElems];
        for (int i = 0; i < dataElems; i++) {
            a[i] = i;
        }
        return a;
    }

    public int[] descendingData() {
        int[] a = new int[dataElems];
        for (int i = 0; i < dataElems; i++) {
            a[i] = dataElems - i;
        }
        return a;
    }
    
    // measure time of sorting algorithm
    // generator to generate the data
    // consumer sorts the data
    // return elapsed time in ms
    // if data is not sorted an exception is thrown
    public double measureTime(Supplier<int[]> generator, Consumer<int[]> sorter) throws Exception {
        double elapsed = 0;

        int[] a = generator.get();
        int[] b = new int[dataElems];

        long startTime = System.currentTimeMillis();

        sorter.accept(a);
        long endTime = System.currentTimeMillis();

        elapsed = (double)(endTime - startTime);
        if (!isSorted(b)) throw new Exception ("ERROR not eorted");
        return elapsed;
    }

    public String execute(String arg)  {
        // Java 9: use Map.of instead
        Map<String,Consumer<int[]>> sorter =  new HashMap<>();
        sorter.put("BUBBLE", this::bubbleSort);
        sorter.put("INSERTION", this::insertionSort);
        sorter.put("SELECTION", this::selectionSort);
        sorter.put("STREAM", this::streamSort);
        
        Map<String,Supplier<int[]>> generator =  new HashMap<>();
        generator.put("RANDOM", this::randomData);
        generator.put("ASC", this::ascendingData);
        generator.put("DESC", this::descendingData);
        
        String args[] = arg.toUpperCase().split(" ");
        dataElems = Integer.parseInt(args[2]);
        try {
            double time = measureTime(generator.get(args[1]), sorter.get(args[0]));
            return arg + " "+Double.toString(time)+" ms";
        } catch (Exception ex){
            return arg + " "+ ex.getMessage();
        }  
    }
    
    public static void main(String[] args) throws Exception {
        SortServer sorter = new SortServer();
        String sort;
        sort = "BUBBLE RANDOM 10000"; System.out.println(sorter.execute(sort));
        sort = "SELECTION RANDOM 10000"; System.out.println(sorter.execute(sort));
        sort = "INSERTION RANDOM 10000"; System.out.println(sorter.execute(sort));
        
        sort = "BUBBLE ASC 10000"; System.out.println(sorter.execute(sort));
        sort = "SELECTION ASC 10000"; System.out.println(sorter.execute(sort));
        sort = "INSERTION ASC 10000"; System.out.println(sorter.execute(sort));
    }
}
