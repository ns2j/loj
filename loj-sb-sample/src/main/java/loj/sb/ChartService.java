package loj.sb;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sun.star.awt.Rectangle;
import com.sun.star.beans.PropertyVetoException;
import com.sun.star.beans.UnknownPropertyException;
import com.sun.star.container.NoSuchElementException;
import com.sun.star.lang.IllegalArgumentException;
import com.sun.star.lang.IndexOutOfBoundsException;
import com.sun.star.lang.WrappedTargetException;
import com.sun.star.table.CellHoriJustify;
import com.sun.star.table.CellRangeAddress;
import com.sun.star.uno.Exception;

import loj.my.Cell;
import loj.my.Doc;
import loj.my.Prop;
import loj.my.Sheet;
import loproxy.chart.P_Chart;
import loproxy.chart.P_TableChart;
import loproxy.chart.P_TableCharts;
import loproxy.container.P_Container;

@Service
public class ChartService {
    private static Logger logger = LoggerFactory.getLogger(ChartService.class);

    public void make(Doc doc) throws IllegalArgumentException, Exception {
        logger.info("Getting spreadsheet") ;
        Sheet sheet = doc.getSheet(0);

        Object style1 = doc.createInstance("com.sun.star.style.CellStyle");
        Object style2 = doc.createInstance("com.sun.star.style.CellStyle");

        P_Container styles = doc.getCellStyles();
        styles.insertByName("My Style", style1);
        styles.insertByName("My Style2", style2);

        Prop.of(style1)
        .set("IsCellBackgroundTransparent", Boolean.FALSE)
        .set("CellBackColor",Integer.valueOf(6710932))
        .set("CharColor",Integer.valueOf(16777215))
        .set("HoriJustify", CellHoriJustify.CENTER);
        Prop.of(style2)
        .set("IsCellBackgroundTransparent", Boolean.FALSE)
        .set("CellBackColor",Integer.valueOf(13421823));

        sheet.getRange("B1:N1").prop("CellStyle", "My Style");
        sheet.getRange("A2:A4").prop("CellStyle", "My Style");
        sheet.getRange("B2:N4").prop("CellStyle", "My Style2");

        setToCells(sheet);

        createChart(sheet);
        
        //sheet.getRange("A1:D4")
        //.prop("HoriJustify", CellHoriJustify.LEFT);
        
        logger.info("done");
    }

    void createChart(Sheet sheet) throws NoSuchElementException, WrappedTargetException, IllegalArgumentException, UnknownPropertyException, PropertyVetoException {
        Rectangle rect = new Rectangle();
        rect.X = 500;
        rect.Y = 3000;
        rect.Width = 25000;
        rect.Height = 11000;

        CellRangeAddress[] addrs = new CellRangeAddress[1];
        addrs[0] = sheet.getRange("A1:N4").getRangeAddress();

        logger.info("Insert Chart");
        P_TableCharts tableCharts = new P_TableCharts(sheet.getCharts());
        tableCharts.addNewByName("Example", rect, addrs, true, true);
        P_TableChart tableChart = new P_TableChart(tableCharts.getByName("Example"));
        P_Chart chart = new P_Chart(tableChart.getEmbeddedObject());

        logger.info("Change Diagram to 3D");
        Prop.of(chart.getDiagram()).set("Dim3D", Boolean.TRUE);

        logger.info("Change the title");
        Prop.of(chart.getTitle()).set("String","The new title");
        
    }
    
    void setToCells(Sheet sheet) throws IndexOutOfBoundsException, IllegalArgumentException, UnknownPropertyException, PropertyVetoException, WrappedTargetException {
        logger.info("Creating the Header") ;
        Cell c = sheet.getCell(1, 0);
        for (Object o: List.of("JAN", "FEB", "MAR", "APR", "MAY", "JUN",
                "JUL", "AUG", "SEP", "OCT", "NOV", "DEC", "SUM")) {

            c = c.set(o).right();
        }
        logger.info("Fill the lines");
        var data = List.of(
                List.of("Smith", 42, 58.9, -66.5, 43.4, 44.5, 45.3,
                        -67.3, 30.5, 23.2, -97.3, 22.4, 32.5, "=SUM(B2:M2)"),
                List.of("Jones", 21, 40.9, -57.5, -23.4, 34.5, 59.3,
                        27.3, -38.5, 43.2, 57.3, 25.4, 28.5, "=SUM(B3:M3)"),
                List.of("Brown", 31.45, -20.9, -117.5, 23.4, -114.5, 115.3,
                        -171.3, 89.5, 41.2, 71.3, 25.4, 38.5, "=SUM(A4:L4)")
                );
        Cell rowCell  = sheet.getCell(0, 1);
        for (var rowData: data) {
            Cell cell = rowCell;
            for (Object o: rowData) {
                cell = cell.set(o).right();        
            }
            rowCell = rowCell.down();
        }
    }
}
