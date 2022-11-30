package view_controller.dashboard.customers;

import DAO.CountryQueries;
import DAO.CustomerQueries;
import DAO.FirstLevelDivisionQueries;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
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

/**
 * This form enables the user to update an existing customer.
 */
public class UpdateCustomerForm implements Initializable {
    @FXML private TextField customerIdField;
    @FXML private ComboBox<Country> countryDropdown;
    @FXML private ComboBox<FirstLevelDivision> divisionDropdown;
    @FXML private TextField nameField;
    @FXML private TextField addressField;
    @FXML private TextField postalCodeField;
    @FXML private TextField phoneField;
    @FXML private Button submitButton;
    @FXML private Button cancelButton;

    private ObservableList<FirstLevelDivision> divisions;
    private ObservableList<Country> countries;
    private Customer selectedCustomer = CustomersTabController.selectedCustomer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

        try {
            loadCustomer();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Loads the attributes from the selected customer into the form fields.
     */
    private void loadCustomer() throws SQLException {
        // Populates the form fields with the user information
        customerIdField.setText(selectedCustomer.getCustomerId().toString());
        nameField.setText(selectedCustomer.getCustomerName());
        addressField.setText(selectedCustomer.getAddress());
        postalCodeField.setText(selectedCustomer.getPostalCode());
        phoneField.setText(selectedCustomer.getPhone());

        // Queries DB for division information. Sets the default value of both combo boxes to the customer's country and region.
        Integer customerDivisionId = selectedCustomer.getDivisionId();
        FirstLevelDivision customerDivision = null;

        // loop through divisions, set customerDivision to the division that matches the divisionId
        for (FirstLevelDivision division: divisions) {
            if (division.getDivisionId().equals(customerDivisionId)) {
                customerDivision = division;
                break;
            }
        }

        Integer customerCountryId = customerDivision.getCountryId();
        Country customerCountry = null;

        // loop through countries, set customerCountry to the country that matches the countryId
        for (Country country: countries) {
            if (country.getCountryId().equals(customerCountryId)){
                customerCountry = country;
                break;
            }
        }

        divisionDropdown.setValue(customerDivision); // FLD object param
        countryDropdown.setValue(customerCountry); // Country object param

    }

    /**
     * Updates an existing customer.
     * @param event
     */
    public void submitForm(ActionEvent event) throws SQLException {
        // Alerts
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setContentText("One or more fields are blank. All fields must be complete.");
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setContentText("Customer successfully updated.");

        // Continuously Prompts user with an error message if any of the fields are empty
        if (nameField.getText().isEmpty() || addressField.getText().isEmpty() || phoneField.getText().isEmpty() ||
                postalCodeField.getText().isEmpty() || divisionDropdown.getSelectionModel().isEmpty() || countryDropdown.getSelectionModel().isEmpty()) {
            errorAlert.show();
            return;
        }

        String customerName = nameField.getText();
        String address = addressField.getText();
        String postalCode = postalCodeField.getText();
        String phone = phoneField.getText();
        Integer divisionId = divisionDropdown.getSelectionModel().getSelectedItem().getDivisionId();

        selectedCustomer.setCustomerName(customerName);
        selectedCustomer.setAddress(address);
        selectedCustomer.setPostalCode(postalCode);
        selectedCustomer.setPhone(phone);
        selectedCustomer.setDivisionId(divisionId);
        CustomerQueries.updateCustomer(selectedCustomer);

        // RELOAD MAIN DATA ON DASHBOARD AFTER SUBMISSION AND CLOSE WINDOW
        ObservableList<Customer> newCustomerList = CustomerQueries.getAllCustomers();
        CustomersTabController.customers.setAll(newCustomerList);

        successAlert.show();
        Helper.closeWindow(submitButton);
    }

    /**
     * Cancels the form submission and closes the window.
     * @param event
     */
    public void closeForm(ActionEvent event) {
        Helper.closeWindow(cancelButton);
    }
}
