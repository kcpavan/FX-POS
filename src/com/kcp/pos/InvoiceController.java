/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos;

import com.kcp.pos.dao.item.ItemDao;
import com.kcp.pos.dao.item.ItemDaoImpl;
import com.kcp.pos.dao.item.invoice.InvoiceDao;
import com.kcp.pos.dao.item.invoice.InvoiceDaoImpl;
import com.kcp.pos.modal.InvoiceDetails;
import com.kcp.pos.modal.Item;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

/**
 *
 * @author pbhimanna
 */
public class InvoiceController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Label invoiceNumber;
    @FXML
    private ChoiceBox itemName;
    @FXML
    private TextField itemBarcode;
    @FXML
    private TextField itemMrp;
    @FXML
    private TextField itemWeight;
    @FXML
    private TextField billingPrice;
    @FXML
    private TextField itemQuantity;
    @FXML
    private CheckBox hasGift = new CheckBox();
    @FXML
    private ChoiceBox weightUnit = new ChoiceBox();
    @FXML
    public TableView<Item> dataTable;
    private final ObservableList<Item> dataTableData = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Item, String> itemNameCol;
    @FXML
    private TableColumn<Item, String> itemBarcodeCol;
    @FXML
    private TableColumn<Item, Double> itemMRP;
    @FXML
    private TableColumn<Item, Double> itemBillingPrice;
    @FXML
    private TableColumn<Item, Double> itemQuantityCol;
    @FXML
    private TableColumn<Item, Double> itemTotal;
    InvoiceDao invoiceDao = new InvoiceDaoImpl();

    public Label getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(Label invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {

        InvoiceDetails invoiceDetails = new InvoiceDetails();



        Object selectedItem = itemName.getSelectionModel().getSelectedItem();

        String invoiceNum = invoiceNumber.getText();

        if (invoiceNum == null) {
            invoiceNum = new Integer(invoiceDao.getInvoiceId()).toString();
        }
        invoiceDetails.setInvoiceIdFk(Integer.parseInt(invoiceNum));


        ItemDao itemDao = new ItemDaoImpl();
        itemDao.getIdByName(selectedItem.toString());

        String itemQty = itemQuantity.getText();
        invoiceDetails.setInvoiceItemQuantity(Integer.parseInt(itemQty));

        Item item = itemDao.getItemByName(selectedItem.toString());

        double itemTotalPrice = item.getSellingPrice() * Integer.parseInt(itemQty);

        invoiceDetails.setInvoiceItemTotalPrice(itemTotalPrice);

        InvoiceDao invoiceDao = new InvoiceDaoImpl();
        invoiceDao.addInvoiceItem(invoiceDetails);



        label.setText("Item Saved");
        animateMessage();
        fillDataTable();
        clearForm();
        System.out.println("saved");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        weightUnit.getItems().removeAll("Item 1", "Item 2", "Item 3", " ");
        weightUnit.getItems().addAll("choose", "mg", "cg", "dg", "g", "kg");
        dataTable.setItems(dataTableData);
        itemBarcodeCol.setCellValueFactory(
                new PropertyValueFactory<Item, String>("itemBarcode"));
        itemNameCol.setCellValueFactory(
                new PropertyValueFactory<Item, String>("itemName"));
        itemMRP.setCellValueFactory(
                new PropertyValueFactory<Item, Double>("itemMrp"));
        fillDataTable();
    }

    private void clearForm() {

        itemBarcode.clear();
        itemMrp.clear();
        itemWeight.clear();


    }

    private void animateMessage() {
        FadeTransition ft = new FadeTransition(Duration.millis(1000), label);
        ft.setFromValue(0.0);
        ft.setToValue(1);
        ft.play();
    }

    private void fillDataTable() {
        ItemDao itemDao = new ItemDaoImpl();
        List<Item> items = itemDao.getAllItems();
        System.out.println("items in list" + items.size());
        dataTableData.setAll(items);
    }

    public void saveAllInvoiceItems() {
        InvoiceDetails invoiceDetails = new InvoiceDetails();

       

        String invoiceNum = invoiceNumber.getText();

        if (invoiceNum == null) {
            /*Tring to save empty invoice*/
            invoiceNum = new Integer(invoiceDao.getInvoiceId()).toString();
        }
        
        invoiceDao.getInvoiceById();
        invoiceDetails.setInvoiceIdFk(Integer.parseInt(invoiceNum));


        ItemDao itemDao = new ItemDaoImpl();
        itemDao.getIdByName(selectedItem.toString());

        String itemQty = itemQuantity.getText();
        invoiceDetails.setInvoiceItemQuantity(Integer.parseInt(itemQty));

        Item item = itemDao.getItemByName(selectedItem.toString());

        double itemTotalPrice = item.getSellingPrice() * Integer.parseInt(itemQty);

        invoiceDetails.setInvoiceItemTotalPrice(itemTotalPrice);

        InvoiceDao invoiceDao = new InvoiceDaoImpl();
        invoiceDao.addInvoiceItem(invoiceDetails);



        label.setText("Item Saved");
        animateMessage();
        fillDataTable();
        clearForm();
        System.out.println("saved");
    }
}
