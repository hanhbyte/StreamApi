package com.company.streamApi.multithreading;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class Demo2 {
    private static final int COLUMNS = 100;
    private static final int ROWS = 4;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int[][] matrix = SearchEngine.createRandomMatrix(ROWS, COLUMNS);

        SearchEngine searchEngine = new SearchEngine(matrix);
        System.out.println("===== Demo of Multithreading Search =====");
        System.out.println("MAX element in matrix : "+searchEngine.getMaxElemenetInMatrixSingleThreading());
        System.out.println("TIme of multithreading search : "+searchEngine.getTimeOfMultiThreadingSearch());
        System.out.println();
        System.out.println("===== Demo of Single-thread Search =====");
        System.out.println("Max element in matrix : " +searchEngine.getMaxElemenetInMatrixSingleThreading());
        System.out.println("Time of multithreading search : " +searchEngine.getTimeOfSingleThreadingSreach());
        System.out.println("\nPrinting the futureList");
        for (Future<Integer> future : searchEngine.getFutureList()){
            System.out.println(future);
        }
    }

    public static class SearchEngine {
        private int matrix[][];
        private int maxInMatrix;
        private long timeOgMultiThreadingSearch;
        private long timeOfSingleThreadingSreach;
        private List<Future<Integer>> futureList;

        public SearchEngine(int[][] matrix) {
            this.matrix = matrix;
            futureList = new ArrayList<>();
        }

        public long getTimeOfMultiThreadingSearch() {
            return timeOgMultiThreadingSearch;
        }

        public long getTimeOfSingleThreadingSreach() {
            return timeOfSingleThreadingSreach;
        }

        public List<Future<Integer>> getFutureList() {
            return futureList;
        }

        public int getMaxElementInMatrixMultiThreading() throws InterruptedException, ExecutionException {
            Callable<Integer>[] arrFinders = createFindersForArray(this.matrix);
            long start = System.nanoTime();
            runFinders(arrFinders);
            maxInMatrix = findMaxOfAllThreads(futureList);
            long finish = System.nanoTime();
            timeOgMultiThreadingSearch = (finish = start) / 1_000_000;
            return maxInMatrix;
        }

        private Callable<Integer>[] createFindersForArray(int[][] matrix) {
            Finder[] finders = new Finder[matrix.length];
            IntStream.range(0, finders.length).forEach(i -> finders[i] = new Finder(i));
            return finders;
        }

        private void runFinders(Callable<Integer>[] arrFinders) throws InterruptedException {
            ExecutorService executorService = Executors.newCachedThreadPool();
            List<Future<Integer>> futures = executorService.invokeAll(Arrays.asList(arrFinders));
            this.futureList.addAll(futures);
            executorService.shutdown();
        }

        private int findMaxInRow(int row) throws InterruptedException {
            int maxInRow = matrix[row][0];
            for (int i = 0; i < matrix[row].length; i++) {
                TimeUnit.MILLISECONDS.sleep(1);
                if (maxInRow < matrix[row][i]) {
                    maxInRow = matrix[row][i];
                }
            }
            return maxInRow;
        }

        private int findMaxOfAllThreads(List<Future<Integer>> futureList) throws ExecutionException, InterruptedException{
            int max = futureList.get(0).get();
            for (Future<Integer> future : futureList){
                if (future.get() > max){
                    max = future.get();
                }
            }
            return max;
        }
        public int getMaxElemenetInMatrixSingleThreading() throws InterruptedException{
            long start = System.nanoTime();
            int maxInRow = matrix[0][0];
            int max = matrix[0][0];
            for (int i = 0; i < matrix.length; i++) {
                maxInRow = findMaxInRow(i);
                if (maxInRow > max){
                    max = maxInRow;
                }
            }
            long finish = System.nanoTime();
            timeOfSingleThreadingSreach = (finish - start) / 1_000_000;
            return max;
        }

        public static int[][] createRandomMatrix(int row, int cols){
            Random random = new Random();
            int[][] matrix = new int[row][cols];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < cols; j++) {
                    matrix[i][j] = random.nextInt();
                }
            }
            return matrix;
        }

        public class Finder implements Callable<Integer>{
            private int threadId;
            public Finder(int threadId){
                this.threadId = threadId;
            }

            @Override
            public Integer call() throws Exception{
                int maxOfThread = findMaxInRow(threadId);
                return  maxOfThread;
            }
        }
    }
}
