package ru.yusdm.javacore.lesson21regularexpressions.lesson;

import ru.yusdm.javacore.lesson11ionio.autoservice.order.domain.Order;

import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 4/8/2019.
 */
public class C_WhyDto {

    private class DomainUser {
        private Long id;
        private String name;
        private String lastName;
        private String INTERNAL_STATUS = "TWICE_UPDATE_ERROR";
        private String status = "VIP";
        private Date createDate;
        private Date updateDate;
        private List<Order> orders;
    }

    private class DomainUserNew {
        private Long id;
        private String name;
        private String lastName;
        private String INTERNAL_STATUS = "TWICE_UPDATE_ERROR";
        private String status = "VIP";
        private Date createDate;
        private Date updateDate;
        private List<Order> orders;
    }

    //bridge

    private class DomainDto {
        private Long id;
        private String name;
        private String lastName;
        private String status = "VIP";
    }


}
