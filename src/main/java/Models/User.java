package Models;

public class User {

    private String firstName;
    private String lastName;
    private String password;

    private User(UserBuilder userBuilder){
        this.firstName = userBuilder.firstName;
        this.lastName = userBuilder.lastName;
        this.password = userBuilder.password;
    }

    public String setFirstName() { return firstName;}
    public String setLastName() { return lastName;}
    public String setPassword() { return password;}


    @Override
    public String toString() {
        return firstName + " " + lastName + " " + password;
    }

    public static class UserBuilder {

        private String firstName;
        private String lastName;
        private String password;

        public User build(){
            return new User(this);
        }

        public UserBuilder buildFirstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public UserBuilder buildLastName(String lastName){
            this.lastName = lastName;
            return this;
        }
        public UserBuilder buildPassword(String password){
            this.password = password;
            return this;
        }

        @Override
        public String toString() {
            return  firstName + " " + lastName + " "+ password;
        }
    }
}
