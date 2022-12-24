package com.example.hmsystemapi.util;

import java.io.IOException;
import java.util.List;

public interface ReportUtil {

    String generatePieChart(String path, List<Object[]> data) throws IOException;
}
