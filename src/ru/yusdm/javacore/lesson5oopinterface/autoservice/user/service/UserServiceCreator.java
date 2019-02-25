package ru.yusdm.javacore.lesson5oopinterface.autoservice.user.service;



import ru.yusdm.javacore.lesson5oopinterface.autoservice.common.business.data.DataType;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.user.repo.impl.UserMemoryRepo;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.user.service.impl.UserDefaultService;

/**
 * Created by Admin on 2/25/2019.
 */
public final class UserServiceCreator {

    private UserServiceCreator() {

    }

    public static UserService getUserService(DataType dataType) {
        switch (dataType) {

            case MEMORY_ARRAY:
                return new UserDefaultService(new UserMemoryRepo());

            case MEMORY_COLLECTION:{
                return null;
            }

            default: {
                return null;
            }
        }
    }
}
