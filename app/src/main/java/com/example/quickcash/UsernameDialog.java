package com.example.quickcash;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class for displaying a dialog to set username for users.
 */
public class UsernameDialog {

    private static final String TAG = "UsernameDialog";
    public static final String USERS = "Users";
    public static final String USERNAME = "username";

    /**
     * Interface for listening to the result of setting a username.
     */
    public interface OnUsernameSetListener {
        void onUsernameSet(String username);
    }

    /**
     * Check if the username is set for the current user, and show the dialog if it's not.
     *
     * @param context  The context to create the dialog.
     * @param listener The listener to be called when the username is set.
     */
    public static void checkIfUsernameSet(Context context, OnUsernameSetListener listener) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference(USERS);
            userRef.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (!dataSnapshot.hasChild(USERNAME)) {
                        // Username not set, show the dialog for setting username
                        showUsernameDialog(context, listener);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle error when querying user data
                    Log.e(TAG, "Error querying user data: " + databaseError.getMessage());
                }
            });
        }
    }

    /**
     * Show a dialog for setting the username.
     *
     * @param context  The context to create the dialog.
     * @param listener The listener to be called when the username is set.
     */
    public static void showUsernameDialog(Context context, OnUsernameSetListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Set Username");

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_set_username, null);
        final EditText editTextUsername = view.findViewById(R.id.editTextUsername);
        builder.setView(view);

        builder.setPositiveButton("Submit", null); // Remove OnClickListener

        builder.setCancelable(false);
        AlertDialog alertDialog = builder.create();
        alertDialog.setOnShowListener(dialog ->
            // Get the "Submit" button of the dialog
            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(v -> {
                String username = editTextUsername.getText().toString().trim();
                if (!username.isEmpty()) {
                    checkUsernameAvailability(context, username, username1 -> {
                        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                        if (currentUser != null) {
                            setUserUsername(currentUser.getUid(), username1);
                        }
                        listener.onUsernameSet(username1);
                        alertDialog.dismiss();
                    });
                } else {
                    Toast.makeText(context, "Please enter a username", Toast.LENGTH_SHORT).show();
                }
            })
        );
        alertDialog.show();
    }

    /**
     * Check if the given username is available or not.
     *
     * @param context  The context to display the toast message.
     * @param username The username to check for availability.
     * @param listener The listener to be called when the username is available.
     */
    private static void checkUsernameAvailability(Context context, String username, OnUsernameSetListener listener) {
        // Check if username contains only alphanumeric characters and is between 3 and 15 characters long
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{3,15}$");
        Matcher matcher = pattern.matcher(username);
        if (!matcher.matches()) {
            Toast.makeText(context, "Username must be alphanumeric and 3-15 characters long", Toast.LENGTH_SHORT).show();
            return;
        }


        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference(USERS);
        usersRef.orderByChild(USERNAME).equalTo(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount() == 0) {
                    // Username is available
                    listener.onUsernameSet(username);
                } else {
                    Toast.makeText(context, "Username is taken, please choose another one", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error when querying user data
                Toast.makeText(context, "Error occurred while querying username, please try again", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Set the username for the user with the given user ID.
     *
     * @param userId   The user ID of the user to set the username for.
     * @param username The username to set for the user.
     */
    public static void setUserUsername(String userId, String username) {
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference(USERS);
        userRef.child(userId).child(USERNAME).setValue(username);
    }
}



