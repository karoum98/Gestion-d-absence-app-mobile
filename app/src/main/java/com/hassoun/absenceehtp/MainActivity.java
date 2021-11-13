package com.hassoun.absenceehtp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;
import com.hassoun.absenceehtp.Fragment.FirstyearFragment;
import com.hassoun.absenceehtp.Fragment.SecondyearFragment;
import com.hassoun.absenceehtp.Fragment.ThirdyearFragment;
import com.hassoun.absenceehtp.Adapter.ViewpagerAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;
    TextView datepicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout=findViewById(R.id.tablayout);
        viewPager=findViewById(R.id.viewpager);
        toolbar=findViewById(R.id.toolbar);
        datepicker=findViewById(R.id.datepicker);

        ViewpagerAdapter viewpagerAdapter=new ViewpagerAdapter(getSupportFragmentManager());
        viewpagerAdapter.AddFragment(new FirstyearFragment(),"1st year");
        viewpagerAdapter.AddFragment(new SecondyearFragment(),"2nd year");
        viewpagerAdapter.AddFragment(new ThirdyearFragment(),"3rd year");

        viewPager.setAdapter(viewpagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        toolbar.inflateMenu(R.menu.menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if(menuItem.getItemId()==R.id.logout){
                    Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                }

                return false;
            }
        });
        DateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
        datepicker.setText(String.valueOf(dateFormat.format(new Date())));




    }
}