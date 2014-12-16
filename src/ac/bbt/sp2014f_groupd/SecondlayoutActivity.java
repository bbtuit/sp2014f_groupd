package ac.bbt.sp2014f_groupd;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.*;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Spinner;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.widget.DatePicker;
import android.widget.TimePicker;

public class SecondlayoutActivity extends Activity { // Activityクラスを継承

	CreateTargetManagementHelper helper = null;
	SQLiteDatabase db = null;	
	
	// onCreateメソッド(画面初期表示イベントハンドラ)
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		// スーパークラスのonCreateメソッド呼び出し
        super.onCreate(savedInstanceState);
        // レイアウト設定ファイルの指定
        setContentView(R.layout.fragment_secondlayout);


/*
    	// インテント取得
    	Intent data = getIntent();
 */       
		// ボタンオブジェクト取得
		Button button1 = (Button)findViewById(R.id.button2_1);
		//タグの設定
		//button1.setTag("button2_1");
		// ボタンオブジェクトにクリックリスナー設定
		button1.setOnClickListener(new ButtonClickListener1());
		
		// ボタンオブジェクト取得
		Button button2 = (Button)findViewById(R.id.button2_2);
		// ボタンオブジェクトにクリックリスナー設定
		button2.setOnClickListener(new ButtonClickListener2());
		
		//DB作成
		helper = new CreateTargetManagementHelper(SecondlayoutActivity.this);
	}


	// クリックリスナー定義
	class ButtonClickListener1 implements OnClickListener {
		// onClickメソッド(ボタンクリック時イベントハンドラ)
		public void onClick(View v) {

			//入力情報取得
			//選択した作業項目の値取得
			Spinner sele_category = (Spinner)findViewById(R.id.select_category);
			String sel_category = (String)sele_category.getSelectedItem();
			
			//選択した目標期間の値取得
			Spinner sele_period = (Spinner)findViewById(R.id.select_period);
			String sel_period = (String)sele_period.getSelectedItem();		
			
			//入力した目標時間数の値取得
			EditText date_time = (EditText)findViewById(R.id.target_hours);			
			
			// 該当DBオブジェクト取得
			db = helper.getWritableDatabase();
			
			try{
				// SQL文定義
					String sql
						= "create table target_managment (" +
							"target_category_id integer primary key autoincrement," +
							"category text not null," +
							"target_set text not null," +
							"range integer not null)";
			
					// SQL実行
					db.execSQL(sql);			
			
			}catch(Exception e){
				Log.e("ERROR",e.toString());
			}
			
			// データ登録
			try{
			
				// トランザクション制御開始
				db.beginTransaction();
	
				// 登録データ設定
				ContentValues val = new ContentValues();
				val.put("category", sel_category);
				val.put("target_set", sel_period);
				val.put("range", date_time.getText().toString());
				// データ登録
				db.insert("diary_memory_managment", null, val);
	
				// コミット
				db.setTransactionSuccessful();
	
				// トランザクション制御終了
				db.endTransaction();
			}catch(Exception e){
				Log.e("ERROR" ,e.toString());
			}
		
			// DBオブジェクトクローズ
			db.close();
			
			//保存時メッセージ
			//showDialog();
				
				
				
				
			// アクティビティ終了(画面クローズ)
			finish();
			
		}
	}

	// クリックリスナー定義
	class ButtonClickListener2 implements OnClickListener {
		// onClickメソッド(ボタンクリック時イベントハンドラ)
		public void onClick(View v) {
			// アクティビティ終了(画面クローズ)
			finish();
		}
	}

	// onCreateOptionsMenuメソッド(オプションメニュー生成)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // オプションメニューを設定
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

