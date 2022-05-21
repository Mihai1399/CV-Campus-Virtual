package org.example.model;

public class User {
    private String username;
    private String password;
    private String Name;
    private String Alias;
    private String role;
    private String PhoneNumber;
    private String EmailAdress;

    public User(String username, String password, String Name, String Alias, String role, String PhoneNumber, String EmailAdress) {
        this.username = username;
        this.password = password;
        this.Name = Name;
        this.Alias = Alias;
        this.role = role;
        this.PhoneNumber = PhoneNumber;
        this.EmailAdress = EmailAdress;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Nume) {
        this.Name = Nume;
    }
    public String getAlias() {
        return Alias;
    }

    public void setAlias(String Prenume) {
        this.Alias = Prenume;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.PhoneNumber = phoneNumber;
    }

    public String getEmailAdress() {
        return EmailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.EmailAdress = emailAdress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        return role != null ? role.equals(user.role) : user.role == null;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
    @Override
    public String toString(){
        String t;
        t= Name +" "+ Alias +"\n"+ PhoneNumber +"\n"+ EmailAdress;
        return t;
    }
}
