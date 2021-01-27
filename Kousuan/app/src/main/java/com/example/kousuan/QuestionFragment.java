package com.example.kousuan;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kousuan.databinding.FragmentQuestionBinding;

public class QuestionFragment extends Fragment implements View.OnClickListener{
    private MyViewModel myViewModel;
    private int current_answer;
    private FragmentQuestionBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myViewModel = new ViewModelProvider(requireActivity(), new SavedStateViewModelFactory(requireActivity().getApplication(), requireActivity())).get(MyViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question, container, false);
        binding.setData(myViewModel);
        binding.setLifecycleOwner(requireActivity());
        binding.numOne.setOnClickListener(this);
        binding.numTwo.setOnClickListener(this);
        binding.numThree.setOnClickListener(this);
        binding.numFour.setOnClickListener(this);
        binding.numFive.setOnClickListener(this);
        binding.numSix.setOnClickListener(this);
        binding.numSeven.setOnClickListener(this);
        binding.numEight.setOnClickListener(this);
        binding.numNine.setOnClickListener(this);
        binding.numZero.setOnClickListener(this);
        binding.clear.setOnClickListener(this);
        binding.submit.setOnClickListener(this);
        return binding.getRoot();
    }

    public void submit(View v){
        if (current_answer==myViewModel.getAnswer().getValue()){
            myViewModel.answerCorrect();
            current_answer=0;
            binding.answerTv.setText(R.string.current_answer);
        }else{
            NavController controller = Navigation.findNavController(v);
            //答错，结束转至win or lose
            if (myViewModel.win_flag) {
                controller.navigate(R.id.action_questionFragment_to_winFragment);
            }else{
                controller.navigate(R.id.action_questionFragment_to_loseFragment);
            }
            myViewModel.win_flag = false;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.num_one:set(1);break;
            case R.id.num_two:set(2);break;
            case R.id.num_three:set(3);break;
            case R.id.num_four:set(4);break;
            case R.id.num_five:set(5);break;
            case R.id.num_six:set(6);break;
            case R.id.num_seven:set(7);break;
            case R.id.num_eight:set(8);break;
            case R.id.num_nine:set(9);break;
            case R.id.num_zero:set(0);break;
            case R.id.clear:clear();break;
            case R.id.submit:submit(v);break;
        }
    }

    private void clear() {
        current_answer = current_answer/10;
        binding.answerTv.setText(getResources().getString(R.string.current_answer)+current_answer);
    }

    public void set(int i){
        current_answer = current_answer*10+i;
        binding.answerTv.setText(getResources().getString(R.string.current_answer)+current_answer);
    }
}