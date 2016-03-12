/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmltabtest;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author tomoko
 */
public class FXMLDocumentController implements Initializable {
    
@FXML
    private ImageView imageView;
@FXML
private ImageView videoImageView;
@FXML
private ImageView calcImageView;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       File file = new File("src\\fxmltabtest\\canon72x72.png");
       Image image = new Image(file.toURI().toString());
       System.out.println(file.toURI().toString());
       imageView.setImage(image);
       
      
       image = new Image("file:src/fxmltabtest/movies72x72.png");
        videoImageView.setImage(image);
        
         file = new File("src\\fxmltabtest\\calc72x72.png");
        image = new Image(file.toURI().toString());
        calcImageView.setImage(image);
    }    
    
}
