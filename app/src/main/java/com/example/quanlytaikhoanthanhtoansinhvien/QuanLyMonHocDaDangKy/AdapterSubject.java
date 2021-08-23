package com.example.quanlytaikhoanthanhtoansinhvien.QuanLyMonHocDaDangKy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quanlytaikhoanthanhtoansinhvien.R;
import com.example.quanlytaikhoanthanhtoansinhvien.QuanLyMonHocDaDangKy.Subject;


import java.util.List;

public class AdapterSubject extends BaseAdapter {
    List<Subject> listSubjects;

    public AdapterSubject(List<Subject> listSubjects) {
        this.listSubjects = listSubjects;
    }

    @Override
    public int getCount() {
        return listSubjects.size();
    }

    @Override
    public Object getItem(int position) {
        return listSubjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_subject, parent, false);

        TextView subjectName = view.findViewById(R.id.subjectName);
        TextView subjectMoney = view.findViewById(R.id.subjectMoney);
        TextView subjectTime = view.findViewById(R.id.subjectTime);
        TextView subjectLocation = view.findViewById(R.id.subjectLocation);
        TextView subjectQuantityStudents = view.findViewById(R.id.subjectQuantityStudents);

        Subject subject = listSubjects.get(position);
        subjectName.setText(subject.getName());
        subjectMoney.setText(subject.getMoney() + " VND");
        subjectTime.setText(subject.getTime());
        subjectLocation.setText(subject.getLocation());
        subjectQuantityStudents.setText("Sĩ số: " + subject.getQuantityStudent() + "/70");

        return view;
    }
}
