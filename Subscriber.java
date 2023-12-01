import java.io.Serializable;

public class Subscriber implements Serializable {
    private String fName;
    private String city;
    private int phone;
    private String lName;

    public Subscriber(String fName, String city, int phone, String lName) {
        this.fName = fName;
        this.city = city;
        this.phone = phone;
        this.lName = lName;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;

    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "fName='" + fName + '\'' +
                ", city='" + city + '\'' +
                ", phone='" + phone + '\'' +
                ", lName='" + lName + '\'' +
                '}';
    }
}
