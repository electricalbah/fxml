/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.eventUtil;

import javax.swing.event.EventListenerList;

/**
 *
 * @author mamadu
 */
//toDo java Interface naming
public class EventSourceManager {
  protected EventListenerList listenerList = new EventListenerList();

  public void addEventListener(IEvent listener) {
    this.listenerList.add(IEvent.class, listener);
  }
  public void removeEventListener(IEvent listener) {
    this.listenerList.remove(IEvent.class, listener);
  }
  public void fireOnMessage(String message) {
    Object[] listeners = this.listenerList.getListenerList();
    for (int i = 0; i < listeners.length; i = i+2) {
      if (listeners[i] == IEvent.class) {
        ((IEvent) listeners[i+1]).onMessage(message);
      }
    }
  }

    public void addMyEventListener() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
