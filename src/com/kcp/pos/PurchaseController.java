/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos;

import com.kcp.pos.dao.invoice.InvoiceDao;
import com.kcp.pos.dao.invoice.InvoiceDaoImpl;
import com.kcp.pos.dao.item.ItemDao;
import com.kcp.pos.dao.item.ItemDaoImpl;
import com.kcp.pos.dao.purchase.PurchaseDao;
import com.kcp.pos.dao.purchase.PurchaseDaoImpl;
import com.kcp.pos.modal.invoice.InvoiceDetails;
import com.kcp.pos.modal.item.Item;
import com.kcp.pos.modal.purchase.PurchaseDetails;
import com.kcp.pos.utils.KCPUtils;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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
 * @author Prakash
 */
public class PurchaseController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Label TotalAmount;
    @FXML
    private Label purchaseNumber;
    @FXML
    private ChoiceBox itemName;
    @FXML
    private TextField barcode;
    @FXML
    private TextField mrp;
    @FXML
    private TextField basicRate;
    @FXML
    private TextField grossAmount;
    @FXML
    private TextField scheme;
    @FXML
    private TextField caseQuantity;
    @FXML
    private TextField unitsQuantity;
    @FXML
    private TextField freeUnits;
    @FXML
    private TextField CD;
    @FXML
    private TextField taxPercentage;
    @FXML
    private TextField taxAmount;
    @FXML
    private TextField netAmount;
    @FXML
    public TableView<PurchaseDetails> dataTable;
    private final ObservableList<PurchaseDetails> dataTableData = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Item, String> itemNameCol;
    @FXML
    private TableColumn<Item, String> itemBarcodeCol;
    @FXML
    private TableColumn<Item, Double> itemMRPCol;
    @FXML
    private TableColumn<Item, Double> caseQuantityCol;
    @FXML
    private TableColumn<Item, Double> unitsQuantityCol;
    @FXML
    private TableColumn<Item, Double> freeUnitsCol;
    @FXML
    private TableColumn<Item, Double> basicRateCol;
    @FXML
    private TableColumn<Item, Double> grossAmountCol;
    @FXML
    private TableColumn<Item, Double> schemeCol;
    @FXML
    private TableColumn<Item, Double> cDCol;
    @FXML
    private TableColumn<Item, Double> taxPercentageCol;
    @FXML
    private TableColumn<Item, Double> taxCol;
    @FXML
    private TableColumn<Item, Double> netAmountCol;
    private List<PurchaseDetails> purchaseDetails = new ArrayList<PurchaseDetails>();
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
    InvoiceDao invoiceDao = new InvoiceDaoImpl();

    public List<PurchaseDetails> getPurchaseDetails() {
        return purchaseDetails;
    }

    public void setPurchaseDetails(List<PurchaseDetails> purchaseDetails) {
        this.purchaseDetails = purchaseDetails;
    }

     PurchaseDao purchaseDao = new PurchaseDaoImpl();
    @FXML
    private void handleButtonAction(ActionEvent event) {

        PurchaseDetails purchaseDetails = new PurchaseDetails();
       

        Object selectedItem = itemName.getSelectionModel().getSelectedItem();

        if (selectedItem == null) {
            label.setText("Please select item");
            animateMessage();
            fillDataTable();

            System.out.println("reEnter item");
            return;
        }

        String purchaseId = purchaseNumber.getText();
        System.out.println("UI purchase Id:"+purchaseId);

        if (purchaseId == null || KCPUtils.isNullString(purchaseId)|| Integer.parseInt(purchaseId)==0)
        {
            purchaseId = new Integer(purchaseDao.getPurchaseId()).toString();
            purchaseNumber.setText(purchaseId);
            System.out.println("new purchase number:" + purchaseId);
        }

        Item item=itemDao.getItemByName(selectedItem.toString());
        
        
        purchaseDetails.setPurchaseId(Integer.parseInt(purchaseId));
        purchaseDetails.setItemId(item.getItemId());
        purchaseDetails.setMrp(Double.parseDouble(mrp.getText()));
        purchaseDetails.setCaseQuantity(Integer.parseInt(caseQuantity.getText()));
        
          String itemQty = unitsQuantity.getText();
         if (KCPUtils.isNullString(itemQty)) {
            label.setText("Please select item quantity");
            animateMessage();
            fillDataTable();

            System.out.println("reenter item");
            return;
        }
        purchaseDetails.setUnitsQuantity(Integer.parseInt(unitsQuantity.getText()));
        
        
         
        purchaseDetails.setFreeUnits(Integer.parseInt(freeUnits.getText()));

        purchaseDetails.setBasicRate(Double.parseDouble(basicRate.getText()));
        purchaseDetails.setGrossAmount(Double.parseDouble(grossAmount.getText()));
        purchaseDetails.setScheme(Integer.parseInt(scheme.getText()));
        purchaseDetails.setCd(Double.parseDouble(CD.getText()));

        purchaseDetails.setTaxPercentage(Double.parseDouble(taxPercentage.getText()));
        purchaseDetails.setTax(Double.parseDouble(taxAmount.getText()));
        purchaseDetails.setNetAmount(Double.parseDouble(netAmount.getText()));


      


       


       
        System.out.println("itemQuantity:" + itemQty);

      
        purchaseDao.addPurcaseItem(purchaseDetails);
        
      



        label.setText("Item Saved");
        animateMessage();
        fillDataTable();
        clearForm();
        System.out.println("saved");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("initialize() start");
        itemName.getItems().removeAll("Item 1", "Item 2", "Item 3", " ");
        List<Item> itemList = itemDao.getAllItems();

        for (Item item : itemList) {
            itemMap.put(item.getName(), item);
            itemName.getItems().add(item.getItemName());
            System.out.println("name:"+item.getItemName());
        }


        itemName.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> selected, String oldItem, String newItem) {
                Item item = itemMap.get(newItem);
                



            }
        });


        
        dataTable.setItems(dataTableData);
   
    
  
  
 
        itemNameCol.setCellValueFactory(
                new PropertyValueFactory<Item, String>("itemName"));
         itemBarcodeCol.setCellValueFactory(
                new PropertyValueFactory<Item, String>("itemBarcode"));
        itemMRPCol.setCellValueFactory(
                new PropertyValueFactory<Item, Double>("itemMrp"));
        caseQuantityCol.setCellValueFactory(
                new PropertyValueFactory<Item, Double>("itemCaseQuantity"));
        unitsQuantityCol.setCellValueFactory(
                new PropertyValueFactory<Item, Double>("itemUnitsQuantity"));
        freeUnitsCol.setCellValueFactory(
                new PropertyValueFactory<Item, Double>("itemFreeUnits"));
        basicRateCol.setCellValueFactory(
                new PropertyValueFactory<Item, Double>("itemBasicPrice"));
        grossAmountCol.setCellValueFactory(
                new PropertyValueFactory<Item, Double>("itemGrossAmount"));
        schemeCol.setCellValueFactory(
                new PropertyValueFactory<Item, Double>("purchaseScheme"));
        cDCol.setCellValueFactory(
                new PropertyValueFactory<Item, Double>("purchaseCd"));
        taxPercentageCol.setCellValueFactory(
                new PropertyValueFactory<Item, Double>("purchaseTaxPercentage"));
        taxCol.setCellValueFactory(
                new PropertyValueFactory<Item, Double>("purchaseTax"));
        netAmountCol.setCellValueFactory(
                new PropertyValueFactory<Item, Double>("purchaseNetAmount"));
       


        fillDataTable();
    }

    private void clearForm() {

        barcode.clear();
        mrp.clear();
        basicRate.clear();
        grossAmount.clear();
        scheme.clear();
        caseQuantity.clear();
        unitsQuantity.clear();
        freeUnits.clear();
        CD.clear();
        taxPercentage.clear();
        taxAmount.clear();
        netAmount.clear();


    }

    private void animateMessage() {
        FadeTransition ft = new FadeTransition(Duration.millis(1000), label);
        ft.setFromValue(0.0);
        ft.setToValue(1);
        ft.play();
    }

    private void fillDataTable() {
        ItemDao itemDao = new ItemDaoImpl();
        purchaseNumber.getText();
        if(!KCPUtils.isNullString(purchaseNumber.getText()))
                {
                    
               
        List<PurchaseDetails> purchaseDetailsList = purchaseDao.getPurchaseItems(purchaseNumber.getText());
        
        
        dataTableData.setAll(purchaseDetailsList);
                }
    }

    public void getInvoiceTotalAmount(List<InvoiceDetails> invoiceDetailsList) {
        Double amount = 0.0;

        for (InvoiceDetails data : invoiceDetailsList) {
            amount = amount + data.getItemTotalAmount();
        }

        TotalAmount.setText(amount.toString());


    }

   
}
