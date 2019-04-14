package ru.yusdm.javacore.lesson24web.autoservice;


import ru.yusdm.javacore.lesson24web.autoservice.common.business.application.StorageType;
import ru.yusdm.javacore.lesson24web.autoservice.common.business.application.servicefactory.ServiceSupplier;
import ru.yusdm.javacore.lesson24web.autoservice.common.business.exception.AutoServiceCheckedException;
import ru.yusdm.javacore.lesson24web.autoservice.common.business.search.OrderDirection;
import ru.yusdm.javacore.lesson24web.autoservice.common.business.search.OrderType;
import ru.yusdm.javacore.lesson24web.autoservice.common.business.search.Paginator;
import ru.yusdm.javacore.lesson24web.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson24web.autoservice.mark.search.MarkOrderByField;
import ru.yusdm.javacore.lesson24web.autoservice.mark.search.MarkSearchCondition;
import ru.yusdm.javacore.lesson24web.autoservice.mark.service.MarkService;
import ru.yusdm.javacore.lesson24web.autoservice.model.service.ModelService;
import ru.yusdm.javacore.lesson24web.autoservice.order.domain.Order;
import ru.yusdm.javacore.lesson24web.autoservice.order.service.OrderService;
import ru.yusdm.javacore.lesson24web.autoservice.reporting.ReportProvider;
import ru.yusdm.javacore.lesson24web.autoservice.storage.initor.StorageInitializer;
import ru.yusdm.javacore.lesson24web.autoservice.storage.initor.fromsql.H2DbInitor;
import ru.yusdm.javacore.lesson24web.autoservice.user.domain.User;
import ru.yusdm.javacore.lesson24web.autoservice.user.service.UserService;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ru.yusdm.javacore.lesson24web.autoservice.common.application.ApplicationConfigurations.*;
import static ru.yusdm.javacore.lesson24web.autoservice.common.solutions.utils.FileUtils.createFileFromResource;
import static ru.yusdm.javacore.lesson24web.autoservice.common.solutions.utils.RandomUtils.getRandomInt;

public class AutoServiceDemo {

    public static class Application {
        private static StorageType storageType = StorageType.RELATIONAL_DB;

        static {
            ServiceSupplier.newInstance(storageType);
        }

        private UserService userService = ServiceSupplier.getInstance().getUserService();
        private MarkService markService = ServiceSupplier.getInstance().getMarkService();
        private ModelService modelService = ServiceSupplier.getInstance().getModelService();
        private OrderService orderService = ServiceSupplier.getInstance().getOrderService();

        public void fillStorage() {
            insertUsers();
            if (!StorageType.RELATIONAL_DB.equals(storageType)) {
                fillStorageIfMemoryStorage();
            }
            insertOrders();
        }

        public void fillStorageIfMemoryStorage() {
            try {
                StorageInitializer storageInitor = new StorageInitializer(markService);
                List<File> filesWithInitData = null;
                try {
                    filesWithInitData = getFilesWithDataToInit();
                    storageInitor.initStorageWithMarksAndModels(filesWithInitData, StorageInitializer.DataSourceType.XML_FILE);
                } catch (AutoServiceCheckedException e) {
                    System.out.println("ERROR while init storage: " + e.getMessage());
                    throw e;
                } catch (Exception e) {
                    System.out.println("Error: Unknown magic :" + e.getMessage());
                    throw e;
                } finally {
                    if (filesWithInitData != null) {
                        for (File file : filesWithInitData) {
                            Files.delete(Paths.get(file.toURI()));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Init data error", e);
            }
        }

        private List<File> getFilesWithDataToInit() throws Exception {
            String files[] = new String[]{INIT_DATA_XML_FILE_PART_1, INIT_DATA_XML_FILE_PART_2};
            List<File> result = new ArrayList<>();

            for (String file : files) {
                result.add(createFileFromResource(this.getClass(),"init-data", ".txt", file));
            }
            return result;
        }


        private void insertUsers() {
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

        private void insertOrders() {
            List<Mark> marks = markService.findAllMarksFetchingModels();
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
            orderService.insert(orders);

            //test transaction
            orderService.update(orders.get(0));
        }

        private Order prepareOrderForUser(User user, List<Mark> marks) {
            Order order = new Order();
            order.setUser(user);
            Mark mark = marks.get(getRandomInt(0, marks.size() - 1));
            order.setMark(mark);
            order.setModel(mark.getModels().get(getRandomInt(0, mark.getModels().size() - 1)));
            order.setPrice(getRandomInt(1, 100000));
            List<String> problems = Arrays.asList("Не заводится",
                    "Еле едет", "Запах масла в салоне", "Стучит подвеска", "Ржавчина на пороге", "Помято крыло", "Не греют сиденья",
                    "Не включается дальний свет");

            order.setDescription(problems.get(getRandomInt(0, problems.size() - 1)));
            return order;
        }

        public void printUsers() {
            userService.printAll();
        }

        public void printMarks() {
            markService.printAll();
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

        public void searchMarksWithPaginator() {
            System.out.println("\n-----------Search marks with paginator---------------------");
            int totalMarks = markService.countAll();
            int totalPages = (int) Math.ceil((float) totalMarks / PAGE_SIZE);

            MarkSearchCondition searchCondition = new MarkSearchCondition();
            searchCondition.setPaginator(new Paginator());

            for (int i = 0; i < totalPages; i++) {
                searchCondition.getPaginator().setOffset(PAGE_SIZE * i);
                List<Mark> found = markService.search(searchCondition);

                if (!found.isEmpty()) {
                    int factLimit = found.size();
                    System.out.println("Display records on page from ["
                            + searchCondition.getPaginator().getOffset() + " - " +
                            (searchCondition.getPaginator().getOffset() + factLimit) + "]");

                    for (Mark mark : found) {
                        System.out.println(mark.getAsStrWithoutModles());
                    }
                    System.out.println("----------");
                }
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
                    //uncomment line to delete temp file
                    boolean deleted = fileWithReport.delete();
                    if (!deleted) {
                        System.out.println("OOps, can't delete file " + fileWithReport.getAbsolutePath());
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {

        H2DbInitor h2DbInitor = new H2DbInitor();
        h2DbInitor.init();

        Application application = new Application();
        application.fillStorage();

        System.out.println("--------Users------------");
        application.printUsers();

        System.out.println("--------Marks------------");
        application.printMarks();

        application.searchMarksWithoutOrder();
        application.searchMarksWithOrderAsc();
        application.searchMarksWithOrderDesc();
        application.searchMarksWithComplexOrderAsc();
        application.searchMarksWithComplexOrderDesc();

        System.out.println("----Demo mark pagination -----");
        application.searchMarksWithPaginator();
        application.demoReporting();
    }


}