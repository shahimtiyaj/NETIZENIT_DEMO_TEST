package com.netizen.netizenittest.Activity;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.netizen.netizenittest.Database.DatabaseHandler;
import com.netizen.netizenittest.ErrorDialog;
import com.netizen.netizenittest.MainActivity;
import com.netizen.netizenittest.ProgressDialogue;
import com.netizen.netizenittest.R;
import com.netizen.netizenittest.model.StudentInfo;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class StudentRegActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private static final int RESULT_LOAD_IMG_FROM_GALLERY = 2;
    private static final int CAMERA_REQUEST = 1;
    private static final int PICK_FROM_GALLERY = 2;
    private static final String TAG = "StudentRegActivity";
    private static final int MY_REQUEST_CODE = 3;
    private final String Tag = getClass().getName();
    private final int CAMERA_RESULT = 1;
    private Context context = StudentRegActivity.this;
    private String picturePath;
    private Button btn_pic_upload;
    private Button student_image;
    private Button btn_profile_submit;
    private Spinner spinner_session, spinner_class, spinner_blood_grp, spinner_contact_person;

    private ImageView imageView;

    private ErrorDialog errorDialog;
    private ProgressDialogue progressDialog;

    private EditText inputStudentName;
    private EditText inputEmail;
    private EditText inputPhoneNumber;
    private EditText inputHouseNo, inputBirthDate, inputArea, inputCity, inputPinCode;

    private RadioGroup genderRadioGroup;
    private RadioButton radioButton;
    private int radioSelectedId;
    private CheckBox checkBox1;

    private String name, email, dateOfBirth, house, city, area, phone, pincode, gender;
    private String selectBloodGrp, selectSession, selectClass, selectContact, blood, pos, check;
    private TextInputLayout inputLayoutName, inputLayoutEmail, inputLayoutPhone, inputLayoutPIN,
            inputLayoutCity, inputLayoutArea, inputLayoutBirthdate, inputLayoutHouse, etProfileName;


    private DatePickerDialog fromDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    private Bitmap bp;
    private byte[] photo;

    private DatabaseHandler db;
    private Toolbar toolbar;
    private TextView toolbarTitle;

    private Dialog myDialog;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_reg);
        toolBarInit();
        initializeViews();
        db = new DatabaseHandler(this);
    }

    /**
     * SetUp toolbar method
     */
    public void toolBarInit() {
        // Lookup the toolbar in activity layout
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Lookup the toolbar title  in activity
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        // Lookup the toolbar in activity layout
        setSupportActionBar(toolbar);
        //Default home button enable false
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, AdminLoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
        finish();
    }

    private void initializeViews() {
        inputStudentName = (EditText) findViewById(R.id.et_student_name);
        inputPhoneNumber = (EditText) findViewById(R.id.phone_no);
        inputHouseNo = (EditText) findViewById(R.id.house_no);
        inputArea = (EditText) findViewById(R.id.area);
        inputEmail = (EditText) findViewById(R.id.input_email);
        inputCity = (EditText) findViewById(R.id.input_city);
        inputPinCode = (EditText) findViewById(R.id.input_pin_code);
        imageView = (ImageView) findViewById(R.id.student_image);
        genderRadioGroup = (RadioGroup) findViewById(R.id.radio_group);
        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);

        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutPhone = (TextInputLayout) findViewById(R.id.input_layout_phone);
        inputLayoutPIN = (TextInputLayout) findViewById(R.id.input_layout_pin_code);
        inputLayoutCity = (TextInputLayout) findViewById(R.id.input_layout_city);
        inputLayoutHouse = (TextInputLayout) findViewById(R.id.input_layout_house);
        inputLayoutArea = (TextInputLayout) findViewById(R.id.input_layout_area);

        inputStudentName.addTextChangedListener(new MyTextWatcher(inputStudentName));
        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));
        inputPhoneNumber.addTextChangedListener(new MyTextWatcher(inputPhoneNumber));
        inputPinCode.addTextChangedListener(new MyTextWatcher(inputPinCode));
        inputCity.addTextChangedListener(new MyTextWatcher(inputCity));
        inputHouseNo.addTextChangedListener(new MyTextWatcher(inputHouseNo));
        inputArea.addTextChangedListener(new MyTextWatcher(inputArea));

        progressDialog = new ProgressDialogue(StudentRegActivity.this);
        errorDialog = new ErrorDialog(context);

        btn_pic_upload = (Button) findViewById(R.id.btn_pic_upload);
        btn_profile_submit = (Button) findViewById(R.id.btn_profile_submit);
        // loadImageFromStorage(picturePath);

        spinner_blood_grp = (Spinner) findViewById(R.id.spinner_blood_grp);
        spinner_blood_grp.setOnItemSelectedListener(this);

        spinner_session = (Spinner) findViewById(R.id.spinner_session);
        spinner_session.setOnItemSelectedListener(this);

        spinner_class = (Spinner) findViewById(R.id.spinner_class);
        spinner_class.setOnItemSelectedListener(this);

        spinner_contact_person = (Spinner) findViewById(R.id.spinner_contact_person);
        spinner_contact_person.setOnItemSelectedListener(this);

        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        checkBox1.setOnClickListener(this);

        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        inputBirthDate = (EditText) findViewById(R.id.birth_of_date);
        inputBirthDate.setInputType(InputType.TYPE_NULL);
        inputBirthDate.setOnClickListener(this);

        myDialog = new Dialog(this);


        setDateTimeField();

        btn_pic_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    selectImage();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btn_profile_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    submitRegistration();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Spinner spinner = (Spinner) parent;
        if (spinner.getId() == R.id.spinner_blood_grp) {
            selectBloodGrp = spinner_blood_grp.getSelectedItem().toString();
            if (selectBloodGrp.equals(getResources().getString(R.string.select_blood))) {
                blood = "";
            } else {
                pos = String.valueOf(position);
                blood = parent.getItemAtPosition(position).toString();
                // Toast.makeText(parent.getContext(), "Blood:" + pos + "&" + "Blood G:" + blood, Toast.LENGTH_LONG).show();
            }
        }
        if (spinner.getId() == R.id.spinner_session) {
            selectSession = spinner_session.getSelectedItem().toString();
            if (selectSession.equals(getResources().getString(R.string.select_session))) {
                selectSession = "";
            } else {
                pos = String.valueOf(position);
                selectSession = parent.getItemAtPosition(position).toString();
                // Toast.makeText(parent.getContext(), "Blood:" + pos + "&" + "Blood G:" + selectSession, Toast.LENGTH_LONG).show();
            }
        }

        if (spinner.getId() == R.id.spinner_class) {
            selectClass = spinner_class.getSelectedItem().toString();
            if (selectClass.equals(getResources().getString(R.string.select_class))) {
                selectClass = "";
            } else {
                pos = String.valueOf(position);
                selectClass = parent.getItemAtPosition(position).toString();
                //Toast.makeText(parent.getContext(), "Blood:" + pos + "&" + "Blood G:" + selectClass, Toast.LENGTH_LONG).show();
            }
        }
        if (spinner.getId() == R.id.spinner_contact_person) {
            selectContact = spinner_contact_person.getSelectedItem().toString();
            if (selectContact.equals(getResources().getString(R.string.select_contact))) {
                selectContact = "";
            } else {
                pos = String.valueOf(position);
                selectContact = parent.getItemAtPosition(position).toString();
                //Toast.makeText(parent.getContext(), "Blood:" + pos + "&" + "Blood G:" + selectContact, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void submitRegistration() {
        if (!validateName()) {
            return;
        }
        if (!validatePhone()) {
            return;
        }
        if (!validateEmail()) {
            return;
        }
        if (!validateBirthDate()) {
            return;
        }

        if (!validateHouse()) {
            return;
        }
        if (!validateArea()) {
            return;
        }
        if (!validateCity()) {
            return;
        }
        if (!validatePIN()) {
            return;
        }

        if (!validateBLood()) {
            return;
        }
        sentRegistrationInformation();

    }

    private void sentRegistrationInformation() {


        name = inputStudentName.getText().toString();
        email = inputEmail.getText().toString();
        phone = inputPhoneNumber.getText().toString();
        house = inputHouseNo.getText().toString();
        dateOfBirth = inputBirthDate.getText().toString();
        area = inputArea.getText().toString();
        city = inputCity.getText().toString();
        pincode = inputPinCode.getText().toString();

        radioSelectedId = genderRadioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(radioSelectedId);
        gender = radioButton.getText().toString();
        photo = profileImage(bp);

     /*   db.addContacts(new StudentInfo(name, selectSession, selectClass, email, dateOfBirth, blood,
                selectContact, area, phone, city, pincode, gender, check, photo));
*/
        //sucessFullToast();
        showPopup(view);

/*        DAO.executeSQL("INSERT OR REPLACE INTO " + TABLE_STUDENT_INFO + "(SName, SSession, SClass, SImage, SEmail, " +
                        "SBirthDate, SBloodGroup, SContactPersion, SArea, " +
                        "SPhone, SCity, SPinCode, SGender, SAgree) " +
                        "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",

                new String[]{name, selectSession, selectClass, str, email, dateOfBirth, blood,
                        selectContact, area, phone, city, pincode, gender, check});*/

    }

    public void sucessFullToast() {
        Context context = getApplicationContext();
        LayoutInflater inflater = getLayoutInflater();
        View customToastroot = inflater.inflate(R.layout.blue_toast, null);
        Toast customtoast = new Toast(context);
        customtoast.setView(customToastroot);
        customtoast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
        customtoast.setDuration(Toast.LENGTH_LONG);
        customtoast.show();
    }

    public void showPopup(View view) {
        Button ok, back;
        TextView txtclose;

        myDialog.setContentView(R.layout.custom_pop_up);
        LinearLayout layone = (LinearLayout) myDialog.findViewById(R.id.linear_item);
        ok = (Button) myDialog.findViewById(R.id.ok);
        back = (Button) myDialog.findViewById(R.id.back);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.addContacts(new StudentInfo(name, selectSession, selectClass, email, dateOfBirth, blood,
                        selectContact, area, phone, city, pincode, gender, check, photo));
                sucessFullToast();
                Intent intent = new Intent(StudentRegActivity.this, AdminLoginActivity.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        txtclose = (TextView) myDialog.findViewById(R.id.txtclose);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    /*
set birthdate
 */
    private void setDateTimeField() {
        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                inputBirthDate.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }
        /*
    find the birthday day from edit text
     */

    private boolean validatePhone() {
        if (inputPhoneNumber.getText().toString().trim().isEmpty() || inputPhoneNumber.length() != 11) {
            inputLayoutPhone.setError(getString(R.string.err_msg_phone));
            requestFocus(inputPhoneNumber);
            return false;
        } else {
            inputLayoutPhone.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateEmail() {
        String email = inputEmail.getText().toString().trim();
        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(inputEmail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }
        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    private boolean validateName() {
        if (inputStudentName.getText().toString().trim().isEmpty()) {
            inputStudentName.setError(getString(R.string.err_msg_name));
            requestFocus(inputStudentName);
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePIN() {
        if (inputPinCode.getText().toString().trim().isEmpty()) {
            inputPinCode.setError(getString(R.string.err_msg_pin));
            requestFocus(inputPinCode);
            return false;
        } else {
            inputLayoutPIN.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateCity() {
        if (inputCity.getText().toString().trim().isEmpty()) {
            inputCity.setError(getString(R.string.err_msg_city));
            requestFocus(inputCity);
            return false;
        } else {
            inputLayoutCity.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateArea() {
        if (inputArea.getText().toString().trim().isEmpty()) {
            inputArea.setError(getString(R.string.err_msg_area));
            requestFocus(inputArea);
            return false;
        } else {
            inputLayoutArea.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateHouse() {
        if (inputHouseNo.getText().toString().trim().isEmpty()) {
            inputHouseNo.setError(getString(R.string.err_msg_house));
            requestFocus(inputHouseNo);
            return false;
        } else {
            inputLayoutHouse.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateBLood() {
        if (blood.trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Select Blood Group", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private boolean validateBirthDate() {
        if (inputBirthDate.getText().toString().trim().isEmpty()) {
            inputBirthDate.setError(getString(R.string.err_msg_birth));
            requestFocus(inputBirthDate);
            return false;
        } else {
            //inputLayoutBirthdate.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == inputBirthDate)
            fromDatePickerDialog.show();

        switch (v.getId()) {
            case R.id.checkBox1:
                if (checkBox1.isChecked()) {
                    check = "Agree to terms & condition";
                    Toast.makeText(getApplicationContext(), "Agreed", Toast.LENGTH_LONG).show();
                } else {
                    check = "Disagree to terms & condition";
                }
                break;
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.et_student_name:
                    validateName();
                    break;
                case R.id.input_email:
                    validateEmail();
                    break;
                case R.id.phone_no:
                    validatePhone();
                    break;
                case R.id.input_pin_code:
                    validatePIN();
                    break;
                case R.id.input_city:
                    validateCity();
                    break;
                case R.id.house_no:
                    validateHouse();
                    break;
                case R.id.area:
                    validateArea();

                    break;
            }
        }
    }

    public void selectImage() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 2:
                if (resultCode == RESULT_OK) {
                    Uri choosenImage = data.getData();
                    if (choosenImage != null) {
                        bp = decodeUri(choosenImage, 400);
                        imageView.setImageBitmap(bp);
                    }
                }
        }
    }


    //COnvert and resize our image to 400dp for faster uploading our images to DB
    protected Bitmap decodeUri(Uri selectedImage, int REQUIRED_SIZE) {

        try {
            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o);
            // The new size we want to scale to
            // final int REQUIRED_SIZE =  size;
            // Find the correct scale value. It should be the power of 2.
            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;
            while (true) {
                if (width_tmp / 2 < REQUIRED_SIZE
                        || height_tmp / 2 < REQUIRED_SIZE) {
                    break;
                }
                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }

            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //Convert bitmap to bytes
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    private byte[] profileImage(Bitmap b) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            b.compress(Bitmap.CompressFormat.PNG, 0, bos);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bos.toByteArray();

    }

    // function to get values from the Edittext and image
    private void getValues() {
        photo = profileImage(bp);
    }


 /*   private void loadImageFromStorage(String path) {

        try {
            File f = new File(path, "profile.jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            imageView.setImageBitmap(b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void selectImage() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(StudentRegActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (checkSelfPermission(Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            requestPermissions(new String[]{Manifest.permission.CAMERA},
                                    MY_REQUEST_CODE);
                        } else {
                            startCameraIntent();
                        }
                    }
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                        startCameraIntent();
                    }

                } else if (options[item].equals("Choose from Gallery")) {

                    Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                    photoPickerIntent.setType("image/*");
                    startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG_FROM_GALLERY);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void startCameraIntent() {
        PackageManager pm = getApplicationContext().getPackageManager();

        if (pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {

            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);

        } else {

            errorDialog.showDialog("No Camera", "Sorry Your Device Has No Camera Permission");

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MY_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startCameraIntent();
            } else {
                errorDialog.showDialog("Error", "Permission Denied");
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        progressDialog.showProgress();
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            try {
                Bitmap photo = (Bitmap) data.getExtras().get("data");

                imageView.setImageBitmap(photo);
                picturePath = saveToInternalStorage(photo);
            } catch (Exception ex) {
                errorDialog.showDialog("Error!", "Try Again");
                Log.d("Main", ex.toString());
            }

        } else if (resultCode == RESULT_OK && requestCode == RESULT_LOAD_IMG_FROM_GALLERY) {
            try {
                final Uri imageUri = data.getData();
                Bitmap selectedImage = decodeUri(StudentRegActivity.this, imageUri, 500);
                imageView.setImageBitmap(selectedImage);
                picturePath = saveToInternalStorage(selectedImage);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                errorDialog.showDialog("Error!", "Try Another Image");
            }

        } else {
            Toast.makeText(StudentRegActivity.this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
        progressDialog.hideProgress();
    }

    public static Bitmap decodeUri(Context c, Uri uri, final int requiredSize)
            throws FileNotFoundException {
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(c.getContentResolver().openInputStream(uri), null, o);

        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;

        while (true) {
            if (width_tmp / 2 < requiredSize || height_tmp / 2 < requiredSize)
                break;
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(c.getContentResolver().openInputStream(uri), null, o2);
    }

    private String saveToInternalStorage(Bitmap bitmapImage) {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath = new File(directory, "profile.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }*/
}
