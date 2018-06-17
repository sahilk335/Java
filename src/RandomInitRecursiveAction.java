/*
 *@Author : Sahil
 * Date : 17 June 2018
 *
 * RecursiveAction -
 *
 * ForkJoinTask is an abstract base class that outlines most of the methods, such as fork()
 * and join(), in a Fork-Join task. If you need to create a ForkJoinTask that does
 * not return a result, then you should subclass RecursiveAction. RecursiveAction
 * extends ForkJoinTask and has a single abstract compute method that you must
 * implement:
 * protected abstract void compute();
 * An example of a task that does not need to return a result would be any task that
 * initializes an existing data structure. The following example will initialize an array to
 * contain random values. Notice that there is only a single array throughout the entire
 * process. When subdividing an array, you should avoid creating new objects when
 * possible.
 */


import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ThreadLocalRandom;

public class RandomInitRecursiveAction extends RecursiveAction {
    public static final int THRESHOLD = 10000;
    private int[] data;
    private int start;
    private int end;

    public RandomInitRecursiveAction(int[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {


        if (end - start <= THRESHOLD) {       //Is it manageable amount of work ??
            //do the task

            for (int i = start; i < end; i++) {
                data[i] = ThreadLocalRandom.current().nextInt(); //ThreadLocalRandom is unique to each thread
            }
        } else {                      //task too big , split it with recursive action

            int half = ((end - start) / 2) + start;

            RandomInitRecursiveAction a1=new RandomInitRecursiveAction(data,start,half);
            a1.fork();          //queue left half of the task, this task can be stolen by other worker unused threads
                                //from forkjoin poo;

            RandomInitRecursiveAction a2=new RandomInitRecursiveAction(data,half,end);
            a2.compute();           //work on second half of the task

            a1.join();          //wait for queued task to complete
        }
    }

    public static void main(String[] args){
        int[]data=new int[10_000_000];

        ForkJoinPool fjPool=new ForkJoinPool();

        RandomInitRecursiveAction action=new RandomInitRecursiveAction(data,0,data.length);

        fjPool.invoke(action);
    }
}
