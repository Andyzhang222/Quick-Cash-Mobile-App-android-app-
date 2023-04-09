package com.example.quickcash.Rating;

/**
 * An interface representing an observer of a PaymentObservable object.
 */
public interface RatingObserver {
    /**
     * Called by a PaymentObservable object when a payment has been completed.
     */
    void onPaymentCompleted();
}
