package GA_Trial;

import java.time.LocalDate;
import java.util.List;

public interface Persistence {

    double getPrices();

    List<Reise> getTrip();

    List<Reise> getMonthPerTrip(String month);

    double getPricePerMonth(String monat);

    void deleteTrip(int nr);

    void setTrip(String destination, double preis, LocalDate datum);
}
