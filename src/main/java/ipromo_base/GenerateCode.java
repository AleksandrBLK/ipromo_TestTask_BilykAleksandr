package ipromo_base;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

public class GenerateCode {
    private AtomicInteger serialNumber = new AtomicInteger();


    public String generateCode(String stockId) {
        int number = serialNumber.get();
        String idAndDate = stockId + LocalDate.now().format(DateTimeFormatter.ofPattern("YYYYMMdd"));
        if (number < 10) {
            return idAndDate + "00" + serialNumber.toString();
        }
        if (number < 100) {
            return idAndDate + "0" + serialNumber.toString();
        } else {
            return idAndDate + serialNumber.toString();
        }



    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber.set(Integer.parseInt(serialNumber));
    }
}
