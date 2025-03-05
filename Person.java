public abstract class Person {
    protected String name, birthDate, gender, phoneNumber;

    public Person(String name, String birthDate, String gender, String phoneNumber) {
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public abstract void displayInfo();
}
