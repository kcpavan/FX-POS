/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos;

import com.kcp.pos.dao.item.ItemDao;
import com.kcp.pos.dao.item.ItemDaoImpl;
import com.kcp.pos.dao.invoice.InvoiceDao;
import com.kcp.pos.dao.invoice.InvoiceDaoImpl;
import com.kcp.pos.modal.invoice.InvoiceDetails;
import com.kcp.pos.modal.item.Item;
import com.kcp.pos.utils.KCPUtils;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    private Label TotalAmount;
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
    public TableView<InvoiceDetails> dataTable;
    private final ObservableList<InvoiceDetails> dataTableData = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Item, String> itemNameCol;
    @FXML
    private TableColumn<Item, String> itemBarcodeCol;
    @FXML
    private TableColumn<Item, Double> itemMRPCol;
    @FXML
    private TableColumn<Item, Double> itemBillingPriceCol;
    @FXML
    private TableColumn<Item, Double> itemQuantityCol;
    @FXML
    private TableColumn<Item, Double> itemTotalAmountCol;
    private List<InvoiceDetails> invoiceDetailsList = new ArrayList<InvoiceDetails>();
    private List<Item> itemList = new ArrayList<Item>();
    private Map<String, Item> itemMap = new HashMap<String, Item>();
    ItemDao itemDao = new ItemDaoImpl();

    public List<Item> getItemList() {
        List<Item> list = itemDao.getAllItems();
        setItemList(list);
        return list;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public List<InvoiceDetails> getInvoiceDetailsList() {
        return invoiceDetailsList;
    }

    public void setInvoiceDetailsList(List<InvoiceDetails> invoiceDetailsList) {
        this.invoiceDetailsList = invoiceDetailsList;
    }
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


        System.out.println("itemName.getSelectionModel().getSelectedItem():" + itemName.getSelectionModel().getSelectedItem());
        Object selectedItem = itemName.getSelectionModel().getSelectedItem();

        if (selectedItem == null) {
            label.setText("Please select item");
            animateMessage();
            fillInvoiceDataTable();

            System.out.println("reenter item");
            return;
        }

        String invoiceNum = invoiceNumber.getText();



        System.out.println("Invoice number:" + invoiceNum);

        if (invoiceNum == null || invoiceNum.equalsIgnoreCase("")) {
            invoiceNum = new Integer(invoiceDao.getInvoiceId()).toString();
            invoiceNumber.setText(invoiceNum);
            System.out.println("new invoice number:" + invoiceNum);
        }
        invoiceDetails.setInvoiceIdFk(Integer.parseInt(invoiceNum));



        //int id=itemDao.getIdByName(selectedItem.toString());
        Item item = itemDao.getItemByName(selectedItem.toString());


        String itemQty = itemQuantity.getText();


        if (KCPUtils.isNullString(itemQty)) {
            label.setText("Please select item quantity");
            animateMessage();
            fillInvoiceDataTable();

            System.out.println("reenter item");
            return;
        }
        System.out.println("itemQuantity:" + itemQty);

        invoiceDetails.setInvoiceItemQuantity(Integer.parseInt(itemQty));



        System.out.println("selling price:" + item.getBillingPrice());
        double itemTotalPrice = item.getBillingPrice() * Integer.parseInt(itemQty);

        invoiceDetails.setInvoiceItemTotalPrice(itemTotalPrice);

        InvoiceDao invoiceDao = new InvoiceDaoImpl();
        invoiceDao.addInvoiceItem(invoiceDetails, item);



        label.setText("Item Saved");
        animateMessage();
        fillInvoiceDataTable();
        clearForm();
        System.out.println("saved");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        itemName.getItems().removeAll("Item 1", "Item 2", "Item 3", " ");
        List<Item> itemList = itemDao.getAllItems();

        for (Item item : itemList) {
            itemMap.put(item.getName(), item);
            itemName.getItems().add(item.getItemName());
        }
        System.out.println("item list size:" + itemList.size());

        itemName.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> selected, String oldItem, String newItem) {
                Item item = itemMap.get(newItem);


                itemBarcode.setText(item.getBarcode());
                System.out.println("MRP:" + item.getMrp());
                itemMrp.setText(new Double(item.getMrp()).toString());
                itemWeight.setText(new Double(item.getWeight()).toString());
                //weightUnit.setSelectionModel(item.getWeightUnit());
                billingPrice.setText(new Double(item.getBillingPrice()).toString());



            }
        });


        dataTable.setItems(dataTableData);
        itemBarcodeCol.setCellValueFactory(
                new PropertyValueFactory<Item, String>("itemBarcode"));
        itemNameCol.setCellValueFactory(
                new PropertyValueFactory<Item, String>("itemName"));
        itemMRPCol.setCellValueFactory(
                new PropertyValueFactory<Item, Double>("itemMrp"));
        /*
         * public SimpleDoubleProperty itemWeight = new SimpleDoubleProperty();
    
         public SimpleStringProperty itemWeightUnit = new SimpleStringProperty();
    
         public SimpleDoubleProperty itemBillingPrice = new SimpleDoubleProperty();
    
         public SimpleDoubleProperty itemActualPrice = new SimpleDoubleProperty();
    
         public SimpleBooleanProperty itemHasFree = new SimpleBooleanProperty();
         */




        itemBillingPriceCol.setCellValueFactory(
                new PropertyValueFactory<Item, Double>("itemBillingPrice"));

        itemQuantityCol.setCellValueFactory(
                new PropertyValueFactory<Item, Double>("itemQuantity"));

        itemTotalAmountCol.setCellValueFactory(
                new PropertyValueFactory<Item, Double>("itemTotalAmount"));







        fillInvoiceDataTable();
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

    private void fillInvoiceDataTable() {
        ItemDao itemDao = new ItemDaoImpl();
        invoiceNumber.getText();
        invoiceDetailsList = invoiceDao.getInvoiceItems(invoiceNumber.getText());
        //List<Item> items=itemDao.getItemListByInvoiceId(invoiceDetailsList);
        System.out.println("invoice items list:" + invoiceDetailsList.size());
        getInvoiceTotalAmount(invoiceDetailsList);
        dataTableData.setAll(invoiceDetailsList);
    }

    public void getInvoiceTotalAmount(List<InvoiceDetails> invoiceDetailsList) {
        Double amount = 0.0;

        for (InvoiceDetails data : invoiceDetailsList) {
            amount = amount + data.getItemTotalAmount();
        }

        TotalAmount.setText(amount.toString());


    }

    public void saveAllInvoiceItems() {
        InvoiceDetails invoiceDetails = new InvoiceDetails();

        invoiceDao.saveInvoice(getInvoiceDetailsList());

        String invoiceNum = invoiceNumber.getText();

        if (invoiceNum == null) {
            /*Tring to save empty invoice*/
            invoiceNum = new Integer(invoiceDao.getInvoiceId()).toString();
        }

        invoiceDao.getInvoiceById();
        invoiceDetails.setInvoiceIdFk(Integer.parseInt(invoiceNum));


        ItemDao itemDao = new ItemDaoImpl();
        // itemDao.getIdByName(selectedItem.toString());

        String itemQty = itemQuantity.getText();
        invoiceDetails.setInvoiceItemQuantity(Integer.parseInt(itemQty));
        Object selectedItem = itemName.getSelectionModel().getSelectedItem();
        Item item = itemDao.getItemByName(selectedItem.toString());

        double itemTotalPrice = item.getBillingPrice() * Integer.parseInt(itemQty);

        invoiceDetails.setInvoiceItemTotalPrice(itemTotalPrice);

        InvoiceDao invoiceDao = new InvoiceDaoImpl();
        //invoiceDao.addInvoiceItem(invoiceDetails);

        //invoiceDao.saveInvoice(invoiceDetails);



        label.setText("Item Saved");
        animateMessage();
        fillInvoiceDataTable();
        clearForm();
        System.out.println("saved");
    }
    
    public void saveInvoice()
    {
        invoiceDao.saveInvoice(invoiceDetailsList);
        

    }
    
    public void deleteInvoiceItem()
    {
        
    
        //invoiceDao.deleteInvoiceItem(invoiceId, itemId);
       invoiceDao.deleteInvoiceItem(1,1);
    }
}