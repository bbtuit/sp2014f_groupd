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
import android.util.Log;
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

public class ThirdlayoutActivity extends Activity { // Activityクラスを継承

	CreateDiaryMemoryManagementHelper helper = null;
	SQLiteDatabase db = null;
	
	// onCreateメソッド(画面初期表示イベントハンドラ)
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		// スーパークラスのonCreateメソッド呼び出し
        super.onCreate(savedInstanceState);
        // レイアウト設定ファイルの指定
        setContentView(R.layout.fragment_thirdlayout);
        
		// 保存ボタンオブジェクト取得
		Button button1 = (Button)findViewById(R.id.button3_1);
		//タグの設定
		button1.setTag("button3_1");
		// ボタンオブジェクトにクリックリスナー設定
		button1.setOnClickListener(new ButtonClickListener());

		// 戻るボタンオブジェクト取得
		Button button2 = (Button)findViewById(R.id.button3_2);
		//タグの設定
		button2.setTag("button3_2");
		// ボタンオブジェクトにクリックリスナー設定
		button2.setOnClickListener(new ButtonClickListener());
		
		//DB作成
		helper = new CreateDiaryMemoryManagementHelper(ThirdlayoutActivity.this);
		
	}

	// クリックリスナー定義
	class ButtonClickListener implements OnClickListener {
		// onClickメソッド(ボタンクリック時イベントハンドラ)
		public void onClick(View v) {

			// タグの取得
			String tag = (String)v.getTag();
			
			//保存処理
			if(tag.equals("button3_1")){
				
				//保存時メッセージ(未実装)　By 中村
				
				//入力情報取得
				EditText date_time = (EditText)findViewById(R.id.date_id);
				Spinner in_dec = (Spinner)findViewById(R.id.plus_minus);
				String in_decrease = (String)in_dec.getSelectedItem();
				Spinner hour_num = (Spinner)findViewById(R.id.hour_number);
				String hour_nu = (String)hour_num.getSelectedItem();
				Spinner minute_num = (Spinner)findViewById(R.id.minute_number);
				String minute_nu = (String)minute_num.getSelectedItem();				
				Spinner sele_category = (Spinner)findViewById(R.id.select_category);
				String sel_category = (String)sele_category.getSelectedItem();				
				
				// 該当DBオブジェクト取得
				db = helper.getWritableDatabase();
				
				//時間と分を合計してテーブル登録用データに変換→やり方がわからないため、とりあえず時間だけ登録する。
				//int life_time = hour_nu + minute_nu;

				//増加、減少の判定処理
				if(in_decrease.equals("＋")){
				
					//実績時間増加処理

					// テーブル作成
				try{
					// SQL文定義
						String sql
							= "create table diary_memory_managment (" +
								"_id integer primary key autoincrement," +
								"day text not null," +
								"category text not null," +
								"life_time text not null)";
						// SQL実行
						db.execSQL(sql);

					}catch(Exception e){
						Log.e("ERROR",e.toString());
					}
					
					// データ登録
					// トランザクション制御開始
						db.beginTransaction();
		
						// 登録データ設定
						ContentValues val = new ContentValues();
						val.put("day", date_time.getText().toString());
						val.put("category", sel_category);
						//★本当は時間＋分にしたいけどやりから分からず★
						val.put("life_time", hour_nu);
						// データ登録
						db.insert("diary_memory_managment", null, val);
		
						// コミット
						db.setTransactionSuccessful();
		
						// トランザクション制御終了
						db.endTransaction();				
				}else if (in_decrease.equals("−")){
/*　未作成				
					//実績時間減少処理

					// テーブル作成
					// SQL文定義
						String sql
							= "create table diary_memory_managment (" +
								"_id integer primary key autoincrement," +
								"day text not null," +
								"category text not null," +
								"life_time text not null)";
						// SQL実行
						db.execSQL(sql);
		
					// データ登録
					// トランザクション制御開始
						db.beginTransaction();
		
						// 登録データ設定
						ContentValues val = new ContentValues();
						val.put("day", date_time.getText().toString());
						val.put("category", sel_category.getText().toString());
						//★本当は時間＋分にしたいけどやりから分からず★
						val.put("life_time", hour_nu.getText().toString());
						// データ登録
						db.update("diary_memory_managment", null, val);
		
						// コミット
						db.setTransactionSuccessful();
		
						// トランザクション制御終了
						db.endTransaction();				
*/					
				}
			
				// DBオブジェクトクローズ
				db.close();
/*	
//エラーが出るため一時コメントアウト　中村
				showDialog();

				//メッセージ表示
				private void showDialog() {
					AlertDialog.Builder dialog = new AlertDialog.Builder(ThirdlayoutActivity.this);
					dialog.setTitle("確認画面");
					dialog.setMessage("保存しました。");
					dialog.setPositiveButton("OK",new DialogInterface.OnClickListener() {
			 
						public void onClick(DialogInterface dialog,int whichButton) {
							// 次のアクティビティの起動
							finish();
						}
					});
					dialog.show();
				}

			// 次のアクティビティの起動
			//finish();
*/			
			//戻る処理
			}else if(tag.endsWith("button3_2")){

				// 次のアクティビティの起動
				finish();
		
			}
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