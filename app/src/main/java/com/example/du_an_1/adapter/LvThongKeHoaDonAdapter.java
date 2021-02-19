package com.example.du_an_1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.du_an_1.R;
import com.example.du_an_1.model.ThongKe;

import java.text.DecimalFormat;
import java.text.NumberFormat;
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
            view = LayoutInflater.from(context).inflate(R.layout.lv_thongkehoadonfragment, parent, false);
            thongKeHolder = new ThongKeHolder();
            thongKeHolder.tvMHDTK = view.findViewById(R.id.tvMHDTK);
            thongKeHolder.tvTime = view.findViewById(R.id.tvTimeMHD);
            thongKeHolder.tvTongTien = view.findViewById(R.id.tvTongTienMHD);
            view.setTag(thongKeHolder);
        } else {
            thongKeHolder = (ThongKeHolder) view.getTag();
        }
        thongKeHolder.tvMHDTK.setText("Mã hóa đơn: " + thongKeList.get(i).getMahoadon());
        thongKeHolder.tvTime.setText("Ngày mua: " + thongKeList.get(i).getNgaymua());
        NumberFormat formatter = new DecimalFormat("#,###");
        double tongTien = Double.parseDouble(thongKeList.get(i).getTongtien());
        String formattedNumber = formatter.format(tongTien);
        thongKeHolder.tvTongTien.setText("Tổng tiền: " + formattedNumber + " VND");
        return view;
    }

    public class ThongKeHolder {
        TextView tvMHDTK, tvTongTien, tvTime;
    }
}
