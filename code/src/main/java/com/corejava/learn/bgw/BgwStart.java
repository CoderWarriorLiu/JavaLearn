package com.corejava.learn.bgw;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BgwStart {

    public static void main(String[] args) {

        System.out.println("Hello World ！");
        //ChList
        List aList = new ArrayList<String>();
        List lList = new LinkedList();

        aList.add("a");
        aList.add("b");
        aList.add("c");
        aList.add("d");

        lList.add("a");
        lList.add("b");
        lList.add("c");
        lList.add("d");

        ChList chList = new ChList(aList,lList);

        System.out.println("Chlist");
    }
}
