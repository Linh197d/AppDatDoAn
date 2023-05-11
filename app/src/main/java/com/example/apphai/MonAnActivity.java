package com.example.apphai;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.apphai.DAO.Database;
import com.example.apphai.fragment.MainActivity;
import com.example.apphai.model.MonAn;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MonAnActivity extends AppCompatActivity {
    private Button btnHuy, btnThem;
    Spinner spinner;
    Uri selectedImageUri;
    private ImageView imgLoadData;
    EditText edtName,edtGia,edtMoTa,edtNguyenlieu;
    MonAn monAn;
    TextView tvtName,tvtGia,tvtMota,tvtNguyenlieu;
    Button btnXoa,btnSua;
    ImageView imgMonAn,imgBack;
    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mon_an);
        tvtGia= findViewById(R.id.tvt_gia);
        tvtMota= findViewById(R.id.tvt_mota);
        tvtNguyenlieu= findViewById(R.id.tvt_nguyenlieu);
        tvtName= findViewById(R.id.tvt_Name);
        btnSua= findViewById(R.id.sua);
        btnXoa= findViewById(R.id.xoa);
        imgMonAn= findViewById(R.id.img_MonAn);
        imgBack=findViewById(R.id.back);
        floatingActionButton= findViewById(R.id.floating_button);
        initView();
    }

    private void initView() {
        if (getIntent().getExtras() != null) {
            monAn = (MonAn) getIntent().getExtras().get("object_data");
            if (monAn != null) {
                tvtGia.setText(""+monAn.getPrice()+" VNĐ");
                tvtName.setText(monAn.getName());
                tvtNguyenlieu.setText(monAn.getNguyenlieu().toString());
                tvtMota.setText(monAn.getMota().toString());
                switch (monAn.getResourceId()){

                    case 1:
                        imgMonAn.setImageResource(R.drawable.slide1);
                        break;
                    case 2:
                        imgMonAn.setImageResource(R.drawable.slide4);
                        break;
                    case 3:
                        imgMonAn.setImageResource(R.drawable.slide3);
                        break;
                    case 4:
                        imgMonAn.setImageResource(R.drawable.slide2);
                        break;
                    case 5:
                        imgMonAn.setImageResource(R.drawable.ga_rang_muoi);
                        break;

                }
            }
        } else {
            return;
        }
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_OK, null);
                finish();
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MonAnActivity.this)
                        .setTitle("Xác nhận xoá dữ liệu?")
                        .setMessage("Bạn có chắc chắn không?")
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Database.getInstance(MonAnActivity.this).dataGD_dao().deleteData(monAn);
                                Toast.makeText(MonAnActivity.this, "Delete Success", Toast.LENGTH_SHORT).show();
                                setResult(Activity.RESULT_OK, null);
                                finish();
                            }
                        })
                        .setNegativeButton("Không", null)
                        .show();
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editData(monAn);
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDIalogThemVaoGIoHang();
            }
        });
    }

    private void openDIalogThemVaoGIoHang() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.bottomsheet_giohang);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        Button btnHuy = dialog.findViewById(R.id.huybo_bottomsheet);
        Button btnThem = dialog.findViewById(R.id.themhang_bottomsheet);
        TextView tvtNameGio = dialog.findViewById(R.id.tvt_monan_gio);
        TextView tvtGiaGio = dialog.findViewById(R.id.tvt_gia_gio);
        EditText editText = dialog.findViewById(R.id.edt_soluong_gio);
        tvtGiaGio.setText(tvtGia.getText());
        tvtNameGio.setText(tvtName.getText());

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monAn.setSoluong(Integer.parseInt(editText.getText().toString()));

                dialog.dismiss();
                Toast.makeText(MonAnActivity.this, "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void editData(MonAn monAn) {
        Dialog dialog = new Dialog(this);
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
        edtGia.setText(monAn.getPrice()+"");
        edtMoTa.setText(tvtMota.getText().toString());
        edtName.setText(tvtName.getText().toString());
        edtNguyenlieu.setText(tvtNguyenlieu.getText().toString());
        btnThem.setText("Sửa");
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
                edit();
//                    requestPermissionGallery();
            }
        });
//        btnChonAnh.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openImageGallery();
//
//            }
//        });
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(MonAnActivity.this,
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
                        selectedImageUri = intent.getData();// gallery
                        //binding.btnCamera.setImageURI(selectedImageUri);
                        Glide.with(MonAnActivity.this)
                                .load(selectedImageUri)
                                .into(imgLoadData);

                    }
                }
            });


    private void edit() {
        if (edtName.getText().toString().isEmpty() || edtNguyenlieu.getText().toString().isEmpty() || edtMoTa.getText().toString().isEmpty() || edtGia.getText().toString().isEmpty()) {
            Toast.makeText(this, "Không được để trống ", Toast.LENGTH_SHORT).show();
        } else {
            monAn.setMota(edtMoTa.getText().toString());
            monAn.setName(edtName.getText().toString());
            monAn.setNguyenlieu(edtNguyenlieu.getText().toString());
            monAn.setPrice(Integer.parseInt(edtGia.getText().toString()));
            monAn.setResourceId(Integer.parseInt(spinner.getSelectedItem().toString()));

//            mListData.add(data);
            if (checkData(monAn.getResourceId(), monAn.getName(), monAn.getPrice())) {
                Toast.makeText(this, "Data Exist", Toast.LENGTH_SHORT).show();
                return;
            }
            Database.getInstance(this).dataGD_dao().updateData(monAn);
            Toast.makeText(this, "Update success", Toast.LENGTH_SHORT).show();
//            setResult(Activity.RESULT_OK, null);
//            finish();
        }
    }

    private boolean checkData(int resourceId, String name, int price) {
        List<MonAn> list = Database.getInstance(this).dataGD_dao().checkData(resourceId, name, price);
        return list != null && !list.isEmpty();
    }
}
