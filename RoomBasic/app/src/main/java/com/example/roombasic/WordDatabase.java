package com.example.roombasic;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

//singleton
@Database(entities = {Word.class},version = 1,exportSchema = false)
public abstract class WordDatabase extends RoomDatabase {
    private static WordDatabase INSTANCE;
//    private static final Migration MIGRATION_1_2 = new Migration(1,2) {
//        @Override
//        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            database.execSQL("ALTER TABLE WORD ADD COLUMN show_chinese INTEGER NOT NULL DEFAULT 1 ");
//        }
//    };
//    private static final Migration MIGRATION_3_4 = new Migration(3,4) {
//        @Override
//        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            database.execSQL("CREATE TABLE word_temp (id INTEGER PRIMARY KEY not null,english_word TEXT," +
//                    "chinese_meaning TEXT)");
//            database.execSQL("INSERT INTO word_temp(id,english_word,chinese_meaning) " +
//                    "SELECT id,english_word,chinese_meaning FROM WORD");
//            database.execSQL("DROP TABLE WORD");
//            database.execSQL("ALTER TABLE word_temp RENAME TO WORD");
//        }
//    };
    static synchronized WordDatabase getDatabase(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), WordDatabase.class,"word_database")
//                    .addMigrations(MIGRATION_3_4)
                    .build();
        }
        return INSTANCE;
    }
    public abstract WordDao getWordDao();
}
