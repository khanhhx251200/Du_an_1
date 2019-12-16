package com.example.du_an_1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.du_an_1.R;
import com.example.du_an_1.model.ThongKe;

import java.util.List;

public class LvThongKeHoaDonAdapter extends BaseAdapter {
    private List<ThongKe> thongKeList;
    private Context context;


    public LvThongKeHoaDonAdapter(List<ThongKe> thongKeList, Context context) {
        this.thongKeList = thongKeList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return thongKeList.size();
    }

    @Override
    public Object getItem(int position) {
        return thongKeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ThongKeHolder thongKeHolder = null;
        if (view == null) {
            LayoutInflater.from(context).inflate(R.layout.lv_thongkehoadonfragment, parent, false);
            thongKeHolder = new ThongKeHolder();
            thongKeHolder.tvMHD = view.findViewById(R.id.tvMHD);
            thongKeHolder.tvTime = view.findViewById(R.id.tvTimeMHD);
            thongKeHolder.tvTongTien = view.findViewById(R.id.tvTongTienMHD);
            view.setTag(thongKeHolder);
        } else {
            thongKeHolder = (ThongKeHolder) view.getTag();
        }
        thongKeHolder.tvMHD.setText(thongKeList.get(i).getMahoadon());
        thongKeHolder.tvTime.setText(thongKeList.get(i).getNgaymua());
        thongKeHolder.tvTongTien.setText(thongKeList.get(i).getTongtien());
        return view;
    }

    public class ThongKeHolder {
        TextView tvMHD, tvTongTien, tvTime;
    }
}
