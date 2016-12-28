package ru.innopolis.tourstore.exception;

public class UserDaoException extends Exception {

    public UserDaoException(){
        super();
    }

    public UserDaoException(String message){
        super(message);
    }
}
