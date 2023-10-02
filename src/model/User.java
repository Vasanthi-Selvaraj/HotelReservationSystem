package model;

public class User {
        private String userEmail;
        private int phoneNumber;
        private String location;

        public User(String userEmail, int phoneNumber, String location) {
            this.userEmail = userEmail;
            this.phoneNumber = phoneNumber;
            this.location = location;
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
    }
