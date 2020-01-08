package GA_Trial;

public enum City {

    ZURICH("Zürich", 81.90),
    BERN("Bern", 127),
    OLTEN("Olten", 116.70),
    ANDERERZIELORT("Anderer Zielort", 0.0);


    private String cityName;
    private double price;

    City(String cityName, double price) {
        this.cityName = cityName;
        this.price = price;
    }


    public double getPrice() {
        return price;
    }

    public static City from (String cityName) {
        for (City city : City.values()) {
            if (city.cityName.equals(cityName)) {
                return city;
            }
        }

        throw new IllegalArgumentException("no city found for " + cityName);
    }
}
