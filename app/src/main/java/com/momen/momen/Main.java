package com.momen.momen;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Main extends AppCompatActivity implements RegistrationFragment.SingupInterface {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);

    fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        RegistrationFragment registrationFragment = new RegistrationFragment();
        fragmentTransaction.add(R.id.fragmentContainer,registrationFragment);
        fragmentTransaction.commit();




    }


    public void getEmail(String email,String phone){
        Toast.makeText(this,email,Toast.LENGTH_SHORT).show();
        Toast.makeText(this,phone,Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onSingUpComplete(String email ,String phone ,String name, String pass) {

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();


        bundle.putString("name",name);

        bundle.putString("email",email);
        bundle.putString("phone",phone);
        bundle.putString("pass",pass);


        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragmentContainer,homeFragment);
        fragmentTransaction.commit();



    }



}
