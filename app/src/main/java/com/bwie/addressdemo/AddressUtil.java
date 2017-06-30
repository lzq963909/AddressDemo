package com.bwie.addressdemo;

import java.util.Comparator;

/**
 * Created by WuXirui
 * Create Time: 2017/6/23
 * Description:
 */

public class AddressUtil {

    public static class AddressCompartor implements Comparator<Address> {

        @Override
        public int compare(Address o1, Address o2) {
            String o1f = PinyinUtils.getFirstLetter(o1.getName());
            String o2f = PinyinUtils.getFirstLetter(o2.getName());
            if (o1f.equals("#")) {
                return 1;
            } else if (o2f.equals("#")) {
                return -1;
            } else {
                return o1f.compareTo(o2f);
            }
        }
    }

}
