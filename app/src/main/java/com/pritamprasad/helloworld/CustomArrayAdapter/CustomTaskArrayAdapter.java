package com.pritamprasad.helloworld.CustomArrayAdapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pritamprasad.helloworld.Model.Task;
import com.pritamprasad.helloworld.R;

import java.util.List;

/**
 * Custom ArrayAdapter
 */

public class CustomTaskArrayAdapter extends ArrayAdapter<Task>{
   private Context context = null;

   public CustomTaskArrayAdapter(Context context, int resource, List<Task> objects) {
       super(context, resource, objects);
       this.context=context;
   }

    /*private view holder class*/
    private class TaskViewHolder{
        TextView taskName;
        TextView taskDesc;
    }

    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        //Log.d("CustomTaskArrayAdapter","getView called for position: "+position);
        TaskViewHolder holder;
        Task rowItem = getItem(position);
        LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.task_list_item, null);
            holder = new TaskViewHolder();
            holder.taskName = (TextView) convertView.findViewById(R.id.task_list_name);
            holder.taskDesc = (TextView) convertView.findViewById(R.id.task_list_desc);
            convertView.setTag(holder);
        }
        else{
            holder = (TaskViewHolder)convertView.getTag();
        }
        assert rowItem != null;
        holder.taskName.setText(rowItem.getTaskName());
        holder.taskDesc.setText(rowItem.getTaskDesc());
        return convertView;
    }
}

