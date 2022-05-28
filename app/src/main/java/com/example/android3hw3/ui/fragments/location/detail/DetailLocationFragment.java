package com.example.android3hw3.ui.fragments.location.detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android3hw3.databinding.FragmentDetailLocationBinding;
import com.example.android3hw3.models.LocationModel;
import com.example.android3hw3.ui.fragments.location.LocationViewModel;


public class DetailLocationFragment extends Fragment {

    private FragmentDetailLocationBinding binding;
    private LocationViewModel locationViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailLocationBinding.inflate(inflater, container, false);
        locationViewModel = new ViewModelProvider(requireActivity()).get(LocationViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int args = DetailLocationFragmentArgs.fromBundle(getArguments()).getPosition();

        locationViewModel.fetchlocationId(args).observe(getViewLifecycleOwner(), new Observer<LocationModel>() {
            @Override
            public void onChanged(LocationModel locationModel) {
                binding.tvLocationDimension.setText(locationModel.getDimension());
                binding.tvDetailLocationName.setText(locationModel.getName());
                binding.tvDetailLocationType.setText(locationModel.getType());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}