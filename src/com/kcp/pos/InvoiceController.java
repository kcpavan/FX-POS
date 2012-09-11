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
public class InvoiceController implements Initializable{
    
    @FXML private Label label;
    
     @FXML private TextField invoiceNumber;
    
    @FXML private TextField itemName;
     
    @FXML private TextField itemBarcode;
    
    @FXML private TextField itemMrp;
    
    @FXML private TextField itemWeight;
    
    @FXML private TextField actualPrice;
     
    @FXML private TextField sellingPrice;
    
    @FXML private CheckBox hasGift = new CheckBox();
    
    @FXML private ChoiceBox weightUnit = new ChoiceBox();
    
    @FXML public TableView<Item> dataTable;
  
    private final ObservableList<Item> dataTableData = FXCollections.observableArrayList();
    
    @FXML private TableColumn<Item, String> itemNameCol;
    @FXML private TableColumn<Item, String> itemBarcodeCol;
    @FXML private TableColumn<Item, Double> itemMRP;
    @FXML private TableColumn<Item, Double> itemBillingPrice;
    @FXML private TableColumn<Item, Double> itemQuantity;
    @FXML private TableColumn<Item, Double> itemTotal;
    
    
    InvoiceDao invoiceDao = new InvoiceDaoImpl();
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        InvoiceDetails invoiceDetails= new InvoiceDetails();
        
        if(getInvoiceNumber()==null)
        {
            setInvoiceNumber(invoiceDao.getInvoiceNumber());
        }
        
        Item item = new Item();
        item.setName(itemName.getText());
        item.setBarcode(itemBarcode.getText());
        item.setMrp(Double.valueOf(itemMrp.getText()));
        item.setWeight(Double.valueOf(itemWeight.getText()));
        item.setActualPrice(Double.valueOf(actualPrice.getText()));
        item.setSellingPrice(Double.valueOf(sellingPrice.getText()));
        System.out.println("hasGift.getText()"+hasGift);
        item.setHasGift(hasGift.isSelected());
        item.setWeightUnit((String)weightUnit.getSelectionModel().getSelectedItem());
        
        itemDao.saveItems(item);
        label.setText("Item Saved");
        animateMessage();
        fillDataTable();
        clearForm();
        System.out.println("saved");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         weightUnit.getItems().removeAll("Item 1","Item 2","Item 3"," ");
         weightUnit.getItems().addAll("choose", "mg", "cg","dg","g","kg");
         dataTable.setItems(dataTableData);
         itemBarcodeCol.setCellValueFactory(
                new PropertyValueFactory<Item, String>("itemBarcode"));
         itemNameCol.setCellValueFactory(
                new PropertyValueFactory<Item, String>("itemName"));
          itemMRP.setCellValueFactory(
                new PropertyValueFactory<Item, Double>("itemMrp"));
         fillDataTable();
    }   
    
    private void clearForm(){
         itemName.clear();
        itemBarcode.clear();
        itemMrp.clear();
        itemWeight.clear();
        actualPrice.clear();
        sellingPrice.clear();
    
    }
    private void animateMessage() {
        FadeTransition ft = new FadeTransition(Duration.millis(1000), label);
        ft.setFromValue(0.0);
        ft.setToValue(1);
        ft.play();
    }
    
    private void fillDataTable(){
       List<Item> items = itemDao.getAllItems();
       System.out.println("items in list"+items.size());
       dataTableData.setAll(items);
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public TextField getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(TextField invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public TextField getItemName() {
        return itemName;
    }

    public void setItemName(TextField itemName) {
        this.itemName = itemName;
    }

    public TextField getItemBarcode() {
        return itemBarcode;
    }

    public void setItemBarcode(TextField itemBarcode) {
        this.itemBarcode = itemBarcode;
    }

    public TextField getItemMrp() {
        return itemMrp;
    }

    public void setItemMrp(TextField itemMrp) {
        this.itemMrp = itemMrp;
    }

    public TextField getItemWeight() {
        return itemWeight;
    }

    public void setItemWeight(TextField itemWeight) {
        this.itemWeight = itemWeight;
    }

    public TextField getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(TextField actualPrice) {
        this.actualPrice = actualPrice;
    }

    public TextField getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(TextField sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public CheckBox getHasGift() {
        return hasGift;
    }

    public void setHasGift(CheckBox hasGift) {
        this.hasGift = hasGift;
    }

    public ChoiceBox getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(ChoiceBox weightUnit) {
        this.weightUnit = weightUnit;
    }

    public TableView<Item> getDataTable() {
        return dataTable;
    }

    public void setDataTable(TableView<Item> dataTable) {
        this.dataTable = dataTable;
    }

    public TableColumn<Item, String> getItemNameCol() {
        return itemNameCol;
    }

    public void setItemNameCol(TableColumn<Item, String> itemNameCol) {
        this.itemNameCol = itemNameCol;
    }

    public TableColumn<Item, String> getItemBarcodeCol() {
        return itemBarcodeCol;
    }

    public void setItemBarcodeCol(TableColumn<Item, String> itemBarcodeCol) {
        this.itemBarcodeCol = itemBarcodeCol;
    }

    public TableColumn<Item, Double> getItemMRP() {
        return itemMRP;
    }

    public void setItemMRP(TableColumn<Item, Double> itemMRP) {
        this.itemMRP = itemMRP;
    }

    public TableColumn<Item, Double> getItemBillingPrice() {
        return itemBillingPrice;
    }

    public void setItemBillingPrice(TableColumn<Item, Double> itemBillingPrice) {
        this.itemBillingPrice = itemBillingPrice;
    }

    public TableColumn<Item, Double> getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(TableColumn<Item, Double> itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public TableColumn<Item, Double> getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(TableColumn<Item, Double> itemTotal) {
        this.itemTotal = itemTotal;
    }

    public InvoiceDao getInvoiceDao() {
        return invoiceDao;
    }

    public void setInvoiceDao(InvoiceDao invoiceDao) {
        this.invoiceDao = invoiceDao;
    }
    
   
}
