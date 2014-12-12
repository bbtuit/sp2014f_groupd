package ac.bbt.sp2014f_groupd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CreateDailymemoryHelper extends SQLiteOpenHelper {

    // �R���X�g���N�^��`
    public CreateDailymemoryHelper(Context con){
        // SQLiteOpenHelper�̃R���X�g���N�^�Ăяo��
        super(con,"dbsample",null,1);
    }

    // onCreate���\�b�h
    @Override
    public void onCreate(SQLiteDatabase db) {
    	// SQL����`
    	String sql
    	="create table diary_memory_managment (" +
    		"diary_id integer primary key autoincrement," +
    		"day integer not null," +
    		"categoryl text not null," +
    		"life_time integer)";
    	
        // SQL���s
        db.execSQL(sql);
    }

    // onUpgrade���\�b�h
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
    }

}