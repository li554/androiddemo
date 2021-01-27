package com.example.navmodel;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.navmodel.databinding.FragmentDetailBinding;
import com.example.navmodel.databinding.FragmentHomeBinding;

public class DetailFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MyViewModel myViewModel = new ViewModelProvider(getActivity()).get(MyViewModel.class);
        FragmentDetailBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail,container,false);
        binding.setData(myViewModel);
        binding.setLifecycleOwner(getActivity());
        binding.imageButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_detailFragment_to_homeFragment));
        return binding.getRoot();
    }
}