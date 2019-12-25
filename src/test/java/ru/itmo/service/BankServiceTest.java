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
        String worker_name = "Donald";
        User worker = new User(worker_name, WORKER);
        inMemoryDataStorage.createUser(worker);
        BankService bankService = new BankService(inMemoryDataStorage);
        String message = bankService.login(worker_name);
        assertEquals("Success", message);
    }

    @Test
    public void deleteUser() throws UserAlreadyExistsException, NoSuchUserException, NoPermissionException {
        InMemoryDataStorage inMemoryDataStorage = new InMemoryDataStorage();
        String worker_name = "Donald";
        User worker = new User(worker_name, WORKER);
        inMemoryDataStorage.createUser(worker);
        inMemoryDataStorage.deleteUser(worker.getUserName());
        try {
            inMemoryDataStorage.getUser(worker.getUserName());
        } catch (NoSuchUserException e) {
            String actual_error = e.getMessage();
            String expected_error = "No user with such username = " + worker_name;
            assertEquals(expected_error, actual_error);
        }

    }


    @Test
    public void loginByDoesntExistingUser() throws UserAlreadyExistsException, NoSuchUserException, NoPermissionException {
        InMemoryDataStorage inMemoryDataStorage = new InMemoryDataStorage();
        String worker_name = "Donald";
        User worker = new User(worker_name, WORKER);
        inMemoryDataStorage.createUser(worker);
        inMemoryDataStorage.deleteUser(worker.getUserName());
        BankService bankService = new BankService(inMemoryDataStorage);
        String message = bankService.login(worker_name);
        assertEquals("User doesn't exist", message);
    }


    @Test
    public void loginByLoggedUser() throws UserAlreadyExistsException, NoSuchUserException, NoPermissionException {
        InMemoryDataStorage inMemoryDataStorage = new InMemoryDataStorage();
        String worker_name = "Donald";
        User worker = new User(worker_name, WORKER);
        inMemoryDataStorage.createUser(worker);
        BankService bankService = new BankService(inMemoryDataStorage);
        bankService.login(worker_name);
        String message = bankService.login(worker_name);
        assertEquals("Logged in user has no rights to log in", message);
    }


    @Test
    public void createUserWithUsernameAlreadyExists() throws UserAlreadyExistsException, NoPermissionException {
        InMemoryDataStorage inMemoryDataStorage = new InMemoryDataStorage();
        String worker_name = "Donald";
        User worker = new User(worker_name, WORKER);
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
        String worker_name = "Donald";
        User worker = new User(worker_name, WORKER);
        inMemoryDataStorage.createUser(worker);
        inMemoryDataStorage.deleteUser(worker.getUserName());
        try {
            inMemoryDataStorage.deleteUser(worker.getUserName());
        } catch (NoSuchUserException e) {
            String actual_error = e.getMessage();
            String expected_error = "No user with such username = " + worker_name;
            assertEquals(expected_error, actual_error);
        }

    }


    @Test
    public void createUserByClient() throws UserAlreadyExistsException, NoPermissionException {
        InMemoryDataStorage inMemoryDataStorage = new InMemoryDataStorage();
        String client_name = "Mark";
        User client = new User(client_name, CLIENT);
        try {
            inMemoryDataStorage.createUser(client);
        } catch (NoPermissionException e) {
            String error = e.getMessage();
            assertEquals("Logged in user has no rights to create new users", error);
        }
    }


}
