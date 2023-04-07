package com.example.quickcash.Explore;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quickcash.R;

import java.util.ArrayList;
import java.util.List;

/**
 * An adapter for displaying a list of employer users in a RecyclerView.
 */
public class EmployerAdapter extends RecyclerView.Adapter<EmployerAdapter.UserViewHolder> {

    private List<User> users;

    /**
     * Constructs a new UserAdapter with an empty list of users.
     */
    public EmployerAdapter() {
        this.users = new ArrayList<>();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the user_item layout and return a new UserViewHolder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employer_item, parent, false);
        return new UserViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        // Bind the user data to the UserViewHolder
        User user = users.get(position);
        holder.usernameTextView.setText("Name: " +user.getUsername());
        holder.emailTextView.setText("Email: " + user.getEmail());
        holder.ratingTextView.setText("Rating: " + user.getRating());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    /**
     * Sets the list of users to be displayed in the RecyclerView.
     *
     * @param users The list of users to be displayed.
     */
    @SuppressLint("NotifyDataSetChanged")
    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    /**
     * Clears the list of users from the RecyclerView.
     */
    @SuppressLint("NotifyDataSetChanged")
    public void clear() {
        this.users.clear();
        notifyDataSetChanged();
    }

    /**
     * A ViewHolder for holding the views of a user_item layout.
     */
    static class UserViewHolder extends RecyclerView.ViewHolder {

        TextView usernameTextView;
        TextView emailTextView;
        TextView ratingTextView;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            // Find the views in the user_item layout
            usernameTextView = itemView.findViewById(R.id.username_text_view);
            emailTextView = itemView.findViewById(R.id.email_text_view);
            ratingTextView = itemView.findViewById(R.id.rating_text_view);
        }
    }
}


