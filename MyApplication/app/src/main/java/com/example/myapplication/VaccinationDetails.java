package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Pair;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VaccinationDetails {
    public List<String> dates;
    public static String currentUser = "";
    public static JSONObject childDetails = null;
    public static JSONObject vaccineDetails = null;
    public static Map<String, Pair<String, String>> chartHeightDetails = new HashMap<String, Pair<String, String>>(){
        {
            put("0", new Pair<String, String>("46", "53.5"));
            put("0.6", new Pair<String, String>("63", "72"));
            put("1", new Pair<String, String>("71", "80"));
            put("1.6", new Pair<String, String>("71", "87"));
            put("2", new Pair<String, String>("82", "94"));
            put("2.6", new Pair<String, String>("85", "98"));
            put("3", new Pair<String, String>("89", "103"));
            put("3.6", new Pair<String, String>("92", "106"));
            put("4", new Pair<String, String>("95.5", "111"));
            put("4.6", new Pair<String, String>("97", "115"));
            put("5", new Pair<String, String>("100", "118.5"));
            put("5.6", new Pair<String, String>("102", "122.5"));
            put("6", new Pair<String, String>("104", "126"));
            put("6.6", new Pair<String, String>("105.5", "129.5"));
            put("7", new Pair<String, String>("109", "132.5"));
            put("7.6", new Pair<String, String>("112", "135.5"));
            put("8", new Pair<String, String>("114", "139"));
            put("8.6", new Pair<String, String>("116", "142"));
            put("9", new Pair<String, String>("119", "145.5"));
            put("9.6", new Pair<String, String>("121", "148.5"));
            put("10", new Pair<String, String>("123.5", "151.5"));
            put("10.6", new Pair<String, String>("126", "154.5"));
            put("11", new Pair<String, String>("128", "157"));
            put("11.6", new Pair<String, String>("130.5", "160.5"));
            put("12", new Pair<String, String>("133", "163.5"));
            put("12.6", new Pair<String, String>("136", "166.5"));
            put("13", new Pair<String, String>("138", "170"));
            put("13.6", new Pair<String, String>("141", "173"));
            put("14", new Pair<String, String>("143", "175.5"));
            put("14.6", new Pair<String, String>("146", "177.5"));
            put("15", new Pair<String, String>("148", "179.5"));
            put("15.6", new Pair<String, String>("150", "181.5"));
            put("16", new Pair<String, String>("152", "183"));
            put("16.6", new Pair<String, String>("153", "184"));
            put("17", new Pair<String, String>("155", "184.5"));
            put("17.6", new Pair<String, String>("156.5", "186"));
            put("18", new Pair<String, String>("158", "186.5"));
        }
    };

    public static Map<String, Pair<String, String>> chartWeightDetails = new HashMap<String, Pair<String, String>>(){
        {
            put("0", new Pair<String, String>("2.5", "4.5"));
            put("0.6", new Pair<String, String>("6", "10"));
            put("1", new Pair<String, String>("7.5", "12"));
            put("1.6", new Pair<String, String>("8.5", "13.5"));
            put("2", new Pair<String, String>("9.5", "15"));
            put("2.6", new Pair<String, String>("10.5", "16.5"));
            put("3", new Pair<String, String>("11.5", "18"));
            put("3.6", new Pair<String, String>("12", "19"));
            put("4", new Pair<String, String>("12.5", "21"));
            put("4.6", new Pair<String, String>("13", "23"));
            put("5", new Pair<String, String>("13.5", "24.5"));
            put("5.6", new Pair<String, String>("14", "26"));
            put("6", new Pair<String, String>("14.5", "28"));
            put("6.6", new Pair<String, String>("15.5", "31"));
            put("7", new Pair<String, String>("16", "33"));
            put("7.6", new Pair<String, String>("16.5", "33.5"));
            put("8", new Pair<String, String>("17.5", "39.5"));
            put("8.6", new Pair<String, String>("18.5", "42"));
            put("9", new Pair<String, String>("19", "45.5"));
            put("9.6", new Pair<String, String>("20", "48.5"));
            put("10", new Pair<String, String>("21", "51.5"));
            put("10.6", new Pair<String, String>("21.5", "55"));
            put("11", new Pair<String, String>("22.5", "58"));
            put("11.6", new Pair<String, String>("24", "62"));
            put("12", new Pair<String, String>("25", "66"));
            put("12.6", new Pair<String, String>("26", "69.5"));
            put("13", new Pair<String, String>("27.5", "72"));
            put("13.6", new Pair<String, String>("29", "75.5"));
            put("14", new Pair<String, String>("30.5", "78"));
            put("14.6", new Pair<String, String>("32.5", "81"));
            put("15", new Pair<String, String>("34.5", "83"));
            put("15.6", new Pair<String, String>("36", "84.5"));
            put("16", new Pair<String, String>("37", "86"));
            put("16.6", new Pair<String, String>("38.5", "86.8"));
            put("17", new Pair<String, String>("39.5", "87.5"));
            put("17.6", new Pair<String, String>("41", "87.5"));
            put("18", new Pair<String, String>("42", "88"));
        }
    };

    public static void SetCurrentUser(String emailId) {
        currentUser = emailId;
    }

    public static String getCurrentUser() {
        return currentUser;
    }

    public static void setChildDetails(JSONObject jsonObject) {
        childDetails = jsonObject;
    }

    public static JSONObject getChildDetails() {
        return childDetails;
    }

    public static JSONObject getVaccineDetails() { return vaccineDetails; }

    public static void setVaccineDetails(JSONObject jsonObject) {
        vaccineDetails = jsonObject;
    }

    public static JSONObject createVaccineDetails() {
        JSONObject jsonObject = new JSONObject();
        try {
            JSONArray birtharray = new JSONArray();
            JSONObject object1 = new JSONObject("{\"VACCINATED\":false,\"NAME\":\"BCG\",\"PRICE\":255}");
            birtharray.put(object1);
            JSONObject object2 = new JSONObject("{\"VACCINATED\":false,\"NAME\":\"MMR\",\"PRICE\":1000}");
            birtharray.put(object2);
            jsonObject.put("BIRTH", birtharray);

            JSONArray array6weeks = new JSONArray();
            JSONObject object6w1 = new JSONObject("{\"VACCINATED\":false,\"NAME\":\"DTP\",\"PRICE\":1252}");
            array6weeks.put(object6w1);
            JSONObject object6w2 = new JSONObject("{\"VACCINATED\":false,\"NAME\":\"Rotavirus\",\"PRICE\":1495}");
            array6weeks.put(object6w2);
            jsonObject.put("6WEEKS", array6weeks);

            JSONArray array10w = new JSONArray();
            JSONObject object10w1 = new JSONObject("{\"VACCINATED\":false,\"NAME\":\"IPV\",\"PRICE\":440}");
            array10w.put(object10w1);
            JSONObject object10w2 = new JSONObject("{\"VACCINATED\":false,\"NAME\":\"Hep B\",\"PRICE\":6000}");
            array10w.put(object10w2);
            jsonObject.put("10WEEKS", array10w);

            JSONArray array14w = new JSONArray();
            JSONObject object14w1 = new JSONObject("{\"VACCINATED\":false,\"NAME\":\"IPV\",\"PRICE\":1499}");
            array14w.put(object14w1);
            JSONObject object14w2 = new JSONObject("{\"VACCINATED\":false,\"NAME\":\"PCV\",\"PRICE\":3800}");
            array14w.put(object14w2);
            jsonObject.put("14WEEKS", array14w);

            JSONArray array6m = new JSONArray();
            JSONObject object6m1 = new JSONObject("{\"VACCINATED\":false,\"NAME\":\"BCG\",\"PRICE\":255}");
            array6m.put(object6m1);
            JSONObject object6m2 = new JSONObject("{\"VACCINATED\":false,\"NAME\":\"TCV\",\"PRICE\":525}");
            array6m.put(object6m2);
            jsonObject.put("6MONTHS", array6m);

            JSONArray array9m = new JSONArray();
            JSONObject object9m1 = new JSONObject("{\"VACCINATED\":false,\"NAME\":\"MMR\",\"PRICE\":255}");
            array9m.put(object9m1);
            JSONObject object9m2 = new JSONObject("{\"VACCINATED\":false,\"NAME\":\"MMR\",\"PRICE\":1000}");
            array9m.put(object9m2);
            jsonObject.put("9MONTHS", array9m);

            JSONArray array12m = new JSONArray();
            JSONObject object12m1 = new JSONObject("{\"VACCINATED\":false,\"NAME\":\"Hepatitis\",\"PRICE\":1000}");
            array12m.put(object12m1);
            JSONObject object12m2 = new JSONObject("{\"VACCINATED\":false,\"NAME\":\"Influenza\",\"PRICE\":700}");
            array12m.put(object12m2);
            jsonObject.put("12MONTHS", array12m);

            JSONArray array15m = new JSONArray();
            JSONObject object15m1 = new JSONObject("{\"VACCINATED\":false,\"NAME\":\"PCV\",\"PRICE\":700}");
            array15m.put(object15m1);
            JSONObject object15m2 = new JSONObject("{\"VACCINATED\":false,\"NAME\":\"MMR\",\"PRICE\":1495}");
            array15m.put(object15m2);
            jsonObject.put("15MONTHS", array15m);

            JSONArray array18m = new JSONArray();
            JSONObject object18m1 = new JSONObject("{\"VACCINATED\":false,\"NAME\":\"DTP\",\"PRICE\":1055}");
            array18m.put(object18m1);
            JSONObject object18m2 = new JSONObject("{\"VACCINATED\":false,\"NAME\":\"PCV\",\"PRICE\":1000}");
            array18m.put(object18m2);
            jsonObject.put("18MONTHS", array18m);

            JSONArray array2y = new JSONArray();
            JSONObject object2y1 = new JSONObject("{\"VACCINATED\":false,\"NAME\":\"IPV\",\"PRICE\":2505}");
            array2y.put(object2y1);
            JSONObject object2y2 = new JSONObject("{\"VACCINATED\":false,\"NAME\":\"HEP2\",\"PRICE\":1000}");
            array2y.put(object18m2);
            jsonObject.put("2YEARS", array2y);

            JSONArray array3y = new JSONArray();
            JSONObject object3y1 = new JSONObject("{\"VACCINATED\":false,\"NAME\":\"DTP\",\"PRICE\":1255}");
            array3y.put(object3y1);
            JSONObject object3y2 = new JSONObject("{\"VACCINATED\":false,\"NAME\":\"Vericlla\",\"PRICE\":2000}");
            array3y.put(object3y2);
            jsonObject.put("3YEARS", array3y);

            JSONArray array5y = new JSONArray();
            JSONObject object5y1 = new JSONObject("{\"VACCINATED\":false,\"NAME\":\"DTPb2\",\"PRICE\":1625}");
            array5y.put(object5y1);
            JSONObject object5y2 = new JSONObject("{\"VACCINATED\":false,\"NAME\":\"MMR\",\"PRICE\":100}");
            array5y.put(object5y2);
            jsonObject.put("5YEARS", array5y);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  jsonObject;
    }

    public List<String> getVaccinationTypes() {
        List<String> dates = new ArrayList<>();
        dates.add("BIRTH");
        dates.add("6WEEKS");
        dates.add("10WEEKS");
        dates.add("14WEEKS");
        dates.add("6MONTHS");
        dates.add("9MONTHS");
        dates.add("12MONTHS");
        dates.add("15MONTHS");
        dates.add("18MONTHS");
        dates.add("2YEARS");
        dates.add("3YEARS");
        dates.add("5YEARS");

        return dates;
    }

    public ArrayList<VaccinationData> getVaccinationDates(Date birthDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birthDate);

        ArrayList<VaccinationData> dates = new ArrayList<>();
        dates.add(getBirthVaccine(birthDate));
        dates.add(get6WeeksVaccine(birthDate));
        dates.add(get10WeeksVaccine(birthDate));
        dates.add(get14WeeksVaccine(birthDate));
        dates.add(get6MonthsVaccine(birthDate));
        dates.add(get9MonthsVaccine(birthDate));
        dates.add(get12MonthsVaccine(birthDate));
        dates.add(get15MonthsVaccine(birthDate));
        dates.add(get18MonthsVaccine(birthDate));
        dates.add(get2YearsVaccine(birthDate));
        dates.add(get3YearsVaccine(birthDate));
        dates.add(get5YearsVaccine(birthDate));
        return dates;
    }

    public static List<VaccinationData.VaccinationInformation> getVaccines(String period) {
        JSONObject jsonObject = getVaccineDetails();
        List<VaccinationData.VaccinationInformation> vaccines = new ArrayList<>();
        if (jsonObject != null) {
            try {
                JSONArray array = jsonObject.getJSONArray(period);
                for(int i=0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    boolean isVaccinated = object.getBoolean("VACCINATED");
                    String name = object.getString("NAME");
                    double price = object.getDouble("PRICE");
                    VaccinationData.VaccinationInformation vaccine = new VaccinationData.VaccinationInformation();
                    vaccine.vaccinated = isVaccinated;
                    vaccine.name = name;
                    vaccine.price = price;
                    vaccines.add(vaccine);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return vaccines;
    }

    public VaccinationData getBirthVaccine(Date birthDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = simpleDateFormat.format(birthDate);
        VaccinationData data = new VaccinationData("At Birth", "Due Date: " + dateString);
        data.date = birthDate;
        data.type = "BIRTH";
        data.vaccinations = getVaccines("BIRTH");
        return data;
    }

    public VaccinationData get6WeeksVaccine(Date birthDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birthDate);
        calendar.add(Calendar.WEEK_OF_MONTH, 6);
        Date date6Weeks = calendar.getTime();
        String dateString = simpleDateFormat.format(date6Weeks);
        VaccinationData data = new VaccinationData("6 Weeks", "Due Date: " + dateString);
        data.date = date6Weeks;
        data.type = "6WEEKS";
        data.vaccinations = getVaccines("6WEEKS");
        return data;
    }

    public VaccinationData get10WeeksVaccine(Date birthDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birthDate);
        calendar.add(Calendar.WEEK_OF_MONTH, 10);
        Date date10Weeks = calendar.getTime();
        String dateString = simpleDateFormat.format(date10Weeks);
        VaccinationData data = new VaccinationData("10 Weeks", "Due Date: " + dateString);
        data.date = date10Weeks;
        data.type = "10WEEKS";
        data.vaccinations = getVaccines("10WEEKS");
        return data;
    }

    public VaccinationData get14WeeksVaccine(Date birthDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birthDate);
        calendar.add(Calendar.WEEK_OF_MONTH, 14);
        Date date14Weeks = calendar.getTime();
        String dateString = simpleDateFormat.format(date14Weeks);
        VaccinationData data = new VaccinationData("14 Weeks", "Due Date: " + dateString);
        data.date = date14Weeks;
        data.type = "14WEEKS";
        data.vaccinations = getVaccines("14WEEKS");
        return data;
    }

    public VaccinationData get6MonthsVaccine(Date birthDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birthDate);
        calendar.add(Calendar.MONTH, 6);
        Date date6Months = calendar.getTime();
        String dateString = simpleDateFormat.format(date6Months);
        VaccinationData data = new VaccinationData("6 Months", "Due Date: " + dateString);
        data.date = date6Months;
        data.type = "6MONTHS";
        data.vaccinations = getVaccines("6MONTHS");
        return data;
    }

    public VaccinationData get9MonthsVaccine(Date birthDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birthDate);
        calendar.add(Calendar.MONTH, 9);
        Date date9Months = calendar.getTime();
        String dateString = simpleDateFormat.format(date9Months);
        VaccinationData data = new VaccinationData("9 Months", "Due Date: " + dateString);
        data.date = date9Months;
        data.type = "9MONTHS";
        data.vaccinations = getVaccines("9MONTHS");
        return data;
    }

    public VaccinationData get12MonthsVaccine(Date birthDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birthDate);
        calendar.add(Calendar.MONTH, 12);
        Date date12Months = calendar.getTime();
        String dateString = simpleDateFormat.format(date12Months);
        VaccinationData data = new VaccinationData("12 Months", "Due Date: " + dateString);
        data.date = date12Months;
        data.type = "12MONTHS";
        data.vaccinations = getVaccines("12MONTHS");
        return data;
    }

    public VaccinationData get15MonthsVaccine(Date birthDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birthDate);
        calendar.add(Calendar.MONTH, 15);
        Date date15Months = calendar.getTime();
        String dateString = simpleDateFormat.format(date15Months);
        VaccinationData data = new VaccinationData("15 Months", "Due Date: " + dateString);
        data.date = date15Months;
        data.type = "15MONTHS";
        data.vaccinations = getVaccines("15MONTHS");
        return data;
    }

    public VaccinationData get18MonthsVaccine(Date birthDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birthDate);
        calendar.add(Calendar.MONTH, 18);
        Date date18Months = calendar.getTime();
        String dateString = simpleDateFormat.format(date18Months);
        VaccinationData data = new VaccinationData("18 Months", "Due Date: " + dateString);
        data.date = date18Months;
        data.type = "18MONTHS";
        data.vaccinations = getVaccines("18MONTHS");
        return data;
    }

    public VaccinationData get2YearsVaccine(Date birthDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birthDate);
        calendar.add(Calendar.YEAR, 2);
        Date date2Years = calendar.getTime();
        String dateString = simpleDateFormat.format(date2Years);
        VaccinationData data = new VaccinationData("2 Years", "Due Date: " + dateString);
        data.date = date2Years;
        data.type = "2YEARS";
        data.vaccinations = getVaccines("2YEARS");
        return data;

    }
    public VaccinationData get3YearsVaccine(Date birthDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birthDate);
        calendar.add(Calendar.YEAR, 3);
        Date date3Years = calendar.getTime();
        String dateString = simpleDateFormat.format(date3Years);
        VaccinationData data = new VaccinationData("3 Years", "Due Date: " + dateString);
        data.date = date3Years;
        data.type = "3YEARS";
        data.vaccinations = getVaccines("3YEARS");
        return data;
    }
    public VaccinationData get5YearsVaccine(Date birthDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birthDate);
        calendar.add(Calendar.YEAR, 5);
        Date date5Years = calendar.getTime();
        String dateString = simpleDateFormat.format(date5Years);
        VaccinationData data = new VaccinationData("5 Years", "Due Date: " + dateString);
        data.date = date5Years;
        data.type = "5YEARS";
        data.vaccinations = getVaccines("5YEARS");
        return data;
    }
}
