package com.example.du_an_1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.du_an_1.R;
import com.example.du_an_1.model.MauGiay;

import java.util.List;

public class LvMauGiayBanChayAdapter extends BaseAdapter {
    Context context;
    List<MauGiay> mauGiayList;

    public LvMauGiayBanChayAdapter(Context context, List<MauGiay> mauGiayList) {
        this.context = context;
        this.mauGiayList = mauGiayList;
    }

    @Override
    public int getCount() {
        return mauGiayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mauGiayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        MauGiayBanChayHolder mauGiayBanChayHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.custom_listview_maugiaybanchay, parent, false);
            mauGiayBanChayHolder = new MauGiayBanChayHolder();

            mauGiayBanChayHolder.tvMaMG = view.findViewById(R.id.tvMaMauGiayBan);
            mauGiayBanChayHolder.tvSoLuong = view.findViewById(R.id.tvSoLuongBan);

            view.setTag(mauGiayBanChayHolder);
        } else {
            mauGiayBanChayHolder = (MauGiayBanChayHolder) view.getTag();
        }

        mauGiayBanChayHolder.tvMaMG.setText(mauGiayList.get(i).getMaMauGiay());
        mauGiayBanChayHolder.tvSoLuong.setText(String.valueOf(mauGiayList.get(i).getSoLuong()) + " Đôi");

        return view;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public class MauGiayBanChayHolder {
        TextView tvMaMG, tvSoLuong;

    }

}
