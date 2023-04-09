package com.example.quickcash;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.example.quickcash.Rating.PaymentObservable;
import com.example.quickcash.Rating.RatingObserver;

public class PaymentObservableTest {

    @Test
    public void testAddAndNotifyObservers() {
        PaymentObservable paymentObservable = new PaymentObservable();
        RatingObserver observer1 = Mockito.mock(RatingObserver.class);
        RatingObserver observer2 = Mockito.mock(RatingObserver.class);

        paymentObservable.addObserver(observer1);
        paymentObservable.addObserver(observer2);

        paymentObservable.notifyObservers();

        verify(observer1, times(1)).onPaymentCompleted();
        verify(observer2, times(1)).onPaymentCompleted();
    }

    @Test
    public void testRemoveObserver() {
        PaymentObservable paymentObservable = new PaymentObservable();
        RatingObserver observer1 = Mockito.mock(RatingObserver.class);
        RatingObserver observer2 = Mockito.mock(RatingObserver.class);

        paymentObservable.addObserver(observer1);
        paymentObservable.addObserver(observer2);

        paymentObservable.removeObserver(observer1);
        paymentObservable.notifyObservers();

        verify(observer1, times(0)).onPaymentCompleted();
        verify(observer2, times(1)).onPaymentCompleted();
    }
}

