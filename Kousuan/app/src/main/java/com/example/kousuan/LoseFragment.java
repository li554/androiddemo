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

import com.example.kousuan.databinding.FragmentLoseBinding;
import com.example.kousuan.databinding.FragmentWinBinding;

public class LoseFragment extends Fragment {

    private MyViewModel myViewModel;
    private FragmentLoseBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myViewModel = new ViewModelProvider(requireActivity(),new SavedStateViewModelFactory(requireActivity().getApplication(),requireActivity())).get(MyViewModel.class);
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_lose,container,false);
        binding.setData(myViewModel);
        binding.setLifecycleOwner(requireActivity());
        binding.loseBackToTitle.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_loseFragment_to_titleFragment));
        return binding.getRoot();
    }
}