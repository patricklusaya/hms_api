package com.example.hmsystemapi.controller;

import java.awt.image.BufferedImage;
import java.util.List;

public class ChartData {
//    public ChartData(List rowKeys, List columnKeys, BufferedImage chartImage) {
//    }

    private List<Comparable> labels;
    private List<String> keys;
    private BufferedImage chartImage;

    public ChartData(List<Comparable> labels, List<String> keys, BufferedImage chartImage) {
        this.labels = labels;
        this.keys = keys;
        this.chartImage = chartImage;
    }

    public List<Comparable> getLabels() {
        return labels;
    }

    public List<String> getKeys() {
        return keys;
    }

    public BufferedImage getChartImage() {
        return chartImage;
    }
}
