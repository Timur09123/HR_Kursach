package com.example.hr;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ResignationRequestAdapter extends RecyclerView.Adapter<ResignationRequestAdapter.ResignationRequestViewHolder> {

    private List<ResignationRequest> resignationRequests;
    private OnResignationRequestClickListener onResignationRequestClickListener;

    public ResignationRequestAdapter(List<ResignationRequest> resignationRequests, OnResignationRequestClickListener onResignationRequestClickListener) {
        this.resignationRequests = resignationRequests;
        this.onResignationRequestClickListener = onResignationRequestClickListener;
    }

    @NonNull
    @Override
    public ResignationRequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_resignation_request, parent, false);
        return new ResignationRequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResignationRequestViewHolder holder, int position) {
        ResignationRequest resignationRequest = resignationRequests.get(position);
        holder.resignationRequestTextView.setText("Employee ID: " + resignationRequest.getEmployeeId());
        holder.approveButton.setOnClickListener(v -> onResignationRequestClickListener.onApproveClick(resignationRequest));
        holder.declineButton.setOnClickListener(v -> onResignationRequestClickListener.onDeclineClick(resignationRequest));
    }

    @Override
    public int getItemCount() {
        return resignationRequests.size();
    }

    static class ResignationRequestViewHolder extends RecyclerView.ViewHolder {
        TextView resignationRequestTextView;
        Button approveButton;
        Button declineButton;

        ResignationRequestViewHolder(@NonNull View itemView) {
            super(itemView);
            resignationRequestTextView = itemView.findViewById(R.id.resignation_request_text_view);
            approveButton = itemView.findViewById(R.id.button_approve_resignation);
            declineButton = itemView.findViewById(R.id.button_decline_resignation);
        }
    }

    public interface OnResignationRequestClickListener {
        void onApproveClick(ResignationRequest resignationRequest);
        void onDeclineClick(ResignationRequest resignationRequest);
    }

    public void updateResignationRequests(List<ResignationRequest> resignationRequests) {
        this.resignationRequests = resignationRequests;
        notifyDataSetChanged();
    }
}
