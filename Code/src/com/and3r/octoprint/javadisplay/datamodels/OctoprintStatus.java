package com.and3r.octoprint.javadisplay.datamodels;

import com.and3r.octoprint.javadisplay.datamodels.actions.OctoprintActionsResponse;
import com.and3r.octoprint.javadisplay.datamodels.files.OctoprintFiles;
import com.and3r.octoprint.javadisplay.datamodels.job.JobStatus;
import com.and3r.octoprint.javadisplay.datamodels.printer.Connection;
import com.and3r.octoprint.javadisplay.datamodels.printer.PrinterStatus;

public class OctoprintStatus {

    public static final int STATUS_OCTOPRINT_NOT_CONNECTED_BAD_KEY = -2;
    public static final int STATUS_OCTOPRINT_NOT_CONNECTED = -1;
    public static final int STATUS_OCTOPRINT_CONNECTED = 0;
    public static final int STATUS_OCTOPRINT_CONNECTED_DETECTING_BAUD_RATE = 1;
    public static final int STATUS_OCTOPRINT_CONNECTED_OPERATIONAL = 2;
    public static final int STATUS_OCTOPRINT_CONNECTED_PRINTING = 3;
    public static final int STATUS_OCTOPRINT_CONNECTED_PAUSED = 4;
    public static final int STATUS_OCTOPRINT_CONNECTED_CANCELLING = 5;

    public boolean connectedToOctopi;
    public Connection connection;
    public PrinterStatus printerStatus;
    public JobStatus jobStatus;
    private int status;
    public OctoprintFiles files;
    public OctoprintActionsResponse actions;


    public OctoprintStatus(){
        this.status = Integer.MIN_VALUE;
    }


    public boolean isPrinterConnected(){
        int currentStatus = getStatus();
        return (currentStatus == STATUS_OCTOPRINT_CONNECTED_OPERATIONAL || currentStatus == STATUS_OCTOPRINT_CONNECTED_PRINTING || currentStatus  == STATUS_OCTOPRINT_CONNECTED_PAUSED || currentStatus == STATUS_OCTOPRINT_CONNECTED_CANCELLING);
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
                    case "printing":
                        status = STATUS_OCTOPRINT_CONNECTED_PRINTING;
                        break;
                    case "paused":
                        status = STATUS_OCTOPRINT_CONNECTED_PAUSED;
                        break;
                    case "canceling":
                        status = STATUS_OCTOPRINT_CONNECTED_CANCELLING;
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
