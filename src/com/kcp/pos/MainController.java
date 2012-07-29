/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos;

import com.kcp.pos.dao.item.ItemDao;
import com.kcp.pos.dao.item.ItemDaoImpl;
import com.kcp.pos.modal.Item;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author pavankumar
 */
public class MainController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private TextField itemName;
     
    @FXML
    private TextField itemBarcode;
    
    @FXML
    private TextField itemMrp;
    
    @FXML
    private TextField itemWeight;
    
    @FXML
    private TextField actualPrice;
     
    @FXML
    private TextField sellingPrice;
    
    @FXML
    private CheckBox hasGift = new CheckBox();
    
    @FXML
    private ChoiceBox weightUnit = new ChoiceBox();
    
  
     
    @FXML
    private void handleButtonAction(ActionEvent event) {
        Item item = new Item();
        item.setName(itemName.getText());
        item.setBarcode(itemBarcode.getText());
        item.setMrp(Double.valueOf(itemMrp.getText()));
        item.setWeight(Double.valueOf(itemWeight.getText()));
        item.setActualPrice(Double.valueOf(actualPrice.getText()));
        item.setSellingPrice(Double.valueOf(sellingPrice.getText()));
        System.out.println("hasGift.getText()"+hasGift);
        //item.setHasGift(hasGift.getText());
        item.setWeightUnit((String)weightUnit.getSelectionModel().getSelectedItem());
        ItemDao itemDao = new ItemDaoImpl();
        itemDao.saveItems(item);
        label.setText("Item Saved");
        System.out.println("saved");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         weightUnit.getItems().removeAll("Item 1","Item 2","Item 3"," ");
         weightUnit.getItems().addAll("choose", "mg", "cg","dg","g","kg");
    }   
    
    private void clearForm(){
    
    }
    
}
