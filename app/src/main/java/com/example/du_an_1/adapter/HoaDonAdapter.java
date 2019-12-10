package com.example.du_an_1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.du_an_1.R;
import com.example.du_an_1.model.MauGiay;

import java.util.ArrayList;
import java.util.List;

public class HoaDonAdapter extends BaseAdapter {
    private List<MauGiay> mauGiayList;
    private ArrayList<Integer> soLuong;
    private Context context;

    public HoaDonAdapter(Context context, List<MauGiay> mauGiayList, ArrayList<Integer> soLuong) {
        this.context = context;
        this.mauGiayList = mauGiayList;
        this.soLuong = soLuong;
    }

    @Override
    public int getCount() {
        return mauGiayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        XemHoaDonHolder xemHoaDonHolder = null;
        if (convertView == null) {
            xemHoaDonHolder = new XemHoaDonHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.lv_xemhoadon, parent, false);
            xemHoaDonHolder.tvTenMauGiay = convertView.findViewById(R.id.tvTenMauGiayXHD);
            xemHoaDonHolder.tvSoLuong = convertView.findViewById(R.id.tvSoLuongXHD);
            xemHoaDonHolder.tvGiaBan = convertView.findViewById(R.id.tvGiaBanXHD);
            xemHoaDonHolder.tvThanhTien = convertView.findViewById(R.id.tvThanhTienXHD);
            convertView.setTag(xemHoaDonHolder);
        } else {
            xemHoaDonHolder = (XemHoaDonHolder) convertView.getTag();
        }
        xemHoaDonHolder.tvGiaBan.setText("Giá bán : " + mauGiayList.get(position).getGiaBan() + "00 VNĐ");

        xemHoaDonHolder.tvSoLuong.setText("Số lượng : " + soLuong.get(position) + "");
        xemHoaDonHolder.tvTenMauGiay.setText("Tên mẫu giày: " + mauGiayList.get(position).getTenMauGiay());
        double giatien = mauGiayList.get(position).getGiaBan() * soLuong.get(position);
        xemHoaDonHolder.tvThanhTien.setText("Thành tiền : " + giatien + "00 VNĐ");

        return convertView;
    }

    public class XemHoaDonHolder {
        TextView tvTenMauGiay, tvSoLuong, tvGiaBan, tvThanhTien;

    }
}
