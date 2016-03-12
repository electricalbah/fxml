/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmltabtest;

import client.device.WebSocketClientEndpoint;
import client.eventUtil.EventSourceManager;
import client.eventUtil.IEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javax.swing.DefaultListModel;

/**
 * FXML Controller class
 *
 * @author tomoko
 */
public class Test1Controller implements Initializable {
    private WebSocketClientEndpoint clientDevice;
  //  private EventSourceManager eventSourceManager;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.clientDevice = new WebSocketClientEndpoint();
        this.clientDevice.initialize();
//        this.eventSourceManager = new EventSourceManager();
//     
//        this.clientDevice.eventSourceManager(this.eventSourceManager);
       // initJListForThumbs();

//        this.eventSourceManager.addEventListener(new IEvent() {
//            @Override
//            public void onMessage(String item) {
//                 
//            }
//        });
    } 
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
}
