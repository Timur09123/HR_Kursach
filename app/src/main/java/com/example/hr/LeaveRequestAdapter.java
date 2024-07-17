package com.example.hr;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LeaveRequestAdapter extends RecyclerView.Adapter<LeaveRequestAdapter.LeaveRequestViewHolder> {

    private List<LeaveRequest> leaveRequests;
    private OnLeaveRequestClickListener onLeaveRequestClickListener;

    public LeaveRequestAdapter(List<LeaveRequest> leaveRequests, OnLeaveRequestClickListener onLeaveRequestClickListener) {
        this.leaveRequests = leaveRequests;
        this.onLeaveRequestClickListener = onLeaveRequestClickListener;
    }

    @NonNull
    @Override
    public LeaveRequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_leave_request, parent, false);
        return new LeaveRequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaveRequestViewHolder holder, int position) {
        LeaveRequest leaveRequest = leaveRequests.get(position);
        holder.leaveRequestTextView.setText("Employee ID: " + leaveRequest.getEmployeeId() + ", Days: " + leaveRequest.getDaysRequested());
        holder.approveButton.setOnClickListener(v -> onLeaveRequestClickListener.onApproveClick(leaveRequest));
        holder.declineButton.setOnClickListener(v -> onLeaveRequestClickListener.onDeclineClick(leaveRequest));
    }

    @Override
    public int getItemCount() {
        return leaveRequests.size();
    }

    static class LeaveRequestViewHolder extends RecyclerView.ViewHolder {
        TextView leaveRequestTextView;
        Button approveButton;
        Button declineButton;

        LeaveRequestViewHolder(@NonNull View itemView) {
            super(itemView);
            leaveRequestTextView = itemView.findViewById(R.id.leave_request_text_view);
            approveButton = itemView.findViewById(R.id.button_approve_leave);
            declineButton = itemView.findViewById(R.id.button_decline_leave);
        }
    }

    public interface OnLeaveRequestClickListener {
        void onApproveClick(LeaveRequest leaveRequest);
        void onDeclineClick(LeaveRequest leaveRequest);
    }

    public void updateLeaveRequests(List<LeaveRequest> leaveRequests) {
        this.leaveRequests = leaveRequests;
        notifyDataSetChanged();
    }
}
