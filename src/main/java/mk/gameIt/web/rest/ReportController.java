package mk.gameIt.web.rest;

import mk.gameIt.domain.UserGameOrder;
import mk.gameIt.service.UserGameOrderService;
import mk.gameIt.service.UserService;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.jasper.builder.export.ExporterBuilders;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.exception.DRException;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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

