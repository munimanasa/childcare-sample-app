package com.example.myapplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VaccinationData {
    public String title;
    public String description;
    public Date date;
    public String type;
    public List<VaccinationInformation> vaccinations;



    public VaccinationData(String title, String description) {
        this.title = title;
        this.description = description;
        this.vaccinations = new ArrayList<>();
    }

    public static class VaccinationInformation {
        public String name;
        public double price;
        public boolean vaccinated;
    }
}
