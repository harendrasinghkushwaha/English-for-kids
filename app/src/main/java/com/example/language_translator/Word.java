package com.example.language_translator;

public class Word {
    private String mDefaultTranslation;

    private String mHindiTranslation;

    private int  mAudio;

    private static final int NO_IMAGE_PROVIDED = -1;
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    public Word(String defaultTranslation, String hindiTranslation, int imageResourceId, int  audio){
        mDefaultTranslation = defaultTranslation;
        mHindiTranslation = hindiTranslation;
        mImageResourceId = imageResourceId;
        mAudio = audio;
    }

    public Word(String defultTranslation, String hindiTranslation, int audio){
        mDefaultTranslation = defultTranslation;
        mHindiTranslation = hindiTranslation;
        mAudio =  audio;
    }

    public String getmDefaultTranslation(){
        return mDefaultTranslation;
    }

    public String getmHindiTranslation() {
        return mHindiTranslation;
    }

    public int getImageResourceId() { return mImageResourceId; }

    public boolean hasImage(){
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    public int getAudio(){ return mAudio; }

}
