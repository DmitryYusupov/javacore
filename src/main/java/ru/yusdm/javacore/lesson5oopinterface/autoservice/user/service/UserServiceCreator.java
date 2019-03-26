package ru.yusdm.javacore.lesson5oopinterface.autoservice.user.service;



import ru.yusdm.javacore.lesson5oopinterface.autoservice.common.business.application.StorageType;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.user.repo.impl.UserMemoryArrayRepo;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.user.service.impl.UserDefaultService;

/**
 * Created by Admin on 2/25/2019.
 */
public final class UserServiceCreator {

    private UserServiceCreator() {

    }

    public static UserService getUserService(StorageType storageType) {
        switch (storageType) {

            case MEMORY_ARRAY:
                return new UserDefaultService(new UserMemoryArrayRepo());

            case MEMORY_COLLECTION:{
                return null;
            }

            default: {
                return null;
            }
        }
    }
}
