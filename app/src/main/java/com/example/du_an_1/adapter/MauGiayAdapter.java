package com.example.du_an_1.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.du_an_1.R;
import com.example.du_an_1.dao.MauGiayDAO;
import com.example.du_an_1.model.HangGiay;
import com.example.du_an_1.model.MauGiay;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class MauGiayAdapter extends BaseAdapter {
    Context context;
    List<MauGiay> mauGiayList;
    List<HangGiay> hangGiayList;
    MauGiayDAO mauGiayDAO;
    private ArrayList<MauGiay> mOriginalValues;
    private ArrayList<MauGiay> mDisplayedValues;

    public MauGiayAdapter( Context context, List<MauGiay> mauGiayList, List<HangGiay> hangGiayList ) {
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
    public MauGiay getItem( int position ) {
        return mauGiayList.get(position);
    }

    @Override
    public long getItemId( int position ) {
        return 0;
    }


    @Override
    public View getView( final int i, View view, ViewGroup parent ) {
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
                public void onClick( View v ) {
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
        mauGiayHolder.tvSoLuong.setText("Số lượng: " + mauGiayList.get(i).getSoLuong());

        NumberFormat formatter = new DecimalFormat("#,###");
        double myNumber = mauGiayList.get(i).getGiaBan();
        String formattedNumber = formatter.format(myNumber);
        String price = "Giá: "+formattedNumber+"VNĐ";

        mauGiayHolder.tvGiaBan.setText(price);
        return view;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void ondatasetchanged( List<MauGiay> mauGiayList, List<HangGiay> hangGiayList ) {
        this.mauGiayList = mauGiayList;
        this.hangGiayList = hangGiayList;
        notifyDataSetChanged();
    }

    public class MauGiayHolder {
        TextView tvMaMG, tvTenMG, tvSoLuong, tvMauSac, tvGiaBan;
        ImageView imgDeleteMG;
    }

    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,FilterResults results) {

                mDisplayedValues = (ArrayList<MauGiay>) results.values; // has the filtered values
                notifyDataSetChanged();  // notifies the data with new filtered values
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                ArrayList<MauGiay> FilteredArrList = new ArrayList<MauGiay>();

                if (mOriginalValues == null) {
                    mOriginalValues = new ArrayList<MauGiay>(mDisplayedValues); // saves the original data in mOriginalValues
                }

                /********
                 *
                 *  If constraint(CharSequence that is received) is null returns the mOriginalValues(Original) values
                 *  else does the Filtering and returns FilteredArrList(Filtered)
                 *
                 ********/
                if (constraint == null || constraint.length() == 0) {

                    // set the Original result to return
                    results.count = mOriginalValues.size();
                    results.values = mOriginalValues;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < mOriginalValues.size(); i++) {
                        String data = mOriginalValues.get(i).getTenMauGiay();
                        if (data.toLowerCase().startsWith(constraint.toString())) {
                            FilteredArrList.add(new MauGiay(mOriginalValues.get(i).getMaMauGiay(),mOriginalValues.get(i).getMaHangGiay(),mOriginalValues.get(i).getTenMauGiay(),mOriginalValues.get(i).getSoLuong(),mOriginalValues.get(i).getMauSac(),mOriginalValues.get(i).getGiaBan()));
                        }
                    }
                    // set the Filtered result to return
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }
        };
        return filter;
    }

}
