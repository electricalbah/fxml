/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.websocket.server.dtos;

import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author mamadu
 */
public class Thumb {

    public Thumb(String title, Image thumbImg) {
        this.title = title;
        this.thumbImg = thumbImg;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Image getThumbImg() {
        return thumbImg;
    }

    public void setThumbImg(BufferedImage thumbImg) {
        this.thumbImg = thumbImg;
    }
    private String title;
    private Image thumbImg;
    
}
