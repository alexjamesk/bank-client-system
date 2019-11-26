package ru.itmo.storage;

import ru.itmo.exception.*;
import ru.itmo.model.User;

//this interface contains convenient methods for users storage and retrieval:
public interface DataStorage {
    void putUser(User user) throws UserAlreadyExistsException;

    void getUser(String username) throws NoSuchUserException;

    User getStatus(String status);

    User getAddress(String address);

    void modifyUserInfo();

    void setPercent(int percent);

    void allowCredit(boolean allow);

    void forbidCredit(boolean forbid);

    void checkPayAble(int monthlyIncome);

    void getBonus(int bonus);


    void deleteUser(String username) throws NoSuchUserException;

}
