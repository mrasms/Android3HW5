package com.example.android3hw3.ui.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android3hw3.databinding.ItemLocationBinding;
import com.example.android3hw3.models.LocationModel;
import com.example.android3hw3.ui.adapters.clickers.OnLocationItemClick;

public class LocationAdapter extends ListAdapter<LocationModel, LocationAdapter.ViewHolder> {
    private OnLocationItemClick onLocationItemClick;

    public LocationAdapter(@NonNull DiffUtil.ItemCallback<LocationModel> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LocationAdapter.ViewHolder(ItemLocationBinding.inflate(LayoutInflater.from(parent.getContext())
                , parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(getItem(position));
    }

    public void setOnLocationItemClick(OnLocationItemClick onLocationItemClick) {
        this.onLocationItemClick = onLocationItemClick;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemLocationBinding binding;

        public ViewHolder(@NonNull ItemLocationBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(LocationModel model) {
            binding.tvLocationName.setText(model.getName());
            binding.tvLocationType.setText(model.getType());
            binding.tvLocationDimension.setText(model.getDimension());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onLocationItemClick.onItemClick(model);
                }
            });
        }
    }

    public static DiffUtil.ItemCallback<LocationModel> diffCallBack = new DiffUtil.ItemCallback<LocationModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull LocationModel oldItem, @NonNull LocationModel newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull LocationModel oldItem, @NonNull LocationModel newItem) {
            return oldItem == newItem;
        }
    };
}
