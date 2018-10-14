package com.and3r.octopijavadisplay.datamodels.actions;

public class OctoprintAction {

    public String action;
    public String confirm;
    public String name;
    public String resource;
    public String source;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof OctoprintAction){
            return action.equals(((OctoprintAction) obj).action);
        }else{
            return super.equals(obj);
        }
    }
}
