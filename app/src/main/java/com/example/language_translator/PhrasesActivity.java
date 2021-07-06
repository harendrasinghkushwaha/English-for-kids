package com.example.language_translator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import android.content.Context;
import android.media.AudioManager;

public class PhrasesActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    private AudioManager mAudioManager;


    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            }else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };




    private MediaPlayer.OnCompletionListener mCompletionListner = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("Where are you going?","तुम कहाँ जा रहे हो?",R.raw.phrase_where_are_you_going));
        words.add(new Word("What is your name?","तुम्हारा नाम क्या है?",R.raw.phrase_what_is_your_name));
        words.add(new Word("My name is...","मेरा नाम है...",R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?","तुम्हे कैसा लग रहा है?",R.raw.phrase_how_are_you_feeling));
        words.add(new Word("I am feeling good.","मैं अच्छा महसूस कर रहा हूं।",R.raw.phrase_im_feeling_good));
        words.add(new Word("Are you coming?","क्या आप आ रहे हैं?",R.raw.phrase_are_you_coming));
        words.add(new Word("Yes, I am coming.","हाँ, मैं आ रहा हूँ।",R.raw.phrase_yes_im_coming));
        words.add(new Word("Come here.","यहाँ आओ।",R.raw.phrase_come_here));

        WordAdapter adapter = new WordAdapter(this,words,R.color.category_phrases);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = words.get(position);
                releaseMediaPlayer();
                int result = mAudioManager.requestAudioFocus(
                        mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT
                );
                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mediaPlayer = MediaPlayer.create(PhrasesActivity.this,word.getAudio());
                    mediaPlayer.start();

                    mediaPlayer.setOnCompletionListener(mCompletionListner);
                }
            }
        });
    }



    @Override
    protected void onStop() {

        super.onStop();
        // Before going to stop state release the
        // allocated mediaplyer resources
        releaseMediaPlayer();
    }
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;

            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

}