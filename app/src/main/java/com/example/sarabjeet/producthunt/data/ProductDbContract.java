package com.example.sarabjeet.producthunt.data;

import android.provider.BaseColumns;

/**
 * Created by sarabjeet on 11/4/17.
 */

public class ProductDbContract {

    public static final class ProductData implements BaseColumns{
        public static final String TABLE_NAME = "products";
        public static final String COLUMN_PROD_NAME = "name";
        public static final String COLUMN_PROD_DESC = "description";
        public static final String COLUMN_PROD_IMG = "img";
        public static final String COLUMN_ID = "id";
    }
}
