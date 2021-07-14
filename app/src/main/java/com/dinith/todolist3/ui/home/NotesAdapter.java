package com.dinith.todolist3.ui.home;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.dinith.todolist3.R;


import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.viewHolder> {

    Context context;
    Activity activity;
    ArrayList<NoteModel> arrayList;
    DatabaseHelper database_helper;

    public NotesAdapter(Context context, Activity activity, ArrayList<NoteModel> arrayList) {
        this.context = context;
        this.activity  = activity ;
        this.arrayList = arrayList;
    }

    @Override
    public NotesAdapter.viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.view1, viewGroup, false);
        return new viewHolder(view);
    }



    @Override
    public void onBindViewHolder(final NotesAdapter.viewHolder holder, final int position) {
        holder.title.setText(arrayList.get(position).getTitle());
        holder.description.setText(arrayList.get(position).getDes()+" Tasks");
        int progessc = Integer.parseInt(arrayList.get(position).getDes());
        if(arrayList.get(position).getDes()!= null){
            holder.progressbard.setProgress(progessc);
        }else
        {
            holder.progressbard.setProgress(0);
        }

        if(arrayList.get(position).getcolor_cd().equals("r1")){
            holder.progressbard.setProgressColor(Color.rgb(244, 67, 54));
        }else if(arrayList.get(position).getcolor_cd().equals("r2")){
            holder.progressbard.setProgressColor(Color.rgb(255, 193, 7));
        }else if(arrayList.get(position).getcolor_cd().equals("r3")){
            holder.progressbard.setProgressColor(Color.rgb(139, 195, 74));
        }else if(arrayList.get(position).getcolor_cd().equals("r4")){
            holder.progressbard.setProgressColor(Color.rgb(3, 169, 244));
        }else if(arrayList.get(position).getcolor_cd().equals("r5")){
            holder.progressbard.setProgressColor(Color.rgb(156, 39, 176));
        }else if(arrayList.get(position).getcolor_cd().equals("r6")){
            holder.progressbard.setProgressColor(Color.rgb(63, 81, 181));
        }else if(arrayList.get(position).getcolor_cd().equals("r7")){
            holder.progressbard.setProgressColor(Color.rgb(255, 235, 59));
        }else if(arrayList.get(position).getcolor_cd().equals("r8")){
            holder.progressbard.setProgressColor(Color.rgb(158, 158, 158));
        }else if(arrayList.get(position).getcolor_cd().equals("r9")){
            holder.progressbard.setProgressColor(Color.rgb(33, 33, 33));
        }else if(arrayList.get(position).getcolor_cd().equals("r10")){
            holder.progressbard.setProgressColor(Color.rgb(186, 104, 200));
        }else if(arrayList.get(position).getcolor_cd().equals("r11")){
            holder.progressbard.setProgressColor(Color.rgb(191, 54, 12));
        }else if(arrayList.get(position).getcolor_cd().equals("r12")){
            holder.progressbard.setProgressColor(Color.rgb(49, 133, 175));
        }else

            // holder.description.setText(arrayList.get(position).getcolor_c());
        database_helper = new DatabaseHelper(context);

     /*   holder.delete.setOnClickListener(new View.OnClickListener() {;
            @Override
            public void onClick(View v) {
                //deleting note
                database_helper.delete(arrayList.get(position).getID());
                arrayList.remove(position);
                notifyDataSetChanged();
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {;
            @Override
            public void onClick(View v) {
                //display edit dialog
                showDialog(position);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView title, description;
        RoundCornerProgressBar progressbard;
       // ImageView delete, edit;
        public viewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.name);
            description = (TextView) itemView.findViewById(R.id.notask);
            progressbard = (RoundCornerProgressBar)itemView.findViewById(R.id.progressbard);

          //  delete = (ImageView) itemView.findViewById(R.id.delete);
         //   edit = (ImageView) itemView.findViewById(R.id.edit);
        }
    }

    public void showDialog(final int pos) {
        final EditText title, des;
        Button submit;
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        dialog.setContentView(R.layout.dialog);
        params.copyFrom(dialog.getWindow().getAttributes());
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(params);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        title = (EditText) dialog.findViewById(R.id.title);
        des = (EditText) dialog.findViewById(R.id.description);
        submit = (Button) dialog.findViewById(R.id.submit);

        title.setText(arrayList.get(pos).getTitle());
        des.setText(arrayList.get(pos).getDes());

        submit.setOnClickListener(new View.OnClickListener() {;
            @Override
            public void onClick(View v) {
                if (title.getText().toString().isEmpty()) {
                    title.setError("Please Enter Title");
                }else if(des.getText().toString().isEmpty()) {
                    des.setError("Please Enter Description");
                }else {
                    //updating note
                    database_helper.updateNote(title.getText().toString(), des.getText().toString(), arrayList.get(pos).getID());
                    arrayList.get(pos).setTitle(title.getText().toString());
                    arrayList.get(pos).setDes(des.getText().toString());
                    dialog.cancel();
                    //notify list
                    notifyDataSetChanged();
                }
            }
        });
    }
}