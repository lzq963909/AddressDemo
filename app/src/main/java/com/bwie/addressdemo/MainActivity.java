package com.bwie.addressdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private ListView lvAddress;
    private SlideBar sbRight;
    private TextView txtLetter;

    private List<Address> addresses;
    private AddressAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvAddress = (ListView) findViewById(R.id.lv_address);
        sbRight = (SlideBar) findViewById(R.id.sb_right);
        txtLetter = (TextView) findViewById(R.id.txt_letter);
        sbRight.setText(txtLetter);

        addresses = Address.getAddresses();
        Collections.sort(addresses, new AddressUtil.AddressCompartor());

        adapter = new AddressAdapter(this, addresses);
        adapter.setIndex();
        lvAddress.setAdapter(adapter);

        // 给SlideBar设置触摸监听事件
        sbRight.setListener(new SlideBar.OnTouchDownListener() {
            @Override
            public void onTouch(String letter) {
                int index = adapter.getIndex(letter);
                if (index != -1) {
                    // listview设置当前的位置
                    lvAddress.setSelection(index);
                }
            }
        });
    }
}
