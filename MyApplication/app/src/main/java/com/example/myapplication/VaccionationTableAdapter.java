package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.prefs.Preferences;

public class VaccionationTableAdapter extends ArrayAdapter<VaccinationData> {

    private int resourceId;
    private boolean isVaccionationtableVisible;
    public VaccionationTableAdapter(Context context, @NonNull List<VaccinationData> objects) {
        super(context, 0, objects);
        isVaccionationtableVisible = false;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // return super.getView(position, convertView, parent);
        final VaccinationData data = getItem(position);

        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.vaccination_data, parent, false);
        }
        else {
            view = convertView;
        }

        TextView titleView =  (TextView) view.findViewById(R.id.vaccination_title);
        titleView.setText(data.title);

        final TextView descriptionView =  (TextView) view.findViewById(R.id.vaccination_description);
        descriptionView.setText(data.description);

        final LinearLayout layout = (LinearLayout) view.findViewById(R.id.vaccines_list);
        layout.setVisibility(View.GONE);

        int size = data.vaccinations.size();
        if (size == 0) {
            final LinearLayout vaccine1 = (LinearLayout) view.findViewById(R.id.vaccination_1_details);
            vaccine1.setVisibility(View.GONE);
            final LinearLayout vaccine2 = (LinearLayout) view.findViewById(R.id.vaccination_2_details);
            vaccine2.setVisibility(View.GONE);
            final LinearLayout vaccine3 = (LinearLayout) view.findViewById(R.id.vaccination_3_details);
            vaccine3.setVisibility(View.GONE);
        } else if (size == 1) {
            final LinearLayout vaccine1 = (LinearLayout) view.findViewById(R.id.vaccination_1_details);
            vaccine1.setVisibility(View.VISIBLE);
            final CheckBox checkBox1 = (CheckBox) view.findViewById(R.id.vaccine_1_checkbox);
            checkBox1.setChecked(data.vaccinations.get(0).vaccinated);
            final TextView name1 = (TextView) view.findViewById(R.id.vaccination_1_name);
            name1.setText(data.vaccinations.get(0).name);
            final TextView price1 = (TextView) view.findViewById(R.id.vaccination_1_price);
            price1.setText("RS." + data.vaccinations.get(0).price);

            final LinearLayout vaccine2 = (LinearLayout) view.findViewById(R.id.vaccination_2_details);
            vaccine2.setVisibility(View.GONE);
            final LinearLayout vaccine3 = (LinearLayout) view.findViewById(R.id.vaccination_3_details);
            vaccine3.setVisibility(View.GONE);
        } else if (size == 2) {
            final LinearLayout vaccine1 = (LinearLayout) view.findViewById(R.id.vaccination_1_details);
            vaccine1.setVisibility(View.VISIBLE);
            final CheckBox checkBox1 = (CheckBox) view.findViewById(R.id.vaccine_1_checkbox);
            checkBox1.setChecked(data.vaccinations.get(0).vaccinated);
            final TextView name1 = (TextView) view.findViewById(R.id.vaccination_1_name);
            name1.setText(data.vaccinations.get(0).name);
            final TextView price1 = (TextView) view.findViewById(R.id.vaccination_1_price);
            price1.setText("RS." + data.vaccinations.get(0).price);

            final LinearLayout vaccine2 = (LinearLayout) view.findViewById(R.id.vaccination_2_details);
            vaccine2.setVisibility(View.VISIBLE);
            final CheckBox checkBox2 = (CheckBox) view.findViewById(R.id.vaccine_2_checkbox);
            checkBox2.setChecked(data.vaccinations.get(1).vaccinated);
            final TextView name2 = (TextView) view.findViewById(R.id.vaccination_2_name);
            name2.setText(data.vaccinations.get(1).name);
            final TextView price2 = (TextView) view.findViewById(R.id.vaccination_2_price);
            price2.setText("RS." + data.vaccinations.get(1).price);

            final LinearLayout vaccine3 = (LinearLayout) view.findViewById(R.id.vaccination_3_details);
            vaccine3.setVisibility(View.GONE);
        } else if(size == 3) {
            final LinearLayout vaccine1 = (LinearLayout) view.findViewById(R.id.vaccination_1_details);
            vaccine1.setVisibility(View.VISIBLE);
            final CheckBox checkBox1 = (CheckBox) view.findViewById(R.id.vaccine_1_checkbox);
            checkBox1.setChecked(data.vaccinations.get(0).vaccinated);
            final TextView name1 = (TextView) view.findViewById(R.id.vaccination_1_name);
            name1.setText(data.vaccinations.get(0).name);
            final TextView price1 = (TextView) view.findViewById(R.id.vaccination_1_price);
            price1.setText("RS." + data.vaccinations.get(0).price);

            final LinearLayout vaccine2 = (LinearLayout) view.findViewById(R.id.vaccination_2_details);
            vaccine2.setVisibility(View.VISIBLE);
            final CheckBox checkBox2 = (CheckBox) view.findViewById(R.id.vaccine_2_checkbox);
            checkBox2.setChecked(data.vaccinations.get(1).vaccinated);
            final TextView name2 = (TextView) view.findViewById(R.id.vaccination_2_name);
            name2.setText(data.vaccinations.get(1).name);
            final TextView price2 = (TextView) view.findViewById(R.id.vaccination_2_price);
            price2.setText("RS." + data.vaccinations.get(1).price);

            final LinearLayout vaccine3 = (LinearLayout) view.findViewById(R.id.vaccination_3_details);
            vaccine3.setVisibility(View.VISIBLE);
            final CheckBox checkBox3 = (CheckBox) view.findViewById(R.id.vaccine_3_checkbox);
            checkBox3.setChecked(data.vaccinations.get(2).vaccinated);
            final TextView name3 = (TextView) view.findViewById(R.id.vaccination_3_name);
            name3.setText(data.vaccinations.get(2).name);
            final TextView price3 = (TextView) view.findViewById(R.id.vaccination_3_price);
            price3.setText("RS." + data.vaccinations.get(2).price);

        }

        // final TextView layoutText = (TextView) view.findViewById(R.id.vaccination_text);
        // layoutText.setText("Status | Vaccination Name | Done");

        view.findViewById(R.id.vaccination_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("VaccinationAdapter", "clickhappening");
                if (isVaccionationtableVisible) {
                    layout.setVisibility(View.GONE);
                } else {
                    layout.setVisibility(View.VISIBLE);
                }

                isVaccionationtableVisible = !isVaccionationtableVisible;
                // descriptionView2.setText("Hello World!");
            }
        });

        view.findViewById(R.id.vaccine_1_checkbox).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("VaccinationAdapter", "click happening");
                CheckBox checkbox1 =  (CheckBox) view.findViewById(R.id.vaccine_1_checkbox);
                boolean vaccine1Status = checkbox1.isChecked();
                String type = data.type;
                JSONObject jsonObject = VaccinationDetails.getVaccineDetails();
                try {
                    jsonObject.getJSONArray(type).getJSONObject(0).put("VACCINATED", vaccine1Status);
                    SharedPreferences pref = getContext().getSharedPreferences("DATA", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("VACCINES_"+ VaccinationDetails.getCurrentUser(), jsonObject.toString());
                    pref.edit();
                    editor.commit();
                    VaccinationDetails.setVaccineDetails(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.i("VaccinationAdapter", "vaccine click happening");
            }
        });

        view.findViewById(R.id.vaccine_2_checkbox).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("VaccinationAdapter", "click happening");
                CheckBox checkbox2 =  (CheckBox) view.findViewById(R.id.vaccine_2_checkbox);
                boolean vaccine1Status = checkbox2.isChecked();
                String type = data.type;
                JSONObject jsonObject = VaccinationDetails.getVaccineDetails();
                try {
                    jsonObject.getJSONArray(type).getJSONObject(1).put("VACCINATED", vaccine1Status);
                    SharedPreferences pref = getContext().getSharedPreferences("DATA", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("VACCINES_"+ VaccinationDetails.getCurrentUser(), jsonObject.toString());
                    pref.edit();
                    editor.commit();
                    VaccinationDetails.setVaccineDetails(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.i("VaccinationAdapter", "vaccine click happening");
            }
        });

        view.findViewById(R.id.vaccine_3_checkbox).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("VaccinationAdapter", "click happening");
                CheckBox checkbox3 =  (CheckBox) view.findViewById(R.id.vaccine_3_checkbox);
                boolean vaccine1Status = checkbox3.isChecked();
                String type = data.type;
                JSONObject jsonObject = VaccinationDetails.getVaccineDetails();
                try {
                    jsonObject.getJSONArray(type).getJSONObject(2).put("VACCINATED", vaccine1Status);
                    SharedPreferences pref = getContext().getSharedPreferences("DATA", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("VACCINES_"+ VaccinationDetails.getCurrentUser(), jsonObject.toString());
                    pref.edit();
                    editor.commit();
                    VaccinationDetails.setVaccineDetails(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.i("VaccinationAdapter", "vaccine click happening");
            }
        });

        return view;
    }

    public void onCheckboxClicked(View view) {
        Log.i("Adapter", "clicked");
    }
}
