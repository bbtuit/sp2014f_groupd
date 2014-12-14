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

public class SecondlayoutActivity extends Activity { // Activityクラスを継承

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
		
	}


	// クリックリスナー定義
	class ButtonClickListener1 implements OnClickListener {
		// onClickメソッド(ボタンクリック時イベントハンドラ)
		public void onClick(View v) {

			//保存処理はまだ未実装　中村
			
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

