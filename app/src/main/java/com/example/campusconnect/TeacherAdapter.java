package com.example.campusconnect;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder> {

    private ArrayList<Teacher> teacherList;
    private Context context;
    private FirebaseFirestore db;

    public TeacherAdapter(ArrayList<Teacher> teacherList, Context context) {
        this.teacherList = teacherList;
        this.context = context;
        db = FirebaseFirestore.getInstance();
    }

    @NonNull
    @Override
    public TeacherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_teacher, parent, false);
        return new TeacherViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherViewHolder holder, int position) {
        Teacher teacher = teacherList.get(position);

        holder.txtSubjectShort.setText(teacher.getSubjectShort());
        holder.txtSubjectName.setText(teacher.getSubjectName());
        holder.txtFacultyName.setText(teacher.getFacultyName());

        holder.imgMore.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(context, holder.imgMore);
            popup.inflate(R.menu.menu_teacher_item);
            popup.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.action_edit) {
                    showEditDialog(teacher, position);
                    return true;
                } else if (item.getItemId() == R.id.action_delete) {
                    deleteTeacher(teacher, position);
                    return true;
                }
                return false;
            });
            popup.show();
        });
    }

    @Override
    public int getItemCount() {
        return teacherList.size();
    }

    class TeacherViewHolder extends RecyclerView.ViewHolder {
        TextView txtSubjectShort, txtSubjectName, txtFacultyName;
        ImageView imgMore;

        public TeacherViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSubjectShort = itemView.findViewById(R.id.txtSubjectShort);
            txtSubjectName = itemView.findViewById(R.id.txtSubjectName);
            txtFacultyName = itemView.findViewById(R.id.txtFacultyName);
            imgMore = itemView.findViewById(R.id.imgMore);
        }
    }

    private void deleteTeacher(Teacher teacher, int position) {
        db.collection("Faculty").document(teacher.getId())
                .delete()
                .addOnSuccessListener(aVoid -> {
                    teacherList.remove(position);
                    notifyItemRemoved(position);
                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }

    private void showEditDialog(Teacher teacher, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Edit Faculty");

        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_add_teacher, null);
        builder.setView(dialogView);

        EditText edtSubjectShort = dialogView.findViewById(R.id.edtSubjectShort);
        EditText edtSubjectName = dialogView.findViewById(R.id.edtSubjectName);
        EditText edtFacultyName = dialogView.findViewById(R.id.edtFacultyName);

        edtSubjectShort.setText(teacher.getSubjectShort());
        edtSubjectName.setText(teacher.getSubjectName());
        edtFacultyName.setText(teacher.getFacultyName());

        builder.setPositiveButton("Update", (dialog, which) -> {
            teacher.setSubjectShort(edtSubjectShort.getText().toString().trim());
            teacher.setSubjectName(edtSubjectName.getText().toString().trim());
            teacher.setFacultyName(edtFacultyName.getText().toString().trim());

            db.collection("Faculty").document(teacher.getId())
                    .set(teacher)
                    .addOnSuccessListener(aVoid -> {
                        teacherList.set(position, teacher);
                        notifyItemChanged(position);
                        Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        builder.show();
    }
}
