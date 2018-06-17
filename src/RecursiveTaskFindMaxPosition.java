  /*
   *@Author : Sahil
   * Date : 17 June 2018
   *
   * RecursiveTasks
   * If you need to create a ForkJoinTask that does return a result, then you should
   * subclass RecursiveTask. RecursiveTask extends ForkJoinTask and has a single
   * abstract compute method that you must implement:
   * protected abstract V compute(); // V is a generic type
   * The following example will find the position in an array with the greatest value; if
   * duplicate values are found, the first occurrence is returned. Notice that there is only
   * a single array throughout the entire process. (Just like before, when subdividing an
   * array, you should avoid creating new objects when possible.)
   *
   */

  import java.util.concurrent.ForkJoinPool;
  import java.util.concurrent.RecursiveTask;

  public class RecursiveTaskFindMaxPosition extends RecursiveTask<Integer> {
      private static final int THRESHOLD = 10000;
      private int[] data;
      private int start;
      private int end;

      public RecursiveTaskFindMaxPosition(int[] data, int start, int end) {
          this.data = data;
          this.start = start;
          this.end = end;
      }

      @Override
      protected Integer compute() {
          if (end - start <= THRESHOLD) {
              int position = 0;
              for (int i = start; i < end; i++) {
                  if (data[i] > data[position])
                      position = i;
              }
              return position;
          } else {
              int half = ((end - start) / 2) + start;

              RecursiveTaskFindMaxPosition t1 = new RecursiveTaskFindMaxPosition(data, start, half);
              t1.fork();

              RecursiveTaskFindMaxPosition t2 = new RecursiveTaskFindMaxPosition(data, half, end);

              int position2 = t2.compute();
              int position1 = t1.join();

              if (data[position2] > data[position1]) {
                  return position2;
              } else if (data[position1] > data[position2]) {
                  return position1;
              } else {
                  return position1 > position2 ? position2 : position1;
              }
          }
      }

      public static void main(String[] args) {
          int[] data = new int[10_000_000];

          ForkJoinPool fjPool = new ForkJoinPool();

          //Initialize array with random integers using Recursive actions
          RandomInitRecursiveAction action = new RandomInitRecursiveAction(data, 0, data.length);
          fjPool.invoke(action);

          RecursiveTaskFindMaxPosition task = new RecursiveTaskFindMaxPosition(data, 0, data.length);
          Integer position = fjPool.invoke(task);

          System.out.print("Position : " + position + ", value : " + data[position]);


      }
  }
