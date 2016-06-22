package com.example.phuc.footballmanagement.Fragment;


import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.TimePickerDialog;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.phuc.footballmanagement.DatabaseHelper;
import com.example.phuc.footballmanagement.Model.Field;
import com.example.phuc.footballmanagement.Model.Match;
import com.example.phuc.footballmanagement.R;

import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddMatchFragment extends Fragment {

    DatabaseHelper db;
    EditText edtPlayerNum, edtPrice, edtStart, edtEnd;
    Spinner spnField;
    Button btnCreate;
    String endTime,startTime;

    public AddMatchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_manage_match, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        spnField = (Spinner) getActivity().findViewById(R.id.spnFieldSetup);
        edtPlayerNum = (EditText) getActivity().findViewById(R.id.txtPlayerSetup);
        edtPrice = (EditText) getActivity().findViewById(R.id.txtPriceSetup);
        edtStart = (EditText) getActivity().findViewById(R.id.txtStartTimeSetup);
        edtEnd = (EditText) getActivity().findViewById(R.id.txtEndTimeSetup);
        btnCreate = (Button) getActivity().findViewById(R.id.btnCreateMatch);
        db = new DatabaseHelper(getActivity());
        List<Field> list = db.getAllFields();
        ArrayAdapter<Field> spinnerArrayAdapter = new ArrayAdapter<Field>(getActivity(), android.R.layout.simple_spinner_item, list);
        spnField.setAdapter(spinnerArrayAdapter);

        edtStart.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                startTime = "";
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        startTime += (hourOfDay + ":" + (minute < 10 ? ("0" + minute) : minute));
                        edtStart.setText(startTime);
                    }
                }, hour, minute, false);
                timePickerDialog.show();
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        startTime += (year + "-" + ((monthOfYear + 1) < 10 ? ("0" + (monthOfYear + 1)) : (monthOfYear + 1)) + "-" +
                                (dayOfMonth < 10 ? ("0" + dayOfMonth) : dayOfMonth) + " ");
                    }
                }, year, month, day);
                datePickerDialog.show();
                return false;
            }
        });

        edtEnd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                endTime = "";
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                final int hour = calendar.get(Calendar.HOUR_OF_DAY);
                final int minute = calendar.get(Calendar.MINUTE);


                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        if(view.isShown()){
                        endTime += (year + "-" + ((monthOfYear + 1) < 10 ? ("0" + (monthOfYear + 1)) : (monthOfYear + 1)) + "-" +
                                (dayOfMonth < 10 ? ("0" + dayOfMonth) : dayOfMonth) + " ");
                        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                if(view.isShown()){
                                    endTime += (hourOfDay + ":" + (minute < 10 ? ("0" + minute) : minute));
                                    edtEnd.setText(endTime);
                                }

                            }
                        }, hour, minute, false);
                        timePickerDialog.show();
                        }
                    }
                }, year, month, day);
                datePickerDialog.show();
                return false;
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Field f = (Field) spnField.getSelectedItem();
                String playerNumber = edtPlayerNum.getText().toString();
                String price = edtPrice.getText().toString();
                String dayStare = edtStart.getText().toString();
                String dayEnd = edtEnd.getText().toString();
                String dayCreate = Calendar.getInstance().toString();
                if ("".equals(playerNumber) || "".equals(price) || "".equals(dayStare) || "".equals(dayEnd)) {
                    Toast.makeText(AddMatchFragment.this.getActivity(), "No field is blanked", Toast.LENGTH_SHORT).show();
                } else {
                    db.insertMatch(new Match(f.getId(), 5, 0, Integer.parseInt(playerNumber), Integer.parseInt(price), dayStare, dayEnd, false, dayCreate));
                    Toast.makeText(AddMatchFragment.this.getActivity(), "Create match succeeded!", Toast.LENGTH_SHORT).show();
                    FragmentManager fragmentManager = getFragmentManager();
                    Fragment fragment = new MatchListFragment();
                    fragmentManager.beginTransaction()
                            .replace(R.id.contentMain, fragment)
                            .commit();
                }
            }
        });
    }
}
