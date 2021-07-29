package com.example.diveadvices.model;

import android.graphics.Bitmap;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseUser;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Model {


    public static final Model instance = new Model();
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

//    public MutableLiveData<LoadingState> advicesLoadingState = new MutableLiveData<LoadingState>(LoadingState.loaded); // TODO: COMPLETE
    LiveData<List<SiteAdvice>> ldsa = null;

    private Model() {} // Singleton constructor



    // --------------------------------------------------------------------------------------------------------------------
    // ----------------------------------------------- Interfaces ----------------------------------------------------------
    // --------------------------------------------------------------------------------------------------------------------

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

    public interface OnGetAdvicesComplete{
        void onComplete(LiveData<List<SiteAdvice>> albums);
    }

    // ----------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------ Users CRUD ----------------------------------------------------
    // ----------------------------------------------------------------------------------------------------------------

    public void addUser(final User user, String password, final OnCompleteListener listener){
        ModelFirebase.addUser(user, password, listener);
    }

    public void login(String id, String pw, final LoginListener listener){
        ModelFirebase.login(id, pw, listener);
    }

    public void logout(String id){
        ModelFirebase.logout(id);
    }

    public FirebaseUser getCurrentUser(){
        return ModelFirebase.getFBUser();
    }


    // ----------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------ Dive Advice CRUD ----------------------------------------------
    // ----------------------------------------------------------------------------------------------------------------


    LiveData<List<SiteAdvice>> allAdvices = AppLocalDB.db.siteAdviceDao().getAll();

    public void uploadImage(Bitmap bmImg, String name, final Model.UploadImageListener listener){
        ModelFirebase.uploadImage(bmImg, name, listener);
    }

    public LiveData<List<SiteAdvice>> getAllSiteAdvices(){

        ModelFirebase.getAllAdvices((advices)->{
            executorService.execute(()->{
                for(SiteAdvice a: advices.getValue()) {
                    // update the local db with the new records
                    AppLocalDB.db.siteAdviceDao().insertAll(a);
                }
            });
            allAdvices = advices;
        });
        return allAdvices;
    }

    public void saveAdvice(SiteAdvice advice, OnCompleteListener listener){
        ModelFirebase.saveAdvice(advice, (success) -> {
            listener.onComplete(success);
        });
    }


    public LiveData<List<SiteAdvice>> getAdviceById(String id){
         ldsa = AppLocalDB.db.siteAdviceDao().getAdviceById(id);
        Log.d("TAG",ldsa.getValue().toString());
         return ldsa;
    }

}
