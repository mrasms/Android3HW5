package com.example.android3hw3.ui.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android3hw3.databinding.ItemCharacterBinding;
import com.example.android3hw3.models.CharacterModel;
import com.example.android3hw3.ui.adapters.clickers.OnCharacterItemClick;

public class CharacterAdapter extends ListAdapter<CharacterModel, CharacterAdapter.ViewHolder> {

    private OnCharacterItemClick onCharacterItemClick;

    public CharacterAdapter(@NonNull DiffUtil.ItemCallback<CharacterModel> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemCharacterBinding.inflate(LayoutInflater.from(parent.getContext())
                , parent, false));
    }

    @Override
    public void onBindViewHolder (@NonNull ViewHolder holder, int position)  {
        holder.onBind(getItem(position));
        }

    public void setOnItemClick(OnCharacterItemClick onCharacterItemClick) {
        this.onCharacterItemClick = onCharacterItemClick;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemCharacterBinding binding;

        public ViewHolder(@NonNull ItemCharacterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(CharacterModel model) {
            Glide.with(binding.characterImage).load(model.getImage()).into(binding.characterImage);
            binding.tvCharacterName.setText(model.getName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onCharacterItemClick.onItemClick(model);
                }
            });
        }
    }

    public static DiffUtil.ItemCallback<CharacterModel> diffCallBack = new DiffUtil.ItemCallback<CharacterModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull CharacterModel oldItem, @NonNull CharacterModel newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull CharacterModel oldItem, @NonNull CharacterModel newItem) {
            return oldItem == newItem;
        }
    };
}
