package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginPage extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.login_page, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.submit_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText emailText = (EditText) getView().findViewById(R.id.login_email);
                String emailId = emailText.getText().toString();

                EditText passwordText = (EditText)  getView().findViewById(R.id.login_password);
                String password = passwordText.getText().toString();

                SharedPreferences preferences = getActivity().getSharedPreferences("DATA", Context.MODE_PRIVATE);
                String response = preferences.getString("USERID_" + emailId, "");

                if(response.equals("")) {
                    Log.e("LoginPage", "Login doesn't exist");
                } else {
                    try {
                        JSONObject json = new JSONObject(response);
                        String storedPassword = (String) json.get("password");
                        if (password.equals(storedPassword)) {
                            Log.i("LoginPage", "Login Successful");
                            VaccinationDetails.SetCurrentUser(emailId);
                            String childsText = preferences.getString("CHILDS_" + emailId, "");
                            if (childsText.equals("")) {
                                NavHostFragment.findNavController(LoginPage.this)
                                        .navigate(R.id.action_LoginPage_to_ChildDetails);
                            } else {
                                JSONObject childsjson = new JSONObject(childsText);
                                VaccinationDetails.setChildDetails(childsjson);
                                String vaccinesText = preferences.getString("VACCINES_" + emailId, "");
                                // uncomment below to update vaccines.
                                // vaccinesText = "";
                                if (vaccinesText.equals("")) {
                                    SharedPreferences.Editor editor = preferences.edit();
                                    JSONObject vaccinesJsonObject = VaccinationDetails.createVaccineDetails();
                                    editor.putString("VACCINES_" + VaccinationDetails.getCurrentUser(), vaccinesJsonObject.toString());
                                    editor.commit();
                                    VaccinationDetails.setVaccineDetails(vaccinesJsonObject);
                                } else {
                                    JSONObject vaccinesjson = new JSONObject(vaccinesText);
                                    VaccinationDetails.setVaccineDetails(vaccinesjson);
                                }

                                NavHostFragment.findNavController((LoginPage.this))
                                        .navigate(R.id.actions_LoginPage_to_Vaccination);
                            }
                        } else {
                            Log.i("LoginPage", "Password do not match.");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}