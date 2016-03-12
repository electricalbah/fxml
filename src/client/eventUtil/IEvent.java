/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.eventUtil;

/**
 *
 * @author mamadu
 */
import java.util.EventListener;

/**
 *
 * @author mamadu
 */
// Listener interface 
//interface extends another, NOT implement
public interface IEvent extends EventListener{
    void onMessage(String message);
}

