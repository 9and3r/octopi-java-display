package com.and3r.octopijavadisplay;

import com.and3r.octopijavadisplay.datamodels.Connection;
import com.and3r.octopijavadisplay.datamodels.JobStatus;
import com.and3r.octopijavadisplay.datamodels.PrinterStatus;

public class OctoprintStatus {

    public static final int STATUS_OCTOPRINT_NOT_CONNECTED_BAD_KEY = -2;
    public static final int STATUS_OCTOPRINT_NOT_CONNECTED = -1;
    public static final int STATUS_OCTOPRINT_CONNECTED = 0;
    public static final int STATUS_OCTOPRINT_CONNECTED_DETECTING_BAUD_RATE = 1;
    public static final int STATUS_OCTOPRINT_CONNECTED_OPERATIONAL = 2;

    public boolean connectedToOctopi;
    public Connection connection;
    public PrinterStatus printerStatus;
    public JobStatus jobStatus;
    private int status;


    public OctoprintStatus(){
        this.status = Integer.MIN_VALUE;
    }

    public boolean isPrinterConnected(){
        int currentStatus = getStatus();
        return (currentStatus == STATUS_OCTOPRINT_CONNECTED_OPERATIONAL);
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus(){
        if (this.status == Integer.MIN_VALUE){
            if (!connectedToOctopi) {
                status = STATUS_OCTOPRINT_NOT_CONNECTED;
            }else if (connection != null && connection.current != null && connection.current.state != null){
                switch (connection.current.state.toLowerCase()){
                    case "closed":
                        status = STATUS_OCTOPRINT_CONNECTED;
                        break;
                    case "detecting baudrate":
                        status = STATUS_OCTOPRINT_CONNECTED_DETECTING_BAUD_RATE;
                        break;
                    case "operational":
                        status = STATUS_OCTOPRINT_CONNECTED_OPERATIONAL;
                        break;
                    default:
                        status = STATUS_OCTOPRINT_CONNECTED;
                        break;
                }
            }else{
                status = STATUS_OCTOPRINT_CONNECTED;
            }
        }
        return status;
    }




}
