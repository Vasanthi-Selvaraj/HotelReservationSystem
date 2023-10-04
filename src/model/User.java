package model;

import java.util.List;

public class User {
        private String userEmail;
        private int phoneNumber;
        private String location;
        private List<Long> reservation ;

        public User(String userEmail, int phoneNumber, String location, List<Long> reservation) {
            this.userEmail = userEmail;
            this.phoneNumber = phoneNumber;
            this.location = location;
            this.reservation = reservation;
        }


        public String getUserEmail() {
            return userEmail;
        }

        public void setUserEmail(String userEmail) {
            this.userEmail = userEmail;
        }

        public int getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(int phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

    public List<Long> getReservation() {
        return reservation;
    }

    public void setReservation(List<Long> reservation) {
        this.reservation = reservation;
    }
    }
