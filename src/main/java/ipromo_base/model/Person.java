package ipromo_base.model;

public class Person {
    private String id;
    private String name;
    private String secondName;
    private String phone;
    private String email;
    private String code;
    private String stockId;
    private String stockName;
    private String serialnumber;

    public Person(String name, String secondName, String phone, String email) {
        this.name = name;
        this.secondName = secondName;
        this.phone = phone;
        this.email = email;
        this.id = null;
        this.code = null;
    }

    public Person(String name, String secondName, String phone, String email, String stockId, String stockName, String serialnumber) {
        this.id = null;
        this.name = name;
        this.secondName = secondName;
        this.phone = phone;
        this.email = email;
        this.code = null;
        this.stockId = stockId;
        this.stockName = stockName;
        this.serialnumber = serialnumber;
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getCode() {
        return code;
    }

    public String getId() {
        return id;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isNew() {
        return id == null;
    }

    public String getStockId() {
        return stockId;
    }

    public String getStockName() {
        return stockName;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public boolean hasCode(){
        return code == null;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
