package com.nothing.dirac_DMP;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.BaseColumns;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/* loaded from: classes4.dex */
public class ParamterProvider extends ContentProvider {
    private static final int AADIRAC_DB_VERSION = 1;
    public static final String AUTHORITY = "com.aa.dirac";
    public static final Uri AUTHORITY_URI;
    public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/meta_parameter";
    public static final String CONTENT_TYPE = "vnd.android.cursor.dir/meta_parameter";
    public static final Uri CONTENT_URI;
    private static final String CREATE_META_PARAMETER = "create table meta_parameter(_id integer primary key,user_mode text not null,tonal_balance numeric(4,2) default 0,stereo_width numeric(4,2) default 0 );";
    private static final String DATABASE_NAME = "aadirac.db";
    public static final String DEFAULT_PARAMETER_FILE = "/system/vendor/etc/default_parameter";
    private static final int META_PARAMETER = 1;
    private static final int META_PARAMETER_ITEM = 2;
    public static final String META_PARAMETER_TABLE_NAME = "meta_parameter";
    public static final double[][] PRESET_META_PARAMETER;
    public static final String[] PRESET_USERCASE;
    public static final String STEREO_WIDTH = "stereo_width";
    private static final String TAG = "ParamterProvider";
    public static final String TONAL_BALANCE = "tonal_balance";
    public static final String USER_MODE = "user_mode";
    private static final UriMatcher sUriMatcher;
    private DataBaseHelper mSqlHelper;

    /* loaded from: classes4.dex */
    public static class MetaParameter implements BaseColumns, MetaParametrColumns {
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/remotecontrol";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/remotecontrol";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(ParamterProvider.AUTHORITY_URI, "remotecontrol");
    }

    /* loaded from: classes4.dex */
    protected interface MetaParametrColumns {
        public static final String HEADSET_GAME = "headset_game";
        public static final String HEADSET_MOVIE = "headset_movie";
        public static final String HEADSET_MUSIC = "headset_music";
        public static final String SPEAK_GAME = "speak_game";
        public static final String SPEAK_MOIVE = "speak_movie";
        public static final String SPEAK_MUSIC = "speak_music";
    }

    static {
        Uri parse = Uri.parse("content://com.aa.dirac");
        AUTHORITY_URI = parse;
        CONTENT_URI = Uri.withAppendedPath(parse, META_PARAMETER_TABLE_NAME);
        PRESET_USERCASE = new String[]{"speak_music", "speak_movie", "speak_game", "headset_music", "headset_movie", "headset_game"};
        PRESET_META_PARAMETER = new double[][]{new double[]{0.0d, -0.5d}, new double[]{0.5d, 0.0d}, new double[]{-0.5d, 0.5d}, new double[]{-0.5d, -0.5d}, new double[]{0.0d, 0.0d}, new double[]{0.5d, 0.5d}};
        UriMatcher uriMatcher = new UriMatcher(-1);
        sUriMatcher = uriMatcher;
        uriMatcher.addURI(AUTHORITY, META_PARAMETER_TABLE_NAME, 1);
        uriMatcher.addURI(AUTHORITY, "meta_parameter/#", 2);
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        this.mSqlHelper = new DataBaseHelper(getContext(), DATABASE_NAME, null, 1);
        Log.d(TAG, "ParamterProvider onCreateed");
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = this.mSqlHelper.getReadableDatabase();
        Cursor cursor = db.query(META_PARAMETER_TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
        return cursor;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case 1:
                return CONTENT_TYPE;
            case 2:
                return CONTENT_ITEM_TYPE;
            default:
                return null;
        }
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues values) {
        String type;
        this.mSqlHelper.getWritableDatabase();
        if (!values.containsKey("user_mode") || (type = values.getAsString("user_mode")) == null || type.isEmpty()) {
            return null;
        }
        SQLiteDatabase db = this.mSqlHelper.getWritableDatabase();
        DecimalFormat df = new DecimalFormat("0.000");
        if (values.containsKey("stereo_width")) {
            values.put("stereo_width", df.format(values.getAsFloat("stereo_width")));
        }
        if (values.containsKey("tonal_balance")) {
            values.put("tonal_balance", df.format(values.getAsFloat("tonal_balance")));
        }
        long id = db.insert(META_PARAMETER_TABLE_NAME, "_id", values);
        Uri newUri = ContentUris.withAppendedId(CONTENT_URI, id);
        return newUri;
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = this.mSqlHelper.getWritableDatabase();
        DecimalFormat df = new DecimalFormat("0.000");
        if (values.containsKey("stereo_width")) {
            values.put("stereo_width", df.format(values.getAsFloat("stereo_width")));
        }
        if (values.containsKey("tonal_balance")) {
            values.put("tonal_balance", df.format(values.getAsFloat("tonal_balance")));
        }
        int num = db.update(META_PARAMETER_TABLE_NAME, values, selection, selectionArgs);
        return num;
    }

    void initDefaultMetaParameter(SQLiteDatabase db) throws IOException {
        File file = new File(DEFAULT_PARAMETER_FILE);
        file.setReadable(true, false);
        if (file.exists()) {
            Log.d(TAG, "/system/vendor/etc/default_parameter exists");
            try {
                SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
                SaxParseXml parseXml = new SaxParseXml();
                FileInputStream stream = new FileInputStream(file);
                parser.parse(stream, parseXml);
                List<UserMode> list = parseXml.getList();
                ContentValues values = new ContentValues();
                DecimalFormat df = new DecimalFormat("0.000");
                int i = 0;
                while (i < list.size()) {
                    values.clear();
                    values.put("user_mode", list.get(i).getName());
                    values.put("tonal_balance", df.format(list.get(i).getTonalBalance()));
                    values.put("stereo_width", df.format(list.get(i).getStereoWidth()));
                    db.insert(META_PARAMETER_TABLE_NAME, "_id", values);
                    i++;
                    parseXml = parseXml;
                    stream = stream;
                }
                Log.d(TAG, "/system/vendor/etc/default_parameter read success!!!");
                return;
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
                return;
            } catch (SAXException e2) {
                e2.printStackTrace();
                return;
            }
        }
        ContentValues values2 = new ContentValues();
        int i2 = 0;
        while (true) {
            String[] strArr = PRESET_USERCASE;
            if (i2 < strArr.length) {
                values2.clear();
                values2.put("user_mode", strArr[i2]);
                double[][] dArr = PRESET_META_PARAMETER;
                values2.put("tonal_balance", Double.valueOf(dArr[i2][2]));
                values2.put("stereo_width", Double.valueOf(dArr[i2][1]));
                db.insert(META_PARAMETER_TABLE_NAME, "_id", values2);
                i2++;
            } else {
                return;
            }
        }
    }

    /* loaded from: classes4.dex */
    class DataBaseHelper extends SQLiteOpenHelper {
        public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(ParamterProvider.CREATE_META_PARAMETER);
            try {
                ParamterProvider.this.initDefaultMetaParameter(db);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d(ParamterProvider.TAG, "init DataBaseHelper finish");
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}
