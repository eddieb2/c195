package View_Controller;

import DAO.CustomerQueries;
import DAO.UserQueries;
import Model.Customer;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Customers implements Initializable {

    @FXML private AnchorPane customersTab;
    @FXML private TableView<Customer> customersTable;
    @FXML private TableColumn<Customer,Integer> idCol;
    @FXML private TableColumn<Customer,String> nameCol;
    @FXML private TableColumn<Customer,String> addressCol;
    @FXML private TableColumn<Customer,String> postalCodeCol;
    @FXML private TableColumn<Customer,String> phoneCol;
    @FXML private TableColumn<Customer,Integer> divisionIdCol;
//    @FXML private TableColumn<Customer,> createDateCol;
//    @FXML private TableColumn<Customer,> createdByCol;
//    @FXML private TableColumn<Customer,> lastUpdatedCol;
//    @FXML private TableColumn<Customer,> lastUpdatedByCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        divisionIdCol.setCellValueFactory(new PropertyValueFactory<>("divisionId"));

        // Queries the database and saves all the customers to an observable list.
        ObservableList<Customer> customers = null;

        try {
            customers = CustomerQueries.getAllCustomers();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        customersTable.setItems(customers);
    }
}
