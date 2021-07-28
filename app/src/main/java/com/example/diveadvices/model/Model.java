package com.example.diveadvices.model;

import android.graphics.Bitmap;

import androidx.lifecycle.MutableLiveData;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Model {

    public static final Model instance = new Model();
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    public MutableLiveData<LoadingState> advicesLoadingState = new MutableLiveData<LoadingState>(LoadingState.loaded);

    private Model() {} // Singleton constructor

    // ----------------------------------------------- Interfaces ----------------------------------------------------------
    public static final String EMPTY = "";

    public enum LoadingState {loading,loaded,error}

    public interface OnCompleteListener{
        void onComplete(boolean success);
    }

    public interface GetUserListener{
        void onComplete(User user);
    }

    public interface UploadImageListener{
        void onComplete(String url);
    }

    public interface LoginListener {
        void onComplete(boolean success);
    }

    // ---------------------------------------------------------------------------------------------------------------------




    // ------------------------------------------------ Users CRUD ---------------------------------------------------------

    public void addUser(final User user, String password, final OnCompleteListener listener){
        ModelFirebase.addUser(user, password, listener);
    }

    public void login(String id, String pw, final LoginListener listener){
        ModelFirebase.login(id, pw, listener);
    }

    public void logout(String id){
        ModelFirebase.logout(id);
    }



    // ------------------------------------------------ Dive Advice CRUD ---------------------------------------------------------

    public void uploadImage(Bitmap bmImg, String name, final Model.UploadImageListener listener){
        ModelFirebase.uploadImage(bmImg, name, listener);
    }


    public void saveAdvice(SiteAdvice advice){
        // Set Live Data Loading state
//        ModelFirebase.
    }



}
