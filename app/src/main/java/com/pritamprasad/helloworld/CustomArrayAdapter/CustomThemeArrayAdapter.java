package com.pritamprasad.helloworld.CustomArrayAdapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pritamprasad.helloworld.Model.Theme;
import com.pritamprasad.helloworld.R;

import java.util.List;

/**
 * Created by jarvis on 1/28/17.
 */

public class CustomThemeArrayAdapter extends ArrayAdapter<Theme> {
    private Context context = null;
    public CustomThemeArrayAdapter(Context context, int resource, List<Theme> objects) {
        super(context, resource, objects);
        this.context = context;

    }

    /*private view holder class*/
    private class ThemeViewHolder {
        TextView themeName;
        TextView themeDesc;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        //Log.d("CustomThemeArrayAdapter","getView called for position: "+position);
        ThemeViewHolder holder = null;
        Theme rowItem = getItem(position);
        LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.theme_list_item, null);
            holder = new ThemeViewHolder();
            holder.themeName = (TextView) convertView.findViewById(R.id.theme_list_name);
            holder.themeDesc = (TextView) convertView.findViewById(R.id.theme_list_desc);
            convertView.setTag(holder);
        }
        else{
            holder = (ThemeViewHolder)convertView.getTag();
        }
        holder.themeName.setText(rowItem.getThemeName());
        holder.themeDesc.setText(rowItem.getThemeDesc());
        return convertView;
    }
}
