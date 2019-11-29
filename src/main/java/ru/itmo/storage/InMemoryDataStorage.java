package ru.itmo.storage;

import ru.itmo.exception.NoSuchUserException;
import ru.itmo.exception.UserAlreadyExistsException;
import ru.itmo.model.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Some difficulties with the HashMap
 */

public class InMemoryDataStorage implements DataStorage {

    private Map<String, User> users = new HashMap<String, User>();


    public void createUser(User user) throws UserAlreadyExistsException {
        if (users.containsKey(user.getUserName())) {
            throw new UserAlreadyExistsException("This user has already created ");
        }
        users.put(user.getUserName(), user);
    }

    public User getUser(String username) throws NoSuchUserException {
        if (!users.containsKey(username)) {
            throw new NoSuchUserException("No user with such username = " + username);
        }
        return users.get(username);
    }

    public void deleteUser(String username) throws NoSuchUserException {
        if (!users.containsKey(username)) {
            throw new NoSuchUserException("No user with such username = " + username);
        }
        users.remove(username);
    }

}

