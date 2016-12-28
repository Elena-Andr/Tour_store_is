package ru.innopolis.tourstore.exception;

public class OrderDaoException extends Exception {

    public OrderDaoException(){
        super();
    }

    public OrderDaoException(String message){
        super(message);
    }
}
