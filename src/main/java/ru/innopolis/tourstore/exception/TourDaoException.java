package ru.innopolis.tourstore.exception;

public class TourDaoException extends Exception {

    public TourDaoException(){
        super();
    }

    public TourDaoException(String message){
        super(message);
    }
}
