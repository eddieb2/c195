package view_controller.dashboard.customers;

import DAO.CustomerQueries;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import src.model.Customer;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 *  Displays all customers. Enables the user to add, update, or delete customers.
 */
public class CustomersTabController implements Initializable {
    @FXML private AnchorPane customersTab;
    @FXML private TableView<Customer> customersTable;
    @FXML private TableColumn<Customer,Integer> idCol;
    @FXML private TableColumn<Customer,String> nameCol;
    @FXML private TableColumn<Customer,String> addressCol;
    @FXML private TableColumn<Customer,String> postalCodeCol;
    @FXML private TableColumn<Customer,String> phoneCol;
    @FXML private TableColumn<Customer,Integer> divisionIdCol;

    public static ObservableList<Customer> customers;
    public static Customer selectedCustomer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Table column properties
        idCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        divisionIdCol.setCellValueFactory(new PropertyValueFactory<>("divisionId"));

        customersTable.getSelectionModel().selectedItemProperty().addListener((observableValue, part, t1) -> {
            selectedCustomer = customersTable.getSelectionModel().getSelectedItem();
        });

        // Queries the database and saves all the customers to an observable list.
        try {
            customers = CustomerQueries.getAllCustomers();
            customersTable.setItems(customers);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

    }

}
