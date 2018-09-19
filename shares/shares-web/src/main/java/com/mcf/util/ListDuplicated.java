package com.mcf.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListDuplicated {

    public static <T> List<T> duplicate(List<T> tList) {
        Set<T> tSet = new HashSet<T>();
        tSet.addAll(tList);
        tList.clear();
        tList.addAll(tSet);
        return tList;
    }

}
