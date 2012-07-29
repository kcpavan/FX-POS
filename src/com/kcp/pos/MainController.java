/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
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
    private CheckBox hasGift;
     
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
