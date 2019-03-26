package ru.yusdm.javacore.lesson12up13xml.autoservice;


import ru.yusdm.javacore.lesson12up13xml.autoservice.common.business.application.StorageType;
import ru.yusdm.javacore.lesson12up13xml.autoservice.common.business.application.servicefactory.ServiceSupplier;
import ru.yusdm.javacore.lesson12up13xml.autoservice.common.business.exception.AutoServiceCheckedException;
import ru.yusdm.javacore.lesson12up13xml.autoservice.common.business.search.OrderDirection;
import ru.yusdm.javacore.lesson12up13xml.autoservice.common.business.search.OrderType;
import ru.yusdm.javacore.lesson12up13xml.autoservice.common.solutions.utils.FileUtils;
import ru.yusdm.javacore.lesson12up13xml.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson12up13xml.autoservice.mark.search.MarkOrderByField;
import ru.yusdm.javacore.lesson12up13xml.autoservice.mark.search.MarkSearchCondition;
import ru.yusdm.javacore.lesson12up13xml.autoservice.mark.service.MarkService;
import ru.yusdm.javacore.lesson12up13xml.autoservice.model.service.ModelService;
import ru.yusdm.javacore.lesson12up13xml.autoservice.order.domain.Order;
import ru.yusdm.javacore.lesson12up13xml.autoservice.order.service.OrderService;
import ru.yusdm.javacore.lesson12up13xml.autoservice.reporting.ReportProvider;
import ru.yusdm.javacore.lesson12up13xml.autoservice.storage.initor.StorageInitializer;
import ru.yusdm.javacore.lesson12up13xml.autoservice.storage.initor.StorageInitorConstants;
import ru.yusdm.javacore.lesson12up13xml.autoservice.user.domain.User;
import ru.yusdm.javacore.lesson12up13xml.autoservice.user.service.UserService;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static ru.yusdm.javacore.lesson12up13xml.autoservice.common.solutions.utils.RandomUtils.getRandomInt;

public class AutoServiceDemo {

    private static class Application {
        static {
            ServiceSupplier.newInstance(StorageType.MEMORY_COLLECTION);
        }

        private UserService userService = ServiceSupplier.getInstance().getUserService();
        private MarkService markService = ServiceSupplier.getInstance().getMarkService();
        private ModelService modelService = ServiceSupplier.getInstance().getModelService();
        private OrderService orderService = ServiceSupplier.getInstance().getOrderService();

        public void fillStorage() throws Exception {
            addUsers();
            StorageInitializer storageInitor = new StorageInitializer(markService);
            File fileWithInitData = null;
            try {
                fileWithInitData = FileUtils
                        .createFileFromResource("init-data", ".txt", StorageInitorConstants.INIT_DATA_XML_FILE);
                storageInitor.initStorageWithMarksAndModels(fileWithInitData.getAbsolutePath(), StorageInitializer.DataSourceType.XML_FILE);
            } catch (AutoServiceCheckedException e) {
                System.out.println("ERROR while init storage: " + e.getMessage());
                throw e;
            } catch (Exception e) {
                System.out.println("Error: Unknown magic :" + e.getMessage());
                throw e;
            } finally {
                if (fileWithInitData != null) {
                    Files.delete(Paths.get(fileWithInitData.toURI()));
                }
            }
            appendOrdersToUsers();

        }

        private void addUsers() {
            String[] usersAsCsv = new String[]{
                    "Ivan        | Ivanov | 21",
                    "Petr        | Petrov | 23",
                    "Dmitry      | yuspov | 31",
                    "Dasha       | Jukova | 25",
                    "Wlad        | Belyh  | 23",
                    "Terminator  | T-800  | 125",
                    "Terminator  | T-1000  | 125",
            };
            for (String csvUser : usersAsCsv) {
                String[] userAttrs = csvUser.split("\\|");

                int attrIndex = -1;
                userService.insert(new User(userAttrs[++attrIndex].trim(),
                        userAttrs[++attrIndex].trim(),
                        Integer.parseInt(userAttrs[++attrIndex].trim())
                ));
            }
        }

        private void appendOrdersToUsers() {
            List<Mark> marks = markService.findAll();
            List<User> users = userService.findAll();

            List<Order> orders = new ArrayList<>();
            int i = 0;
            for (User user : users) {
                i++;
                orders.add(prepareOrderForUser(user, marks));

                if (i % 2 == 0) {
                    orders.add(prepareOrderForUser(user, marks));
                }
            }

            for (Order order : orders) {
                orderService.insert(order);
            }
        }

        private Order prepareOrderForUser(User user, List<Mark> marks) {
            Order order = new Order();
            order.setUser(user);
            Mark mark = marks.get(getRandomInt(0, marks.size() - 1));
            order.setMark(mark);
            order.setModel(mark.getModels().get(getRandomInt(0, mark.getModels().size() - 1)));
            order.setPrice(getRandomInt(1, 100000));

            return order;
        }

        public void printUsers() {
            userService.printAll();
        }

        public void printMarks() {
            markService.printAll();
        }

        public void deleteUsers() {
            userService.deleteById(1L);
            userService.insert(new User("SSSS", "AAAA", 333));
            userService.deleteById(33L);
        }

        public void searchMarksWithoutOrder() {
            System.out.println("\n----------Search marks No order ------------");
            MarkSearchCondition markSearchCondition = new MarkSearchCondition();
            List<Mark> searchResult = markService.search(markSearchCondition);
            for (Mark mark : searchResult) {
                System.out.println(mark.getAsStrWithoutModles());
            }
        }

        public void searchMarksWithOrderAsc() {
            System.out.println("\n----------Search marks Order ASC ------------");
            MarkSearchCondition markSearchCondition = new MarkSearchCondition();
            markSearchCondition.setOrderDirection(OrderDirection.ASC);
            markSearchCondition.setOrderByField(MarkOrderByField.COUNTRY);
            List<Mark> searchResult = markService.search(markSearchCondition);
            for (Mark mark : searchResult) {
                System.out.println(mark.getAsStrWithoutModles());
            }
        }

        public void searchMarksWithOrderDesc() {
            System.out.println("\n----------Search marks Order Desc ------------");
            MarkSearchCondition markSearchCondition = new MarkSearchCondition();
            markSearchCondition.setOrderDirection(OrderDirection.DESC);
            markSearchCondition.setOrderByField(MarkOrderByField.COUNTRY);
            List<Mark> searchResult = markService.search(markSearchCondition);
            for (Mark mark : searchResult) {
                System.out.println(mark.getAsStrWithoutModles());
            }
        }

        public void searchMarksWithComplexOrderAsc() {
            System.out.println("\n----------Search marks COMPLEX Order Desc ------------");
            MarkSearchCondition markSearchCondition = new MarkSearchCondition();
            markSearchCondition.setOrderDirection(OrderDirection.ASC);
            markSearchCondition.setOrderByField(MarkOrderByField.COUNTRY);
            markSearchCondition.setOrderType(OrderType.COMPLEX);
            List<Mark> searchResult = markService.search(markSearchCondition);
            for (Mark mark : searchResult) {
                System.out.println(mark.getAsStrWithoutModles());
            }
        }

        public void searchMarksWithComplexOrderDesc() {
            System.out.println("\n----------Search marks COMPLEX Order Desc ------------");
            MarkSearchCondition markSearchCondition = new MarkSearchCondition();
            markSearchCondition.setOrderDirection(OrderDirection.DESC);
            markSearchCondition.setOrderByField(MarkOrderByField.COUNTRY);
            markSearchCondition.setOrderType(OrderType.COMPLEX);
            List<Mark> searchResult = markService.search(markSearchCondition);
            for (Mark mark : searchResult) {
                System.out.println(mark.getAsStrWithoutModles());
            }
        }

        public void demoReporting() {
            ReportProvider reportProvider = new ReportProvider(userService, orderService, markService, modelService);

            File fileWithReport = null;
            try {
                fileWithReport = reportProvider.getUserOrdersTextFileReport();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

                if (fileWithReport != null) {
                    System.out.println("File with report '" + fileWithReport.getAbsolutePath() + "'");
                    /*
                    //uncomment line to delete temp file
                    boolean deleted = fileWithReport.delete();
                    if (!deleted) {
                        System.out.println("OOps, can't delete file " + fileWithReport.getAbsolutePath());
                    }*/
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Application application = new Application();
        application.fillStorage();

        System.out.println("--------Users------------");
        application.printUsers();

        System.out.println("--------Marks------------");
        application.printMarks();

        System.out.println("--------Delete users------------");
        application.deleteUsers();

        application.searchMarksWithoutOrder();
        application.searchMarksWithOrderAsc();
        application.searchMarksWithOrderDesc();
        application.searchMarksWithComplexOrderAsc();
        application.searchMarksWithComplexOrderDesc();

        application.demoReporting();
    }

}