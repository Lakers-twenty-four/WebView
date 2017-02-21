package com.example.administrator.webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化视图
        initView();
    }

    //用途四：
    //如果希望浏览的网页后退而不是退出浏览器，
    // 需要WebView覆盖URL加载，让它自动生成历史访问记录，那样就可以通过前进或后退访问已访问过的站点。
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView.canGoBack()) {
                mWebView.goBack();//返回上一页面
                return true;
            } else {
                System.exit(0);//退出程序
            }
        }
        return super.onKeyDown(keyCode, event);
    }




    //初始化视图
    private void initView() {

        mWebView = (WebView) findViewById(R.id.wv);

        //load加载   WebView加载web资源
        mWebView.loadUrl("http://a1.gdcp.cn/index.shtml");

        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                return true;


            }

            //用途三：

            //如果访问的页面中有Javascript，则webview必须设置支持Javascript

            //启用支持javascript
            //WebSettings settings=mWebView.getSettings();







        });

        //用途五：判断页面加载过程
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                if (newProgress == 100) {
                    // 网页加载完成
                    Toast.makeText(MainActivity.this,"网页加载完成",Toast.LENGTH_SHORT).show();

                } else {
                    // 加载中
                    Toast.makeText(MainActivity.this,"加载中",Toast.LENGTH_SHORT).show();

                }

            }



        });

        //用途六：缓存的使用
        //优先使用缓存
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        //不使用缓存：
        //mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);


    }

}


