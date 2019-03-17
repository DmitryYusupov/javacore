package ru.yusdm.javacore.lesson11io.autoservice.reporting;

import ru.yusdm.javacore.lesson11io.autoservice.common.business.exception.AutoServiceCheckedException;
import ru.yusdm.javacore.lesson11io.autoservice.mark.service.MarkService;
import ru.yusdm.javacore.lesson11io.autoservice.model.service.ModelService;
import ru.yusdm.javacore.lesson11io.autoservice.order.domain.Order;
import ru.yusdm.javacore.lesson11io.autoservice.order.service.OrderService;
import ru.yusdm.javacore.lesson11io.autoservice.user.domain.User;
import ru.yusdm.javacore.lesson11io.autoservice.user.service.UserService;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class UserOrdersIoTextFileReport implements ReportComponent {
    private static final String USER_SEPARATOR = "--------------------------";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    private final UserService userService;
    private final OrderService orderService;
    private final MarkService markService;
    private final ModelService modelService;

    public UserOrdersIoTextFileReport(UserService userService,
                                      OrderService orderService,
                                      MarkService markService,
                                      ModelService modelService) {
        this.userService = userService;
        this.orderService = orderService;
        this.markService = markService;
        this.modelService = modelService;
    }

    @Override
    public File generateReport() throws Exception {
        File tempFile = File.createTempFile(System.currentTimeMillis() + "_user_orders_report", "_io.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            List<String> report = getReportData();

            for (String reportLine : report) {
                writer.write(reportLine);
                writer.newLine();
            }

            writer.flush();
        }

        return tempFile;
    }

    private List<String> getReportData() {
        List<String> report = new ArrayList<>();
        List<User> users = userService.findAll();

        if (users != null && !users.isEmpty()) {
            for (User user : users) {
                report.add(USER_SEPARATOR);
                report.add(userToReportLine(user));
                report.add("\n");
                report.addAll(getOrderInformation(user));
            }
        }

        return report;
    }

    private String userToReportLine(User user) {
        StringBuilder userAsStr = new StringBuilder();

        userAsStr.append("User:").append(LINE_SEPARATOR)
                .append("Full name: ").append(user.getFirstName()).append(" ").append(user.getLastName());

        return userAsStr.toString();
    }

    private List<String> getOrderInformation(User user) {
        List<String> reportData = new ArrayList<>();

        List<Order> orders = orderService.getOrdersByUser(user.getId());
        reportData.add("Total orders: " + orders.size());

        if (!orders.isEmpty()) {
            reportData.add("Orders:");
            for (Order order : orders) {
                reportData.add(orderToReportLine(order));
            }
        }

        return reportData;
    }

    private String orderToReportLine(Order order) {
        StringBuilder orderAsStr = new StringBuilder();

        orderAsStr.append("Order: ")
                .append("Mark: ").append(order.getMark().getName()).append(";")
                .append(" Model: ").append(order.getModel().getName()).append(";")
                .append(" Price: ").append(order.getPrice());

        return orderAsStr.toString();
    }

}
