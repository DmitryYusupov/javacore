package ru.yusdm.javacore.lesson22up23relationaldb.autoservice.user.dto;

import ru.yusdm.javacore.lesson22up23relationaldb.autoservice.common.business.dto.BaseDto;
import ru.yusdm.javacore.lesson22up23relationaldb.autoservice.user.domain.ClientType;

public class UserDto extends BaseDto<Long> {
    private String firstName;
    private String lastName;
    private int age;
    private ClientType clientType;

    public UserDto() {
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", clientType='" + clientType + '\'' +
                '}';
    }

}
