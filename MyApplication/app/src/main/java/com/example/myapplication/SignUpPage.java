package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUpPage extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.sign_up, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.submit_signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText emailText = (EditText) getView().findViewById(R.id.signup_email);
                String emailId = emailText.getText().toString();

                EditText passwordText = (EditText)  getView().findViewById(R.id.signup_password);
                String password = passwordText.getText().toString();

                EditText nameText = (EditText) getView().findViewById(R.id.signup_name);
                String name = nameText.getText().toString();

                JSONObject json = new JSONObject();
                try {
                    json.put("name", name);
                    json.put("password", password);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String value = json.toString();
                SharedPreferences preferences = getActivity().getSharedPreferences("DATA", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("USERID_" + emailId, value);
                editor.commit();
                NavHostFragment.findNavController(SignUpPage.this)
                        .navigate(R.id.action_SignUpPage_to_LoginPage);
            }
        });
    }
}