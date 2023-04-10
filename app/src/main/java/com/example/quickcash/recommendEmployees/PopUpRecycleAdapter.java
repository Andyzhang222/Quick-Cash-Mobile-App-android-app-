package com.example.quickcash.recommendEmployees;

/**
 * The activity is for connect the the data to the views in the employee view
 * Editor: Ziyue Wang, Guanxiang Wang
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quickcash.EmployeePage;
import com.example.quickcash.Job;
import com.example.quickcash.JobListAdapter;
import com.example.quickcash.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

//Used S of the SOLID Principle
//Used I of the SOLID Principle
public class PopUpRecycleAdapter extends RecyclerView.Adapter<PopUpRecycleViewHolder> {
    Context context;
    List<us2_Employee> employeeList;

    public PopUpRecycleAdapter(Context context, List<us2_Employee> employeeList) {
        this.context = context;
        this.employeeList = employeeList;
    }

    @NonNull
    @Override
    public PopUpRecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflated layout will be attached to the employee page
        return new PopUpRecycleViewHolder(LayoutInflater.from(context).inflate(R.layout.single_pop_up_employee, parent, false));
    }

    /**
     * Bind the data to the views in the job view
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull PopUpRecycleViewHolder holder, int position) {
        holder.title.setText(employeeList.get(position).getEmployeeTitle());
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

//    /**
//     * Filter the job list based on a search query and update the RecyclerView
//     */
//    public void filteredJobList(List<us2_Employee> employeeList){
//        this.employeeList = employeeList;
//
//        notifyDataSetChanged();
//    }
}