package com.example.quickcash.recommendEmployees;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class US2FillInEmployeeList {
    List<us2_Employee> employeeList;

    public US2FillInEmployeeList () {
        employeeList = new ArrayList<>();
    }

    public List<us2_Employee> fillInList (List<us2_Employee> employeeList) {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userRef = rootRef.child("Users");
        employeeList.add(new us2_Employee("a",3, "g"));

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot employeeSnapshot) {
                String userType = "";
                employeeList.add(new us2_Employee("a",3, "r"));
                for (DataSnapshot employees : employeeSnapshot.getChildren()) {
                    employeeList.add(new us2_Employee("a",3, "q"));
                    userType = employees.child("userType").getValue(String.class);
                    double rating = 0;
                    employeeList.add(new us2_Employee("a",3, "h"));
                    if (employees.child("rating").getValue() != null) {
                        employeeList.add(new us2_Employee("a",3, "t"));
                        rating = (double) employees.child("rating").getValue(Double.class);

                        if (userType.equals("Employee") && rating >= 4.5) {
                            employeeList.add(new us2_Employee("a",3, "o"));


                            String employeeName = employees.child("username").getValue(String.class);

                            String employeeTitle = String.format("Employee Name: %s\nRating: %.1f", employeeName, rating);
                            employeeList.add(new us2_Employee(employeeName, rating, employeeTitle));
                        }
                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //We don't need to control this situation
            }
        });
        employeeList.add(new us2_Employee("a",3, "e"));
        return employeeList;
    }
}
