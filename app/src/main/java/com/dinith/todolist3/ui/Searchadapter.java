package com.dinith.todolist3.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dinith.todolist3.R;
import com.dinith.todolist3.ui.db2.NoteModel2;

import com.dinith.todolist3.ui.home.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Searchadapter extends RecyclerView.Adapter<Searchadapter.viewHolder> {

    Context context;

    Activity activity;
    ArrayList<NoteModel2> arrayList;
    DatabaseHelper database_helper;
    int count  = 0;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;

    public Searchadapter(Context context, Activity activity, ArrayList<NoteModel2> arrayList) {
        this.context = context;
        this.activity  = activity ;
        this.arrayList = arrayList;
    }




    @Override
    public Searchadapter.viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.todosearch, viewGroup, false);
        return new Searchadapter.viewHolder(view);
    }



    @Override
    public void onBindViewHolder(final Searchadapter.viewHolder holder, final int position) {

        calendar = Calendar.getInstance();
        String getdt = arrayList.get(position).getcolor_cd2();
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        String[] db_date = getdt.split(" ");
        date = dateFormat.format(calendar.getTime());


            holder.title.setText("Task Name :"+arrayList.get(position).getTitle2());

        holder.setcata.setText("To Do In : "+arrayList.get(position).getcolor_cd2());

        holder.setdate.setText("category name: "+arrayList.get(position).getDes2());

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
        TextView title, setdate,setcata;
        LinearLayout liner;
        //  RoundCornerProgressBar progressbard;
        // ImageView delete, edit;


        public viewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.taskname);
            liner = (LinearLayout) itemView.findViewById(R.id.liner);

            setdate = (TextView) itemView.findViewById(R.id.setdate);
            setcata = (TextView) itemView.findViewById(R.id.setcata);

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

        title.setText(arrayList.get(pos).getTitle2());
        des.setText(arrayList.get(pos).getDes2());

        submit.setOnClickListener(new View.OnClickListener() {;
            @Override
            public void onClick(View v) {
                if (title.getText().toString().isEmpty()) {
                    title.setError("Please Enter Title");
                }else if(des.getText().toString().isEmpty()) {
                    des.setError("Please Enter Description");
                }else {
                    //updating note
                    database_helper.updateNote(title.getText().toString(), des.getText().toString(), arrayList.get(pos).getID2());
                    arrayList.get(pos).setTitle2(title.getText().toString());
                    arrayList.get(pos).setDes2(des.getText().toString());
                    dialog.cancel();
                    //notify list
                    notifyDataSetChanged();
                }
            }
        });
    }
}