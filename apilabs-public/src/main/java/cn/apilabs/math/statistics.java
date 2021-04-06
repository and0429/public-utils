package cn.apilabs.math;


import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.apache.commons.math3.util.FastMath;

/**
 * @author zhangkai
 * @version 1.0
 * @date 2021/3/19
 * @className statistics
 */
public class statistics {

    public static void main(String[] args) {
        double[] arrs = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        //DescriptiveStatistics
        DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics();
        for (double arr : arrs) {
            descriptiveStatistics.addValue(arr);
        }
        final double max = descriptiveStatistics.getMax();
        final long n = descriptiveStatistics.getN();
        final double mean = descriptiveStatistics.getMean();
        final double standardDeviation = descriptiveStatistics.getStandardDeviation();
        final double percentile = descriptiveStatistics.getPercentile(50);
        System.out.println(max + "=" + n + "=" + mean + "+" + standardDeviation + "=" + percentile);

        //SummaryStatistics
        SummaryStatistics summaryStatistics = new SummaryStatistics();
        for (double arr : arrs) {
            summaryStatistics.addValue(arr);
        }
        final double geometricMean = summaryStatistics.getGeometricMean();
        final double max1 = summaryStatistics.getMax();
        final double variance = summaryStatistics.getVariance();
        System.out.println(geometricMean + "=" + max1 + "=" + variance);

        //StatUtils
        final double mean1 = StatUtils.mean(arrs);
        double std = FastMath.sqrt(StatUtils.variance(arrs));
        double median = StatUtils.percentile(arrs, 50);
        System.out.println(mean1 + "=" + std + "=" + median);
    }


}
