package com.example.sarabjeet.producthunt.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by sarabjeet on 11/4/17.
 */

public class ProductProvider extends ContentProvider {

    private static final int QUOTE = 100;

    private static final UriMatcher uriMatcher = buildUriMatcher();
    private ProductDbHelper dbHelper;

    private static UriMatcher buildUriMatcher() {
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(ProductDbContract.AUTHORITY, ProductDbContract.PATH_QUOTE, QUOTE);
        return matcher;
    }

    @Override
    public boolean onCreate() {
        dbHelper = new ProductDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor retCursor;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        retCursor = db.query(ProductDbContract.ProductData.TABLE_NAME, projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);
        Context context = getContext();
        if (context != null) {
            retCursor.setNotificationUri(context.getContentResolver(), uri);
        }
        return retCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri returnUri;

        switch (uriMatcher.match(uri)) {
            case QUOTE:
                db.insert(
                        ProductDbContract.ProductData.TABLE_NAME,
                        null,
                        values
                );
                returnUri = ProductDbContract.ProductData.URI;
                break;
            default:
                throw new UnsupportedOperationException("Unknown URI:" + uri);
        }

        Context context = getContext();
        if (context != null) {
            context.getContentResolver().notifyChange(uri, null);
        }

        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rowsDeleted;

        if (null == selection) {
            selection = "1";
        }
        switch (uriMatcher.match(uri)) {
            case QUOTE:
                rowsDeleted = db.delete(
                        ProductDbContract.ProductData.TABLE_NAME,
                        selection,
                        selectionArgs
                );
                break;
            default:
                throw new UnsupportedOperationException("Unknown URI:" + uri);
        }
        if (rowsDeleted != 0) {
            Context context = getContext();
            if (context != null) {
                context.getContentResolver().notifyChange(uri, null);
            }
        }

        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String
            selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}

