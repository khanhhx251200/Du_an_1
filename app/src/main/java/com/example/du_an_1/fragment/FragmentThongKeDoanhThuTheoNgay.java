package com.example.du_an_1.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.du_an_1.R;
import com.example.du_an_1.XemHoaDonChiTietActivity;
import com.example.du_an_1.adapter.LvThongKeHoaDonAdapter;
import com.example.du_an_1.dao.HoaDonChiTietDAO;
import com.example.du_an_1.model.ThongKe;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.M)

public class FragmentThongKeDoanhThuTheoNgay extends Fragment {
    HoaDonChiTietDAO hoaDonChiTietDAO;
    EditText edFragmentNgay;
    ImageView imgCalendar;
    Button btnTim;
    ListView lv;
    List<ThongKe> thongKeList;
    TextView tvTongDoanhThuTheoNgay;
    LvThongKeHoaDonAdapter lvThongKeHoaDonAdapter;
    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hoaDonChiTietDAO = new HoaDonChiTietDAO(getActivity().getApplicationContext());

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ngay, container, false);
        edFragmentNgay = view.findViewById(R.id.edFragmentNgay);
        btnTim = view.findViewById(R.id.btnFragmentNgay);
        imgCalendar = view.findViewById(R.id.imgCalendar);
        tvTongDoanhThuTheoNgay = view.findViewById(R.id.tvTongDoanhThuTheoNgay);
        lv = view.findViewById(R.id.lvFragmentNgay);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        thongKeList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int month = calendar.get(Calendar.MONTH) + 1;
        final int year = calendar.get(Calendar.YEAR);
        edFragmentNgay.setEnabled(false);
        final DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String monthS = null;
                String dayS;
                if (month < 10) {
                    monthS = "0" + (month + 1);
                } else {
                    monthS = (month + 1) + "";
                }
                if (dayOfMonth < 10) {
                    dayS = "0" + dayOfMonth;

                } else {
                    dayS = dayOfMonth + "";
                }
                edFragmentNgay.setText(dayS + "-" + monthS + "-" + year);
            }
        }, year, month, day);
        imgCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
        btnTim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    thongKeList = hoaDonChiTietDAO.getHoaDonTheoNgay(edFragmentNgay.getText().toString());
                } catch (Exception e) {
                    Toast.makeText(context, "Sai roi`", Toast.LENGTH_SHORT).show();
                }
                if (thongKeList.size() > 0) {
                    lvThongKeHoaDonAdapter = new LvThongKeHoaDonAdapter(thongKeList, context);
                    lv.setAdapter(lvThongKeHoaDonAdapter);
                    double tongdoanhthu = 0;
                    for (int i = 0; i < thongKeList.size(); i++) {
                        double doanhthu = Double.parseDouble(thongKeList.get(i).getTongtien());
                        tongdoanhthu += doanhthu;
                    }
                    NumberFormat formatter = new DecimalFormat("#,###");
                    String formattedNumber = formatter.format(tongdoanhthu);
                    tvTongDoanhThuTheoNgay.setText("Tổng doanh thu theo ngày là: " + formattedNumber + " VNĐ");
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                            Intent intent = new Intent(getActivity(), XemHoaDonChiTietActivity.class);
                            intent.putExtra("mahoadon", thongKeList.get(i).getMahoadon());
                            startActivity(intent);
                        }
                    });
                } else {
                    tvTongDoanhThuTheoNgay.setText("");
                    thongKeList.clear();
                    lvThongKeHoaDonAdapter = new LvThongKeHoaDonAdapter(thongKeList, context);
                    lv.setAdapter(lvThongKeHoaDonAdapter);
                }

            }
        });


    }

}
