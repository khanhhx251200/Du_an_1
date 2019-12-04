package com.example.du_an_1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.du_an_1.R;
import com.example.du_an_1.dao.MauGiayDAO;
import com.example.du_an_1.model.HangGiay;
import com.example.du_an_1.model.MauGiay;

import java.util.ArrayList;
import java.util.List;

public class MauGiayAdapter extends BaseAdapter {
    Context context;
    List<MauGiay> mauGiayList;
    List<HangGiay> hangGiayList;
    MauGiayDAO mauGiayDAO;
    MauGiay mauGiay;

    public MauGiayAdapter(Context context, List<MauGiay> mauGiayList, List<HangGiay> hangGiayList) {
        this.context = context;
        this.mauGiayList = mauGiayList;
        this.hangGiayList = hangGiayList;
        mauGiayDAO = new MauGiayDAO(context);
    }

    @Override
    public int getCount() {
        return mauGiayList.size();
    }

    @Override
    public MauGiay getItem(int position) {
        return mauGiayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(final int i, View view, ViewGroup parent) {
        MauGiayHolder mauGiayHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.custom_listview_maugiay, parent, false);
            mauGiayHolder = new MauGiayHolder();
            mauGiayHolder.tvMaMG = view.findViewById(R.id.tvMaMaugiay);
            mauGiayHolder.tvTenMG = view.findViewById(R.id.tvTenMauGiay);
            mauGiayHolder.tvSoLuong = view.findViewById(R.id.tvSoLuongMG);
            mauGiayHolder.tvGiaBan = view.findViewById(R.id.tvGiaban);
            mauGiayHolder.imgDeleteMG = view.findViewById(R.id.imgDeleteMauGiay);
            mauGiayHolder.imgDeleteMG.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mauGiayDAO.deleteMauGiay(mauGiayList.get(i).getMaMauGiay());
                    mauGiayList.remove(i);
                    notifyDataSetChanged();
                }
            });
            view.setTag(mauGiayHolder);
        } else {
            mauGiayHolder = (MauGiayHolder) view.getTag();
        }
        mauGiayHolder.tvMaMG.setText(mauGiayList.get(i).getMaMauGiay());
        mauGiayHolder.tvTenMG.setText(mauGiayList.get(i).getTenMauGiay());
        mauGiayHolder.tvSoLuong.setText(String.valueOf(mauGiayList.get(i).getSoLuong()) + " Đôi");
        mauGiayHolder.tvGiaBan.setText(String.valueOf(mauGiayList.get(i).getGiaBan()) + "00 VNĐ");
        return view;
    }
    public class MauGiayHolder {
        TextView tvMaMG, tvTenMG, tvSoLuong, tvMauSac, tvGiaBan;
        ImageView imgDeleteMG;
    }
}
