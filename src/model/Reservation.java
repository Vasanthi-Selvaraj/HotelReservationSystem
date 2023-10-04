package model;

public class Reservation {

    private int preference;

    public int getPreference() {
        return preference;
    }

    public void setPreference(int preference) {
        this.preference = preference;
    }

    public int getNumberOfMembers() {
        return numberOfMembers;
    }

    public void setNumberOfMembers(int numberOfMembers) {
        this.numberOfMembers = numberOfMembers;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public String getFoodPreference() {
        return foodPreference;
    }

    public void setFoodPreference(String foodPreference) {
        this.foodPreference = foodPreference;
    }

    public String getSpecialArrangements() {
        return specialArrangements;
    }

    public void setSpecialArrangements(String specialArrangements) {
        this.specialArrangements = specialArrangements;
    }

    private int numberOfMembers;
    private int numberOfDays;
    private String foodPreference;
    private String specialArrangements;
    public Reservation(int preference, int numberOfMembers, int numberOfDays, String foodPreference, String specialArrangements) {
        this.preference = preference;
        this.numberOfMembers = numberOfMembers;
        this.numberOfDays = numberOfDays;
        this.foodPreference = foodPreference;
        this.specialArrangements = specialArrangements;
    }




}

