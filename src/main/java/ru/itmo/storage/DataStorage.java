package ru.itmo.storage;

import ru.itmo.exception.NoSuchUserException;
import ru.itmo.exception.UserAlreadyExistsException;
import ru.itmo.model.User;

import javax.naming.NoPermissionException;

public interface DataStorage {

    void createUser(User user) throws UserAlreadyExistsException, NoPermissionException;

    User getUser(String username) throws NoSuchUserException;

    void deleteUser(String username) throws NoSuchUserException;

}