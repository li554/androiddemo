package com.example.navmodel;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.example.navmodel.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MyViewModel myViewModel = new ViewModelProvider(getActivity()).get(MyViewModel.class);
        FragmentHomeBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false);
        binding.setData(myViewModel);
        binding.setLifecycleOwner(getActivity());
        binding.button.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_detailFragment));
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}