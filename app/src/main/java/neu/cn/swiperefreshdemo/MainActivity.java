package neu.cn.swiperefreshdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import neu.cn.swiperefreshdemo.fragment.BaiDuRefreshFragment;
import neu.cn.swiperefreshdemo.fragment.ELeMaRefreshFragment;
import neu.cn.swiperefreshdemo.fragment.JingDongRefreshFragment;
import neu.cn.swiperefreshdemo.fragment.MeiTuanRefreshFragment;
import neu.cn.swiperefreshdemo.fragment.TianMaoRefreshFragment;
import neu.cn.swiperefreshdemo.fragment.WeatherFragment;
import neu.cn.swiperefreshdemo.fragment.WeiBoRefreshFragment;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    private NavigationView mNavigationView;

    private FloatingActionButton mFloatingActionButton;

    private FragmentManager mFragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.floating_action_button);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "觉得不错点个赞吧！", Toast.LENGTH_SHORT).show();
            }
        });
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        // 开始默认打开第一个下拉刷新效果
        getSupportActionBar().setTitle("WeatherRefreshHeader");
        WeatherFragment weatherFragment = new WeatherFragment();
        mFragmentManager.beginTransaction().replace(R.id.content_frame, weatherFragment).commit();
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_weather:
                        getSupportActionBar().setTitle("WeatherRefreshHeader");
                        WeatherFragment weatherFragment = new WeatherFragment();
                        mFragmentManager.beginTransaction().replace(R.id.content_frame, weatherFragment).commit();
                        break;
                    case R.id.nav_jingdong:
                        getSupportActionBar().setTitle("JingDongRefreshHeader");
                        JingDongRefreshFragment jingDongRefreshFragment = new JingDongRefreshFragment();
                        mFragmentManager.beginTransaction().replace(R.id.content_frame, jingDongRefreshFragment).commit();
                        break;
                    case R.id.nav_tianmao:
                        getSupportActionBar().setTitle("TianMaoRefreshHeader");
                        TianMaoRefreshFragment tianMaoRefreshFragment = new TianMaoRefreshFragment();
                        mFragmentManager.beginTransaction().replace(R.id.content_frame, tianMaoRefreshFragment).commit();
                        break;
                    case R.id.nav_weibo:
                        getSupportActionBar().setTitle("WeiBoRefreshHeader");
                        WeiBoRefreshFragment weiBoRefreshFragment = new WeiBoRefreshFragment();
                        mFragmentManager.beginTransaction().replace(R.id.content_frame, weiBoRefreshFragment).commit();
                        break;
                    case R.id.nav_meituanwaimai:
                        getSupportActionBar().setTitle("MeiTuanRefreshHeader");
                        MeiTuanRefreshFragment meiTuanRefreshFragment = new MeiTuanRefreshFragment();
                        mFragmentManager.beginTransaction().replace(R.id.content_frame, meiTuanRefreshFragment).commit();
                        break;
                    case R.id.nav_elema:
                        getSupportActionBar().setTitle("ELeMaRefreshHeader");
                        ELeMaRefreshFragment eLeMaRefreshFragment = new ELeMaRefreshFragment();
                        mFragmentManager.beginTransaction().replace(R.id.content_frame, eLeMaRefreshFragment).commit();
                        break;
                    case R.id.nav_baiduwaimai:
                        getSupportActionBar().setTitle("BaiDuRefreshHeader");
                        BaiDuRefreshFragment baiDuRefreshFragment = new BaiDuRefreshFragment();
                        mFragmentManager.beginTransaction().replace(R.id.content_frame, baiDuRefreshFragment).commit();
                        break;
                    default:
                        break;
                }
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                break;
        }
        return true;
    }
}
