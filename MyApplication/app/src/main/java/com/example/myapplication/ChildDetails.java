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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ChildDetails extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.child_details, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.child_details_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText kidnameText = (EditText) getView().findViewById(R.id.input_kidname);
                String kidname = kidnameText.getText().toString();

                EditText dobText = (EditText) getView().findViewById(R.id.input_dob);
                String dob = dobText.getText().toString();

                EditText genderText = (EditText) getView().findViewById(R.id.input_gender);
                String gender = genderText.getText().toString();

                EditText birthweightText = (EditText) getView().findViewById(R.id.input_birthweight);
                String birthweight = birthweightText.getText().toString();

                JSONObject kidsJSON = new JSONObject();
                try {
                    JSONObject json = new JSONObject();
                    json.put("kidname", kidname);
                    json.put("dob", dob);
                    json.put("gender", gender);
                    json.put("birthweight", birthweight);
                    JSONArray array = new JSONArray();
                    array.put(json);
                    kidsJSON.put("allkids", array);
                    String value = kidsJSON.toString();
                    SharedPreferences preferences = getActivity().getSharedPreferences("DATA", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("CHILDS_" + VaccinationDetails.getCurrentUser(), value);
                    editor.commit();
                    VaccinationDetails.setChildDetails(kidsJSON);
                    String vaccinesText = preferences.getString("VACCINES_" + VaccinationDetails.getCurrentUser(), "");
                    // uncomment below to update vaccines.
                    // vaccinesText = "";
                    if (vaccinesText.equals("")) {
                        JSONObject vaccinesJsonObject = VaccinationDetails.createVaccineDetails();
                        editor.putString("VACCINES_" + VaccinationDetails.getCurrentUser(), vaccinesJsonObject.toString());
                        editor.commit();
                        VaccinationDetails.setVaccineDetails(vaccinesJsonObject);
                    } else {
                        JSONObject vaccinesjson = new JSONObject(vaccinesText);
                        VaccinationDetails.setVaccineDetails(vaccinesjson);
                    }
                    NavHostFragment.findNavController(ChildDetails.this)
                            .navigate(R.id.action_ChildDetails_to_Vaccination);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}