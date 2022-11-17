package view_controller.dashboard.customers;

import DAO.CountryQueries;
import DAO.CustomerQueries;
import DAO.FirstLevelDivisionQueries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class UpdateCustomerForm implements Initializable {
    @FXML private TextField customerIdField;
    @FXML private ComboBox<String> countryDropdown;
    @FXML private ComboBox<String> divisionDropdown;
    @FXML private TextField nameField;
    @FXML private TextField addressField;
    @FXML private TextField postalCodeField;
    @FXML private TextField phoneField;
    @FXML private Button submitButton;
    @FXML private Button cancelButton;

    // FIXME: 11/15/2022 Repeated code - divisions, countries
    private ObservableList<FirstLevelDivision> divisions;
    private ObservableList<Country> countries;
    private ObservableList<String> countryNames;
    private ObservableList<String> divisionNames;
    private Customer customer;

    private Customer selectedCustomer = CustomersTabController.selectedCustomer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // FIXME: 11/15/2022 Repeating code
        try {
            // QUERY: Lists to hold the objects for the dropdown boxes -- the name properties are gathered below.
            divisions = FirstLevelDivisionQueries.getAllDivisions();
            countries = CountryQueries.getAllCountries();

            // Lists to hold the string names to display in the dropdown boxes.
            countryNames = FXCollections.observableArrayList();
            divisionNames = FXCollections.observableArrayList();

            countries.forEach((d) -> countryNames.add(d.getCountry()));

            countryDropdown.setItems(countryNames);

            // Watches country selection in country dropdown box.
            countryDropdown.getSelectionModel().selectedItemProperty().addListener((observableValue, part, t1) -> {
                String selectedCountry = countryDropdown.getSelectionModel().getSelectedItem();

                // Upon new selection -- refresh divisionNames list and fully clear the combobox -- fixes a problem where the combobox had blank space in it.
                divisionNames.clear();
                divisionDropdown.getItems().clear();

                // Checks for all divisions that fall within the selected country and adds them to  divisionNames list for display.
                divisions.forEach((d) -> {
                    if (d.getCountry().equals(selectedCountry)) {
                        divisionNames.add(d.getDivision());
                    }
                });

                // Set division names in the divisionDropdown based on the currently selected country
                divisionDropdown.setItems(divisionNames);
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            loadCustomer();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
        Integer divisionId = selectedCustomer.getDivisionId();
        String divisionName = FirstLevelDivisionQueries.getDivisionById(divisionId).getDivision();
        String countryName = FirstLevelDivisionQueries.getDivisionById(divisionId).getCountry();

        divisionDropdown.setValue(divisionName);
        countryDropdown.setValue(countryName);
    }

    /**
     * Updates the customer in the UI and database.
     * @param event
     */
    // FIXME: 11/15/2022 Repeating code
    public void submitForm(ActionEvent event) throws SQLException {
        // Alerts
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setContentText("One or more fields are blank. All fields must be complete.");
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setContentText("Customer successfully updated.");

        // Continuously Prompts user with an error message if any of the fields are empty
        // FIXME: 11/15/2022 Add dropdowns to this error check
        if (nameField.getText().isEmpty() || addressField.getText().isEmpty() || phoneField.getText().isEmpty() ||
                postalCodeField.getText().isEmpty() || divisionDropdown.getSelectionModel().isEmpty() || countryDropdown.getSelectionModel().isEmpty()) {
            errorAlert.show();
            return;
        }

        String customerName = nameField.getText();
        String address = addressField.getText();
        String postalCode = postalCodeField.getText();
        String phone = phoneField.getText();
        String selectedDivisionName = divisionDropdown.getSelectionModel().getSelectedItem();
        Integer divisionId = null;


        // Retrieve the division id by checking the selectedDivisionName against all divisions that are in the database.
        for (FirstLevelDivision d : divisions) {
            if (d.getDivision().equals(selectedDivisionName)) {
                divisionId = d.getDivisionId();
            }
        }

        // FIXME: 11/15/2022 only difference between updatecustomer and addcustomer
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
