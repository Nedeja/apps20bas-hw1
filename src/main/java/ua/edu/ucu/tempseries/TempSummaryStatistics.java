package ua.edu.ucu.tempseries;

public class TempSummaryStatistics {
    double avgTemp, devTemp, minTemp, maxTemp;

    TempSummaryStatistics(double avgTemp, double devTemp, double minTemp, double maxTemp) {
        this.avgTemp = avgTemp;
        this.devTemp = devTemp;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }

    private boolean equalTemp(double firstTemp, double secondTemp){
        return Math.abs(firstTemp - secondTemp) == 0;
    }

    @Override
    public int hashCode() {
        return Double.valueOf(avgTemp + minTemp + maxTemp + minTemp).hashCode();
    }

    public double getAvgTemp() {
        return avgTemp;
    }

    public double getDevTemp() {
        return devTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    @Override
    public boolean equals(Object o) {
        TempSummaryStatistics other = (TempSummaryStatistics) o;
        if (o != null && hashCode() != other.hashCode()){
            return equalTemp(avgTemp, other.getAvgTemp()) && equalTemp(devTemp, other.getDevTemp())
            && equalTemp(minTemp, other.getMinTemp()) && equalTemp(maxTemp, other.getMaxTemp());
        }
        else {
            return false;
        }
    }
}
