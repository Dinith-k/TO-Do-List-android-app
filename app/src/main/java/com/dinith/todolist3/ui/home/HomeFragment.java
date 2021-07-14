package com.dinith.todolist3.ui.home;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dinith.todolist3.R;
import com.dinith.todolist3.ui.db2.DatabaseHealper2;
import com.dinith.todolist3.ui.db2.NoteModel2;
import com.dinith.todolist3.ui.db2.NotesAdapter3;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static android.content.ContentValues.TAG;
import static android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    ArrayList<NoteModel> arrayList;

    ArrayList<NoteModel2> arrayList3;


    ArrayList<String> arrayList2;

    ArrayList<String> arrayList4;
    ArrayList<String> arrayList5;
    Calendar date;
    RecyclerView recyclerView,recyclerView2;
    FloatingActionButton actionButton;
    DatabaseHelper database_helper;
    DatabaseHealper2 database_helper2;
    TextView add_task,add_cata,settitle;
    Spinner spinner;
    LinearLayout trancelay ,catalay,catalay2;
    ImageButton btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10,btn11,btn12,donebt,donebt2,pbtn1,pbtn2,pbtn3;
    EditText getcata,timer;
    String  color_code = "null";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        add_cata = (TextView)root.findViewById(R.id.addcata);
        add_task = (TextView)root.findViewById(R.id.addtaks);
        settitle= (TextView)root.findViewById(R.id.settitle);
        spinner = (Spinner)root.findViewById(R.id.spinner);
        timer  = (EditText)root.findViewById(R.id.timer);

        pbtn1 = (ImageButton)root.findViewById(R.id.pbtn1) ;
        pbtn2 = (ImageButton)root.findViewById(R.id.pbtn2) ;
        pbtn3 = (ImageButton)root.findViewById(R.id.pbtn3) ;

        catalay2= (LinearLayout)root.findViewById(R.id.catalay2);
        add_cata.setVisibility(View.GONE);
        add_task.setVisibility(View.GONE);


        LinearLayoutManager layoutManager
                = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        trancelay = (LinearLayout)root.findViewById(R.id.trancelay);
        catalay = (LinearLayout)root.findViewById(R.id.catalay);
        recyclerView = (RecyclerView) root.findViewById(R.id.my_recycler_view);
        recyclerView2= (RecyclerView) root.findViewById(R.id.my_recycler_view2);
        trancelay.setVisibility(View.GONE);
        catalay.setVisibility(View.GONE);
        catalay2.setVisibility(View.GONE);
        database_helper = new DatabaseHelper(getContext());

        database_helper2 = new DatabaseHealper2(getContext());
        displayNotes();
        getcata = (EditText)root.findViewById(R.id.getcatname);
        btn1 = (ImageButton) root.findViewById(R.id.btn1);
        btn2 = (ImageButton) root.findViewById(R.id.btn2);
        btn3 = (ImageButton) root.findViewById(R.id.btn3);
        btn4 = (ImageButton) root.findViewById(R.id.btn4);
        btn5 = (ImageButton) root.findViewById(R.id.btn5);
        btn6 = (ImageButton) root.findViewById(R.id.btn6);
        btn7 = (ImageButton) root.findViewById(R.id.btn7);
        btn8 = (ImageButton) root.findViewById(R.id.btn8);
        btn9 = (ImageButton) root.findViewById(R.id.btn9);
        btn10 = (ImageButton) root.findViewById(R.id.btn10);
        btn11 = (ImageButton) root.findViewById(R.id.btn11);
        btn12 = (ImageButton) root.findViewById(R.id.btn12);
        donebt = (ImageButton) root.findViewById(R.id.donebt);
        donebt2= (ImageButton) root.findViewById(R.id.donebt2);

        FloatingActionButton fab = root.findViewById(R.id.fab);

        noborder();
        loadSpinnerData();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noborder();
                btn1.setBackgroundDrawable(getResources().getDrawable(R.drawable.border));
                color_code = "r1";
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noborder();
                btn2.setBackgroundDrawable(getResources().getDrawable(R.drawable.border));
                color_code = "r2";
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noborder();
                btn3.setBackgroundDrawable(getResources().getDrawable(R.drawable.border));
                color_code = "r3";
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noborder();
                btn4.setBackgroundDrawable(getResources().getDrawable(R.drawable.border));
                color_code = "r4";
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noborder();
                btn5.setBackgroundDrawable(getResources().getDrawable(R.drawable.border));
                color_code = "r5";
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noborder();
                btn6.setBackgroundDrawable(getResources().getDrawable(R.drawable.border));
                color_code = "r6";
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noborder();
                btn7.setBackgroundDrawable(getResources().getDrawable(R.drawable.border));
                color_code = "r7";
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noborder();
                btn8.setBackgroundDrawable(getResources().getDrawable(R.drawable.border));
                color_code = "r8";
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noborder();
                btn9.setBackgroundDrawable(getResources().getDrawable(R.drawable.border));
                color_code = "r9";
            }
        });
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noborder();
                btn10.setBackgroundDrawable(getResources().getDrawable(R.drawable.border));
                color_code = "r10";
            }
        });
        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noborder();
                btn11.setBackgroundDrawable(getResources().getDrawable(R.drawable.border));
                color_code = "r11";
            }
        });
        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noborder();
                btn12.setBackgroundDrawable(getResources().getDrawable(R.drawable.border));
                color_code = "r12";
            }
        });

        pbtn1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        noborder();
        pbtn1.setBackgroundDrawable(getResources().getDrawable(R.drawable.border));
    }
});
        pbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noborder();
                pbtn2.setBackgroundDrawable(getResources().getDrawable(R.drawable.border));
            }
        });


        pbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noborder();
                pbtn3.setBackgroundDrawable(getResources().getDrawable(R.drawable.border));
            }
        });

        trancelay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fab.setVisibility(View.VISIBLE);
                trancelay.setVisibility(View.GONE);
                catalay2.setVisibility(View.GONE);
                catalay.setVisibility(View.GONE);
            }
        });


        donebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(color_code.equals("null")){
                    Toast.makeText(getActivity(),"Please Select Color",Toast.LENGTH_SHORT).show();
                }else {
                    if(getcata.getText().toString().equals("")){
                        Toast.makeText(getActivity(),"Please Enter Title",Toast.LENGTH_SHORT).show();
                    }else {
                        //wokr

                        database_helper.addNotes(getcata.getText().toString(), "0",color_code);
                        displayNotes();
                        trancelay.setVisibility(View.GONE);
                        catalay.setVisibility(View.GONE);
                        add_cata.setVisibility(View.GONE);
                        fab.setVisibility(View.VISIBLE);
                        add_task.setVisibility(View.GONE);
                    }
                }
            }
        });



        fab.setVisibility(View.VISIBLE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                add_cata.setVisibility(View.VISIBLE);
                add_task.setVisibility(View.VISIBLE);

                // showDialog();

            }
        });

        add_cata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
                add_cata.setVisibility(View.GONE);
                add_task.setVisibility(View.GONE);
                fab.setVisibility(View.GONE);

            }
        });

        add_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // showDialog();
                fab.setVisibility(View.GONE);
                catalay2.setVisibility(View.VISIBLE);
                trancelay.setVisibility(View.VISIBLE);
                add_cata.setVisibility(View.GONE);
                add_task.setVisibility(View.GONE);

            }
        });


        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePicker();
            }
        });
        donebt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(settitle.getText().toString().equals("")){

                }else if(spinner.getSelectedItem().toString().equals("")){

                }else if (timer.getText().toString().equals("")){

                }else {
                    //bs
                    database_helper2.addNotes(settitle.getText().toString(), spinner.getSelectedItem().toString(),timer.getText().toString());
                    fab.setVisibility(View.VISIBLE);

          int  position ;
           position =  spinner.getSelectedItemPosition();



                   arrayList5 = new ArrayList<>(database_helper.getdescrip(arrayList4.get(position)));

                   int cal;
                   cal = Integer.parseInt(arrayList5.get(0));
                   cal = cal +1;

                   Log.d("myTag", String.valueOf(cal)+arrayList4.get(position)+"koko"+arrayList5.get(0));

                   database_helper.updateNote(spinner.getSelectedItem().toString(), String.valueOf(cal),arrayList4.get(position));
                   displayNotes();
                   catalay2.setVisibility(View.GONE);
                   trancelay.setVisibility(View.GONE);
                   catalay.setVisibility(View.GONE);
                   add_cata.setVisibility(View.GONE);
                   add_task.setVisibility(View.GONE);
                   settitle.setText("");
                   timer.setText("");



                }


            }
        });





        return root;
    }

    private void loadSpinnerData() {
        //DatabaseHelper db = new DatabaseHelper(getActivity());


        arrayList2 = new ArrayList<>(database_helper.getcatalist());
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item,arrayList2);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);


        arrayList4 = new ArrayList<>(database_helper.getids());

    }

    private void noborder() {
        color_code = "null";
        btn1.setBackgroundDrawable(getResources().getDrawable(R.drawable.noborder));
        btn2.setBackgroundDrawable(getResources().getDrawable(R.drawable.noborder));
        btn3.setBackgroundDrawable(getResources().getDrawable(R.drawable.noborder));
        btn4.setBackgroundDrawable(getResources().getDrawable(R.drawable.noborder));
        btn5.setBackgroundDrawable(getResources().getDrawable(R.drawable.noborder));
        btn6.setBackgroundDrawable(getResources().getDrawable(R.drawable.noborder));
        btn7.setBackgroundDrawable(getResources().getDrawable(R.drawable.noborder));
        btn8.setBackgroundDrawable(getResources().getDrawable(R.drawable.noborder));
        btn9.setBackgroundDrawable(getResources().getDrawable(R.drawable.noborder));
        btn10.setBackgroundDrawable(getResources().getDrawable(R.drawable.noborder));
        btn11.setBackgroundDrawable(getResources().getDrawable(R.drawable.noborder));
        btn12.setBackgroundDrawable(getResources().getDrawable(R.drawable.noborder));
        pbtn1.setBackgroundDrawable(getResources().getDrawable(R.drawable.noborder));
        pbtn2.setBackgroundDrawable(getResources().getDrawable(R.drawable.noborder));
        pbtn3.setBackgroundDrawable(getResources().getDrawable(R.drawable.noborder));
    }

    public void displayNotes() {

        loadSpinnerData();


        arrayList = new ArrayList<>(database_helper.getNotes());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        NotesAdapter adapter = new NotesAdapter(getContext(), getActivity(), arrayList);
        recyclerView.setAdapter(adapter);





            arrayList3 = new ArrayList<>(database_helper2.getNotes());
            recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            recyclerView2.setItemAnimator(new DefaultItemAnimator());



            NotesAdapter3 adapter2 = new NotesAdapter3(getContext(), getActivity(), arrayList3);
            recyclerView2.setAdapter(adapter2);




    }



    public void showDialog() {

        trancelay.setVisibility(View.VISIBLE);
        catalay.setVisibility(View.VISIBLE);


    }

    public void showDateTimePicker() {
        final Calendar currentDate = Calendar.getInstance();
        date = Calendar.getInstance();
        new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                date.set(year, monthOfYear, dayOfMonth);
                new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        String DATE_FORMAT_NOW = "dd-MM-yyyy HH:mm";
                        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);

                        timer.setText(sdf.format(date.getTime()));
                        date.set(Calendar.MINUTE, minute);
                        Log.v(TAG, "The choosen one " + date.getTime());
                    }
                }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
    }



}