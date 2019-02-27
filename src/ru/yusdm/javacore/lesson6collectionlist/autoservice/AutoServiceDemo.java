package ru.yusdm.javacore.lesson6collectionlist.autoservice;


import ru.yusdm.javacore.lesson6collectionlist.autoservice.common.business.application.servicefactory.ServiceSupplier;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.common.business.application.StorageType;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.common.solutions.dataclasses.Pair;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.mark.search.MarkSearchCondition;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.mark.service.MarkService;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.model.service.ModelService;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.order.service.OrderService;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.storage.Storage;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.user.domain.User;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.user.service.UserService;

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

        private Storage storage = new Storage();

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
            Long id = 0L;
            for (String csvUser : usersAsCsv) {
                String[] userAttrs = csvUser.split("\\|");

                int attrIndex = -1;
                userService.add(new User(++id,
                        userAttrs[++attrIndex].trim(),
                        userAttrs[++attrIndex].trim(),
                        Integer.parseInt(userAttrs[++attrIndex].trim())
                ));
            }
        }

        private void addMarksWithModels() {
            Pair[] marksWithModels = new Pair[]{
                    new Pair("Mercedes-Benz | Germany",
                            new String[]{
                                    "G-500 Amg   | Fast and brutal | 1960 | -1",
                                    "SLR McLaren | Great Sound     | 2002 | 2008"
                            }
                    ),
                    new Pair("Kamaz | Russia",
                            new String[]{
                                    "53125 | Power yeaah | 1970 | -1"
                            }
                    ),

                    new Pair("Ural | Russia",
                            new String[]{
                                    "53125 | Power yeaah | 1970 | -1"
                            }
                    ),

                    new Pair("Ford | USA",
                            new String[]{
                                    "Focus   | Casual, economic | 2002 | -1",
                                    "Scorpio | 90-th dream      | 1992 | 1998",
                            }
                    ),
            };

            for (Pair markModelData : marksWithModels) {
                addMark(markModelData.getLeft(), markModelData.getRight());
            }
        }

        private void addMark(String markCsv, String[] modelsCsv) {
            String[] attrs = markCsv.split("\\|");
            int attrIndex = -1;

            Mark mark = new Mark(attrs[++attrIndex].trim(), attrs[++attrIndex].trim());
            mark.setModels(new Model[modelsCsv.length]);

            for (int i = 0; i < modelsCsv.length; i++) {
                String csvModel = modelsCsv[i];
                attrIndex = -1;
                attrs = csvModel.split("\\|");

                Model model = new Model();
                model.setName(attrs[++attrIndex].trim());
                model.setDescription(attrs[++attrIndex].trim());
                model.setProductionYearStart(Integer.parseInt(attrs[++attrIndex].trim()));
                int productionYearEnd = Integer.parseInt(attrs[++attrIndex].trim());
                model.setProductionYearEnd(productionYearEnd == -1 ? null : productionYearEnd);

                mark.getModels()[i] = model;
            }

            markService.add(mark);
        }

        public void fillStorage() {
            addUsers();
            addMarksWithModels();
        }

        public void printUsers() {
            userService.printAll();
        }

        public void printMarks() {
            markService.printAll();
        }

        public void deleteUsers() {

            userService.deleteById(1L);
            System.out.println("----------Search marks by country and mark name------------");
            MarkSearchCondition markSearchCondition = new MarkSearchCondition();
            markSearchCondition.setCountry("Russia");
            markSearchCondition.setName("Ural");
            List<Mark> searchResult = markService.search(markSearchCondition);
            System.out.println("-----Search result----------------------");
            for (Mark mark : searchResult) {
                System.out.println(mark);
            }
            userService.add(new User(33L, "SSSS", "AAAA", 333));
            userService.deleteById(33L);
        }
    }

    public static void main(String[] args) {
        Application application = new Application();
        application.fillStorage();

        System.out.println("--------Users------------");
        application.printUsers();

        System.out.println("--------Marks------------");
        application.printMarks();

        application.deleteUsers();
        System.out.println();
    }

}
