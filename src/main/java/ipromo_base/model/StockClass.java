package ipromo_base.model;

public class StockClass {
    private String id;
    private String stock;
    private String serialnumber;

    public StockClass() {

    }


    public StockClass(String id, String stock, String serialnumber) {
        this.id = id;
        this.stock = stock;
        this.serialnumber = serialnumber;
    }

    public String getId() {
        return id;
    }

    public String getStock() {
        return stock;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }


    @Override
    public String toString() {
        return "StockClass{" +
                "id=" + id +
                ", stock='" + stock + '\'' +
                ", serialnumber=" + serialnumber +
                '}';
    }
}
