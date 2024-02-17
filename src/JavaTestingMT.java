public class JavaTestingMT
{
    public static void main(String[] args)
    {
        long lowerBound = Long.MIN_VALUE;
        long upperBound = Long.MAX_VALUE;
        int numThreads = 15;
        MyThread[] myThreads = new MyThread[numThreads];
        long workingRange = Long.MAX_VALUE / 2;
        long rangePerThread = workingRange / numThreads;

        System.out.println("Start");

        for(int i = 0; i < numThreads; i++)
        {
            lowerBound = Long.MIN_VALUE + (i * rangePerThread);
            upperBound = Long.MIN_VALUE + ((i+1) * rangePerThread);
            myThreads[i] = new MyThread(lowerBound, upperBound);
            System.out.println("Starting thread " + myThreads[i].getName());
            myThreads[i].start();
        }

        // wait for all threads to complete
        for(int i = 0; i < numThreads; i++)
        {
            try {
                myThreads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Joining thread " + myThreads[i].getName());
        }

        System.out.println("Halfway");

        for(int i = 0; i < numThreads; i++)
        {
            lowerBound = Long.MAX_VALUE - ((i+1) * rangePerThread);
            upperBound = Long.MAX_VALUE - (i * rangePerThread);
            myThreads[i] = new MyThread(lowerBound, upperBound);
            System.out.println("Starting thread " + myThreads[i].getName());
            myThreads[i].start();
        }

        // wait for all threads to complete
        for(int i = 0; i < numThreads; i++)
        {
            try {
                myThreads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Joining thread " + myThreads[i].getName());
        }

        System.out.println("Done");
    }
}
