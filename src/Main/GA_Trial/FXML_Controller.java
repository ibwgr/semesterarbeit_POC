package GA_Trial;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



public class FXML_Controller  {


    @FXML
    private Button showAll;
    @FXML
    public ComboBox comboBoxMonat;
    @FXML
    public ComboBox comboBoxZiel;
    @FXML
    private TableView<Reise> reiseTable;
    @FXML
    public TextField kostenTotal;
    @FXML
    public TextField relation;
    @FXML
    public TextField other;
    @FXML
    public TextField price;
    @FXML
    public Persistence persistence;
    @FXML
    public GridPane gp;
    @FXML
    public Calculations calculations;
    @FXML
    public DatePicker datePicker;
    @FXML
    public String others;
    @FXML
    public Chart_GA chartGA;



    public FXML_Controller() {
        this.persistence = new SQL_Persistence();
        this.calculations = new Calculations(persistence);
        this.chartGA = new Chart_GA();
    }


    @FXML
    public void showAllTrips(ActionEvent event) {
        ObservableList<Reise> data = FXCollections.observableArrayList(persistence.getTrip());
        kostenTotal.setText(String.valueOf(calculations.totalCost()));
        relation.setText(calculations.gaRelation() + "%");
        reiseTable.setItems(data);
        comboBoxMonat.getSelectionModel().clearSelection();
        gp.getChildren().remove(chartGA.chart());
        chartGA = new Chart_GA();
        gp.add(chartGA.chart(), 0, 22, 4, 1);
    }


    @FXML
    public void deleteTrip(ActionEvent event) {
        try {
            Reise r = reiseTable.getSelectionModel().getSelectedItem();
            persistence.deleteTrip(r.nr);
            if (comboBoxMonat.getSelectionModel().isEmpty()) {
                showAll.fire();
            } else {
                selectMonth(comboBoxMonat, reiseTable, kostenTotal, relation);
                gp.getChildren().remove(chartGA.chart());
                chartGA = new Chart_GA();
                gp.add(chartGA.chart(),0,22,4,1);
            }
        } catch (NullPointerException e) {
            System.out.println("Keine Reise ausgewählt");
        }
    }

    @FXML
    private void selectMonth(ComboBox comboBoxMonat, TableView<Reise> reiseTable, TextField kostenTotal, TextField relation) {
        Object selectedItem = comboBoxMonat.getSelectionModel().getSelectedItem();
        String month = Calculations.showMonth(selectedItem.toString());
        ObservableList<Reise> abc = FXCollections.observableArrayList(persistence.getMonthPerTrip(month));
        double pp = persistence.getPricePerMonth(month);
        reiseTable.setItems(abc);
        kostenTotal.setText(String.valueOf(pp));
        relation.setText((int) (pp * 100 / (calculations.gaPerMonth) - 100) + "%");
    }


    @FXML
    public void setTrip(ActionEvent event) {
        if (comboBoxZiel.getSelectionModel().getSelectedItem() == others) {
            if (other.getText().matches("[a-zA-Z, ä,ö,ü,è,à,é,Ä,Ö,Ü]+")) {
                if (price.getText().matches("[0-9, .]+")) {
                        persistence.setTrip(other.getText(), Double.parseDouble(price.getText()), datePicker.getValue());
                        comboBoxZiel.getSelectionModel().clearSelection();
                        other.clear();
                        other.setVisible(false);
                        price.clear();
                        price.setPromptText("");
                        if (comboBoxMonat.getSelectionModel().isEmpty()) {
                        showAll.fire();
                        } else {
                        selectMonth(comboBoxMonat, reiseTable, kostenTotal, relation);
                        gp.getChildren().remove(chartGA.chart());
                        chartGA = new Chart_GA();
                        gp.add(chartGA.chart(), 0, 22, 4, 1);
                        }
                } else {
                    price.setText("Preis fehlt!");
                }
            } else {
                price.setText("Reiseziel fehlt!");
            }
        } else {
            if (price.getText().matches("[0-9, .]+")) {
                persistence.setTrip((String) comboBoxZiel.getSelectionModel().getSelectedItem(), Double.parseDouble(price.getText()), datePicker.getValue());
                comboBoxZiel.getSelectionModel().clearSelection();
                price.clear();
                if (comboBoxMonat.getSelectionModel().isEmpty()) {
                    showAll.fire();
                } else {
                    selectMonth(comboBoxMonat, reiseTable, kostenTotal, relation);
                    gp.getChildren().remove(chartGA.chart());
                    chartGA = new Chart_GA();
                    gp.add(chartGA.chart(), 0, 22, 4, 1);
                }
            } else {
                if (comboBoxZiel.getSelectionModel().isEmpty()) {
                    price.setText("Kein Reiseziel gewählt!");
                } else {
                    price.setText("Kein gültiger Betrag");
                }
            }
        }
    }


    @FXML
    public void setDestination(ActionEvent event) {
        Object selectedItem = comboBoxZiel.getSelectionModel().getSelectedItem();

        City city = City.from((String) selectedItem);
        switch (city) {
            case ANDERERZIELORT:
                price.clear();
                price.setPromptText("Preis eingeben");
                other.setVisible(true);
                other.setPromptText("Reiseziel");
                break;
            default:
                price.setText(String.valueOf(city.getPrice()));
                other.clear();
                other.setVisible(false);
        }
    }

    @FXML
    public void setMonth(ActionEvent event) {
        if (comboBoxMonat.getValue() != null) {
            selectMonth(comboBoxMonat, reiseTable, kostenTotal, relation);
        }
    }


    public void initialize() {
        datePicker.setValue(LocalDate.now());
        showAll.fire();
        reiseTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        gp.setGridLinesVisible(false);


        }
        public void setDatepicker(){
            DatePicker datePicker = new DatePicker();
            datePicker.setValue(LocalDate.now());
            datePicker.setShowWeekNumbers(false);
            datePicker.setPromptText("dd-MMM-yyyy");

            StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

                @Override
                public String toString(LocalDate date) {
                    if (date != null) {
                        return dateFormatter.format(date);
                    } else {
                        return "";
                    }
                }

                @Override
                public LocalDate fromString(String string) {
                    if (string != null && !string.isEmpty()) {
                        return LocalDate.parse(string, dateFormatter);
                    } else {
                        return null;
                    }
                }
            };
            datePicker.setConverter(converter);
    }
}