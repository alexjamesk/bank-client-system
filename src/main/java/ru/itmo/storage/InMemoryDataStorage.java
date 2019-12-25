package ru.itmo.storage;

import ru.itmo.exception.NoSuchUserException;
import ru.itmo.exception.UserAlreadyExistsException;
import ru.itmo.model.User;
import ru.itmo.model.UserType;
import ru.itmo.service.BankService;

import javax.naming.NoPermissionException;
import java.util.HashMap;
import java.util.Map;

public class InMemoryDataStorage implements DataStorage {

    private Map<String, User> users = new HashMap<String, User>();


    public void createUser(User user) throws UserAlreadyExistsException, NoPermissionException {
        if(!checkIsWorker()) {
            throw new NoPermissionException("Logged in user has no rights to create new users");
        }

        if (users.containsKey(user.getUserName())) {
            throw new UserAlreadyExistsException("This user has already created");
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


    private boolean checkIsWorker() {
        try {
            return UserType.WORKER.equals(BankService.getloggedInUserType());
        }catch(NullPointerException e){
            return true; //We can't create worker as a start, because we aren't worker as a start.
        }
    }

}