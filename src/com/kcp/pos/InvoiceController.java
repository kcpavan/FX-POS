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
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;


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
    
    private List<InvoiceDetails> invoiceDetailsList=new ArrayList<InvoiceDetails>();
    private List<Item> itemList=new ArrayList<Item>();
    private Map<String,Item> itemMap=new HashMap<String,Item>();
    
    
     ItemDao itemDao = new ItemDaoImpl();

    public List<Item> getItemList() {
       List<Item> list=itemDao.getAllItems();
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


        System.out.println("itemName.getSelectionModel().getSelectedItem():"+itemName.getSelectionModel().getSelectedItem());
        Object selectedItem = itemName.getSelectionModel().getSelectedItem();
        
        if(selectedItem==null)
        {
             label.setText("Please select item");
        animateMessage();
        fillInvoiceDataTable();
        
        System.out.println("reenter item");
        return;
        }

        String invoiceNum = invoiceNumber.getText();
        
        

        System.out.println("Invoice number:"+invoiceNum);
        
        if (invoiceNum == null || invoiceNum.equalsIgnoreCase("")) {
            invoiceNum = new Integer(invoiceDao.getInvoiceId()).toString();
            invoiceNumber.setText(invoiceNum);
            System.out.println("new invoice number:"+invoiceNum);
        }
        invoiceDetails.setInvoiceIdFk(Integer.parseInt(invoiceNum));


       
        itemDao.getIdByName(selectedItem.toString());

        String itemQty = itemQuantity.getText();
        
         if(KCPUtils.isNullString(itemQty))
        {
             label.setText("Please select item quantity");
        animateMessage();
        fillInvoiceDataTable();
        
        System.out.println("reenter item");
        return;
        }
         System.out.println("itemQuantity:"+itemQty);
        
        invoiceDetails.setInvoiceItemQuantity(Integer.parseInt(itemQty));

        Item item = itemDao.getItemByName(selectedItem.toString());

         System.out.println("selling price:"+item.getSellingPrice());
        double itemTotalPrice = item.getSellingPrice() * Integer.parseInt(itemQty);

        invoiceDetails.setInvoiceItemTotalPrice(itemTotalPrice);

        InvoiceDao invoiceDao = new InvoiceDaoImpl();
        invoiceDao.addInvoiceItem(invoiceDetails,item);



        label.setText("Item Saved");
        animateMessage();
        fillInvoiceDataTable();
        clearForm();
        System.out.println("saved");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        itemName.getItems().removeAll("Item 1", "Item 2", "Item 3", " ");
        List<Item> itemList=itemDao.getAllItems();
        
        for(Item item:itemList)
        {
            itemMap.put(item.getName(),item);
            itemName.getItems().add(item.getItemName());
        }

        
        itemName.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
      @Override public void changed(ObservableValue<? extends String> selected, String oldItem, String newItem) {
       Item item=itemMap.get(newItem);
       itemBarcode.setText(item.getBarcode());
       itemMrp.setText(new Double(item.getMrp()).toString());
       itemWeight.setText(new Double(item.getWeight()).toString());
       //weightUnit.setSelectionModel(item.getWeightUnit());
       billingPrice.setText(new Double(item.getSellingPrice()).toString());
       
       
      
      }
    });
        
        
        weightUnit.getItems().removeAll("Item 1", "Item 2", "Item 3", " ");
        weightUnit.getItems().addAll("choose", "mg", "cg", "dg", "g", "kg");
        dataTable.setItems(dataTableData);
        itemBarcodeCol.setCellValueFactory(
                new PropertyValueFactory<Item, String>("itemBarcode"));
        itemNameCol.setCellValueFactory(
                new PropertyValueFactory<Item, String>("itemName"));
        itemMRP.setCellValueFactory(
                new PropertyValueFactory<Item, Double>("itemMrp"));
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
        List<InvoiceDetails> items = invoiceDao.getInvoiceItems(invoiceNumber.getText());
        System.out.println("items in list" + items.size());
        dataTableData.setAll(items);
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

        double itemTotalPrice = item.getSellingPrice() * Integer.parseInt(itemQty);

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
}
