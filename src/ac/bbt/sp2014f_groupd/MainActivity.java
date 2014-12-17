package ac.bbt.sp2014f_groupd;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.os.Build;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.util.*;
import android.view.Gravity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;

public class MainActivity extends Activity {

	CreateDiaryMemoryManagementHelper helper = null;
	//目標管理のDB検索は未実装　中村
	SQLiteDatabase db = null;	
	
	// onCreateメソッド(画面初期表示イベントハンドラ)
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		// スーパークラスのonCreateメソッド呼び出し
        super.onCreate(savedInstanceState);
        // レイアウト設定ファイルの指定
        setContentView(R.layout.fragment_main);

		// 目標入力ボタンオブジェクト取得
		Button button1 = (Button)findViewById(R.id.button1_1);
		//タグの設定
		button1.setTag("button1_1");
		// ボタンオブジェクトにクリックリスナー設定
		button1.setOnClickListener(new ButtonClickListener());

		// 実績入力ボタンオブジェクト取得
		Button button2 = (Button)findViewById(R.id.button1_2);
		//タグの設定
		button2.setTag("button1_2");
		// ボタンオブジェクトにクリックリスナー設定
		button2.setOnClickListener(new ButtonClickListener());

		// 統計情報ボタンオブジェクト取得
		Button button3 = (Button)findViewById(R.id.button1_3);
		//タグの設定
		button3.setTag("button1_3");
		// ボタンオブジェクトにクリックリスナー設定
		button3.setOnClickListener(new ButtonClickListener());
		
		//DB作成
		helper = new CreateDiaryMemoryManagementHelper(MainActivity.this);
		//目標設定のDBは未定義
	}
/*
	protected void onStart(Bundle savedInstanceState) {

		// メッセージ表示用
		String message  = "";
		TextView label = (TextView)findViewById(R.id.tv_message);
		
		// テーブルレイアウトオブジェクト取得
		TableLayout tablelayout = (TableLayout)findViewById(R.id.tl_list);

		// テーブルレイアウトのクリア
		tablelayout.removeAllViews();

		// 該当DBオブジェクト取得
		db = helper.getWritableDatabase();
		
		// データ取得
		try{
			// 該当DBオブジェクト取得
			db = helper.getReadableDatabase();

			// 列名定義
			String columns[] = {"day","category","unit","life_time"};

			// データ取得
			Cursor cursor = db.query("diary_memory_managment", columns, null, null, null, null, null);

			// テーブルレイアウトの表示範囲を設定
			tablelayout.setStretchAllColumns(true);

			// テーブル一覧のヘッダ部設定
			TableRow headrow = new TableRow(MainActivity.this);
			TextView headtxt1 = new TextView(MainActivity.this);
			headtxt1.setText("日付");
			headtxt1.setGravity(Gravity.CENTER_HORIZONTAL);
			headtxt1.setWidth(60);

			TextView headtxt2 = new TextView(MainActivity.this);
			headtxt2.setText("分類");
			headtxt2.setGravity(Gravity.CENTER_HORIZONTAL);
			headtxt2.setWidth(100);

			TextView headtxt3 = new TextView(MainActivity.this);
			headtxt3.setText("単位");
			headtxt3.setGravity(Gravity.CENTER_HORIZONTAL);
			headtxt3.setWidth(60);

			TextView headtxt4 = new TextView(MainActivity.this);
			headtxt4.setText("時間");
			headtxt4.setGravity(Gravity.CENTER_HORIZONTAL);
			headtxt4.setWidth(60);

			headrow.addView(headtxt1);
			headrow.addView(headtxt2);
			headrow.addView(headtxt3);
			headrow.addView(headtxt4);
			tablelayout.addView(headrow);

			// 取得したデータをテーブル明細部に設定
			while(cursor.moveToNext()){

				TableRow row = new TableRow(MainActivity.this);
				
				TextView day_txt = new TextView(MainActivity.this);
				day_txt.setGravity(Gravity.CENTER_HORIZONTAL);
				day_txt.setText(cursor.getString(0));
				
				TextView category_txt = new TextView(MainActivity.this);
				category_txt.setGravity(Gravity.CENTER_HORIZONTAL);
				category_txt.setText(cursor.getString(1));
				
				TextView unit_txt = new TextView(MainActivity.this);
				unit_txt.setGravity(Gravity.CENTER_HORIZONTAL);
				unit_txt.setText(cursor.getString(2));

				TextView life_time_txt = new TextView(MainActivity.this);
				life_time_txt.setGravity(Gravity.CENTER_HORIZONTAL);
				life_time_txt.setText(cursor.getString(3));
				
				row.addView(day_txt);
				row.addView(category_txt);
				row.addView(unit_txt);
				row.addView(life_time_txt);
				tablelayout.addView(row);

				// メッセージ設定
				message = "データを取得しました！";
			}

		}catch(Exception e){
			// メッセージ設定
			message = "データ取得に失敗しました！";
			Log.e("ERROR",e.toString());
		}
		
		// DBオブジェクトクローズ
		db.close();
	
		// メッセージ表示  
		label.setText(message);
	}
*/	
	// クリックリスナー定義
	class ButtonClickListener implements OnClickListener {
		// onClickメソッド(ボタンクリック時イベントハンドラ)
		public void onClick(View v) {

			// タグの取得
			String tag = (String)v.getTag();
/*	　初期表示時にDB検索する機能は未実装
			// メッセージ表示用
			String message  = "";
			TextView label = (TextView)findViewById(R.id.tv_message);

			// 入力情報取得
			EditText date_time = (EditText)findViewById(R.id.date_id);
			Spinner in_dec = (Spinner)findViewById(R.id.plus_minus);
			String in_decrease = (String)in_dec.getSelectedItem();
			Spinner hour_num = (Spinner)findViewById(R.id.hour_number);
			String hour_nu = (String)hour_num.getSelectedItem();
			Spinner minute_num = (Spinner)findViewById(R.id.minute_number);
			String minute_nu = (String)minute_num.getSelectedItem();				
			Spinner sele_category = (Spinner)findViewById(R.id.select_category);
			String sel_category = (String)sele_category.getSelectedItem();				
			
			// テーブルレイアウトオブジェクト取得
			TableLayout tablelayout = (TableLayout)findViewById(R.id.tl_list);

			// テーブルレイアウトのクリア
			tablelayout.removeAllViews();

			// 該当DBオブジェクト取得
			db = helper.getWritableDatabase();
*/			
			
			//画面2への遷移情報(目標入力)
			if(tag.equals("button1_1")){
				
				// インテントの生成(呼び出すクラスの指定)
				Intent intent1 = new Intent(MainActivity.this, SecondlayoutActivity.class);
				// 次のアクティビティの起動
				startActivity(intent1);
			
			//画面3への遷移情報(日々実績入力)
			}else if(tag.equals("button1_2")){

				// インテントの生成(呼び出すクラスの指定)
				Intent intent2 = new Intent(MainActivity.this, ThirdlayoutActivity.class);
				// 次のアクティビティの起動
				startActivity(intent2);
			
			//画面4への遷移情報(統計情報)			
			}else if(tag.endsWith("button1_3")){

				// インテントの生成(呼び出すクラスの指定)
				Intent intent3 = new Intent(MainActivity.this, FourthlayoutActivity.class);
				// 次のアクティビティの起動
				startActivity(intent3);
		
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


//以下は除外しました。何か問題があれば連絡をお願い致します。中村
/*
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * A placeholder fragment containing a simple view.
     
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}
*/