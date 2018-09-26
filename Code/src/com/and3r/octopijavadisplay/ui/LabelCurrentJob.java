package com.and3r.octopijavadisplay.ui;

import javax.swing.*;
import java.awt.*;

public class LabelCurrentJob extends JLabel {

    public LabelCurrentJob(String text){
        super(text);
        init();
    }

    public LabelCurrentJob(String text, int align){
        super(text, align);
        init();
    }

    private void init(){
        setForeground(Color.WHITE);
        setFont(new Font("Arial", Font.PLAIN, 23));
    }

}
