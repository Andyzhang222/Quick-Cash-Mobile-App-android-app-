package com.example.quickcash.Rating;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing an observable payment subject that can be observed by RatingObservers.
 */
public class PaymentObservable {
    //A list of RatingObservers that are currently observing this PaymentObservable.
    private List<RatingObserver> observers = new ArrayList<>();

    /**
     * Adds a new observer to the list of observers for this PaymentObservable.
     * @param observer The RatingObserver to be added.
     */
    public void addObserver(RatingObserver observer) {
        observers.add(observer);
    }

    /**
     * Removes an observer from the list of observers for this PaymentObservable.
     * @param observer The RatingObserver to be removed.
     */
    public void removeObserver(RatingObserver observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all observers in the list of observers that a payment has been completed.
     * This is done by calling the onPaymentCompleted method for each observer in the list.
     */
    public void notifyObservers() {
        for (RatingObserver observer : observers) {
            observer.onPaymentCompleted();
        }
    }
}

