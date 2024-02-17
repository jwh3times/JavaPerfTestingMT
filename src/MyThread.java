public class MyThread extends Thread
{
    private long startIdx, endIdx, resultVal, cycleCnt;
    private double pct = 0.0;
    private double pctDiv = 0.00000001;
    private double pctInc = 1.0; // leave this alone at 1.0

    public MyThread(long s, long e)
    {
        this.startIdx = s;
        this.endIdx = e;
    }

    @Override
    public void run()
    {
        long range = this.endIdx - this.startIdx;
        cycleCnt = 0;
        //long t1 = System.nanoTime();
        //long t2;
        for(long i = this.startIdx; i < this.endIdx; i++)
        {
            pct = Math.abs((double)++cycleCnt / (double)range);
            if (pct >= (pctDiv * pctInc))
            {
                //System.out.println(this.getName() + " is now " + Double.toString(pct) + "% complete");
                String pctStr = String.format("%.8f", pct);
                //t2 = System.nanoTime();
                //System.out.println(this.getName() + " is now " + pctStr + "% complete and has been running " + Long.toString(t2 - t1) + " ns since last pct checkpoint with thread prio " + this.getPriority());
                //t1 = t2;
                System.out.println(this.getName() + " is now " + pctStr + "% complete");
                pctInc += 1.0;
            }

            for(long ii = -3; ii < 3; ii++)
            {
                resultVal = i * ii;
                if(resultVal / i != ii)
                {
                    // overflow
                    //System.out.println("no overflow for " + this.getName());
                }
                else
                {
                    try {
                        //BigInteger resultValBI = BigInteger.valueOf(i).multiply(BigInteger.valueOf(ii));
                        //resultVal = resultValBI.longValueExact();
                        resultVal = Math.multiplyExact(i, ii);
                    }
                    catch (ArithmeticException ae) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Overflow not detected -> ");
                        sb.append(Long.toString(i));
                        sb.append(" * ");
                        sb.append(Long.toString(ii));
                        sb.append(" = ");
                        sb.append(Long.toString(resultVal));
                        System.out.println(sb.toString());
                    }
                }
            }
        }
    }
}