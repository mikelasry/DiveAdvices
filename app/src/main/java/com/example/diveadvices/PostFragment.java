package com.example.diveadvices;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static androidx.core.content.ContextCompat.checkSelfPermission;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.diveadvices.model.Model;
import com.example.diveadvices.model.MyApplication;
import com.example.diveadvices.model.SiteAdvice;

public class PostFragment extends Fragment {

    private EditText nameEt;
    private EditText countryEt;
    private EditText descEt;
    private EditText minDepthEt;
    private EditText maxDepthEt;

    private ImageView imgIv;

    private Button addImgBtn;
    private Button submitBtn;
    private Button backBtn;

    private Bitmap imageBitmap;


    static final int CAMERA_REQUEST_CODE = 0;
    static final int GALLERY_REQUEST_CODE = 1;
    static final int PERMISSIONS_REQUEST_CODE = 2;
    static final int SHORT = 3;
    static final int LONG = 4;
    public View view;
    private final String CAMERA = "Camera";
    private final String GALLERY = "Gallery";
    private final String CANCEL = "Cancel";
    private final String HEADER = "Where from?";

    public PostFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.view = inflater.inflate(R.layout.fragment_post, container, false);

        this.nameEt = view.findViewById(R.id.post_name_et);
        this.countryEt = view.findViewById(R.id.post_country_et);
        this.descEt = view.findViewById(R.id.post_desc_et);
        this.minDepthEt = view.findViewById(R.id.post_mindepth_et);
        this.maxDepthEt = view.findViewById(R.id.post_maxdepth_et);
        this.imgIv = view.findViewById(R.id.post_img_iv);
        this.addImgBtn = view.findViewById(R.id.post_addimg_btn);
        this.submitBtn = view.findViewById(R.id.post_submit_btn);
        this.backBtn = view.findViewById(R.id.post_back_btn);

        this.addImgBtn.setOnClickListener((v) -> {
            this.openImgDialog();
        });

        this.submitBtn.setOnClickListener((v) -> {
            this.submitBtn.setEnabled(false);

            String name = this.nameEt.getText().toString().trim();
            String country = this.countryEt.getText().toString().trim();
            String desc = this.descEt.getText().toString().trim();
            String minDepth = this.minDepthEt.getText().toString().trim();
            String maxDepth = this.maxDepthEt.getText().toString().trim();

            if (name.isEmpty() || country.isEmpty() || desc.isEmpty() ||
                    imageBitmap == null || minDepth.isEmpty() || maxDepth.isEmpty()) {
                toast("Must supply all fields", LONG);
                return;
            }
            this.post();
        });

        this.backBtn.setOnClickListener((v) -> {
            Navigation.findNavController(view).navigateUp();
        });

        return view;
    }

    private void openImgDialog() {
        final CharSequence[] options = {CAMERA, GALLERY, CANCEL};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(HEADER);
        builder.setItems(options, (DialogInterface dialog, int item) -> {

            if (options[item].equals(CAMERA)) {
                Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture, CAMERA_REQUEST_CODE);
            } else if (options[item].equals(GALLERY)) {
                Context context = MyApplication.context;
                String requiredPermissions = Manifest.permission.READ_EXTERNAL_STORAGE;
                int permissionResult = checkSelfPermission(context, requiredPermissions);

                boolean currVersionMatch = Build.VERSION.SDK_INT == Build.VERSION_CODES.M;
                boolean premissionGranted = (permissionResult == PackageManager.PERMISSION_DENIED);

                if (currVersionMatch && premissionGranted) {
                    String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                    requestPermissions(permissions, PERMISSIONS_REQUEST_CODE);
                } else {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, GALLERY_REQUEST_CODE);
                }
            } else if (options[item].equals(CANCEL)) dialog.dismiss();

        });
        builder.show();
    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        if (resultCode != RESULT_CANCELED) {

            switch (reqCode) {

                case CAMERA_REQUEST_CODE:
                    if (resultCode == RESULT_OK && data != null) {
                        imageBitmap = (Bitmap) data.getExtras().get("data");
                        imgIv.setImageBitmap(imageBitmap);
                    }
                    break;

                case GALLERY_REQUEST_CODE:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        if (selectedImage != null) {
                            Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                                    filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();
                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String picturePath = cursor.getString(columnIndex);

                                String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                                requestPermissions(permissions, PERMISSIONS_REQUEST_CODE);

                                imageBitmap = BitmapFactory.decodeFile(picturePath);
                                imgIv.setImageBitmap(imageBitmap);
                                imgIv.setScaleType(ImageView.ScaleType.FIT_XY);
                                cursor.close();
                            }
                        }
                    }
                    break;

                default:
                    break;
            }
        }
    }

    private void post() {
        //pb.setVisibility(View.VISIBLE);
        submitBtn.setEnabled(false);
        addImgBtn.setEnabled(false);

        if (imageBitmap != null) {
            Model.instance.uploadImage(imageBitmap, nameEt.getText().toString(), (url) -> {
                saveAsdvice(url);
            });
        } else {
            saveAsdvice(null);
        }

    }

    private void saveAsdvice(String url) {
        String name = this.nameEt.getText().toString().trim();
        String country = this.countryEt.getText().toString().trim();
        String desc = this.descEt.getText().toString().trim();
        String minDepth = this.minDepthEt.getText().toString().trim();
        String maxDepth = this.maxDepthEt.getText().toString().trim();
        String photoUrl = (url == null) ? "" : url;
        String id = "" + System.currentTimeMillis();


        SiteAdvice sa = new SiteAdvice(id, name, country, desc, photoUrl, "", minDepth, maxDepth); // owner: Model.instance.getCurrentUser().getEmail() implemented on firebase level
        Model.instance.saveAdvice(sa, (success)->{

            if(success){
                Navigation.findNavController(view).navigate(R.id.action_postFragment_to_sitesListFragment);
            } else{
                toast("Please  try again", LONG);
            }
                this.submitBtn.setEnabled(true);
                this.addImgBtn.setEnabled(true);
        });

    }

    private void toast(String msg, int dur) {
        Toast.makeText(getContext(), msg, (dur == LONG ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT)).show();
    }
}