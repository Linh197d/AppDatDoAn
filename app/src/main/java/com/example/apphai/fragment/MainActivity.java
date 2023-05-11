package com.example.apphai.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.apphai.DAO.Database;
import com.example.apphai.MonAnActivity;
import com.example.apphai.R;
import com.example.apphai.adapter.ViewPagerAdapter;
import com.example.apphai.model.MonAn;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private static final int MY_REQUEST_CODE = 10;
    private ViewPager viewPager;
    private BottomNavigationView bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        viewPager= findViewById(R.id.viewpager);
         bottomNavigation = findViewById(R.id.bottombar);
        setViewPager();
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.btnTrangchu:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.btnGioHang:
                        viewPager.setCurrentItem(1);

                        break;

                    case R.id.btnLsu:
                        viewPager.setCurrentItem(2);

                        break;


                }
                return true;
            }
        });
    }
    private void setViewPager(){
        ViewPagerAdapter viewPagerAdapter= new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bottomNavigation.getMenu().findItem(R.id.btnTrangchu).setChecked(true);
                        break;
                    case 1:
                        bottomNavigation.getMenu().findItem(R.id.btnGioHang).setChecked(true);
                        break;
                    case 2:
                        bottomNavigation.getMenu().findItem(R.id.btnLsu).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    public void goToThemMon(MonAn monAn){
        Intent intent = new Intent(MainActivity.this, MonAnActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_data", monAn);
        intent.putExtras(bundle);
        startActivityForResult(intent, MY_REQUEST_CODE);
//        FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.frame_layout_parent,new ThemMonAnFragment());
//        fragmentTransaction.commit();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

        }
    }

}