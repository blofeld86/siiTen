package models;

public class User {

    private String firstName;
    private String lastName;
    private String password;
    private String mail;
    private String alias;
    private String company;
    private String street;
    private String city;
    private String zipCode;
    private String phone;

    private User(UserBuilder userBuilder){
        this.firstName = userBuilder.firstName;
        this.lastName = userBuilder.lastName;
        this.password = userBuilder.password;
        this.mail = userBuilder.mail;
        this.alias = userBuilder.alias;
        this.street = userBuilder.street;
        this.company = userBuilder.company;
        this.city = userBuilder.city;
        this.zipCode = userBuilder.zipCode;
        this.phone = userBuilder.phone;
    }

    public String getFirstName() { return firstName;}
    public String getLastName() { return lastName;}
    public String getPassword() { return password;}
    public String getMail(){return mail;}
    public String getAlias(){return alias;}
    public String getCompany() { return company;}
    public String getStreet() { return street;}
    public String getCity() { return city;}
    public String getZipCode() { return zipCode;}
    public String getPhone() { return phone;}

    @Override
    public String toString() {
        return  firstName+" "+lastName+" "+password+" "+mail+" " +alias+" "+company+" "
                +street+" "+city+" "+zipCode+" "+phone;
    }

    public static class UserBuilder {

        private String firstName;
        private String lastName;
        private String password;
        private String mail;
        private String alias;
        private String company;
        private String street;
        private String city;
        private String zipCode;
        private String phone;

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

        public UserBuilder buildMail(String mail){
            this.mail = mail;
            return this;
        }

        public UserBuilder buildAlias(String alias){
            this.alias = alias;
            return this;
        }

        public UserBuilder buildCompany(String company){
            this.company = company;
            return this;
        }

        public UserBuilder buildStreet(String street){
            this.street = street;
            return this;
        }

        public UserBuilder buildCity(String city){
            this.city = city;
            return this;
        }

        public UserBuilder buildZipCode(String zipCode){
            this.zipCode = zipCode;
            return this;
        }

        public UserBuilder buildPhone(String phone){
            this.phone = phone;
            return this;
        }

        @Override
        public String toString() {
            return firstName+" "+lastName+" "+password+" "+mail+" "+alias
                   +" "+company+" "+street+" "+city+" "+zipCode+" "+phone;
        }
    }
}
