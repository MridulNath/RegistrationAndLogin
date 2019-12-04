package com.momen.momen;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private Button home;

    private TextView emailTV,phoneTV,nameTV,passTV;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String email = getArguments().getString("email");
        String phone = getArguments().getString("phone");
        String name = getArguments().getString("name");
        String password = getArguments().getString("pass");

        home=view.findViewById(R.id.home_btn);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(),HomeActivity.class);
                startActivity(intent);

            }
        });

        emailTV = view.findViewById(R.id.emailTV);
        emailTV.setText(email);

        phoneTV = view.findViewById(R.id.phoneTV);
        phoneTV.setText(phone);


        nameTV = view.findViewById(R.id.nameTV);
        nameTV.setText(name);

        passTV = view.findViewById(R.id.passwordTV);
        passTV.setText(password);


        return;

    }
}
