package com.example.sarabjeet.producthunt.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by sarabjeet on 11/4/17.
 */

public class ProductDbContract {

    static final String AUTHORITY = "com.example.sarabjeet.producthunt";
    static final String PATH_QUOTE = "quote";
    private static final Uri BASE_URI = Uri.parse("content://" + AUTHORITY);

    public ProductDbContract(){

    }


    public static final class ProductData implements BaseColumns{
        public static final String TABLE_NAME = "products";
        public static final String COLUMN_PROD_NAME = "name";
        public static final String COLUMN_PROD_DESC = "description";
        public static final String COLUMN_PROD_IMG = "img";
        public static final String COLUMN_PROD_CATERGORY = "category";
        public static final String COLUMN_ID = "id";
    }
}
