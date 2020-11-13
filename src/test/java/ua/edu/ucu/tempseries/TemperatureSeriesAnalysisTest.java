package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysisTest {
    private TemperatureSeriesAnalysis emptyArray;
    private TemperatureSeriesAnalysis simpleArray;

    @Before
    public void setUp() throws Exception{
        emptyArray = new TemperatureSeriesAnalysis();
        simpleArray = new TemperatureSeriesAnalysis(new double[]{-5.0, 1.0, 3.0, 5.0});
    }

    @Test
    public void testAverageWithOneElementArray() {
        // setup input data and expected result
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        // call tested method
        double actualResult = seriesAnalysis.average();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        seriesAnalysis.average();
    }

    @Test
    public void testAverage() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;

        double actualResult = seriesAnalysis.average();
        
        assertEquals(expResult, actualResult, 0.00001);        
    }

    @Test(expected = InputMismatchException.class)
    public void testTemperatureSeriesWithWrongData() {
        double[] temps = {-12345, 0, 20.0, -290.0, 7};
        // expect exception here
        TemperatureSeriesAnalysis newTemps = new TemperatureSeriesAnalysis(temps);
    }

    @Test
    public void  testDeviation() {
        double expResult = 3.87298;
        double actualResult = simpleArray.deviation();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyArrayDeviation() {
        emptyArray.deviation();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinEmptyArray() {
        emptyArray.min();
    }

    @Test
    public void testMin(){
        double expResult = -5.0;
        double actualResult = simpleArray.min();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxEmptyArray() {
        emptyArray.max();
    }

    @Test
    public void testMax() {
        double expResult = 5.0;
        double actualResult = simpleArray.max();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testClosestToZeroWithEmptyArray() {
        emptyArray.findTempClosestToZero();
    }

    @Test
    public void testClosestToZero() {
        double expResult = 1.0;
        double actualResult = simpleArray.findTempClosestToZero();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testClosestToValueWithEmptyArray() {
        emptyArray.findTempClosestToValue(-2);
    }

    @Test
    public void testClosestToValue1() {
        double expResult = 5.0;
        double actualResult = simpleArray.findTempClosestToValue(4);
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempsLessThenWithEmptyArray() {
        double[] expResult = {};
        double[] actualResult = simpleArray.findTempsLessThen(-1);
        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempsLessThen() {
        double[] expResult = {-5.0, 1.0, 3.0};
        double[] actualResult = simpleArray.findTempsLessThen(4);
        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempsMoreThenWithEmptyArray() {
        double[] expResult = {};
        double[] actualResult = simpleArray.findTempsGreaterThen(-1);
        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempsMoreThen() {
        double[] expResult = {3.0, 1.0, 5.0};
        double[] actualResult = simpleArray.findTempsGreaterThen(-1);
        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testSummaryStatisticsAvgTemp() {
        double expResult =
                new TempSummaryStatistics(1.0, 3.87298, -5.0, 5.0).getAvgTemp();
        double actualResult = simpleArray.summaryStatistics().getAvgTemp();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testSummaryStatisticsDefTemp() {
        double expResult =
                new TempSummaryStatistics(2.0, 3.87298, -5.0, 5.0).getDevTemp();
        double actualResult = simpleArray.summaryStatistics().getDevTemp();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testSummaryStatisticsMinTemp() {
        double expResult =
                new TempSummaryStatistics(2.0, 3.87298, -5.0, 5.0).getMinTemp();
        double actualResult = simpleArray.summaryStatistics().getMinTemp();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testSummaryStatisticsMaxTemp() {
        double expResult =
                new TempSummaryStatistics(2.0, 3.87298, -5.0, 5.0).getMaxTemp();
        double actualResult = simpleArray.summaryStatistics().getMaxTemp();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testAddTemps() {
        int expResult = 6;
        int actualResult = simpleArray.addTemps(2.0, -230.0);
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = InputMismatchException.class)
    public void testAddTempsWithWrongData() {
        int actualResult = simpleArray.addTemps(2, -230);
    }


}
