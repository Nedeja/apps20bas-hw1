package ua.edu.ucu.tempseries;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private double[] temps;
    private final int capacity;
    private final int tempNum;

    public TemperatureSeriesAnalysis() {
        temps = new double[1];
        capacity = 1;
        tempNum = 0;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        final int MIN = -273;
        for (double temp : temperatureSeries) {
            if (temp < MIN) {
                throw new InputMismatchException();
            }
        }
        temps = temperatureSeries;
        capacity = temps.length;
        tempNum = capacity;

    }

    public double average() {
        if (tempNum != 0) {
            double sum = 0;
            for (int i = 0; i < tempNum; i++) {
                sum += temps[i];
            }
            return sum / tempNum;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public double deviation() {
        double av = average();
        double sumPow = 0;
        for (int i = 0; i < tempNum; i++) {
            sumPow += Math.abs(temps[i] - av) * Math.abs(temps[i] - av);
        }
        return sumPow / tempNum;

    }

    public double min() {
        if (tempNum != 0) {
            double minim = temps[0];
            for (int i = 0; i < tempNum; i++) {
                minim = Math.min(minim, temps[i]);
            }
            return minim;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public double max() {
        if (tempNum != 0) {
            double maxim = temps[0];
            for (int i = 0; i < tempNum; i++) {
                maxim = Math.max(maxim, temps[i]);
            }
            return maxim;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0.0);
    }

    public double findTempClosestToValue(double tempValue) {
        if (tempNum == 0){
            throw new IllegalArgumentException();
        }
        double closestTemp = temps[0];
        double temporary = Math.abs(temps[0] - tempValue);
        for ( int i = 0; i < tempNum; i++) {
            if (Math.abs(temporary - Math.abs(temps[i] - tempValue)) <= 0
                && temps[i] > tempValue) {
                closestTemp = temps[i];
            }
            else if (temporary > Math.abs(temps[i] - tempValue)){
                temporary = Math.abs(temps[i] - tempValue);
                closestTemp = temps[i];
            }
        }
    return closestTemp;
    }

    public double[] findTempsLessThen(double tempValue) {
        TemperatureSeriesAnalysis temporary = new TemperatureSeriesAnalysis();
        for (int i = 0; i < tempNum; i++) {
            if (temps[i] < tempValue) {
                temporary.addTemps(temps[i]);
            }
        }
        double[] result = new double[temporary.tempNum];
        System.arraycopy(temporary.temps, 0, result, 0, result.length);
        return result;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        TemperatureSeriesAnalysis temporary = new TemperatureSeriesAnalysis();
        for (int i = 0; i < tempNum; i++) {
            if (temps[i] > tempValue) {
                temporary.addTemps(temps[i]);
            }
        }
        double[] result = new double[temporary.tempNum];
        System.arraycopy(temporary.temps, 0, result, 0, result.length);
        return result;
    }

    public TempSummaryStatistics summaryStatistics() {
        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }

    public int addTemps(double... temperatures) {
        final int MIN = -273;
        int i;
        int j;
        double[] newTemps = new double[this.temps.length + temperatures.length];
        for (i = 0; i < tempNum; i++) {
            newTemps[i] = this.temps[i];
        }
        for (j = i; j < this.tempNum + tempNum; j++) {
            if (temperatures[j - i] < MIN) {
                throw new InputMismatchException();
            }
            newTemps[j] = temperatures[j - i];
        }
        this.temps = newTemps;
        return j;

    }
}
