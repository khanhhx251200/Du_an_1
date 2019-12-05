package com.example.du_an_1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.du_an_1.R;
import com.example.du_an_1.model.HoaDonChiTiet;

import java.util.List;

public class HoaDonChiTietAdapter extends BaseAdapter {
    List<HoaDonChiTiet> hoaDonChiTietList;
    Context context;

    public HoaDonChiTietAdapter(List<HoaDonChiTiet> hoaDonChiTietList, Context context) {
        this.hoaDonChiTietList = hoaDonChiTietList;
        this.context = context;

    }

    @Override
    public int getCount() {
        return hoaDonChiTietList.size();
    }

    @Override
    public HoaDonChiTiet getItem(int position) {
        return hoaDonChiTietList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup parent) {
        HoaDonHolder hoaDonHolder = null;
        if (view == null) {
            hoaDonHolder = new HoaDonHolder();
            view = LayoutInflater.from(context).inflate(R.layout.lv_themhoadon, parent, false);
            hoaDonHolder.tvTenMauGiay = view.findViewById(R.id.tvTenMauGiayTHD);
            hoaDonHolder.tvSoLuong = view.findViewById(R.id.tvSoLuongTHD);
            hoaDonHolder.tvGiaBan = view.findViewById(R.id.tvGiaBanTHD);
            hoaDonHolder.tvThanhTien = view.findViewById(R.id.tvThanhTienTHD);
            hoaDonHolder.imgXoa = view.findViewById(R.id.imgXoaTHD);
            view.setTag(hoaDonHolder);
        } else {
            hoaDonHolder = (HoaDonHolder) view.getTag();
        }
        hoaDonHolder.tvTenMauGiay.setText("Tên mẫu giày: " + hoaDonChiTietList.get(i).getMauGiay().getTenMauGiay());
        hoaDonHolder.tvGiaBan.setText("Giá bán: " + hoaDonChiTietList.get(i).getMauGiay().getGiaBan() + "");
        hoaDonHolder.tvSoLuong.setText("Số lượng mua: " + hoaDonChiTietList.get(i).getSoLuongMua() + "");
        int soLuong = hoaDonChiTietList.get(i).getSoLuongMua();
        Double giatien = hoaDonChiTietList.get(i).getMauGiay().getGiaBan();
        hoaDonHolder.tvThanhTien.setText("Giá: " + soLuong * giatien + "");
        hoaDonHolder.imgXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hoaDonChiTietList.remove(i);
                notifyDataSetChanged();
            }
        });


        return view;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public class HoaDonHolder {
        TextView tvGiaBan, tvTenMauGiay, tvSoLuong, tvThanhTien;
        ImageView imgXoa;
    }
}
