package com.and3r.octopijavadisplay;

import com.and3r.octopijavadisplay.datamodels.Connection;
import com.and3r.octopijavadisplay.datamodels.PrinterStatus;
import com.google.gson.Gson;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class OctoprintConnectionManager {

    private final ArrayList<OctoprintStatusListener> octoprintStatusListeners;
    private OctoprintStatus lastStatus;

    private String host;
    private String port;
    private String apiKey;

    private Gson gson;

    public OctoprintConnectionManager(String host, int port, String apiKey){
        this.host = host;
        this.port = String.valueOf(port);
        this.octoprintStatusListeners = new ArrayList<>();
        this.apiKey = apiKey;
        this.gson = new Gson();
        this.lastStatus = new OctoprintStatus();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                checkStatus();
            }
        });
        thread.start();
    }

    public void addStatusListener(OctoprintStatusListener listener){
        listener.onOctoprintStatusChanged(lastStatus);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (octoprintStatusListeners){
                    octoprintStatusListeners.add(listener);
                }
            }
        });
        thread.start();
    }

    /*
    public void removeStatusListener(OctoprintStatusListener listener){
        octoprintStatusListeners.remove(listener);
    }
    */


    private void checkStatus(){
        while (true){
            OctoprintStatus octoprintStatus = new OctoprintStatus();
            try {
                String response = makeGetRequest("/api/connection");
                octoprintStatus.connection = gson.fromJson(response, Connection.class);
                octoprintStatus.connectedToOctopi = true;

                response = makeGetRequest("/api/printer");
                octoprintStatus.printerStatus = gson.fromJson(response, PrinterStatus.class);

            } catch (StatusCodeException e) {
                e.printStackTrace();
            }

            this.lastStatus = octoprintStatus;

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    synchronized (octoprintStatusListeners){
                        for (OctoprintStatusListener listener: octoprintStatusListeners){
                            listener.onOctoprintStatusChanged(octoprintStatus);
                        }
                    }
                }
            });

            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String makeGetRequest(String path) throws StatusCodeException{
        return makeRequest(path, false, null);
    }

    public String makePostRequest(String path, String postData) throws StatusCodeException {
        return makeRequest(path, true, postData);
    }


    private String makeRequest(String path, boolean post, String postData) throws StatusCodeException {
        URL url = null;
        try {
            url = new URL("http://" + host + ":" + port + path);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.setRequestMethod("GET");
            con.setDoOutput(post);
            con.setDoInput(true);
            con.setRequestProperty("X-Api-Key", apiKey);

            if (post){
                byte[] out = postData.getBytes(StandardCharsets.UTF_8);
                int length = out.length;

                //con.setFixedLengthStreamingMode(length);
                con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                con.connect();
                try(OutputStream os = con.getOutputStream()) {
                    os.write(out);
                    os.flush();
                    os.close();
                }
            }

            int statusCode = con.getResponseCode();

            if (statusCode < 200 || statusCode >= 300){
                throw new StatusCodeException(statusCode);
            }

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            System.out.println(content.toString());


            return content.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Possible commands
    public void homeXY(){
        try {
            makePostRequest("/api/printer/printhead", "{\"axes\":[\"x\",\"y\"],\"command\":\"home\"}");
        } catch (StatusCodeException e) {
            e.printStackTrace();
        }
    }

    // Possible commands
    public void homeZ(){
        try {
            makePostRequest("/api/printer/printhead", "{\"axes\":[\"z\"],\"command\":\"home\"}");
        } catch (StatusCodeException e) {
            e.printStackTrace();
        }
    }

    public void moveHead(String axis, int value){
        String command = "{\"absolute\":false,\"" + axis + "\":" + value + ",\"command\":\"jog\"}";
        try {
            makePostRequest("/api/printer/printhead", command);
        } catch (StatusCodeException e) {
            e.printStackTrace();
        }

    }

}
