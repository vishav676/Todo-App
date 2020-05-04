package com.vishavlakhtia.todo;

import android.content.Context;
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

public class fetchTask extends ArrayAdapter<Task>{
    TextView title;
    private ArrayList<Task> dataSet;
    Context mContext;
    public fetchTask(@NonNull Context context, ArrayList<Task> data) {
        super(context, R.layout.item_list,data);
        this.dataSet = data;
        this.mContext = context;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Task task = getItem(position);
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_list,parent,false);
            title = (TextView)convertView.findViewById(R.id.disTitle);
        }
        assert task != null;
        title.setText(task.getTitle());
        ImageView delete = (ImageView)convertView.findViewById(R.id.del);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"delete "+ task.getTitle(),Toast.LENGTH_SHORT).show();
                dataSet.remove(position);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
}
