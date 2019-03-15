package ru.yusdm.javacore.lesson11io.autoservice;


import ru.yusdm.javacore.lesson11io.autoservice.common.business.application.StorageType;
import ru.yusdm.javacore.lesson11io.autoservice.common.business.application.servicefactory.ServiceSupplier;
import ru.yusdm.javacore.lesson11io.autoservice.common.business.exception.AutoServiceCheckedException;
import ru.yusdm.javacore.lesson11io.autoservice.common.business.search.OrderDirection;
import ru.yusdm.javacore.lesson11io.autoservice.common.business.search.OrderType;
import ru.yusdm.javacore.lesson11io.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson11io.autoservice.mark.search.MarkOrderByField;
import ru.yusdm.javacore.lesson11io.autoservice.mark.search.MarkSearchCondition;
import ru.yusdm.javacore.lesson11io.autoservice.mark.service.MarkService;
import ru.yusdm.javacore.lesson11io.autoservice.model.service.ModelService;
import ru.yusdm.javacore.lesson11io.autoservice.order.service.OrderService;
import ru.yusdm.javacore.lesson11io.autoservice.storage.initor.StorageInitor;
import ru.yusdm.javacore.lesson11io.autoservice.storage.initor.StorageInitorConstants;
import ru.yusdm.javacore.lesson11io.autoservice.user.domain.User;
import ru.yusdm.javacore.lesson11io.autoservice.user.service.UserService;

import java.util.List;

public class AutoServiceDemo {

    private static class Application {
        static {
            ServiceSupplier.newInstance(StorageType.MEMORY_COLLECTION);
        }

        private UserService userService = ServiceSupplier.getInstance().getUserService();
        private MarkService markService = ServiceSupplier.getInstance().getMarkService();
        private ModelService modelService = ServiceSupplier.getInstance().getModelService();
        private OrderService orderService = ServiceSupplier.getInstance().getOrderService();

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

        public void fillStorage() throws Exception {
            addUsers();
            StorageInitor storageInitor = new StorageInitor(markService);
            try {
                storageInitor.initStorage(StorageInitorConstants.INIT_DATA_TXT_FILE, StorageInitor.DataSourceType.TXT_FILE);
            }
            catch (AutoServiceCheckedException e){
                System.out.println("ERROR while init storage: " + e.getMessage());
                throw e;
            }
            catch (Exception e) {
                System.out.println("Error: Unknown magic :" +e.getMessage());
                throw e;
            }
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
    }

}