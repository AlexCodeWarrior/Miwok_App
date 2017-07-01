package com.example.android.miwok;

/**
 * Created by at on 6/26/17.
 * Custom Class dor Miwok translation
 * Translate from default language to Miwok
 */

public class Word {

    //Default Translation
    private String mDefTrans;

    //Miwok Translation
    private String mMiwokTrans;

    //Constant defined intial status
    private  static final int NO_IMAGE_PROVIDED = -1 ;

    //ImageResourceId
    private int mImResId = NO_IMAGE_PROVIDED;

    //AudioResourceId
    private int mAudioId;




    /** Default construnctor no Arguments **/
    public Word () {
        mDefTrans = "empty";

        mMiwokTrans="empty";
    }

    //For Phrases which have no Images
    /** Constructor Two Arguments**/
    public Word (String DefaultTrans , String MiwokTrans , int AudioRawId){

        mDefTrans = DefaultTrans;

        mMiwokTrans = MiwokTrans;

        mAudioId = AudioRawId;
    }


    /** Constructor Three Arguments**/
    public Word (String DefaultTrans , String MiwokTrans, int ImResId , int AudioRawId){

        mDefTrans = DefaultTrans;

        mMiwokTrans = MiwokTrans;

        mImResId = ImResId;

        mAudioId = AudioRawId;
    }

    /** setter for default trans **/
    public void setmDefTrans(String mDefTrans) {

        this.mDefTrans = mDefTrans;

    }

    /** Setter for Miwok Trans **/

    public void setmMiwokTrans(String mMiwokTrans) {
        this.mMiwokTrans = mMiwokTrans;
    }

    /** Getter DefTrans  **/

    public String getmDefTrans() {
        return mDefTrans;
    }


    /**Getter Miwok Trans**/
    public String getmMiwokTrans() {
        return mMiwokTrans;
    }

    //** getResourceId **/
    public int getImResId (){
        return mImResId;
    }

    //**get Audio Id **//
    public int getmAudioId (){
        return mAudioId;
    }



    //** check if it has an image **//

    public boolean hasImage (){
        return mImResId != NO_IMAGE_PROVIDED;
    }


    @Override
    public String toString() {
        return "Word{" +
                "mDefTrans='" + mDefTrans + '\'' +
                ", mMiwokTrans='" + mMiwokTrans + '\'' +
                ", mImResId=" + mImResId +
                ", mAudioId=" + mAudioId +
                '}';
    }
}


