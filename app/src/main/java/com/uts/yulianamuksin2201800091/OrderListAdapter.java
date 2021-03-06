package com.uts.yulianamuksin2201800091;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderListAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<String> list = new ArrayList<String>();
    private Context context;
    ArrayList<String> dt = OrderData.getInstance().datas;
    ArrayList<Integer> tp = OrderData.getInstance().totalPrices;

    public OrderListAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.orderlist, null);
        }

        TextView listitm = (TextView)view.findViewById(R.id.list_item_string);
        listitm.setText(list.get(position));

        Button delBtn = (Button)view.findViewById(R.id.delete_btn);

        delBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                list.remove(position);
                dt.remove(position);
                tp.remove(position);
                notifyDataSetChanged();
            }
        });

        return view;
    }
}