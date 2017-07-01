package com.example.android.miwok;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.view.menu.ListMenuItemView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by at on 6/26/17.
 */

public class WordAdapter extends ArrayAdapter<Word> {

      private int mbackgColor;

    private MediaPlayer mp;

    //** Two Argument Constructor **//*

    public WordAdapter(Context context, ArrayList<Word> pWords , int backgColor) {

        super(context, 0, pWords);
        mbackgColor = backgColor;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);

        }


        // Get the {@link Word} object located at this position in the list
        Word currentWord = getItem(position);


        //Find the TextView in the list_item.xml layout with the ID miwok_text_view
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);

        // Get the miwok_text_view from the current Word object and
        // set this text on the miwok TextView
        miwokTextView.setText(currentWord.getmMiwokTrans());


        //Find the TextView in the list_item.xml layout with the ID version_name
        TextView defTextView = (TextView) listItemView.findViewById(R.id.default_text_view);


        // Get the default_text_view from the current Word object and
        // set this text on the default TextView
        defTextView.setText(currentWord.getmDefTrans());


        //Get ImageView resource id from the listview
        ImageView iconImageView = (ImageView) listItemView.findViewById(R.id.image);

        //if an image is associated with word
        if (currentWord.hasImage()) {

            //output the image by going to the current word and get it's image
            iconImageView.setImageResource(currentWord.getImResId());

            //Make sure image is set to Visible
            iconImageView.setVisibility(View.VISIBLE);

        }
        //don't put image on a phrase and remove image
        else {
            iconImageView.setVisibility(View.GONE);
        }

        //grab the text container from xml
          View textContainer = listItemView.findViewById(R.id.text_container);

        //Find the color that resource ID maps to
        int color = ContextCompat.getColor(getContext(),mbackgColor);

        //set background of the textcontianer View
        textContainer.setBackgroundColor(color);


        // Return the whole list item layout (containing 2 TextViews and 1 Picture )
        // so that it can be shown in the ListView
        return listItemView;


    }
}

