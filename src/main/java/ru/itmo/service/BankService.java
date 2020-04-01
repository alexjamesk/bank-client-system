package ru.itmo.service;

import ru.itmo.exception.NoSuchUserException;
import ru.itmo.exception.UserAlreadyExistsException;
import ru.itmo.model.User;
import ru.itmo.model.UserType;
import ru.itmo.storage.DataStorage;
import ru.itmo.storage.InMemoryDataStorage;

import javax.naming.NoPermissionException;

public class BankService {
    private User loggedInUser;
    private DataStorage dataStorage;

    public BankService() {
        this.dataStorage = new InMemoryDataStorage();
    }

    BankService(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    public String login(String username) {

        if (loggedInUser!=null) {
            return "Can't login: some user is already logged in";
        } else {
            try {
                this.loggedInUser = dataStorage.getUser(username);
                return "Success";
            } catch (NoSuchUserException exception) {
                return "User doesn't exist";
            }
        }
    }

    public void logout(String username) {
        this.loggedInUser = null;
    }

    public String createUser(String username, UserType type) {
        if (!isLoggedInUserWorker()) {
            return "Logged in user has no rights to create new users";
        }
        User newUser = new User(username, type);
        try {
            dataStorage.createUser(newUser);
        } catch (UserAlreadyExistsException | NoPermissionException e) {
            return "User with such username already exists";
        }
        return "Success";
    }

    public String deleteUser(String username) {
        if (!isLoggedInUserWorker()) {
            return "Logged in user has no rights to delete new users";
        }
        try {
            dataStorage.deleteUser(username);
        } catch (NoSuchUserException e) {
            return "User with such username doesn't exist";
        }
        return "Success";
    }

    private boolean isLoggedInUserWorker() {
        return UserType.WORKER.equals(loggedInUser.getType());
    }

   // private boolean isUserLoggedIn(String username){
   //     if (loggedInUser == null) {
   //         return false;
   //     }
   //     System.out.println(username+"+"+loggedInUser.getUserName());
    //    if(username.equals(loggedInUser.getUserName())){
    //        //After deleting User: LoggedInUser.getUserName already get previous username...
    //        return true;
     //   }else{
     //     return false;
      //  }
    //}
}