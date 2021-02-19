package com.example.du_an_1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.du_an_1.R;
import com.example.du_an_1.dao.HangGiayDAO;
import com.example.du_an_1.model.HangGiay;

import java.util.List;

public class HangGiayAdapter extends BaseAdapter {
    List<HangGiay> hangGiayList;
    Context context;
    HangGiayDAO hangGiayDAO;

    public HangGiayAdapter( List<HangGiay> hangGiayList, Context context ) {
        this.hangGiayList = hangGiayList;
        this.context = context;
        hangGiayDAO = new HangGiayDAO(context);
    }

    @Override
    public int getCount() {
        return hangGiayList.size();
    }

    @Override
    public HangGiay getItem( int position ) {
        return hangGiayList.get(position);
    }

    @Override
    public long getItemId( int position ) {
        return 0;
    }

    @Override
    public View getView( final int position, View view, ViewGroup parent ) {
        HangGiayHolder hangGiayHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.custom_listview_hanggiay, parent, false);
            hangGiayHolder = new HangGiayHolder();
            hangGiayHolder.tvMahanggiay = view.findViewById(R.id.tvMaHangGiay);
            hangGiayHolder.tvTenhanggiay = view.findViewById(R.id.tvTenHangGiay);
            hangGiayHolder.imgLogo = view.findViewById(R.id.img);
            hangGiayHolder.imgDelete = view.findViewById(R.id.imgDeleteHangGiay);
            hangGiayHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick( View v ) {
                    if (hangGiayDAO.deleteHangGiay(hangGiayList.get(position).getMaHangGiay()) > 0) {
                        hangGiayList.remove(position);
                        notifyDataSetChanged();
                    }

                }
            });
            view.setTag(hangGiayHolder);
        } else {
            hangGiayHolder = (HangGiayHolder) view.getTag();
        }
        if (hangGiayList.get(position).getTenHangGiay().equals("Adidas")) {
            hangGiayHolder.imgLogo.setImageResource(R.drawable.logoadidas);
        } else if (hangGiayList.get(position).getTenHangGiay().equals("Gucci")) {
            hangGiayHolder.imgLogo.setImageResource(R.drawable.gucci);
        } else if (hangGiayList.get(position).getTenHangGiay().equals("Nike")) {
            hangGiayHolder.imgLogo.setImageResource(R.drawable.nike);
        } else if (hangGiayList.get(position).getTenHangGiay().equals("Converse")) {
            hangGiayHolder.imgLogo.setImageResource(R.drawable.converse);
        } else if (hangGiayList.get(position).getTenHangGiay().equals("Chanel")) {
            hangGiayHolder.imgLogo.setImageResource(R.drawable.chanel);
        } else if (hangGiayList.get(position).getTenHangGiay().equals("Dolce&Gabbana")) {
            hangGiayHolder.imgLogo.setImageResource(R.drawable.dolce);
        } else if (hangGiayList.get(position).getTenHangGiay().equals("Lacoste")) {
            hangGiayHolder.imgLogo.setImageResource(R.drawable.lacoste);
        } else if (hangGiayList.get(position).getTenHangGiay().equals("Puma")) {
            hangGiayHolder.imgLogo.setImageResource(R.drawable.puma);
        }
        hangGiayHolder.tvMahanggiay.setText(hangGiayList.get(position).getMaHangGiay());
        hangGiayHolder.tvTenhanggiay.setText(hangGiayList.get(position).getTenHangGiay());

        return view;
    }

    public void dataSetChange( List<HangGiay> hangGiays ) {
        this.hangGiayList = hangGiays;
        notifyDataSetChanged();
    }

    public class HangGiayHolder {
        TextView tvMahanggiay;
        TextView tvTenhanggiay;
        ImageView imgLogo;
        ImageView imgDelete;
    }
}
