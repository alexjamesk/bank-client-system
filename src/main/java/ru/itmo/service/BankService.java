package ru.itmo.service;

import ru.itmo.exception.NoSuchUserException;
import ru.itmo.exception.UserAlreadyExistsException;
import ru.itmo.model.User;
import ru.itmo.model.UserType;
import ru.itmo.storage.*;

public class BankService implements DataStorage {
    private User loggedInUser;
    private DataStorage dataStorage;

    public BankService() {
        this.dataStorage = new InMemoryDataStorage();
    }

    String login(String username) {

    }

    void logout(String username) {

    }

    String createUser(String username, UserType type) throws UserAlreadyExistsException {
        return "Success";
    }

    public void putUser(User user) throws UserAlreadyExistsException {

    }

    public void getUser(String username) throws NoSuchUserException {
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
