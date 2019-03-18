package com.github.pogryziony.pendlessblocks.managers;

import java.util.Iterator;
import java.util.List;

public class UtilManager {
    public UtilManager() {
    }

    public static boolean checkLores(List<String> loreLine1, List<String> loreLine2) {
        if (loreLine1.size() == loreLine2.size()) {
            Iterator localIterator = loreLine1.iterator();
            if (localIterator.hasNext()) {
                String s = (String)localIterator.next();
                return s.equalsIgnoreCase(loreLine2.get(loreLine1.indexOf(s)));
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
