package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.calculator.databinding.ActivityMainBinding;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    double firstNum;
    String operation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstNum = 0;
                binding.screen.setText("0");
            }
        });


        binding.off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.screen.setVisibility(View.GONE);
                binding.screen.setText("0");
            }
        });

        binding.on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.screen.setVisibility(View.VISIBLE);
                binding.screen.setText("0");
            }
        });

        ArrayList<Button> nums = new ArrayList<>();
        nums.add(binding.num0);
        nums.add(binding.num1);
        nums.add(binding.num2);
        nums.add(binding.num3);
        nums.add(binding.num4);
        nums.add(binding.num5);
        nums.add(binding.num6);
        nums.add(binding.num7);
        nums.add(binding.num8);
        nums.add(binding.num9);

        for (Button b : nums){
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!binding.screen.getText().toString().equals("0")){
                        binding.screen.setText(binding.screen.getText().toString() + b.getText().toString());
                    }else {
                        binding.screen.setText(b.getText().toString());
                    }
                }
            });
        }

        ArrayList<Button> operands = new ArrayList<>();
        operands.add(binding.div);
        operands.add(binding.times);
        operands.add(binding.plus);
        operands.add(binding.min);

        for (Button b : operands){
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    firstNum = Double.parseDouble(binding.screen.getText().toString());
                    operation = b.getText().toString();
                    binding.screen.setText("0");
                }
            });
        }

        binding.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = binding.screen.getText().toString();
                if (num.length() > 1){
                    binding.screen.setText(num.substring(0,num.length() - 1));
                }else if(num.length() == 1 && !num.equals("0")) {
                    binding.screen.setText("0");
                }
            }
        });

        binding.point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.screen.getText().toString().contains(".")){
                    binding.screen.setText(binding.screen.getText().toString() + ".");
                }
            }
        });

        binding.equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double secondNum = Double.parseDouble(binding.screen.getText().toString());
                double result;
                switch (operation) {
                    case "/":
                        result = firstNum/secondNum;
                        break;
                    case "X":
                        result = firstNum*secondNum;
                        break;
                    case "+":
                        result = firstNum+secondNum;
                        break;
                    case "-":
                        result = firstNum-secondNum;
                        break;
                    default:
                        result = firstNum+secondNum;
                }
                binding.screen.setText(String.valueOf(result));
                firstNum = result;
            }
        });


    }
}