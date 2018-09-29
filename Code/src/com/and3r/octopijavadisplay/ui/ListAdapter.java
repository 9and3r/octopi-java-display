package com.and3r.octopijavadisplay.ui;

import com.and3r.octopijavadisplay.datamodels.OctoprintFile;
import com.and3r.octopijavadisplay.datamodels.OctoprintFiles;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class ListAdapter<T> {


    private JPanel mainPanel;
    private ArrayList<ViewItemHolder<T>> items;
    private Class<T> type;

    public ListAdapter(JPanel mainPanel, Class<T> type){
        this.mainPanel = mainPanel;
        this.type = type;
        items = new ArrayList<>();
    }

    public boolean updateItems(List<T> newItems) {

        boolean changed = false;

        // Add new files
        for (T item : newItems) {
            if (!items.contains(item)) {
                JComponent view = generateView(item);
                items.add(new ViewItemHolder<>(view, item, type));
                mainPanel.add(view);
                changed = true;
            }
        }

        // Remove files that do not exist anymore
        Iterator<ViewItemHolder<T>> itr = items.iterator();
        while (itr.hasNext()) {
            ViewItemHolder<T> current = itr.next();
            if (!newItems.contains(current)){
                itr.remove();
                mainPanel.remove(current.view);
                changed = true;
            }
        }
        return changed;
    }


    protected abstract JComponent generateView(T item);

    private static class ViewItemHolder<T>{
        public JComponent view;
        public T item;
        private Class<T> type;

        public ViewItemHolder(JComponent view, T item, Class<T> type){
            this.view = view;
            this.item = item;
            this.type = type;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof ViewItemHolder) {
                return ((ViewItemHolder) obj).item.equals(item);
            }else if (type.isInstance(obj)){
                return item.equals(obj);
            }else{
                return super.equals(obj);
            }
        }
    }


}
