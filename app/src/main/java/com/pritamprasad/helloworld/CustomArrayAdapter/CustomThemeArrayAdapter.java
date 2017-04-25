package com.pritamprasad.helloworld.CustomArrayAdapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pritamprasad.helloworld.Model.Theme;
import com.pritamprasad.helloworld.R;

import java.util.List;

/**
 * Custom ArrayAdapter
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

    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        //Log.d("CustomThemeArrayAdapter","getView called for position: "+position);
        ThemeViewHolder holder;
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
        assert rowItem != null;
        holder.themeName.setText(rowItem.getTheme_name());
        holder.themeDesc.setText(rowItem.getTheme_description());
        return convertView;
    }
}
