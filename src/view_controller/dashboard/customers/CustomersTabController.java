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
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class CustomersTabController implements Initializable {

    @FXML private AnchorPane customersTab;
    @FXML private TableView<Customer> customersTable; // FIXME: 11/14/2022  MIGHT BREAK
    @FXML private TableColumn<Customer,Integer> idCol;
    @FXML private TableColumn<Customer,String> nameCol;
    @FXML private TableColumn<Customer,String> addressCol;
    @FXML private TableColumn<Customer,String> postalCodeCol;
    @FXML private TableColumn<Customer,String> phoneCol;
    @FXML private TableColumn<Customer,Integer> divisionIdCol;
    @FXML private TableColumn<Customer,Timestamp> createDateCol;
    @FXML private TableColumn<Customer,String> createdByCol;
    @FXML private TableColumn<Customer,Timestamp> lastUpdateCol;
    @FXML private TableColumn<Customer,String> lastUpdatedByCol;

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
        createDateCol.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        createdByCol.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        lastUpdateCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        lastUpdatedByCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));


        customersTable.getSelectionModel().selectedItemProperty().addListener((observableValue, part, t1) -> {
            selectedCustomer = customersTable.getSelectionModel().getSelectedItem();
        });


        // Queries the database and saves all the customers to an observable list.
        try {
             customers = CustomerQueries.getAllCustomers();
//            refreshCustomersTable();
            System.out.println("Initialize");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        customersTable.setItems(customers);
    }

    // FIXME: 11/14/2022 Broken - details in notes.txt
    public void refreshCustomersTable() throws SQLException {
        customers = CustomerQueries.getAllCustomers();
        customersTable.setItems(customers);
        System.out.println("Table refreshed.");
    }
}
