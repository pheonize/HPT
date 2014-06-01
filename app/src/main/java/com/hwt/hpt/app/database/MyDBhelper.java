package com.hwt.hpt.app.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by oVANILLAz on 6/1/14 AD.
 */
public class MyDBhelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME="HappyTalk";

    public static final String TABLE_CONVERSATION = "conversation";
    public static final String TABLE_THING = "thing";
    public static final String TABLE_PLACE = "place";
    public static final String TABLE_EMERGENCY= "emergency";
    public static final String TABLE_LOGISTIC = "logistic";


    public static final String COLUMN_ID = "id";
    public static final String COLUMN_LANGFROM ="langFrom";
    public static final String COLUMN_LANGTO ="langTo";
    public static final String COLUMN_WORDFROM = "wordFrom";
    public static final String COLUMN_WORDTO ="wordTo";
    public static final String COLUMN_KARAOKETH="karaokeTH";
    public static final String COLUMN_KARAOKEEN = "karaokeEN";
    public static final String COLUMN_SOUND ="sound";

    private SQLiteDatabase db;


    public MyDBhelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);


    }



    //Create Table
    @Override
    public void onCreate(SQLiteDatabase db){

        String CREATE_CONVERSATION_TABLE = "CREATE TABLE "+ TABLE_CONVERSATION + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_LANGFROM +
                " TEXT, " + COLUMN_LANGTO + " TEXT, " + COLUMN_WORDFROM + " TEXT, " +
                COLUMN_WORDTO + " TEXT, " + COLUMN_KARAOKETH + " TEXT, " + COLUMN_KARAOKEEN +
                " TEXT, " + COLUMN_SOUND + " TEXT " + ")";

        String CREATE_THING_TABLE = "CREATE TABLE "+ TABLE_THING + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_LANGFROM +
                " TEXT, " + COLUMN_LANGTO + " TEXT, " + COLUMN_WORDFROM + " TEXT, " +
                COLUMN_WORDTO + " TEXT, " + COLUMN_KARAOKETH + " TEXT, " + COLUMN_KARAOKEEN +
                " TEXT, " + COLUMN_SOUND + " TEXT " + ")";

        String CREATE_PLACE_TABLE = "CREATE TABLE "+ TABLE_PLACE + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_LANGFROM +
                " TEXT, " + COLUMN_LANGTO + " TEXT, " + COLUMN_WORDFROM + " TEXT, " +
                COLUMN_WORDTO + " TEXT, " + COLUMN_KARAOKETH + " TEXT, " + COLUMN_KARAOKEEN +
                " TEXT, " + COLUMN_SOUND + " TEXT " + ")";

        String CREATE_EMERGENCY_TABLE = "CREATE TABLE "+ TABLE_EMERGENCY + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_LANGFROM +
                " TEXT, " + COLUMN_LANGTO + " TEXT, " + COLUMN_WORDFROM + " TEXT, " +
                COLUMN_WORDTO + " TEXT, " + COLUMN_KARAOKETH + " TEXT, " + COLUMN_KARAOKEEN +
                " TEXT, " + COLUMN_SOUND + " TEXT " + ")";

        String CREATE_LOGISTIC_TABLE = "CREATE TABLE "+ TABLE_LOGISTIC + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_LANGFROM +
                " TEXT, " + COLUMN_LANGTO + " TEXT, " + COLUMN_WORDFROM + " TEXT, " +
                COLUMN_WORDTO + " TEXT, " + COLUMN_KARAOKETH + " TEXT, " + COLUMN_KARAOKEEN +
                " TEXT, " + COLUMN_SOUND + " TEXT " + ")";


        db.execSQL(CREATE_CONVERSATION_TABLE);
        db.execSQL(CREATE_THING_TABLE);
        db.execSQL(CREATE_PLACE_TABLE);
        db.execSQL(CREATE_EMERGENCY_TABLE);
        db.execSQL(CREATE_LOGISTIC_TABLE);

    }


    //Conversation


    void addConversation(Conversation conversation){
        SQLiteDatabase db =this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_LANGFROM,conversation.getLangFrom());
        values.put(COLUMN_LANGTO,conversation.getLangTo());
        values.put(COLUMN_WORDFROM,conversation.getWordFrom());
        values.put(COLUMN_WORDTO,conversation.getWordTo());
        values.put(COLUMN_KARAOKETH,conversation.getKaraokeTH());
        values.put(COLUMN_KARAOKEEN,conversation.getKaraokeEN());
        values.put(COLUMN_SOUND,conversation.getSound());

        db.insert(TABLE_CONVERSATION,null,values);
        db.close();
    }


    Conversation getConversation(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONVERSATION,new String[]{
                        COLUMN_ID,COLUMN_LANGFROM,COLUMN_LANGTO,COLUMN_WORDFROM,COLUMN_WORDTO,
                        COLUMN_KARAOKETH,COLUMN_KARAOKEEN,COLUMN_SOUND} , COLUMN_ID + "=?",
                new String[]{String.valueOf(id)},null,null,null,null);

        if (cursor !=null)
            cursor.moveToFirst();

        Conversation conversation = new Conversation(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),
                cursor.getString(5),cursor.getString(6),cursor.getString(7));
        return conversation;

    }


    public List<Conversation> getAllConversation(){
        List<Conversation> conversationList = new ArrayList<Conversation>();

        String selectQry ="SELECT * FROM " + TABLE_CONVERSATION;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQry,null);

        if(cursor.moveToFirst()){
            do{
                Conversation conversation = new Conversation();
                conversation.setId(Integer.parseInt(cursor.getString(0)));
                conversation.setLangFrom(cursor.getString(1));
                conversation.setLangTo(cursor.getString(2));
                conversation.setWordFrom(cursor.getString(3));
                conversation.setWordTo(cursor.getString(4));
                conversation.setKaraokeTH(cursor.getString(5));
                conversation.setKaraokeEN(cursor.getString(6));
                conversation.setSound(cursor.getString(7));

                conversationList.add(conversation);

            }while (cursor.moveToNext());
        }
        return conversationList;
    }
    //Get all list
    public Cursor readAllConversation(){

        String selectQry ="SELECT * FROM " + TABLE_CONVERSATION;
        Cursor cursor = db.rawQuery(selectQry,null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;

    }

    //Show parentlist
    public List<Conversation> getConversationParentList(){
        List<Conversation> conversationList = new ArrayList<Conversation>();

        String selectQry ="SELECT wordFrom FROM " + TABLE_CONVERSATION;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQry,null);
        if(cursor.moveToFirst()){
            do{
                Conversation conversation = new Conversation();
//                conversation.setId(Integer.parseInt(cursor.getString(0)));
//                conversation.setLangFrom(cursor.getString(1));
//                conversation.setLangTo(cursor.getString(2));
                conversation.setWordFrom(cursor.getString(3));
//                conversation.setWordTo(cursor.getString(4));
//                conversation.setKaraokeTH(cursor.getString(5));
//                conversation.setKaraokeEN(cursor.getString(6));
//                conversation.setSound(cursor.getString(7));

                conversationList.add(conversation);

            }while (cursor.moveToNext());
        }
        return conversationList;
    }




    //Show in list
    public List<Conversation> getConversationList(){
        List<Conversation> conversationList = new ArrayList<Conversation>();

        String selectQry ="SELECT wordFrom , wordTO , karaokeTH , karaokeEN  FROM " + TABLE_CONVERSATION;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQry,null);

        if(cursor.moveToFirst()){
            do{
                Conversation conversation = new Conversation();
//                conversation.setId(Integer.parseInt(cursor.getString(0)));
//                conversation.setLangFrom(cursor.getString(1));
//                conversation.setLangTo(cursor.getString(2));
                conversation.setWordFrom(cursor.getString(3));
                conversation.setWordTo(cursor.getString(4));
                conversation.setKaraokeTH(cursor.getString(5));
                conversation.setKaraokeEN(cursor.getString(6));
                conversation.setSound(cursor.getString(7));

                conversationList.add(conversation);

            }while (cursor.moveToNext());
        }
        return conversationList;
    }

    public void deleteConversation(Conversation conversation){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONVERSATION, COLUMN_ID + " = ?",
                new String[] {String.valueOf(conversation.getId())});
        db.close();
    }

    //count

    public int getConversationCount(){
        String countQry = "SELECT * FROM " + TABLE_CONVERSATION;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQry,null);
        cursor.close();

        return cursor.getCount();
    }



    //Thing-----------------------------------------------------------------------------------------

    void addThing(Thing thing){
        SQLiteDatabase db =this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_LANGFROM,thing.getLangFrom());
        values.put(COLUMN_LANGTO,thing.getLangTo());
        values.put(COLUMN_WORDFROM,thing.getWordFrom());
        values.put(COLUMN_WORDTO,thing.getWordTo());
        values.put(COLUMN_KARAOKETH,thing.getKaraokeTH());
        values.put(COLUMN_KARAOKEEN,thing.getKaraokeEN());
        values.put(COLUMN_SOUND,thing.getSound());

        db.insert(TABLE_THING,null,values);
        db.close();
    }

    Thing getThing(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_THING,new String[]{
                        COLUMN_ID,COLUMN_LANGFROM,COLUMN_LANGTO,COLUMN_WORDFROM,COLUMN_WORDTO,
                        COLUMN_KARAOKETH,COLUMN_KARAOKEEN,COLUMN_SOUND} , COLUMN_ID + "=?",
                new String[]{String.valueOf(id)},null,null,null,null);

        if (cursor !=null)
            cursor.moveToFirst();

        Thing thing = new Thing(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),
                cursor.getString(5),cursor.getString(6),cursor.getString(7));
        return thing;

    }


    public List<Thing> getAllThing(){
        List<Thing> thingList = new ArrayList<Thing>();

        String selectQry ="SELECT * FROM " + TABLE_THING;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQry,null);

        if(cursor.moveToFirst()){
            do{
                Thing thing = new Thing();
                thing.setId(Integer.parseInt(cursor.getString(0)));
                thing.setLangFrom(cursor.getString(1));
                thing.setLangTo(cursor.getString(2));
                thing.setWordFrom(cursor.getString(3));
                thing.setWordTo(cursor.getString(4));
                thing.setKaraokeTH(cursor.getString(5));
                thing.setKaraokeEN(cursor.getString(6));
                thing.setSound(cursor.getString(7));

                thingList.add(thing);

            }while (cursor.moveToNext());
        }
        return thingList;
    }

    public void deleteThing(Thing thing){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_THING, COLUMN_ID + " = ?",
                new String[] {String.valueOf(thing.getId())});
        db.close();
    }

    //count

    public int getThingCount(){
        String countQry = "SELECT * FROM " + TABLE_THING;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQry,null);
        cursor.close();

        return cursor.getCount();
    }

    //Place-----------------------------------------------------------------------------------------

    void addPlace(Place place){
        SQLiteDatabase db =this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_LANGFROM,place.getLangFrom());
        values.put(COLUMN_LANGTO,place.getLangTo());
        values.put(COLUMN_WORDFROM,place.getWordFrom());
        values.put(COLUMN_WORDTO,place.getWordTo());
        values.put(COLUMN_KARAOKETH,place.getKaraokeTH());
        values.put(COLUMN_KARAOKEEN,place.getKaraokeEN());
        values.put(COLUMN_SOUND,place.getSound());

        db.insert(TABLE_PLACE,null,values);
        db.close();
    }
    Place getPlace(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PLACE,new String[]{
                        COLUMN_ID,COLUMN_LANGFROM,COLUMN_LANGTO,COLUMN_WORDFROM,COLUMN_WORDTO,
                        COLUMN_KARAOKETH,COLUMN_KARAOKEEN,COLUMN_SOUND} , COLUMN_ID + "=?",
                new String[]{String.valueOf(id)},null,null,null,null);

        if (cursor !=null)
            cursor.moveToFirst();

        Place place = new Place(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),
                cursor.getString(5),cursor.getString(6),cursor.getString(7));
        return place;

    }


    public List<Place> getAllPlace(){
        List<Place> placeList = new ArrayList<Place>();

        String selectQry ="SELECT * FROM " + TABLE_PLACE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQry,null);

        if(cursor.moveToFirst()){
            do{
                Place place = new Place();
                place.setId(Integer.parseInt(cursor.getString(0)));
                place.setLangFrom(cursor.getString(1));
                place.setLangTo(cursor.getString(2));
                place.setWordFrom(cursor.getString(3));
                place.setWordTo(cursor.getString(4));
                place.setKaraokeTH(cursor.getString(5));
                place.setKaraokeEN(cursor.getString(6));
                place.setSound(cursor.getString(7));

                placeList.add(place);

            }while (cursor.moveToNext());
        }
        return placeList;
    }

    public void deletePlace(Place place){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PLACE, COLUMN_ID + " = ?",
                new String[] {String.valueOf(place.getId())});
        db.close();
    }

    //count

    public int getPlaceCount(){
        String countQry = "SELECT * FROM " + TABLE_PLACE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQry,null);
        cursor.close();

        return cursor.getCount();
    }


    //Help-----------------------------------------------------------------------------------------

    void addEmergency(Emergency emergency){
        SQLiteDatabase db =this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_LANGFROM,emergency.getLangFrom());
        values.put(COLUMN_LANGTO,emergency.getLangTo());
        values.put(COLUMN_WORDFROM,emergency.getWordFrom());
        values.put(COLUMN_WORDTO,emergency.getWordTo());
        values.put(COLUMN_KARAOKETH,emergency.getKaraokeTH());
        values.put(COLUMN_KARAOKEEN,emergency.getKaraokeEN());
        values.put(COLUMN_SOUND,emergency.getSound());

        db.insert(TABLE_EMERGENCY,null,values);
        db.close();
    }
    Emergency getEmergency(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_EMERGENCY,new String[]{
                        COLUMN_ID,COLUMN_LANGFROM,COLUMN_LANGTO,COLUMN_WORDFROM,COLUMN_WORDTO,
                        COLUMN_KARAOKETH,COLUMN_KARAOKEEN,COLUMN_SOUND} , COLUMN_ID + "=?",
                new String[]{String.valueOf(id)},null,null,null,null);

        if (cursor !=null)
            cursor.moveToFirst();

        Emergency emergency = new Emergency(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),
                cursor.getString(5),cursor.getString(6),cursor.getString(7));
        return emergency;

    }


    public List<Emergency> getAllEmergency(){
        List<Emergency> emergencyList = new ArrayList<Emergency>();

        String selectQry ="SELECT * FROM " + TABLE_EMERGENCY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQry,null);

        if(cursor.moveToFirst()){
            do{
                Emergency emergency = new Emergency();
                emergency.setId(Integer.parseInt(cursor.getString(0)));
                emergency.setLangFrom(cursor.getString(1));
                emergency.setLangTo(cursor.getString(2));
                emergency.setWordFrom(cursor.getString(3));
                emergency.setWordTo(cursor.getString(4));
                emergency.setKaraokeTH(cursor.getString(5));
                emergency.setKaraokeEN(cursor.getString(6));
                emergency.setSound(cursor.getString(7));

                emergencyList.add(emergency);

            }while (cursor.moveToNext());
        }
        return emergencyList;
    }

    public void deleteEmergency(Emergency emergency){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EMERGENCY, COLUMN_ID + " = ?",
                new String[] {String.valueOf(emergency.getId())});
        db.close();
    }

    //count


    public int getEmergencyCount(){
        String countQry = "SELECT * FROM " + TABLE_EMERGENCY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQry,null);
        cursor.close();

        return cursor.getCount();
    }

    //Logistic-----------------------------------------------------------------------------------------

    void addLogistic(Logistic logistic){
        SQLiteDatabase db =this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_LANGFROM,logistic.getLangFrom());
        values.put(COLUMN_LANGTO,logistic.getLangTo());
        values.put(COLUMN_WORDFROM,logistic.getWordFrom());
        values.put(COLUMN_WORDTO,logistic.getWordTo());
        values.put(COLUMN_KARAOKETH,logistic.getKaraokeTH());
        values.put(COLUMN_KARAOKEEN,logistic.getKaraokeEN());
        values.put(COLUMN_SOUND,logistic.getSound());

        db.insert(TABLE_LOGISTIC,null,values);
        db.close();
    }
    Logistic getLogistic(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_LOGISTIC,new String[]{
                        COLUMN_ID,COLUMN_LANGFROM,COLUMN_LANGTO,COLUMN_WORDFROM,COLUMN_WORDTO,
                        COLUMN_KARAOKETH,COLUMN_KARAOKEEN,COLUMN_SOUND} , COLUMN_ID + "=?",
                new String[]{String.valueOf(id)},null,null,null,null);

        if (cursor !=null)
            cursor.moveToFirst();

        Logistic logistic = new Logistic(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),
                cursor.getString(5),cursor.getString(6),cursor.getString(7));
        return logistic;

    }


    public List<Logistic> getAllLogistic(){
        List<Logistic> logisticList = new ArrayList<Logistic>();

        String selectQry ="SELECT * FROM " + TABLE_LOGISTIC;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQry,null);

        if(cursor.moveToFirst()){
            do{
                Logistic logistic = new Logistic();
                logistic.setId(Integer.parseInt(cursor.getString(0)));
                logistic.setLangFrom(cursor.getString(1));
                logistic.setLangTo(cursor.getString(2));
                logistic.setWordFrom(cursor.getString(3));
                logistic.setWordTo(cursor.getString(4));
                logistic.setKaraokeTH(cursor.getString(5));
                logistic.setKaraokeEN(cursor.getString(6));
                logistic.setSound(cursor.getString(7));

                logisticList.add(logistic);

            }while (cursor.moveToNext());
        }
        return logisticList;
    }

    public void deleteLogistic(Logistic logistic){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LOGISTIC, COLUMN_ID + " = ?",
                new String[] {String.valueOf(logistic.getId())});
        db.close();
    }

    //count

    public int getLogisticCount(){
        String countQry = "SELECT * FROM " + TABLE_LOGISTIC;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQry,null);
        cursor.close();

        return cursor.getCount();
    }




    //Upgrade---------------------------------------------------------------------------------------
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONVERSATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_THING);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLACE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMERGENCY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGISTIC);
        onCreate(db);
    }


}







