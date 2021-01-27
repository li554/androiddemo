package com.example.kousuan;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kousuan.databinding.FragmentTitleBinding;


public class TitleFragment extends Fragment {

    FragmentTitleBinding binding;
    MyViewModel myViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myViewModel = new ViewModelProvider(requireActivity(),new SavedStateViewModelFactory(requireActivity().getApplication(),requireActivity())).get(MyViewModel.class);
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_title,container,false);
        binding.setData(myViewModel);
        binding.setLifecycleOwner(requireActivity());
        myViewModel.genRandomQuestion();
        myViewModel.getCurrentScore().setValue(0);
        binding.button.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_titleFragment_to_questionFragment));
        return binding.getRoot();
    }
}