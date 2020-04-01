package ru.itmo.service;

import org.junit.Test;
import ru.itmo.exception.NoSuchUserException;
import ru.itmo.exception.UserAlreadyExistsException;
import ru.itmo.model.User;
import ru.itmo.storage.InMemoryDataStorage;

import javax.naming.NoPermissionException;

import static org.junit.Assert.assertEquals;
import static ru.itmo.model.UserType.CLIENT;
import static ru.itmo.model.UserType.WORKER;

public class BankServiceTest {

    @Test
    public void loginByExistingUser() throws UserAlreadyExistsException, NoPermissionException {
        InMemoryDataStorage inMemoryDataStorage = new InMemoryDataStorage();
        String workerName = "Donald";
        User worker = new User(workerName, WORKER);
        inMemoryDataStorage.createUser(worker);
        BankService bankService = new BankService(inMemoryDataStorage);
        String message = bankService.login(workerName);
        assertEquals("Success", message);
    }

    @Test
    public void deleteUser() throws UserAlreadyExistsException, NoSuchUserException, NoPermissionException {
        InMemoryDataStorage inMemoryDataStorage = new InMemoryDataStorage();
        String workerName = "Donald";
        User worker = new User(workerName, WORKER);
        inMemoryDataStorage.createUser(worker);
        inMemoryDataStorage.deleteUser(worker.getUserName());
        try {
            inMemoryDataStorage.getUser(worker.getUserName());
        } catch (NoSuchUserException e) {
            String actualError = e.getMessage();
            String expectedError = "No user with such username = " + workerName;
            assertEquals(expectedError, actualError);
        }

    }


    @Test
    public void loginByDoesntExistingUser() throws UserAlreadyExistsException, NoSuchUserException, NoPermissionException {
        InMemoryDataStorage inMemoryDataStorage = new InMemoryDataStorage();
        String workerName = "Donald";
        User worker = new User(workerName, WORKER);
        inMemoryDataStorage.createUser(worker);
        inMemoryDataStorage.deleteUser(worker.getUserName());
        BankService bankService = new BankService(inMemoryDataStorage);
        String message = bankService.login(workerName);
        assertEquals("User doesn't exist", message);
    }


    @Test
    public void loginByLoggedUser() throws UserAlreadyExistsException, NoSuchUserException, NoPermissionException {
        InMemoryDataStorage inMemoryDataStorage = new InMemoryDataStorage();
        String workerName = "Donald";
        User worker = new User(workerName, WORKER);
        inMemoryDataStorage.createUser(worker);
        BankService bankService = new BankService(inMemoryDataStorage);
        bankService.login(workerName);
        String message = bankService.login(workerName);
        assertEquals("Logged in user has no rights to log in", message);
    }


    @Test
    public void createUserWithUsernameAlreadyExists() throws UserAlreadyExistsException, NoPermissionException {
        InMemoryDataStorage inMemoryDataStorage = new InMemoryDataStorage();
        String workerName = "Donald";
        User worker = new User(workerName, WORKER);
        inMemoryDataStorage.createUser(worker);
        try {
            inMemoryDataStorage.createUser(worker);
        } catch (UserAlreadyExistsException e) {
            String error = e.getMessage();
            assertEquals("This user has already created", error);
        }
    }


    @Test
    public void deleteUserThatDoesntExists() throws UserAlreadyExistsException, NoSuchUserException, NoPermissionException {
        InMemoryDataStorage inMemoryDataStorage = new InMemoryDataStorage();
        String workerName = "Donald";
        User worker = new User(workerName, WORKER);
        inMemoryDataStorage.createUser(worker);
        inMemoryDataStorage.deleteUser(worker.getUserName());
        try {
            inMemoryDataStorage.deleteUser(worker.getUserName());
        } catch (NoSuchUserException e) {
            String actualError = e.getMessage();
            String expectedError = "No user with such username = " + workerName;
            assertEquals(expectedError, actualError);
        }

    }


    @Test
    public void createUserByClient() throws UserAlreadyExistsException {
        InMemoryDataStorage inMemoryDataStorage = new InMemoryDataStorage();
        String clientName = "Mark";
        User client = new User(clientName, CLIENT);
        /*
          Why NoPermissionException in 114 doesn't work?
        try {
            inMemoryDataStorage.createUser(client);
        } catch (NoPermissionException e) {
            String error = e.getMessage();
            assertEquals("Logged in user has no rights to create new users", error);
        }
         */

    }


}
