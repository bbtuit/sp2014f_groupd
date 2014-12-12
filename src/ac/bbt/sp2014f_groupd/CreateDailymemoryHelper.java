package ac.bbt.sp2014f_groupd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CreateDailymemoryHelper extends SQLiteOpenHelper {

    // コンストラクタ定義
    public CreateDailymemoryHelper(Context con){
        // SQLiteOpenHelperのコンストラクタ呼び出し
        super(con,"dbsample",null,1);
    }

    // onCreateメソッド
    @Override
    public void onCreate(SQLiteDatabase db) {
    	// SQL文定義
    	String sql
    	="create table diary_memory_managment (" +
    		"diary_id integer primary key autoincrement," +
    		"day integer not null," +
    		"categoryl text not null," +
    		"life_time integer)";
    	
        // SQL実行
        db.execSQL(sql);
    }

    // onUpgradeメソッド
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
    }

}