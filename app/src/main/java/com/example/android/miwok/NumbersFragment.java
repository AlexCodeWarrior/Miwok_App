package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.media.MediaPlayer;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;



public class NumbersFragment extends Fragment {

  //* Handles Audio playback //
    private MediaPlayer mMediaPlayer;

    //** Handles audio focus //

    private AudioManager mAudioManager;

    View rootView;
    //** Triggers when th audio focus changes **/

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener(){
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                // Pause playback because your Audio Focus was
                // temporarily stolen, but will be back soon.
                // i.e. for a phone call

                mMediaPlayer.pause();

            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // Stop playback, because you lost the Audio Focus.
                // i.e. the user started some other playback app
                // Remember to unregister your controls/buttons here.
                // And release the kra — Audio Focus!
                // You’re done.

                mMediaPlayer.stop();
                releaseMediaPlayer();

            } else if (focusChange ==
                    AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // Lower the volume, because something else is also
                // playing audio over you.
                // i.e. for notifications or navigation directions
                // Depending on your audio playback, you may prefer to
                // pause playback here instead. You do you.
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);

            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // Resume playback, because you hold the Audio Focus
                // again!
                // i.e. the phone call ended or the nav directions
                // are finished
                // If you implement ducking and lower the volume, be
                // sure to return it to normal here, as well.

                mMediaPlayer.start();
            }
        }
    };




    //This gets triggeres the {Linked MediaPlayer} has completed playing the audio
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer mediaPlayer){
            releaseMediaPlayer();
        }

    };




    public NumbersFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.word_list, container, false);




        //Create a list of Words

        ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("one", "lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("five", "massokka", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("nine", "wo'e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("ten", "na'aacha", R.drawable.number_ten, R.raw.number_ten));


        // get the linear layout from xml

      /* LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);



         for ( int index = 0 ; index <  words.size() ; index ++) {

             // Create a new TextView
              TextView wordView = new TextView(this);

             // set the text of the current  word  in index
             wordView.setText(words.get(index));

             //Add this TextView as another child of the RootView of this layout
             rootView.addView(wordView);

         }*/

        /**
         * Clean up the media player by releasing its resources.
         */


        // Make an Array adapter that contains Strings as status source
        // The current context/Activity , layout in which you want to see it -predefined ,the objects of the source
        //ArrayAdapter (Context context, int resource, int textViewResourceId)

        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_numbers);


        // Finding View Hierarchy for list and store it in listView
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        // Associate the ArrayAdapter with the ListView
        listView.setAdapter(adapter);





        //create instance of audioManager
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);






        //listener for list View and modify
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position,
                                    long id) {
                // get the current word in the AdapterView and cast to Word
                Word crrentWord = (Word) parent.getItemAtPosition(position);

                //get is particular audio ID
                int crrentAudio = crrentWord.getmAudioId();



                //Release Meadia player if it currently exist
                releaseMediaPlayer();






                // Request audio focus for playback
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);



                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    //We have Audio focus now



                    //create a mediaPlayer instance with current cotenxt
                    mMediaPlayer = MediaPlayer.create(getActivity(), crrentAudio);



                    // start playing audio
                    mMediaPlayer.start();



                    //Create a callback when audio  completed and releases audio
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);



                }





            }
        });















      return rootView;

    }







    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);

        }
    }


}

