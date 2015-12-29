package ua.artcode.javafun.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TestCase implements Serializable {

    private List<DataPoint> dataPointList = new ArrayList<>();

    public TestCase() {
    }

    public List<DataPoint> getDataPointList() {
        return dataPointList;
    }

    public void setDataPointList(List<DataPoint> dataPointList) {
        this.dataPointList = dataPointList;
    }

    public void addDataPoint(DataPoint dataPoint){
        dataPointList.add(dataPoint);
    }

}
