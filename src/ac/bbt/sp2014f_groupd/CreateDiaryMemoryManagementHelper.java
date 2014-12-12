
package ac.bbt.sp2014f_groupd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CreateDiaryMemoryManagementHelper extends SQLiteOpenHelper{

	// コンストラクタ定義
	public CreateDiaryMemoryManagementHelper(Context con){
		// SQLiteOpenHelperのコンストラクタ呼び出し
		super(con,"sp2014f_groupd",null,1);
	}

	// onCreateメソッド
	@Override
	public void onCreate(SQLiteDatabase db) {
	}
 

	// onUpgradeメソッド
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
	}
	
}
