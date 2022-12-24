package com.example.hmsystemapi.util;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Component;


import java.io.File;
import java.io.IOException;
import java.util.List;
@Component
public class ReportUtilImpl implements  ReportUtil{
    @Override
    public String generatePieChart(String path, List<Object[]> data) throws IOException {

        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Object[] objects: data ) {

      dataset.setValue(objects[0].toString(), new Double(objects[1].toString()));
        }
       JFreeChart chart =  ChartFactory.createPieChart3D("Visit Type Report" , dataset);
        ChartUtils.saveChartAsPNG(new File(path + "/pieChart.png") , chart ,300 ,300);
        return path;
    }
}
