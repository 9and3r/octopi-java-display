package com.and3r.octopijavadisplay.datamodels.files;

import java.util.ArrayList;

public class OctoprintFiles {

    public OctoprintFile[] files;

    private ArrayList<OctoprintFile> allFiles;

    public OctoprintFiles(){
        files = new OctoprintFile[0];
    }

    public boolean contains(OctoprintFile octoprintFile){
        boolean found = false;
        int i = 0;
        while(!found && i<files.length){
            if (octoprintFile.hash.equals(files[i].hash)){
                found = true;
            }else{
                i++;
            }
        }
        return found;
    }

    public ArrayList<OctoprintFile> getAllFilesRecursive() {
        if (allFiles == null){
            // Generate array
            allFiles = new ArrayList<>();
            for (OctoprintFile file: files){
                if (file.isFolder()){
                    allFiles.addAll(file.getChildrenFilesRecursive());
                }else{
                    allFiles.add(file);
                }
            }
        }
        return allFiles;
    }
}
