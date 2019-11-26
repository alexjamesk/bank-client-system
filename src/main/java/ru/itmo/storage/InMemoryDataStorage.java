package ru.itmo.storage;

import ru.itmo.exception.NoSuchUserException;
import ru.itmo.exception.UserAlreadyExistsException;
import ru.itmo.model.User;

import java.util.HashMap;
import java.util.Map;

public class InMemoryDataStorage implements DataStorage {

    private Map<String, User> clients = new HashMap<String, User>();

    public void putUser(User user) throws UserAlreadyExistsException {

    }

    public void getUser(String username) throws NoSuchUserException {
        return null;
    }

    public User getStatus(String status) {
        return null;
    }

    public User getAddress(String address) {
        return null;
    }

    public void modifyUserInfo() {

    }

    public void setPercent(int percent) {

    }

    public void allowCredit(boolean allow) {

    }

    public void forbidCredit(boolean forbid) {

    }

    public void checkPayAble(int monthlyIncome) {

    }

    public void getBonus(int bonus) {

    }

    public void makeBonus(int bonus) {

    }

    public void deleteUser(String username) throws NoSuchUserException {

    }
}

