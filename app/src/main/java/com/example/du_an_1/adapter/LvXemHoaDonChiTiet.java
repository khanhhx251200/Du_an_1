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

public class LvXemHoaDonChiTiet extends BaseAdapter {
    private Context context;
    private List<ThongKe> thongKeList;

    public LvXemHoaDonChiTiet(Context context, List<ThongKe> thongKeList) {
        this.context = context;
        this.thongKeList = thongKeList;
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
        XemHoaDonHolder xemHoaDonHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.lv_xemhoadonchitiet, parent, false);
            xemHoaDonHolder = new XemHoaDonHolder();
            xemHoaDonHolder.tvMaMauGiay = view.findViewById(R.id.tvMaMauGiayhdct);
            xemHoaDonHolder.tvMaHDCT = view.findViewById(R.id.tvmahdct);
            xemHoaDonHolder.tvGiaBan = view.findViewById(R.id.tvgiabanhdct);
            xemHoaDonHolder.tvSoLuong = view.findViewById(R.id.tvsoluonghdct);
            view.setTag(xemHoaDonHolder);
        } else {
            xemHoaDonHolder = (XemHoaDonHolder) view.getTag();
        }
        xemHoaDonHolder.tvMaHDCT.setText("Mã hóa đơn chi tiết: " + thongKeList.get(i).getMahoadonchitiet());
        xemHoaDonHolder.tvMaMauGiay.setText("Mã mẫu giày: " + thongKeList.get(i).getMagiay());
        xemHoaDonHolder.tvSoLuong.setText("Số lượng mua: " + thongKeList.get(i).getSoluong() + " Đôi");
        xemHoaDonHolder.tvGiaBan.setText("Giá bán: " + thongKeList.get(i).getGiaban() + "00 VNĐ");
        return view;
    }

    public class XemHoaDonHolder {
        TextView tvMaHDCT, tvMaMauGiay, tvSoLuong, tvGiaBan;

    }


}

