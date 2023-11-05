package com.example.mycomics.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mycomics.R;
import com.example.mycomics.beans.EditeurNomBean;

import java.util.List;

public class EditeursListAdapter extends ArrayAdapter<EditeurNomBean> {

    private Context mContext;
    private int id;
    private List <EditeurNomBean>items ;

    public EditeursListAdapter(Context context, int textViewResourceId , List<EditeurNomBean> list )
    {
        super(context, textViewResourceId, list);
        mContext = context;
        id = textViewResourceId;
        items = list ;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent)
    {
        View mView = v ;
        if(mView == null){
            LayoutInflater vi = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = vi.inflate(id, null);
        }

        TextView text = (TextView) mView.findViewById(R.id.tvListviewTemplate);

        if(items.get(position) != null )
        {
            text.setText(items.get(position).getEditeur_nom());
        }

        return mView;
    }

}
