package com.example.quickcash.Explore;

import androidx.annotation.NonNull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A class for searching users in the Firebase Realtime Database.
 */
public class UserSearch {

    private DatabaseReference usersRef;
    private List<User> userList;
    private OnSearchResultListener resultListener;


    /**
     * A listener interface for getting the search results.
     */
    public interface OnSearchResultListener {
        /**
         * Called when the search is complete and the results are available.
         *
         * @param users A list of users matching the search criteria.
         */
        void onSearchResult(List<User> users);
    }

    /**
     * Constructs a new UserSearch object with the given listener.
     *
     * @param resultListener The listener to receive the search results.
     */
    public UserSearch(OnSearchResultListener resultListener) {
        this.resultListener = resultListener;
        userList = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("Users");
    }


    /**
     * Convert a DataSnapshot object to a User object.
     *
     * @param userSnapshot The DataSnapshot object to be converted.
     * @return The converted User object or null if not valid.
     */
    private User convertSnapshotToUser(DataSnapshot userSnapshot) {
        User user = userSnapshot.getValue(User.class);
        if (user != null) {
            if (user.getRating() == null) {
                user.setRating(0.00);
            }
            if (user.getIncomeEarned() == null) {
                user.setIncomeEarned(0.00);
            }
            user.setUid(userSnapshot.getKey());
        }
        return user;
    }


    /**
     * Searches for users with the given user type and search string.
     *
     * @param userType     The type of user to search for.
     * @param searchString The search string to match against the username of the user.
     */
    public void searchUsers(String userType, String searchString) {
        Query query = usersRef.orderByChild("userType").equalTo(userType);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userList.clear();
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    User user = convertSnapshotToUser(userSnapshot);
                    if(user != null && (user.getUsername() != null && (user.getUsername().toLowerCase().contains(searchString.toLowerCase())))) {
                        userList.add(user);
                    }


                }
                resultListener.onSearchResult(userList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
            }
        });
    }

}
