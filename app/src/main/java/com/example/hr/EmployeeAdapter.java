package com.example.hr;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private List<Employee> employees;

    public EmployeeAdapter(List<Employee> employees) {
        this.employees = employees;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employee, parent, false);
        return new EmployeeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        Employee current = employees.get(position);
        holder.textViewName.setText(current.getName());
        holder.textViewPosition.setText(current.getPosition());
        holder.textViewSalary.setText(current.getSalary());
        holder.textViewLeave.setText(current.getLeave());
        holder.textViewId.setText(String.valueOf(current.getId()));
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
        notifyDataSetChanged();
    }

    static class EmployeeViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewPosition;
        private TextView textViewSalary;
        private TextView textViewLeave;
        private TextView textViewId;

        private EmployeeViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewPosition = itemView.findViewById(R.id.textViewPosition);
            textViewSalary = itemView.findViewById(R.id.textViewSalary);
            textViewLeave = itemView.findViewById(R.id.textViewLeave);
            textViewId = itemView.findViewById(R.id.textViewId);
        }
    }
}
