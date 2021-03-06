package com.and3r.octoprint.javadisplay.ui.components;

import com.and3r.octoprint.javadisplay.datamodels.actions.OctoprintAction;

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
            if (!containsItem(items, item)) {

                if (item instanceof OctoprintAction){
                    int a = 0;
                }

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
            if (!newItems.contains(current.item)){
                itr.remove();
                mainPanel.remove(current.view);
                changed = true;
            }
        }
        return changed;
    }

    private boolean containsItem(ArrayList<ViewItemHolder<T>> list, T item){
        boolean found = false;
        int i = 0;
        int size = list.size();
        while(!found && i < size){
            if (item.equals(list.get(i).item)){
                found = true;
            }else{
                i++;
            }
        }
        return found;
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
    }


}
