package com.example.du_an_1.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.du_an_1.R;
import com.example.du_an_1.adapter.LvThongKeHoaDonAdapter;
import com.example.du_an_1.dao.HoaDonChiTietDAO;
import com.example.du_an_1.model.ThongKe;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FragmentThongKeTheoKhoangThoiGian extends Fragment {
    HoaDonChiTietDAO hoaDonChiTietDAO;
    EditText edDateFrom, edDateTo;
    ImageView imgCalendarFrom, imgCalendarTo;
    Button btnTimTKTG;
    ListView lvTKTG;
    TextView tvTongDoanhThuTKTG;
    List<ThongKe> thongKeList;
    private Context context;

    @Override
    public void onAttach( Context context ) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onCreate( @Nullable Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView( @NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState ) {
        View view = inflater.inflate(R.layout.fragment_khoang_thoi_gian, container, false);
        edDateFrom = view.findViewById(R.id.edDateFrom);
        edDateTo = view.findViewById(R.id.edDateTo);
        imgCalendarFrom = view.findViewById(R.id.imgCalendarFrom);
        imgCalendarTo = view.findViewById(R.id.imgCalendarTo);
        btnTimTKTG = view.findViewById(R.id.btnTimTKDT);
        lvTKTG = view.findViewById(R.id.lvTimHoaDonTheoKhoangThoiGian);
        tvTongDoanhThuTKTG = view.findViewById(R.id.tvTongDoanhThuTheoKhoangThoiGian);

        return view;
    }

    @Override
    public void onActivityCreated( @Nullable Bundle savedInstanceState ) {
        super.onActivityCreated(savedInstanceState);
        thongKeList = new ArrayList<>();
        hoaDonChiTietDAO = new HoaDonChiTietDAO(context);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        edDateFrom.setEnabled(false);
        edDateTo.setEnabled(false);
        final DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet( DatePicker view, int year, int month, int dayOfMonth ) {
                String monthS;
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
                edDateFrom.setText(year + "-" + monthS + "-" + dayS);
            }
        }, year, month, day);
        imgCalendarFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                datePickerDialog.show();
            }
        });
        final DatePickerDialog datePickerDialog1 = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet( DatePicker view, int year, int month, int dayOfMonth ) {
                String monthS;
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
                edDateTo.setText(year + "-" + monthS + "-" + dayS);
            }
        }, year, month, day);
        imgCalendarTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                datePickerDialog1.show();
            }
        });

        btnTimTKTG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                if (edDateFrom.getText().toString().equals("") || edDateTo.getText().toString().equals("")) {
                    Toast.makeText(context, "Vui lòng chọn khoảng thời gian muốn tìm!!", Toast.LENGTH_SHORT).show();
                } else {
                    thongKeList = hoaDonChiTietDAO.getDoanhThuTheoKhoangThoiGian(edDateFrom.getText().toString(), edDateTo.getText().toString());
                    if (thongKeList.size() > 0) {
                        double result = getTongDoanhThuTheoThoiGian(thongKeList);
                        NumberFormat formatter = new DecimalFormat("#,###");
                        String formattedNumber = formatter.format(result);
                        tvTongDoanhThuTKTG.setText("Tổng doanh thu từ " + edDateFrom.getText().toString() + " đến " + edDateTo.getText().toString() + " là: \n" + formattedNumber + " VNĐ");
                        LvThongKeHoaDonAdapter lvThongKeHoaDonAdapter = new LvThongKeHoaDonAdapter(thongKeList, context);
                        lvTKTG.setAdapter(lvThongKeHoaDonAdapter);
                    } else {
                        thongKeList.clear();
                        LvThongKeHoaDonAdapter lvThongKeHoaDonFragmentAdapter = new LvThongKeHoaDonAdapter(thongKeList, context);
                        lvTKTG.setAdapter(lvThongKeHoaDonFragmentAdapter);
                    }
                }
            }
        });


    }

    private double getTongDoanhThuTheoThoiGian( List<ThongKe> list ) {
        double tongDoanhThu = 0;
        for (int i = 0; i < list.size(); i++) {
            double tongTien = Double.parseDouble(list.get(i).getTongtien());
            tongDoanhThu += tongTien;
        }
        return tongDoanhThu;
    }


}
