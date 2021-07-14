package com.dinith.todolist3.ui.db2;

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

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.dinith.todolist3.MainActivity;
import com.dinith.todolist3.R;
import com.dinith.todolist3.ui.home.DatabaseHelper;

import java.security.AccessControlContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class NotesAdapter3 extends RecyclerView.Adapter<NotesAdapter3.viewHolder> {

        Context context;

        Activity activity;
        ArrayList<NoteModel2> arrayList;
        DatabaseHelper database_helper;
    int count  = 0;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;

public NotesAdapter3(Context context, Activity activity, ArrayList<NoteModel2> arrayList) {
        this.context = context;
        this.activity  = activity ;
        this.arrayList = arrayList;
        }




    @Override
public NotesAdapter3.viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.todo, viewGroup, false);
        return new viewHolder(view);

        }



@Override
public void onBindViewHolder(final NotesAdapter3.viewHolder holder, final int position) {

    calendar = Calendar.getInstance();
    String getdt = arrayList.get(position).getcolor_cd2();
    dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    String[] db_date = getdt.split(" ");
    date = dateFormat.format(calendar.getTime());
    Intent intent = new Intent("custom-message");

if(date.equals(db_date[0])){
    holder.title.setText(arrayList.get(position).getTitle2());



    //            intent.putExtra("quantity",Integer.parseInt(quantity.getText().toString()));
    count++;

    intent.putExtra("quantity",String.valueOf(count));

    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    holder.liner.setVisibility(View.VISIBLE);
 //   holder.itemView.setVisibility(View.GONE);
}else {
    holder.liner.setVisibility(View.GONE);

    holder.itemView.setVisibility(View.GONE);
    holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));



    //Intent intent2 = new Intent("custom-message2");
    //intent2.putExtra("quantity2",String.valueOf(position));
   // LocalBroadcastManager.getInstance(context).sendBroadcast(intent2);
}

      //  holder.description.setText(arrayList.get(position).getDes2()+" Tasks");
      /*  if(arrayList.get(position).getcolor_cd2().equals("r1")){
        holder.progressbard.setProgressColor(Color.rgb(244, 67, 54));
        }else if(arrayList.get(position).getcolor_cd2().equals("r2")){
        holder.progressbard.setProgressColor(Color.rgb(255, 193, 7));
        }else if(arrayList.get(position).getcolor_cd2().equals("r3")){
        holder.progressbard.setProgressColor(Color.rgb(139, 195, 74));
        }else if(arrayList.get(position).getcolor_cd2().equals("r4")){
        holder.progressbard.setProgressColor(Color.rgb(3, 169, 244));
        }else if(arrayList.get(position).getcolor_cd2().equals("r5")){
        holder.progressbard.setProgressColor(Color.rgb(156, 39, 176));
        }else if(arrayList.get(position).getcolor_cd2().equals("r6")){
        holder.progressbard.setProgressColor(Color.rgb(63, 81, 181));
        }else if(arrayList.get(position).getcolor_cd2().equals("r7")){
        holder.progressbard.setProgressColor(Color.rgb(255, 235, 59));
        }else if(arrayList.get(position).getcolor_cd2().equals("r8")){
        holder.progressbard.setProgressColor(Color.rgb(158, 158, 158));
        }else if(arrayList.get(position).getcolor_cd2().equals("r9")){
        holder.progressbard.setProgressColor(Color.rgb(33, 33, 33));
        }else if(arrayList.get(position).getcolor_cd2().equals("r10")){
        holder.progressbard.setProgressColor(Color.rgb(186, 104, 200));
        }else if(arrayList.get(position).getcolor_cd2().equals("r11")){
        holder.progressbard.setProgressColor(Color.rgb(191, 54, 12));
        }else if(arrayList.get(position).getcolor_cd2().equals("r12")){
        holder.progressbard.setProgressColor(Color.rgb(49, 133, 175));
        }else*/

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
    LinearLayout liner;
 //  RoundCornerProgressBar progressbard;
    // ImageView delete, edit;


    public viewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.taskname);
        liner = (LinearLayout) itemView.findViewById(R.id.liner56);

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