package com.example.samsung.sample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {



    // FrameLayout에 각 메뉴의 Fragment를 바꿔 줌
    private FragmentManager fragmentManager = getSupportFragmentManager();
    // 4개의 메뉴에 들어갈 Fragment들
    private menu1 menu1 = new menu1();
    private menu2 menu2 = new menu2();
    private menu3 menu3 = new menu3();
    private menu4 menu4 = new menu4();
    private menu5 menu5 = new menu5();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar sss = findViewById(R.id.mtoolbar);
        setSupportActionBar(sss);







        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        BottomNavigationHelper.disableShiftMode(bottomNavigationView);
        // 첫 화면 지정
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, menu1).commitAllowingStateLoss();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (item.getItemId()) {

                    case R.id.action_home: {
                        transaction.replace(R.id.frame_layout, menu1).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.action_hos: {
                        transaction.replace(R.id.frame_layout, menu2).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.action_map: {
                        transaction.replace(R.id.frame_layout, menu3).commitAllowingStateLoss();
                        break;
                    }

                    case R.id.action_faq: {
                        transaction.replace(R.id.frame_layout, menu4).commitAllowingStateLoss();
                        break;
                    }

                    case R.id.action_myp: {
                        transaction.replace(R.id.frame_layout, menu5).commitAllowingStateLoss();
                        break;
                    }



                }
                return true;
            }
        });




    }
 @Override
    public boolean onCreateOptionsMenu(Menu menu)
 {
     getMenuInflater().inflate(R.menu.serch_main,menu);
     MenuItem searchItem = menu.findItem(R.id.action_serch);
     SearchView searchView =(SearchView) searchItem.getActionView();
     searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
         @Override
         public boolean onQueryTextSubmit(String query) {// 돋보기 버튼 submit
             Toast.makeText(MainActivity.this, query, Toast.LENGTH_SHORT).show();
             return true;
         }

         @Override
         public boolean onQueryTextChange(String newText) {//검색어 이벤트
             Log.d("MainActivty", "onQueryTextChange: " + newText);
             return true;



         }
     });


     return super.onCreateOptionsMenu(menu);
 }

}


