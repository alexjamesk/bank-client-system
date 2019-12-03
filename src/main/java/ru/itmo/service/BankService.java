package ru.itmo.service;

import ru.itmo.exception.NoSuchUserException;
import ru.itmo.exception.UserAlreadyExistsException;
import ru.itmo.model.User;
import ru.itmo.model.UserType;
import ru.itmo.storage.DataStorage;
import ru.itmo.storage.InMemoryDataStorage;

public class BankService {
    private User loggedInUser;
    private DataStorage dataStorage;

    public BankService() {
        this.dataStorage = new InMemoryDataStorage();
    }

    public String login(String username) {
        try {
            this.loggedInUser = dataStorage.getUser(username);
        } catch (NoSuchUserException exception) {
            return "User doesn't exist";
        }
        return "Success";
    }

    public void logout(String username) {
        this.loggedInUser = null;
    }

    public String createUser(String username, UserType type) {
        if (!checkLoggedInUserWorker()) {
            return "Logged in user has no rights to create new users";
        }
        User newUser = new User(username, type);
        try {
            dataStorage.createUser(newUser);
        } catch (UserAlreadyExistsException e) {
            return "User with such username already exists";
        }
        return "Success";
    }

    public String deleteUser(String username) {
        if (!checkLoggedInUserWorker()) {
            return "Logged in user has no rights to delete new users";
        }
        try {
            dataStorage.deleteUser(username);
        } catch (NoSuchUserException e) {
            return "User with such username doesn't exist";
        }
        return "Success";
    }

    private boolean checkLoggedInUserWorker() {
        return UserType.WORKER.equals(loggedInUser.getType());
    }

}
