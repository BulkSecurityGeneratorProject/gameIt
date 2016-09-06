package mk.gameIt.web.rest;

import mk.gameIt.domain.UserGameOrder;
import mk.gameIt.service.UserGameOrderService;
import mk.gameIt.service.UserService;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.ReportTemplateBuilder;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.builder.group.CustomGroupBuilder;
import net.sf.dynamicreports.report.constant.Calculation;
import net.sf.dynamicreports.report.constant.GroupHeaderLayout;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.dynamicreports.jasper.builder.*;
import net.sf.dynamicreports.report.builder.column.*;


import java.io.*;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

@RestController
public class ReportController {
    private static final String PARAMETER_ITEM_NAME = "itemName";
    private static final String PARAMETER_TYPE = "type";
    private static final String VALUE_TYPE_PDF = "pdf";
    private static final String VALUE_TYPE_XLS = "xls";

    private static final Map<String, String> FILE_TYPE_2_CONTENT_TYPE =
            new HashMap<String, String>();
    static {
        FILE_TYPE_2_CONTENT_TYPE.put(VALUE_TYPE_PDF, "application/pdf");
        FILE_TYPE_2_CONTENT_TYPE.put(VALUE_TYPE_XLS, "application/vnd.ms-excel");
    }

    @Autowired
    UserService userService;


    @Autowired
    DataSource dataSource;

    @Autowired
    private UserGameOrderService userGameOrderService;


    @RequestMapping("/report/users")
    public void jasperReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("application/pdf");
        OutputStream out = response.getOutputStream();
        try {
            JasperReportBuilder jrb = createJasperReport();

                jrb.toPdf(out);
        } catch (DRException e) {
            throw new ServletException(e);
        }
        out.close();


    }
   private JasperReportBuilder createJasperReport() throws SQLException {
       JasperReportBuilder report = DynamicReports.report();//a new report
       report
               .columns(
                       Columns.column("User Id", "user_id", DataTypes.longType()),
                       Columns.column("First Name", "first_name", DataTypes.stringType()),
                       Columns.column("Email ", "email", DataTypes.stringType()),
                       Columns.column("Username", "username", DataTypes.stringType()))
               .title(//title of the report
                       Components.text("SimpleReportExample")
                               .setHorizontalAlignment(HorizontalAlignment.CENTER))
               .pageFooter(Components.pageXofY())//show page number on the page footer
               .setDataSource("SELECT user_id, first_name, email, username FROM user",
                       dataSource.getConnection());

       return report;
    }
//TODO: EDNA KOLONA SUMA, PO EDNA KOLONA DA GRUPIRAM
    //HEADER - DA IAM GODINA PO GRUPIRANWE\
    //GRUPIRAJ GI PO KATEGORIJA, KOLKU SE VKUPNO COUNT, PROSECNA OCENA PROSECNO PO KATEGORIJA
    //
//eden grafik
    @RequestMapping(value = "report/1", method = RequestMethod.GET)
    public void jasperGroupReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("application/pdf");
        OutputStream out = response.getOutputStream();
        try {
            JasperReportBuilder jrb = getGroupReport();

            jrb.toPdf(out);
        } catch (DRException e) {
            throw new ServletException(e);
        }
        out.close();


    }

    public JasperReportBuilder getGroupReport() {
        TextColumnBuilder<String> yearColumn = col.column("Order year", exp.text(""));

        TextColumnBuilder<Date> orderDateColumn = col.column("Order date", "orderdate", type.dateType());
        TextColumnBuilder<String> itemColumn = col.column("Item", "item", type.stringType());
        TextColumnBuilder<Integer> quantityColumn = col.column("Quantity", "quantity", type.integerType());
        TextColumnBuilder<BigDecimal> unitPriceColumn = col.column("Unit price", "unitprice", type.bigDecimalType());

        CustomGroupBuilder yearGroup = grp.group(new YearExpression())
                .groupByDataType()
                .setHeaderLayout(GroupHeaderLayout.EMPTY)
                .setPadding(0);
        JasperReportBuilder report = DynamicReports.report();//a new report
        report
                .setTemplate(Templates.reportTemplate)
                .setSubtotalStyle(stl.style(Templates.boldStyle))
                .fields(
                        field("orderdate", type.dateYearType()))
                .columns(
                        yearColumn, orderDateColumn, itemColumn, quantityColumn, unitPriceColumn)
                .groupBy(yearGroup)
                .subtotalsAtGroupHeader(yearGroup,
                        sbt.first(new YearExpression(), yearColumn), sbt.sum(quantityColumn), sbt.sum(unitPriceColumn))
                .subtotalsAtSummary(
                        sbt.aggregate(exp.text("Total"), yearColumn, Calculation.NOTHING), sbt.sum(quantityColumn), sbt.sum(unitPriceColumn))
                .title(Templates.createTitleComponent("GroupLayout"))
                .pageFooter(Templates.footerComponent)
                .setDataSource(createDataSource());
        return report;
    }
    private void build() {

    }

    private class YearExpression extends AbstractSimpleExpression<String> {
        private static final long serialVersionUID = 1L;

        @Override
        public String evaluate(ReportParameters reportParameters) {
            return type.dateYearType().valueToString("orderdate", reportParameters);
        }
    }

    private JRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("orderdate", "item", "quantity", "unitprice");
        dataSource.add(toDate(2009, 11, 1), "Tablet", 5, new BigDecimal(250));
        dataSource.add(toDate(2009, 11, 1), "Laptop", 3, new BigDecimal(480));
        dataSource.add(toDate(2009, 12, 1), "Smartphone", 1, new BigDecimal(280));
        dataSource.add(toDate(2009, 12, 1), "Tablet", 1, new BigDecimal(190));
        dataSource.add(toDate(2010, 1, 1), "Tablet", 4, new BigDecimal(230));
        dataSource.add(toDate(2010, 1, 1), "Laptop", 2, new BigDecimal(650));
        dataSource.add(toDate(2010, 2, 1), "Laptop", 3, new BigDecimal(550));
        dataSource.add(toDate(2010, 2, 1), "Smartphone", 5, new BigDecimal(210));
        return dataSource;
    }

    private Date toDate(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, day);
        return c.getTime();
    }

    @RequestMapping("/report/order/{id}")
    public void jasperReport(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("application/pdf");
        UserGameOrder order = userGameOrderService.findOne(id);
        OutputStream out = response.getOutputStream();
        try {
            JasperReportBuilder jrb = createOrderReport(order);

            jrb.toPdf(out);
        } catch (DRException e) {
            throw new ServletException(e);
        }
        out.close();


    }
    private JasperReportBuilder createOrderReport(UserGameOrder order) throws SQLException {
        JasperReportBuilder report = DynamicReports.report();//a new report
        report
                .columns(
                        Columns.column("Order Id", "order_id", DataTypes.longType()),
                        Columns.column("Game Name", "game_name", DataTypes.stringType()),
                        Columns.column("Game Price ", "game_price", DataTypes.doubleType()))
                .title(//title of the report
                        Components.text("Order Report")
                                .setHorizontalAlignment(HorizontalAlignment.CENTER))
                .pageFooter(Components.pageXofY())//show page number on the page footer
                .setDataSource("SELECT u.order_id, g.game_name, g.game_price FROM user_game_order as u,game as g" +
                                " where u.game_gameId = g.game_id AND u.order_id="+order.getOrderId(),
                        dataSource.getConnection());

        return report;
    }

}

