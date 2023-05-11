package com.example.apphai.fragment;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.apphai.DAO.Database;
import com.example.apphai.Dialog;
import com.example.apphai.IClickMonAnHome;
import com.example.apphai.adapter.SlideMonAnAdapter;
import com.example.apphai.model.MonAn;
import com.example.apphai.R;
import com.example.apphai.model.SlideMonAN;
import com.example.apphai.adapter.HomeAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;


public class HomeFragment extends Fragment {
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private List<SlideMonAN> list;
    private RecyclerView rcvMonAn;
    private FloatingActionButton floatingActionButton;
    private HomeAdapter homeAdapter;
    FragmentTransaction fragmentTransaction;
    private SlideMonAnAdapter slideAdapter;
    private Button btnHuy, btnThem;
    private Spinner spinner;
    private ImageView imgLoadData;
    private EditText edtName, edtGia, edtMoTa, edtNguyenlieu;
    private MainActivity mainActivity;
    private Handler mHandler= new Handler();
    private Runnable mRunnable= new Runnable() {
        @Override
        public void run() {
            if(viewPager.getCurrentItem()== list.size()-1){
                viewPager.setCurrentItem(0);
            }else{
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            }
        }
    };



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager = view.findViewById(R.id.viewpager);
        circleIndicator = view.findViewById(R.id.circle_indicator);
        rcvMonAn= view.findViewById(R.id.rcv_monAn);
        mainActivity= (MainActivity)getActivity();
        floatingActionButton=view.findViewById(R.id.floating_button);
        initView();
        return view;
    }

    private void initView() {
        slideAdapter = new SlideMonAnAdapter(getContext(), getListSlide());
        viewPager.setAdapter(slideAdapter);
        circleIndicator.setViewPager(viewPager);
        slideAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
        mHandler.postDelayed(mRunnable,5000);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mHandler.removeCallbacks(mRunnable);
                mHandler.postDelayed(mRunnable,5000);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        // recyclerview
        homeAdapter = new HomeAdapter(getContext(), new IClickMonAnHome() {
            @Override
            public void OnClickMonAnHome(MonAn monAn) {
                onClickItem(monAn);
            }
        });
        GridLayoutManager gridLayoutManager= new GridLayoutManager(getContext(),2);
        rcvMonAn.setLayoutManager(gridLayoutManager);
        homeAdapter.setData(Database.getInstance(getActivity()).dataGD_dao().getListData());
        rcvMonAn.setAdapter(homeAdapter);
        //floating button them
        rcvMonAn.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if(dy>0){
                    floatingActionButton.hide();
                }else {
                    floatingActionButton.show();
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openDialog();
            }
        });
    }

    private void openDialog()
        {
                    Dialog dialog = new Dialog(getContext());
            dialog.setContentView(R.layout.fragment_them_mon);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
            dialog.getWindow().setGravity(Gravity.BOTTOM);
             btnHuy = dialog.findViewById(R.id.btnHuy);
             btnThem = dialog.findViewById(R.id.btnThem);
             spinner= dialog.findViewById(R.id.spinner);
             imgLoadData= dialog.findViewById(R.id.img_loaddata);
             edtGia = dialog.findViewById(R.id.edtGia);
             edtMoTa = dialog.findViewById(R.id.edtMoTa);
             edtNguyenlieu = dialog.findViewById(R.id.edtNguyenlieu);
             edtName = dialog.findViewById(R.id.edtMonAn);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                    R.array.planets_array, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    switch (position){
                        case 0:
                            imgLoadData.setImageResource(R.drawable.ga_rang_muoi);

                            break;
                        case 1:
                            imgLoadData.setImageResource(R.drawable.slide4);
                            break;
                        case 2:
                            imgLoadData.setImageResource(R.drawable.slide1);
                            break;
                        case 3:
                            imgLoadData.setImageResource(R.drawable.slide2);
                            break;
                        case 4:
                            imgLoadData.setImageResource(R.drawable.slide3);
                            break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            btnHuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            btnThem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    themData();
                }
            });
//            btnChonAnh.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    openImageGallery();
//
//                }
//            });
        }


    public void openImageGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResult.launch(intent);//intent //gallery
    }
    final ActivityResultLauncher<Intent> mActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
            , new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent intent = result.getData();
                            Uri selectedImageUri = intent.getData();// gallery
                            //binding.btnCamera.setImageURI(selectedImageUri);
                            Glide.with(getContext())
                                    .load(selectedImageUri)
                                    .into(imgLoadData);
                        
                    }
                }
            });

    private void themData() {
        if (edtName.getText().toString().isEmpty() || edtNguyenlieu.getText().toString().isEmpty() || edtMoTa.getText().toString().isEmpty() || edtGia.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), "Không được để trống ", Toast.LENGTH_SHORT).show();
        } else {

            MonAn data = new MonAn(Integer.parseInt(spinner.getSelectedItem().toString()), edtName.getText().toString(), Integer.parseInt(edtGia.getText().toString()), edtMoTa.getText().toString(), edtNguyenlieu.getText().toString());
//            mListData.add(data);
            if (checkData(data.getResourceId(), data.getName(), data.getPrice())) {
                Toast.makeText(getActivity(), "Data Exist", Toast.LENGTH_SHORT).show();
                return;
            }
            Database.getInstance(getActivity()).dataGD_dao().insertData(data);
            Toast.makeText(getActivity(), "Add success", Toast.LENGTH_SHORT).show();
//            homeAdapter.notifyDataSetChanged();
//            rcvMonAn.setAdapter(homeAdapter);
        }
    }


    private void onClickItem(MonAn monAn) {
        mainActivity.goToThemMon(monAn);
    }
    private boolean checkData(int rscID,String name, int gia) {
        List<MonAn> list = Database.getInstance(getActivity()).dataGD_dao().checkData(rscID, name, gia);
        return list != null && !list.isEmpty();
    }
    

    private List<SlideMonAN> getListSlide() {
        list = new ArrayList<>();
        list.add(new SlideMonAN(R.drawable.slide1));
        list.add(new SlideMonAN(R.drawable.slide2));
        list.add(new SlideMonAN(R.drawable.slide3));
        list.add(new SlideMonAN(R.drawable.slide4));
        return list;
    }
    @Override
    public void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        mHandler.postDelayed(mRunnable,3000);
        homeAdapter.setData(Database.getInstance(getActivity()).dataGD_dao().getListData());
        homeAdapter.notifyDataSetChanged();
    }


}