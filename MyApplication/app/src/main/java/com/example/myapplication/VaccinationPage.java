package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class VaccinationPage extends Fragment {
    private ListView list;
    private ArrayList<VaccinationData> arrayList;
    private ArrayAdapter<VaccinationData> adapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.vaccination_page, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        list = (ListView) getView().findViewById(R.id.vaccination_list);
        Calendar calendar = Calendar.getInstance();
        Date date = null;
        String heightValue = "--";
        String weightValue = "--";
        try {

            JSONObject childsJSON = VaccinationDetails.getChildDetails();
            JSONArray childsJSONArray = childsJSON.getJSONArray("allkids");
            JSONObject firstkid = childsJSONArray.getJSONObject(0);
            date = new SimpleDateFormat("dd/MM/yyyy").parse(firstkid.getString("dob"));
            heightValue = firstkid.optString("height", "--");
            weightValue = firstkid.optString("weight", "--");
        } catch (ParseException | JSONException e) {
            e.printStackTrace();
        }

        arrayList = new VaccinationDetails().getVaccinationDates(date);

        // Adapter: You need three parameters 'the context, id of the layout (it will be where the data is shown),
        // and the array that contains the data
        adapter = new VaccionationTableAdapter(getContext(), arrayList);

        // Here, you set the data in your ListView
        list.setAdapter(adapter);

        final ScrollView graph = (ScrollView) getView().findViewById(R.id.vaccination_graph);
        final ScrollView food = (ScrollView) getView().findViewById(R.id.vaccination_food);
        final ScrollView forum = (ScrollView) getView().findViewById(R.id.vaccination_forum);
        final Button vaccineMenuButton = (Button) getView().findViewById(R.id.vaccine_menu);
        final Button graphMenuButton = (Button) getView().findViewById(R.id.vaccine_graph);
        final Button foodMenuButton = (Button) getView().findViewById(R.id.vaccine_food);
        final Button forumMenuButton = (Button) getView().findViewById(R.id.vaccine_forum);
        final LinearLayout nutritionTable = (LinearLayout) getView().findViewById(R.id.nutrition_table);
        final ImageView foodrecieps = (ImageView) getView().findViewById(R.id.foodreciepes_table);
        final ImageView nutritionIcon = (ImageView) getView().findViewById(R.id.nutrition_icon);
        final ImageView foodreciepsIcon = (ImageView) getView().findViewById(R.id.foodreciepes_icon);

        final ImageView chartsHeightIcon = (ImageView) getView().findViewById(R.id.charts_height_icon);
        final ImageView chartsWeightIcon = (ImageView) getView().findViewById(R.id.charts_weight_icon);
        final LinearLayout chartsHeightInfo = (LinearLayout) getView().findViewById(R.id.charts_height_info);
        final LinearLayout chartsWeightInfo = (LinearLayout) getView().findViewById(R.id.charts_weight_info);

        final TextView heightValue1 = (TextView) getView().findViewById(R.id.charts_height_value_1);
        final TextView heightValue2 = (TextView) getView().findViewById(R.id.charts_height_value_2);
        final TextView weightValue1 = (TextView) getView().findViewById(R.id.charts_weight_value_1);
        final TextView weightValue2 = (TextView) getView().findViewById(R.id.charts_weight_value_2);

        heightValue1.setText(heightValue + " CM");
        heightValue2.setText(heightValue + " CM");
        weightValue1.setText(weightValue + " KG");
        weightValue2.setText(weightValue + " KG");

        Instant instant = date.toInstant();
        ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
        LocalDate givenDate = zone.toLocalDate();
        Period period = Period.between(givenDate, LocalDate.now());
        int years = period.getYears();
        String age = period.getMonths() <= 6 ? String.valueOf(years) : years + ".6";
        String ageString = age + " years";
        Pair<String, String> minmaxHeight = VaccinationDetails.chartHeightDetails.get(age);
        Pair<String, String> minmaxWeight = VaccinationDetails.chartWeightDetails.get(age);

        final TextView heightAge = (TextView) getView().findViewById(R.id.height_age);
        heightAge.setText(ageString);
        final TextView idealMinHeightInfo = (TextView) getView().findViewById(R.id.ideal_min_height);
        idealMinHeightInfo.setText(minmaxHeight.first + " cm");
        final TextView idealMaxHeightInfo = (TextView) getView().findViewById(R.id.ideal_max_height);
        idealMaxHeightInfo.setText(minmaxHeight.second + " cm");

        final TextView weightAge = (TextView) getView().findViewById(R.id.weight_age);
        weightAge.setText(ageString);
        final TextView idealMinWeightInfo = (TextView) getView().findViewById(R.id.ideal_min_weight);
        idealMinWeightInfo.setText(minmaxWeight.first + " kg");
        final TextView idealMaxWeightInfo = (TextView) getView().findViewById(R.id.ideal_max_weight);
        idealMaxWeightInfo.setText(minmaxWeight.second + " kg");

        final TextView heightLabel = (TextView) getView().findViewById(R.id.height_add_label);
        final TextView weightLabel = (TextView) getView().findViewById(R.id.weight_add_label);
        final LinearLayout heightInputlayout = (LinearLayout) getView().findViewById(R.id.height_add_data);
        final LinearLayout weightInputlayout = (LinearLayout) getView().findViewById(R.id.weight_add_data);
        final EditText inputHeight = (EditText) getView().findViewById(R.id.input_height);
        final EditText inputWeight = (EditText) getView().findViewById(R.id.input_weight);
        final LinearLayout calLayout = (LinearLayout) getView().findViewById(R.id.calories);
        calLayout.setVisibility(View.GONE);

        heightLabel.setVisibility(View.VISIBLE);
        heightInputlayout.setVisibility(View.GONE);
        weightLabel.setVisibility(View.GONE);
        weightInputlayout.setVisibility(View.GONE);

        list.setVisibility(View.VISIBLE);
        graph.setVisibility(View.GONE);
        food.setVisibility(View.GONE);
        forum.setVisibility(View.GONE);

        vaccineMenuButton.setBackgroundResource(R.drawable.vaccinationmenuorange);
        graphMenuButton.setBackgroundResource(R.drawable.chartsmenugray);
        foodMenuButton.setBackgroundResource(R.drawable.nutritionmenugray);
        forumMenuButton.setBackgroundResource(R.drawable.forummenugray);

        nutritionTable.setVisibility(View.VISIBLE);
        foodrecieps.setVisibility(View.GONE);
        nutritionIcon.setBackgroundResource(R.drawable.nutritioniconfoodorange);
        foodreciepsIcon.setBackgroundResource(R.drawable.foodrecipesgray);

        chartsHeightIcon.setBackgroundResource(R.drawable.chartsheightorange);
        chartsWeightIcon.setBackgroundResource(R.drawable.chartsweightgray);
        chartsHeightInfo.setVisibility(View.VISIBLE);
        chartsWeightInfo.setVisibility(View.GONE);

        view.findViewById(R.id.vaccine_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.setVisibility(View.VISIBLE);
                graph.setVisibility(View.GONE);
                food.setVisibility(View.GONE);
                forum.setVisibility(View.GONE);
                vaccineMenuButton.setBackgroundResource(R.drawable.vaccinationmenuorange);
                graphMenuButton.setBackgroundResource(R.drawable.chartsmenugray);
                foodMenuButton.setBackgroundResource(R.drawable.nutritionmenugray);
                forumMenuButton.setBackgroundResource(R.drawable.forummenugray);
            }
        });

        view.findViewById(R.id.vaccine_graph).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.setVisibility(View.GONE);
                graph.setVisibility(View.VISIBLE);
                food.setVisibility(View.GONE);
                forum.setVisibility(View.GONE);
                vaccineMenuButton.setBackgroundResource(R.drawable.vaccinationmenugray);
                graphMenuButton.setBackgroundResource(R.drawable.chartsmenuorange);
                foodMenuButton.setBackgroundResource(R.drawable.nutritionmenugray);
                forumMenuButton.setBackgroundResource(R.drawable.forummenugray);
            }
        });

        view.findViewById(R.id.vaccine_food).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.setVisibility(View.GONE);
                graph.setVisibility(View.GONE);
                food.setVisibility(View.VISIBLE);
                forum.setVisibility(View.GONE);
                vaccineMenuButton.setBackgroundResource(R.drawable.vaccinationmenugray);
                graphMenuButton.setBackgroundResource(R.drawable.chartsmenugray);
                foodMenuButton.setBackgroundResource(R.drawable.nutritionmenuorange);
                forumMenuButton.setBackgroundResource(R.drawable.forummenugray);
            }
        });

        view.findViewById(R.id.vaccine_forum).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.setVisibility(View.GONE);
                graph.setVisibility(View.GONE);
                food.setVisibility(View.GONE);
                forum.setVisibility(View.VISIBLE);
                vaccineMenuButton.setBackgroundResource(R.drawable.vaccinationmenugray);
                graphMenuButton.setBackgroundResource(R.drawable.chartsmenugray);
                foodMenuButton.setBackgroundResource(R.drawable.nutritionmenugray);
                forumMenuButton.setBackgroundResource(R.drawable.forummenuorange);
            }
        });

        view.findViewById(R.id.nutrition_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nutritionTable.setVisibility(View.VISIBLE);
                foodrecieps.setVisibility(View.GONE);
                nutritionIcon.setBackgroundResource(R.drawable.nutritioniconfoodorange);
                foodreciepsIcon.setBackgroundResource(R.drawable.foodrecipesgray);
            }
        });

        view.findViewById(R.id.foodrecipes_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nutritionTable.setVisibility(View.GONE);
                foodrecieps.setVisibility(View.VISIBLE);
                nutritionIcon.setBackgroundResource(R.drawable.nutritioniconfoodgray);
                foodreciepsIcon.setBackgroundResource(R.drawable.foodrecipesorange);
            }
        });

        view.findViewById(R.id.charts_height_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chartsHeightIcon.setBackgroundResource(R.drawable.chartsheightorange);
                chartsWeightIcon.setBackgroundResource(R.drawable.chartsweightgray);
                chartsHeightInfo.setVisibility(View.VISIBLE);
                chartsWeightInfo.setVisibility(View.GONE);

                heightLabel.setVisibility(View.VISIBLE);
                heightInputlayout.setVisibility(View.GONE);
                weightLabel.setVisibility(View.GONE);
                weightInputlayout.setVisibility(View.GONE);
            }
        });

        view.findViewById(R.id.charts_weight_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chartsHeightIcon.setBackgroundResource(R.drawable.chartsheightgray);
                chartsWeightIcon.setBackgroundResource(R.drawable.chartsweightorange);
                chartsHeightInfo.setVisibility(View.GONE);
                chartsWeightInfo.setVisibility(View.VISIBLE);

                heightLabel.setVisibility(View.GONE);
                heightInputlayout.setVisibility(View.GONE);
                weightLabel.setVisibility(View.VISIBLE);
                weightInputlayout.setVisibility(View.GONE);
            }
        });

        view.findViewById(R.id.height_add_label).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heightLabel.setVisibility(View.GONE);
                heightInputlayout.setVisibility(View.VISIBLE);
                weightLabel.setVisibility(View.GONE);
                weightInputlayout.setVisibility(View.GONE);
            }
        });

        view.findViewById(R.id.weight_add_label).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heightLabel.setVisibility(View.GONE);
                heightInputlayout.setVisibility(View.GONE);
                weightLabel.setVisibility(View.GONE);
                weightInputlayout.setVisibility(View.VISIBLE);
            }
        });

        view.findViewById(R.id.height_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String height = inputHeight.getText().toString();

                try {
                    JSONObject childsJSON = VaccinationDetails.getChildDetails();
                    childsJSON.getJSONArray("allkids").getJSONObject(0).put("height", height);
                    SharedPreferences pref = getContext().getSharedPreferences("DATA", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("CHILDS_"+ VaccinationDetails.getCurrentUser(), childsJSON.toString());
                    pref.edit();
                    editor.commit();
                    VaccinationDetails.setChildDetails(childsJSON);
                    heightValue1.setText(height + " CM");
                    heightValue2.setText(height + " CM");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                heightLabel.setVisibility(View.VISIBLE);
                heightInputlayout.setVisibility(View.GONE);
                weightLabel.setVisibility(View.GONE);
                weightInputlayout.setVisibility(View.GONE);
            }
        });

        view.findViewById(R.id.height_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heightLabel.setVisibility(View.VISIBLE);
                heightInputlayout.setVisibility(View.GONE);
                weightLabel.setVisibility(View.GONE);
                weightInputlayout.setVisibility(View.GONE);
            }
        });

        view.findViewById(R.id.weight_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String weight = inputWeight.getText().toString();

                try {
                    JSONObject childsJSON = VaccinationDetails.getChildDetails();
                    childsJSON.getJSONArray("allkids").getJSONObject(0).put("weight", weight);
                    SharedPreferences pref = getContext().getSharedPreferences("DATA", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("CHILDS_"+ VaccinationDetails.getCurrentUser(), childsJSON.toString());
                    pref.edit();
                    editor.commit();
                    VaccinationDetails.setChildDetails(childsJSON);
                    weightValue1.setText(weight + " KG");
                    weightValue2.setText(weight + " KG");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                heightLabel.setVisibility(View.GONE);
                heightInputlayout.setVisibility(View.GONE);
                weightLabel.setVisibility(View.VISIBLE);
                weightInputlayout.setVisibility(View.GONE);
            }
        });

        view.findViewById(R.id.weight_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heightLabel.setVisibility(View.GONE);
                heightInputlayout.setVisibility(View.GONE);
                weightLabel.setVisibility(View.VISIBLE);
                weightInputlayout.setVisibility(View.GONE);
            }
        });
    }
}