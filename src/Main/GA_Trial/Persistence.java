package GA_Trial;

import java.util.List;

public interface Persistence {

    double getPrices();

    List<Reise> getTrip();

    List<Reise> getMonthPerTrip(String month);

    void deleteTrip(int nr);
}
