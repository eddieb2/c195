package view_controller.dashboard.customers;

import DAO.CountryQueries;
import DAO.CustomerQueries;
import DAO.FirstLevelDivisionQueries;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import src.model.Country;
import src.model.Customer;
import src.model.FirstLevelDivision;
import utils.Helper;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddCustomerForm implements Initializable {
    @FXML private ComboBox<Country> countryDropdown;
    @FXML private ComboBox<FirstLevelDivision> divisionDropdown;
    @FXML private TextField nameField;
    @FXML private TextField addressField;
    @FXML private TextField postalCodeField;
    @FXML private TextField phoneField;
    @FXML private Button submitButton;
    @FXML private Button cancelButton;

    // FIXME: 11/15/2022 Repeated code
    private ObservableList<FirstLevelDivision> divisions;
    private ObservableList<Country> countries;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // FIXME: 11/15/2022 Repeated code
        try {
            // QUERY: Lists to hold the objects for the dropdown boxes -- the name properties are gathered below.
            divisions = FirstLevelDivisionQueries.getAllDivisions();
            countries = CountryQueries.getAllCountries();

            countryDropdown.setItems(countries);

            // Watches country selection in country dropdown box.
            countryDropdown.getSelectionModel().selectedItemProperty().addListener((observableValue, part, t1) -> {
                Country selectedCountry = countryDropdown.getSelectionModel().getSelectedItem();

                // Set division names in the divisionDropdown based on the currently selected country -- filters division list
                FilteredList<FirstLevelDivision> filteredDivisions = divisions.filtered(division -> division.getCountry().equals(selectedCountry.getCountry()));
                divisionDropdown.setItems(filteredDivisions);

            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    // FIXME: 11/15/2022 Repeating code
    public void submitForm() throws SQLException {
        // Alerts
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setContentText("One or more fields are blank. All fields must be complete.");
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setContentText("Customer successfully created.");

        // Continuously Prompts user with an error message if any of the fields are empty
        if (nameField.getText().isEmpty() || addressField.getText().isEmpty() || phoneField.getText().isEmpty() ||
                postalCodeField.getText().isEmpty() || divisionDropdown.getSelectionModel().isEmpty() || countryDropdown.getSelectionModel().isEmpty()) {
            errorAlert.show();
            return;
        }

        // COLLECTS DATA & ADDS NEW CUSTOMER TO THE DATABASE
        String customerName = nameField.getText();
        String address = addressField.getText();
        String postalCode = postalCodeField.getText();
        String phone = phoneField.getText();
        System.out.println( divisionDropdown.getSelectionModel());
        Integer divisionId = divisionDropdown.getSelectionModel().getSelectedItem().getDivisionId();

        Customer newCustomer = new Customer(customerName, address, postalCode, phone, divisionId);
        CustomerQueries.addCustomer(newCustomer);

        // RELOAD MAIN DATA ON DASHBOARD AFTER SUBMISSION AND CLOSE WINDOW
        ObservableList<Customer> newCustomerList = CustomerQueries.getAllCustomers();
        CustomersTabController.customers.setAll(newCustomerList);

        successAlert.show();
        Helper.closeWindow(submitButton);
    }

    /**
     * Closes window when the cancel button is pressed.
     */
    public void closeForm() {
        Helper.closeWindow(cancelButton);
    }



}
