/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.device;

import client.eventUtil.EventSourceManager;
import com.client.chatbot.api.ChatBotAPI;
import com.websocket.server.commons.MessageDecoder;
import com.websocket.server.commons.MessageEncoder;
import com.websocket.server.dtos.Message;
import com.websocket.server.dtos.SenderInfo;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

/**
 * Sample client endpoint.
 *
 * @author mamadu.bah
 */
@ClientEndpoint(
        encoders = {MessageEncoder.class},
        decoders = {MessageDecoder.class}
)
public class WebSocketClientEndpoint {
       private final String NAME = "name";
     private final String DEVICE = "device";
    private final String SESSION_TOKEN = "sessionToken";

   private Session session;
    private int hash;
      ChatBotAPI bot = new ChatBotAPI();
    private MessageHandler messageHandler;
    /**
     * Main.
     *
     */
    public void initialize(){
        
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        try {                                                                       //ws://localhost:8080/chat
            this.session = container.connectToServer(WebSocketClientEndpoint.class, //ws://localhost:8080/movieplex7/websocket
                  URI.create(createWebSocketURL()));  // URI.create("ws://localhost:8080/web-socket-example-server/sample"))
           
        } catch (DeploymentException | IOException ex) {
            //Logger.getLogger(WebSocketClientEndpoint.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("##### SERVER APPEARS TO BE UNREACHABLE");
        }

    }
    
    private String createWebSocketURL(){
        this.hash = this.hashCode();
        String endPointURL = "ws://localhost:8080/chat"; //todo: use ipAddress
        String nameParam = "?name=e-NAVI_Customer_Centre";
        String platformParam = "&device=" + "JAVAFX";
        String sessionToken = "&" + SESSION_TOKEN + this.hash;

       return endPointURL + nameParam + platformParam +sessionToken;
    }
    
    @OnOpen
    public void onOpen(Session senderSession) {
             System.out.println("############################# OPEN ");
    }
    
//    private static EventSourceManager eventSourceManager;
//    public void eventSourceManager(EventSourceManager eventSourceManager){
//     WebSocketClientEndpoint.eventSourceManager = eventSourceManager;
//     System.out.println("INITIALIZED: " + eventSourceManager);
  //  }

    /**
     * On message.
     *
     * @param session
     * @param message
     */
    @OnMessage
    public void onMessage(Session serverSession, Message  message) {
        System.out.println("############################# Message received " + message.getMessageType());
        
        //                if (this.messageHandler != null)
//            this.messageHandler.handleMessage(message);
        


        
                Objects.requireNonNull(message, "Cannot be null");

        switch (message.getMessageType()) {
            case MESSAGE:
              String output = bot.sendReceiveMessage(message.getMessage());
              send(message, serverSession, output, true);
                break;
            case ACKNOWLEDGED:
      
              //List<Session> sessions =  getAllSocketSessionsFromHttpSessionToken(senderSession.getOpenSessions(),
                     // message.getSenderInfo().getSessionToken());
             // sendBroadCast(message, sessions, senderSession);
                break;
            case BROADCAST:
             
                break;
               case GROUP:
                //sendGroupBroadCast(message, senderSession);
                break;
            case RESET:

            default:
              //  throw new IllegalArgumentException(message.getMessageType().getIndex() + ": Not allowed");

   }
    }

        @OnClose
    public void onClose(Session senderSession) {
         System.out.println("############################# CLOSE");
    }

     private void setChatBotInfo(Session serverSession, Message message, String chatBotResposne) {
     message.setMessageType(Message.MessageType.MESSAGE);
     message.setMessage(chatBotResposne);
     message.setReceiverToken(message.getSenderInfo().getSessionToken());
     message.setSenderInfo(
                new SenderInfo("e-NAVI CUSTOMER CENTER",
                          Integer.toString(hash),
                        "JAVAFX") {
                }
                 );
    }
 
    
       private void send(Message message, Session serverSession, String chatBotResposne, boolean setChatBotInfo) {
        try {
            if (serverSession.isOpen()) {
                if (setChatBotInfo) {
                    setChatBotInfo(serverSession, message, chatBotResposne);
                }
                serverSession.getBasicRemote().sendObject(message);
            }
        } catch (IOException | EncodeException ex) {
            Logger.getLogger(WebSocketClientEndpoint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       

    
       /**
     * register message handler
     * 
     * @param message
     */
    public void addMessageHandler(MessageHandler msgHandler) {
        this.messageHandler = msgHandler;
    }
    
     /**
     * Message handler for this class
     * 
     */
    public static interface MessageHandler {
       // public void handleMessage(Messsage message);
    }

}
