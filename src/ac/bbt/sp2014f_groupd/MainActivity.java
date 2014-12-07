package ac.bbt.sp2014f_groupd;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.os.Build;

public class MainActivity extends Activity {

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
		
	}

	// クリックリスナー定義
	class ButtonClickListener implements OnClickListener {
		// onClickメソッド(ボタンクリック時イベントハンドラ)
		public void onClick(View v) {

			// タグの取得
			String tag = (String)v.getTag();
			
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