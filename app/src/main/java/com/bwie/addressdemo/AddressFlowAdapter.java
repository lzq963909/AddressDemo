package com.bwie.addressdemo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by WuXirui
 * Create Time: 2017/6/23
 * Description:
 */

public class AddressFlowAdapter extends BaseAdapter implements StickyListHeadersAdapter {
    private Context context;
    private List<Address> addresses;

    private Map<String, Integer> indexMap = new HashMap<>();

    public AddressFlowAdapter(Context context, List<Address> addresses) {
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
            convertView = View.inflate(context, R.layout.item_address_title, null);
            holder.txtName = (TextView) convertView.findViewById(R.id.txt_address_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.txtName.setText(addresses.get(position).getName());
        return convertView;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        ViewHolderHeader holder = null;
        if (convertView == null) {
            holder = new ViewHolderHeader();
            convertView = View.inflate(context, R.layout.item_address_header, null);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.txt_address_header);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolderHeader) convertView.getTag();
        }
        holder.txtTitle.setText(PinyinUtils.getFirstLetter(addresses.get(position).getName()));
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        return PinyinUtils.getFirstLetter(addresses.get(position).getName()).charAt(0);
    }

    class ViewHolder {
        TextView txtName;
    }

    class ViewHolderHeader {
        TextView txtTitle;
    }
}
