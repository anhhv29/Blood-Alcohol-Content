package anhhv.blood_alcohol_content;

import static anhhv.blood_alcohol_content.R.drawable.text_box_active;
import static anhhv.blood_alcohol_content.R.drawable.text_box_default;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    public static final int MALE = 1;
    public static final int FEMALE = 2;
    public static final int BEER = 1;
    public static final int BRANDY = 2;
    public static final int WINE = 3;
    public static final int MOTO = 1;
    public static final int CAR = 2;
    public static final int BIKE = 3;
    public static final String KEY_SEX = "sex";
    public static final String KEY_VEHICLE = "vehicle";
    public static final String KEY_TYPE_DRANK = "typeDrank";
    public static final String KEY_HEIGHT = "height";
    public static final String KEY_DRANK = "drank";
    private TextView tvMale, tvFeMale, tvBeer, tvBrandy, tvWine, tvMotobike, tvCar, tvRudimentaryCar, tvTypeDrank, tvInforDrink;
    private EditText edtHeight, edtNumberDrank;
    private Button btnConfirm;
    private int sex, typeDrank, vehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        tvMale = findViewById(R.id.tvMale);
        tvFeMale = findViewById(R.id.tvFeMale);
        edtHeight = findViewById(R.id.edtHeight);
        tvBeer = findViewById(R.id.tvBeer);
        tvBrandy = findViewById(R.id.tvBrandy);
        tvWine = findViewById(R.id.tvWine);
        edtNumberDrank = findViewById(R.id.edtNumberDrank);
        tvMotobike = findViewById(R.id.tvMotobike);
        tvCar = findViewById(R.id.tvCar);
        tvRudimentaryCar = findViewById(R.id.tvBike);
        tvTypeDrank = findViewById(R.id.tvTypeDrank);
        tvInforDrink = findViewById(R.id.tvInforDrink);
        btnConfirm = findViewById(R.id.btnConfirm);
        edtHeight.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE){
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(textView.getWindowToken(), 0);
                }
                return false;
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String height = edtHeight.getText().toString().trim();
                String drank = edtNumberDrank.getText().toString().trim();
                if (checkValidate(height, drank)) {
                    Intent intent = new Intent(HomeActivity.this, ResultActivity.class);
                    intent.putExtra(KEY_SEX, sex);
                    intent.putExtra(KEY_TYPE_DRANK, typeDrank);
                    intent.putExtra(KEY_VEHICLE, vehicle);
                    intent.putExtra(KEY_HEIGHT, Float.parseFloat(height));
                    intent.putExtra(KEY_DRANK, Float.parseFloat(drank));
                    startActivity(intent);
                }
            }
        });

        tvMale.setOnClickListener(view -> chooseSex(view.getId()));

        tvFeMale.setOnClickListener(view -> chooseSex(view.getId()));

        tvBeer.setOnClickListener(view -> chooseTypeDrank(view.getId()));

        tvBrandy.setOnClickListener(view -> chooseTypeDrank(view.getId()));

        tvWine.setOnClickListener(view -> chooseTypeDrank(view.getId()));

        tvMotobike.setOnClickListener(view -> chooseVehicle(view.getId()));

        tvCar.setOnClickListener(view -> chooseVehicle(view.getId()));

        tvRudimentaryCar.setOnClickListener(view -> chooseVehicle(view.getId()));
    }

    private void chooseSex(int id) {

        tvMale.setBackgroundResource(text_box_default);
        tvMale.setBackgroundResource(text_box_default);
        tvFeMale.setBackgroundResource(text_box_default);
        tvMale.setTextColor(getResources().getColor(R.color.Black));
        tvFeMale.setTextColor(getResources().getColor(R.color.Black));

        switch (id) {
            case R.id.tvMale: {
                sex = MALE;
                tvMale.setBackgroundResource(text_box_active);
                tvMale.setTextColor(getResources().getColor(R.color.White));
                break;
            }
            case R.id.tvFeMale: {
                sex = FEMALE;
                tvFeMale.setBackgroundResource(text_box_active);
                tvFeMale.setTextColor(getResources().getColor(R.color.White));
                break;
            }
        }
    }

    private void chooseTypeDrank(int id) {
        tvBeer.setBackgroundResource(text_box_default);
        tvBrandy.setBackgroundResource(text_box_default);
        tvWine.setBackgroundResource(text_box_default);
        tvBeer.setTextColor(getResources().getColor(R.color.Black));
        tvBrandy.setTextColor(getResources().getColor(R.color.Black));
        tvWine.setTextColor(getResources().getColor(R.color.Black));

        switch (id) {
            case R.id.tvBeer: {
                typeDrank = BEER;
                tvBeer.setBackgroundResource(text_box_active);
                tvBeer.setTextColor(getResources().getColor(R.color.White));
                tvInforDrink.setVisibility(View.VISIBLE);
                tvInforDrink.setText("1 lon bia c?? th??? t??ch: 330ml, ????? c???n 5??");
                tvTypeDrank.setVisibility(View.VISIBLE);
                tvTypeDrank.setText("lon bia");
                break;
            }
            case R.id.tvBrandy: {
                typeDrank = BRANDY;
                tvBrandy.setBackgroundResource(text_box_active);
                tvBrandy.setTextColor(getResources().getColor(R.color.White));
                tvInforDrink.setVisibility(View.VISIBLE);
                tvInforDrink.setText("1 ch??n r?????u m???nh c?? th??? t??ch: 40ml, ????? c???n 30??");
                tvTypeDrank.setVisibility(View.VISIBLE);
                tvTypeDrank.setText("ch??n r?????u");
                break;
            }
            case R.id.tvWine: {
                typeDrank = WINE;
                tvWine.setBackgroundResource(text_box_active);
                tvWine.setTextColor(getResources().getColor(R.color.White));
                tvInforDrink.setVisibility(View.VISIBLE);
                tvInforDrink.setText("1 ly r?????u vang c?? th??? t??ch: 100ml, ????? c???n 13,5??");
                tvTypeDrank.setVisibility(View.VISIBLE);
                tvTypeDrank.setText("ly vang");
                break;
            }
        }
    }

    private void chooseVehicle(int id) {
        tvRudimentaryCar.setBackgroundResource(text_box_default);
        tvMotobike.setBackgroundResource(text_box_default);
        tvCar.setBackgroundResource(text_box_default);
        tvMotobike.setTextColor(getResources().getColor(R.color.Black));
        tvCar.setTextColor(getResources().getColor(R.color.Black));
        tvRudimentaryCar.setTextColor(getResources().getColor(R.color.Black));

        switch (id) {
            case R.id.tvBike: {
                vehicle = BIKE;
                tvRudimentaryCar.setBackgroundResource(text_box_active);
                tvRudimentaryCar.setTextColor(getResources().getColor(R.color.White));
                break;
            }
            case R.id.tvCar: {
                vehicle = CAR;
                tvCar.setBackgroundResource(text_box_active);
                tvCar.setTextColor(getResources().getColor(R.color.White));
                break;
            }
            case R.id.tvMotobike: {
                vehicle = MOTO;
                tvMotobike.setBackgroundResource(text_box_active);
                tvMotobike.setTextColor(getResources().getColor(R.color.White));
                break;
            }
        }
    }

    private boolean checkValidate(String height, String drank) {
        if (sex != MALE && sex != FEMALE) {
            Toast.makeText(HomeActivity.this, "Vui l??ng ch???n gi???i t??nh", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (typeDrank != BEER && typeDrank != BRANDY && typeDrank != WINE) {
            Toast.makeText(HomeActivity.this, "Vui l??ng ch???n lo???i ????? u???ng", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (vehicle != MOTO && vehicle != CAR && vehicle != BIKE) {
            Toast.makeText(HomeActivity.this, "Vui l??ng ch???n lo???i ph????ng ti???n", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (height.isEmpty()) {
            Toast.makeText(HomeActivity.this, "Vui l??ng nh???p c??n n???ng", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (drank.isEmpty()) {
            Toast.makeText(HomeActivity.this, "Vui l??ng nh???p s??? l?????ng ???? u???ng", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }
}
