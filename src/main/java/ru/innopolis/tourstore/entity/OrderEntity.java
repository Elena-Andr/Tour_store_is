package ru.innopolis.tourstore.entity;

import org.h2.engine.User;

import javax.persistence.*;

/**
 * Object represents entity "Order"
 */
@Entity
@Table(name = "ORDERS")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="tour_id")
    private TourEntity tourEntity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public TourEntity getTourEntity() {
        return tourEntity;
    }

    public void setTourEntity(TourEntity tourEntity) {
        this.tourEntity = tourEntity;
    }

    public void setUserId(int userId){
        this.userEntity.setId(userId);
    }

    public int getUserId(){
        return userEntity.getId();
    }

    public void setTourId(int tourId){
        this.tourEntity.setId(tourId);
    }

    public int getTourId(){
        return tourEntity.getId();
    }
}
