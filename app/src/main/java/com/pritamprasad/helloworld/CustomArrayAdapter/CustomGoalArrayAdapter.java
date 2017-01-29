package com.pritamprasad.helloworld.CustomArrayAdapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pritamprasad.helloworld.Model.Goal;
import com.pritamprasad.helloworld.R;

import java.util.List;

/**
 * Created by jarvis on 1/28/17.
 */

public class CustomGoalArrayAdapter extends ArrayAdapter<Goal> {
    private Context context = null;

    public CustomGoalArrayAdapter(Context context, int resource, List<Goal> objects) {
        super(context, resource, objects);
        this.context=context;
    }

    /*private view holder class*/
    private class GoalViewHolder{
        TextView goalName;
        TextView goalDesc;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        //Log.d("CustomGoalArrayAdapter","getView called for position: "+position);
        CustomGoalArrayAdapter.GoalViewHolder holder = null;
        Goal rowItem = getItem(position);
        LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.goal_list_item, null);
            holder = new GoalViewHolder();
            holder.goalName = (TextView) convertView.findViewById(R.id.goal_list_name);
            holder.goalDesc = (TextView) convertView.findViewById(R.id.goal_list_desc);
            convertView.setTag(holder);
        }
        else{
            holder = (GoalViewHolder)convertView.getTag();
        }
        holder.goalName.setText(rowItem.getGoalName());
        holder.goalDesc.setText(rowItem.getGoalDesc());
        return convertView;
    }
}
