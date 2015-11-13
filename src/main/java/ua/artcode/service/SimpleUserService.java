package ua.artcode.service;

import ua.artcode.exception.AppException;
import ua.artcode.model.common.AccountType;
import ua.artcode.model.common.UserAccount;

import java.util.Set;

public interface SimpleUserService {

    UserAccount authenticate(String username, String password) throws AppException;

    UserAccount register(String username, String password, String email, AccountType accountType) throws AppException;

    UserAccount getUserInfo(String username);

    Set<String> getAllUser();

}
