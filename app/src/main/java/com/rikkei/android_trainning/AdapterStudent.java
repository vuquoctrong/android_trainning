package com.rikkei.android_trainning;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AdapterStudent extends RecyclerView.Adapter<AdapterStudent.NoteViewHolder> {
    private List<Student> students;

    public AdapterStudent() {
        students = new ArrayList<>();
    }

    public void setStudents(List<Student> students) {
        this.students = students;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_student, viewGroup, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder noteViewHolder, int i) {
        final Student student = students.get(i);
        noteViewHolder.tvname.setText(student.getmName());
        noteViewHolder.tvbirthyear.setText(student.getBirthYear()+"");

    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {

        private TextView tvname;
        private TextView tvbirthyear;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvname = itemView.findViewById(R.id.tvstudentname);
            tvbirthyear = itemView.findViewById(R.id.tvbirthyear);

        }
    }
}
