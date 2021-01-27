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

import com.example.kousuan.databinding.FragmentQuestionBinding;
import com.example.kousuan.databinding.FragmentWinBinding;

public class WinFragment extends Fragment {

    private MyViewModel myViewModel;
    private FragmentWinBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myViewModel = new ViewModelProvider(requireActivity(),new SavedStateViewModelFactory(requireActivity().getApplication(),requireActivity())).get(MyViewModel.class);
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_win,container,false);
        binding.setData(myViewModel);
        binding.setLifecycleOwner(requireActivity());
        binding.winBackToTitle.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_winFragment_to_titleFragment));
        return binding.getRoot();
    }
}