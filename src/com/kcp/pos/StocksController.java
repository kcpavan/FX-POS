/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos;

import com.kcp.pos.dao.invoice.InvoiceDao;
import com.kcp.pos.dao.invoice.InvoiceDaoImpl;
import com.kcp.pos.dao.item.ItemDao;
import com.kcp.pos.dao.item.ItemDaoImpl;
import com.kcp.pos.dao.stocks.StocksDao;
import com.kcp.pos.dao.stocks.StocksDaoImpl;
import com.kcp.pos.modal.item.Item;
import com.kcp.pos.modal.purchase.PurchaseDetails;
import com.kcp.pos.modal.stocks.Stocks;
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
public class StocksController implements Initializable{
    
    @FXML
    private Label label;
    
   
    @FXML
    private ChoiceBox itemName;
    @FXML
    private TextField barcode;
    @FXML
    private TextField mrp;
    @FXML
    
    
    private TextField caseQuantity;
    @FXML
    private TextField unitsQuantity;
    @FXML
    private TextField freeUnits;
    
    @FXML
    public TableView<Stocks> dataTable;
    
    private final ObservableList<Stocks> dataTableData = FXCollections.observableArrayList();
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
    
    private List<Stocks> stocksList = new ArrayList<Stocks>();
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

    public List<Stocks> getStocksList() {
        return stocksList;
    }

    public void setStocksList(List<Stocks> stocksList) {
        this.stocksList = stocksList;
    }

     StocksDao stocksDao = new StocksDaoImpl();
     
    @FXML
    private void handleButtonAction(ActionEvent event) {

        /*PurchaseDetails purchaseDetails = new PurchaseDetails();
       

        Object selectedItem = itemName.getSelectionModel().getSelectedItem();

        if (selectedItem == null) {
            label.setText("Please select item");
            animateMessage();
            fillDataTable();

            System.out.println("reEnter item");
            return;
        }

        String purchaseId = purchaseNumber.getText();

        if (purchaseId == null || purchaseId.equalsIgnoreCase("")) {
            purchaseId = new Integer(stocksDao.getStocksList()).toString();
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

      
        stocksDao.addPurcaseItem(purchaseDetails);
        
      



        label.setText("Item Saved");
        animateMessage();
        fillDataTable();
        clearForm();
        System.out.println("saved");*/
   }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

       


        
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
  

        fillDataTable();
    }

    private void clearForm() {

        barcode.clear();
        mrp.clear();
        
        caseQuantity.clear();
        unitsQuantity.clear();
        freeUnits.clear();
        


    }

    private void animateMessage() {
        FadeTransition ft = new FadeTransition(Duration.millis(1000), label);
        ft.setFromValue(0.0);
        ft.setToValue(1);
        ft.play();
    }

    private void fillDataTable() {
      
       
                    
               
        List<Stocks> stocksList = stocksDao.getStocksList();
        
        
        dataTableData.setAll(stocksList);
                
    }

   

 
    
}

