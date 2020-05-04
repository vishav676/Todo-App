package com.vishavlakhtia.todo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class fetchTask extends ArrayAdapter{
    private TextView title;
    private ArrayList dataSet;
    private Context mContext;
    public fetchTask(@NonNull Context context, ArrayList data) {
        super(context, R.layout.item_list,data);
        this.dataSet = data;
        this.mContext = context;
    }
    private DBHelper db = new DBHelper(getContext());
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Object task =  getItem(position);
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_list,parent,false);
            title = (TextView)convertView.findViewById(R.id.disTitle);
        }
        assert task != null;
        title.setText(task.toString());
        ImageView delete = (ImageView)convertView.findViewById(R.id.del);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"delete "+ task.toString(),Toast.LENGTH_SHORT).show();
                db.deleteTask((Integer)position);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
}
