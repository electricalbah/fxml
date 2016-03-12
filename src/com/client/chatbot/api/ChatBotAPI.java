/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.client.chatbot.api;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author mamadu
 */
public class ChatBotAPI {
//apiKey: FHchqTaC72zkjKYV
//apiSecret: GOTEB8kY3K2PpXwgIUguVIjsxWnnJK6q

    public ChatBotAPI() {
    }

    /**
     * http://www.personalityforge.com/api/chat/?apiKey=<apiKey>&hash=<hash>&message=<message>
     *
     * @param payload
     * @return
     */
    public String sendReceiveMessage(String payload) {
        String output = "";
        String JSonPayload = jsonPayloadConverter(payload);
        URL url = createGetURL(JSonPayload);
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                output = readResponseToString(conn);
            } else {
                output = "Error response from e-NAVI chatbot: " + conn.getResponseMessage();
            }

        } catch (IOException ex) {
            Logger.getLogger(ChatBotAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jsonMessageExtractor(output);
    }

    private URL createGetURL(String JSonPayload) {
        String baseURL = "http://www.personalityforge.com/api/chat/?";
        String apiKey = "FHchqTaC72zkjKYV";
        String hash = hmacSHA256(JSonPayload, "GOTEB8kY3K2PpXwgIUguVIjsxWnnJK6q");
        String UrlEncodedJson = JSonPayloadUrlEncode(JSonPayload);
        String urlStr = baseURL + "apiKey=" + apiKey + "&hash=" + hash.toLowerCase() + "&message=" + UrlEncodedJson;
        URL url = null;
        try {
            url = new URL(urlStr);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ChatBotAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return url;
    }

    /**
     * Encode the Message using the apiSecret by HMac and SHA256
     *
     * @param payLoad
     * @param key
     * @return 2f9cc19664d598ceda4d5d15c4da96f0caef90668d10328b59dbbd8a8ee40b0a
     */
    //todo+ handle null
    private String hmacSHA256(String JSonPayload, String key) {
        String hash = null;
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");

            Mac mac = Mac.getInstance("HmacSHA256"); //Mac mac = Mac.getInstance("HmacMD5");
            mac.init(secretKey);

            mac.update(JSonPayload.getBytes("UTF-8")); //byte[] bytes = mac.doFinal(payload.getBytes("UTF-8"));
            byte[] hmacData = mac.doFinal();

            //Bytes to Hex String
            //String hash = Base64.encodeBase64String(hmacData);
            hash = DatatypeConverter.printHexBinary(hmacData);

        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeyException ex) {
            Logger.getLogger(ChatBotAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("### JSonPayload" + JSonPayload);
        System.out.println("### hash" + hash);
        return hash;
    }

    //todo handle null
    private String JSonPayloadUrlEncode(String JSonPayload) {
        String UrlEncodedJson = null;
        try {
            UrlEncodedJson = URLEncoder.encode(JSonPayload, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ChatBotAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return UrlEncodedJson;
    }

//http://www.personalityforge.com/bookofapi.php
//  "message": {
//    "message": "How are you doing today?",
//    "chatBotID": 6,
//    "timestamp": 1352853648
//  },
//  "user": {
//    "firstName": "Tugger",
//    "lastName": "Sufani",
//    "gender": "m",
//    "externalID": "abc-639184572"
//  }
//}
    public String jsonPayloadConverter(String payload) {
        JsonObjectBuilder personObjectBuilder = Json.createObjectBuilder();
        JsonObject personObject = personObjectBuilder
                .add("message",
                        Json.createObjectBuilder().add("message", payload)
                        .add("chatBotID", "6")
                        .add("timestamp", System.currentTimeMillis() / 1000L)
                        .build()
                )
                .add("user",
                        Json.createObjectBuilder().add("firstName", "VisiorFirst")
                        .add("lastName", "VisiorLast")
                        .add("gender", "m")
                        .add("externalID", "abc-639184572")
                        .build()
                )
                .build();

        return personObject.toString();
    }

    public String jsonMessageExtractor(String jsonMessage) {
        JsonObject jsonObject = Json.createReader(new StringReader(jsonMessage)).readObject();
        String output = "";
        int success = jsonObject.getInt("success");
        if (success == 1) {
            JsonObject jsonMessageObj = jsonObject.getJsonObject("message");
            output = jsonMessageObj.getString("message");
        } else {
            output = "e-NAVI Customer service is temporarily out of service";
        }

        return output;
    }

    //todo: sanitize
    private String readResponseToString(HttpURLConnection connection) {
        String result = null;
        StringBuilder sb = new StringBuilder();
        InputStream is = null;

        try {
            is = new BufferedInputStream(connection.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String inputLine = "";
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
            result = sb.toString();
        } catch (Exception e) {
            result = null;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }

        return result;
    }

}
