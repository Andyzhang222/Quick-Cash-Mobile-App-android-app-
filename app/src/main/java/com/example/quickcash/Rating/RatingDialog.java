package com.example.quickcash.Rating;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

import androidx.annotation.NonNull;

import com.example.quickcash.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A custom dialog for submitting a rating for a user.
 * This dialog contains a rating bar and a submit button. When the user clicks the submit button,
 * the rating selected on the rating bar is submitted to Firebase and passed to the OnRatingSubmitListener.
 */
public class RatingDialog extends Dialog {

    public static final String RATING = "rating";
    public static final String RATING_COUNT = "ratingCount";
    private OnRatingSubmitListener listener;
    private String userId;


    /**
     * Constructs a RatingDialog with the given context, user ID, and OnRatingSubmitListener.
     *
     * @param context the context of the dialog
     * @param userId the ID of the user to submit a rating for
     * @param listener the listener for when the user submits a rating
     */
    public RatingDialog(@NonNull Context context, String userId, OnRatingSubmitListener listener) {
        super(context);
        this.userId = userId;
        this.listener = listener;
    }


    /**
     * Called when the dialog is created.
     *
     * Sets up the rating bar and submit button, and sets a click listener on the submit button
     * to call the updateRating method with the selected rating and the OnRatingSubmitListener
     * with the selected rating when clicked.
     *
     * @param savedInstanceState the saved instance state of the dialog
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rating_dialog);

        RatingBar ratingBar = findViewById(R.id.rating_bar);
        Button submitButton = findViewById(R.id.submit_button);

        submitButton.setOnClickListener(v -> {
            float rating = ratingBar.getRating();
            updateRating(userId, rating);
            listener.onSubmit(rating);
        });
    }

    /**
     * Updates the user's rating and rating count in Firebase with the new rating.
     *
     * @param userId the ID of the user to update the rating for
     * @param newRating the new rating to submit
     */
    private void updateRating(String userId, float newRating) {
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users").child(userId);

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    float currentRating;
                    int ratingCount;

                    if (dataSnapshot.hasChild(RATING) && dataSnapshot.hasChild(RATING_COUNT)) {
                        currentRating = dataSnapshot.child(RATING).getValue(Float.class);
                        ratingCount = dataSnapshot.child(RATING_COUNT).getValue(Integer.class);
                    } else {
                        currentRating = 0;
                        ratingCount = 0;
                    }

                    // Calculate new average rating
                    float updatedRating = ((currentRating * ratingCount) + newRating) / (ratingCount + 1);
                    int updatedRatingCount = ratingCount + 1;

                    // Update Firebase with the new rating and rating count
                    userRef.child(RATING).setValue(Math.round(updatedRating * 100.0f) / 100.0f);
                    userRef.child(RATING_COUNT).setValue(updatedRatingCount);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Error handling
            }
        });
    }


    /**
     * Interface definition for a callback to be invoked when a rating is submitted.
     */
    public interface OnRatingSubmitListener {
        void onSubmit(float rating);
    }
}


