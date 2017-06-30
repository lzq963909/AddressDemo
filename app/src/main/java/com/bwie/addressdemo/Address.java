package com.bwie.addressdemo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WuXirui
 * Create Time: 2017/6/22
 * Description:
 */

public class Address {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public static List<Address> getAddresses() {
        List<String> list = getAllMember();
        List<Address> addresses = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Address address = new Address();
            address.setId(i);
            address.setName(list.get(i));
            addresses.add(address);
        }
        return addresses;
    }

    public static List<String> getAllMember() {
        List<String> addresses = new ArrayList<>();
        addresses.add("马南");
        addresses.add("马思征");
        addresses.add("王威");
        addresses.add("李德佳");
        addresses.add("李飞宇");
        addresses.add("张伟");
        addresses.add("马田野");
        addresses.add("商恩东");
        addresses.add("韩仕阳");
        addresses.add("张红");
        addresses.add("韩天宇");
        addresses.add("张海顺");
        addresses.add("李冯壮");
        addresses.add("薛文慧");
        addresses.add("杨晓羽");
        addresses.add("行会张");
        addresses.add("郭沛");
        addresses.add("郭栋栋");
        addresses.add("李迎港");
        addresses.add("王毅");
        addresses.add("段晓红");
        addresses.add("李海丽");
        addresses.add("张培森");
        addresses.add("卢程");
        addresses.add("刘婕");
        addresses.add("李金涛");
        addresses.add("1504D王成哲");
        addresses.add("高肖艳");
        addresses.add("郭龙刚");
        addresses.add("刘雷");
        addresses.add("靳风浩");
        addresses.add("徐帅");
        addresses.add("李志强");
        addresses.add("崔程起");
        addresses.add("宋前龙");
        addresses.add("廉锦雪");
        addresses.add("张惠行");
        addresses.add("绳利娟");
        addresses.add("陈庆");
        addresses.add("黄慧");
        addresses.add("李开虎");
        addresses.add("王文杰");
        addresses.add("丛云龙");
        addresses.add("刘晓倩");
        addresses.add("1503A韩强");
        addresses.add("庞晓旭");
        addresses.add("杨永丽");
        addresses.add("尹锐");
        return addresses;
    }
}
