package com.and3r.octopijavadisplay.datamodels.files;

import java.util.ArrayList;

public class OctoprintFile {

    public String name;
    public String origin;
    public int size;
    public long date;
    public String hash;
    public String type;
    public String path;

    public OctoprintFile[] children;


    public boolean isFolder(){
        return type.toLowerCase().equals("folder");
    }

    public ArrayList<OctoprintFile> getChildrenFilesRecursive() {
        if (isFolder()){
            ArrayList<OctoprintFile> files = new ArrayList<>();
            for (OctoprintFile child: children){
                files.addAll(child.getChildrenFilesRecursive());
            }
            return files;
        }else{
            return null;
        }

    }

}
