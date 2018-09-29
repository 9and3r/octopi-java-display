package com.and3r.octopijavadisplay;

import com.and3r.octopijavadisplay.datamodels.*;
import com.google.gson.Gson;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class OctoprintConnectionManager {

    private final ArrayList<OctoprintStatusListener> octoprintStatusListeners;
    private OctoprintStatus lastStatus;
    private OctoprintStatus currentStatus;

    private int updateTime;

    public final String host;
    public final String port;
    private String apiKey;

    private Gson gson;

    public OctoprintConnectionManager(String host, int port, String apiKey, int updateTime){
        this.host = host;
        this.port = String.valueOf(port);
        this.updateTime = updateTime;
        this.octoprintStatusListeners = new ArrayList<>();
        this.apiKey = apiKey;
        this.gson = new Gson();
        this.lastStatus = new OctoprintStatus();
        this.currentStatus = new OctoprintStatus();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                checkStatus();
            }
        });
        thread.start();
    }


    public void addStatusListener(OctoprintStatusListener listener){
        if (lastStatus != null){
            listener.onOctoprintStatusChanged(lastStatus);
        }
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

                response = makeGetRequest("/api/system/commands");

                // EJEMPLO DE RESPUESTA SACADO DE IMPRESORA CON OCTOPI
                // {  "core": [    {      "action": "shutdown",       "confirm": "<strong>You are about to shutdown the system.</strong></p><p>This action may disrupt any ongoing print jobs (depending on your printer's controller and general setup that might also apply to prints run directly from your printer's internal storage).",       "name": "Shutdown system",       "resource": "http://192.168.1.126/api/system/commands/core/shutdown",       "source": "core"    },     {      "action": "reboot",       "confirm": "<strong>You are about to reboot the system.</strong></p><p>This action may disrupt any ongoing print jobs (depending on your printer's controller and general setup that might also apply to prints run directly from your printer's internal storage).",       "name": "Reboot system",       "resource": "http://192.168.1.126/api/system/commands/core/reboot",       "source": "core"    },     {      "action": "restart",       "confirm": "<strong>You are about to restart the OctoPrint server.</strong></p><p>This action may disrupt any ongoing print jobs (depending on your printer's controller and general setup that might also apply to prints run directly from your printer's internal storage).",       "name": "Restart OctoPrint",       "resource": "http://192.168.1.126/api/system/commands/core/restart",       "source": "core"    },     {      "action": "restart_safe",       "confirm": "<strong>You are about to restart the OctoPrint server in safe mode.</strong></p><p>This action may disrupt any ongoing print jobs (depending on your printer's controller and general setup that might also apply to prints run directly from your printer's internal storage).",       "name": "Restart OctoPrint in safe mode",       "resource": "http://192.168.1.126/api/system/commands/core/restart_safe",       "source": "core"    }  ],   "custom": []}
                response = "{  \"core\": [    {      \"action\": \"shutdown\",       \"confirm\": \"<strong>You are about to shutdown the system.</strong></p><p>This action may disrupt any ongoing print jobs (depending on your printer's controller and general setup that might also apply to prints run directly from your printer's internal storage).\",       \"name\": \"Shutdown system\",       \"resource\": \"http://192.168.1.126/api/system/commands/core/shutdown\",       \"source\": \"core\"    },     {      \"action\": \"reboot\",       \"confirm\": \"<strong>You are about to reboot the system.</strong></p><p>This action may disrupt any ongoing print jobs (depending on your printer's controller and general setup that might also apply to prints run directly from your printer's internal storage).\",       \"name\": \"Reboot system\",       \"resource\": \"http://192.168.1.126/api/system/commands/core/reboot\",       \"source\": \"core\"    },     {      \"action\": \"restart\",       \"confirm\": \"<strong>You are about to restart the OctoPrint server.</strong></p><p>This action may disrupt any ongoing print jobs (depending on your printer's controller and general setup that might also apply to prints run directly from your printer's internal storage).\",       \"name\": \"Restart OctoPrint\",       \"resource\": \"http://192.168.1.126/api/system/commands/core/restart\",       \"source\": \"core\"    },     {      \"action\": \"restart_safe\",       \"confirm\": \"<strong>You are about to restart the OctoPrint server in safe mode.</strong></p><p>This action may disrupt any ongoing print jobs (depending on your printer's controller and general setup that might also apply to prints run directly from your printer's internal storage).\",       \"name\": \"Restart OctoPrint in safe mode\",       \"resource\": \"http://192.168.1.126/api/system/commands/core/restart_safe\",       \"source\": \"core\"    }  ],   \"custom\": []}";

                response = makeGetRequest("/api/files");
                System.out.println(response);
                octoprintStatus.files = gson.fromJson(response, OctoprintFiles.class);

                response = makeGetRequest("/api/printer");

                octoprintStatus.printerStatus = gson.fromJson(response, PrinterStatus.class);

                if (octoprintStatus.isPrinterConnected()){
                    response = makeGetRequest("/api/job");
                    octoprintStatus.jobStatus = gson.fromJson(response, JobStatus.class);
                }

            } catch (StatusCodeException e) {
                if (e.getStatusCode() == 403){
                    octoprintStatus.setStatus(OctoprintStatus.STATUS_OCTOPRINT_NOT_CONNECTED_BAD_KEY);
                }
                e.printStackTrace();
            } catch (IOException e) {
                //e.printStackTrace();
            }

            try{
                System.out.println(octoprintStatus.connection.current.state);
            }catch (Exception e){

            }


            this.lastStatus = octoprintStatus;

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    synchronized (octoprintStatusListeners){
                        for (OctoprintStatusListener listener: octoprintStatusListeners){
                            try{
                                listener.onOctoprintStatusChanged(octoprintStatus);
                            }catch (Exception e){
                                e.printStackTrace();

                            }

                        }
                    }
                }
            });

            try {
                Thread.sleep(updateTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private String makeGetRequest(String path) throws StatusCodeException, IOException {
        return makeRequest(path, false, null);
    }

    public String makePostRequest(String path, String postData) throws StatusCodeException, IOException {
        return makeRequest(path, true, postData);
    }


    private String makeRequest(String path, boolean post, String postData) throws StatusCodeException, IOException {
        URL url = null;
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
            //System.out.println(content.toString());

            return content.toString();

    }

    // Possible commands
    public void homeXY(){
        try {
            makePostRequest("/api/printer/printhead", "{\"axes\":[\"x\",\"y\"],\"command\":\"home\"}");
        } catch (StatusCodeException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Possible commands
    public void homeZ(){
        try {
            makePostRequest("/api/printer/printhead", "{\"axes\":[\"z\"],\"command\":\"home\"}");
        } catch (StatusCodeException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moveHead(String axis, int value){
        String command = "{\"absolute\":false,\"" + axis + "\":" + value + ",\"command\":\"jog\"}";
        try {
            makePostRequest("/api/printer/printhead", command);
        } catch (StatusCodeException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
