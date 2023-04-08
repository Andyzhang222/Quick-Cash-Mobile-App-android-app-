package com.example.quickcash.Explore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.SearchView;

import com.example.quickcash.R;

import java.util.List;

/**
 * This activity allows the user to search for employees by name or other attributes and displays the search results
 * in a recycler view using an EmployeeAdapter.
 */
public class ExploreEmployerActivity extends AppCompatActivity implements UserSearch.OnSearchResultListener {

    private UserSearch userSearch; // Instance of the UserSearch class to search for users
    private EmployeeAdapter employeeAdapter; // Adapter for displaying employee search results
    private static final String USER_TYPE = "Employee"; // Specifies the user type being searched for - set to "Employee" or "Employer"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_employee);

        searchView();
    }

    /**
     * Sets up the search view and the recycler view with the employee adapter for displaying search results.
     */
    private void searchView() {
        SearchView searchView = findViewById(R.id.search_view);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        employeeAdapter = new EmployeeAdapter(); // Create a user adapter instance
        recyclerView.setAdapter(employeeAdapter);

        userSearch = new UserSearch(this);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText)) {
                    userSearch.searchUsers(USER_TYPE, newText);
                } else {
                    employeeAdapter.clear(); // Clear the data in the adapter
                }
                return true;
            }
        });
    }

    /**
     * This method is called when a search query returns a list of User objects. It updates the employee adapter with the new search results.
     *
     * @param users A list of User objects returned from a search query.
     */
    @Override
    public void onSearchResult(List<User> users) {
        employeeAdapter.setUsers(users); // Update adapter based on search results
    }
}
