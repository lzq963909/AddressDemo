package com.bwie.addressdemo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WuXirui
 * Create Time: 2017/6/23
 * Description:
 */

public class AddressAdapter extends BaseAdapter {
    private Context context;
    private List<Address> addresses;

    private Map<String, Integer> indexMap = new HashMap<>();

    public AddressAdapter(Context context, List<Address> addresses) {
        this.context = context;
        this.addresses = addresses;
    }

    public void setIndex() {
        indexMap.clear();
        for (int i = 0; i < addresses.size(); i++) {
            // 遍历数据，得到首字母
            String index = PinyinUtils.getFirstLetter(addresses.get(i).getName());
            // 如果索引map中不包含这个字母，就添加进索引map，key是字母，value是第一次出现的位置
            if (!indexMap.containsKey(index)) {
                indexMap.put(index, i);
            }
        }
    }

    /**
     * 获取字母在列表中的索引值（第一次出现的位置）
     * @param letter
     * @return
     */
    public int getIndex(String letter) {
        if (!indexMap.containsKey(letter)){
            return -1;
        }
        return indexMap.get(letter);
    }

    @Override
    public int getCount() {
        return addresses.size();
    }

    @Override
    public Object getItem(int position) {
        return addresses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_address, null);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.txt_address_title);
            holder.txtName = (TextView) convertView.findViewById(R.id.txt_address_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        // 拿到当前条目
        String str = addresses.get(position).getName();
        // 取当前条目的首字母
        String first = PinyinUtils.getFirstLetter(str);
        if (indexMap.get(first) == position) {
            holder.txtTitle.setVisibility(View.VISIBLE);
            holder.txtTitle.setText(first);
        } else {
            holder.txtTitle.setVisibility(View.GONE);
        }
        holder.txtName.setText(addresses.get(position).getName());
        return convertView;
    }

    class ViewHolder {
        TextView txtTitle;
        TextView txtName;
    }
}
