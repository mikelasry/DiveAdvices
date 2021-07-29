package com.example.diveadvices.model;

import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.io.ByteArrayOutputStream;
import java.util.LinkedList;
import java.util.List;

public class ModelFirebase {

    final static String usersCollection = "users";
    final static String advicesCollection = "advices";
    final static String PHOTOS = "photos";


    private ModelFirebase(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
    }

    public static FirebaseAuth getFirebaseAuth(){
        return FirebaseAuth.getInstance();
    }

    // Users CRUD

    // post (register)
    public static void addUser(User user, String password, final Model.OnCompleteListener listener) {
        // add user credentials
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(user.getEmail(), password)
            .addOnCompleteListener((task)->{
                if(task.isSuccessful()){
                    Log.d("TAG", task.getResult().getUser().toString());

                    // add user to users collection
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    db.collection(usersCollection)
                            .document(user.getEmail())
                            .set(user.jsonify())
                            .addOnSuccessListener((v)->{listener.onComplete(true);})
                            .addOnFailureListener((@NonNull Exception e) -> {listener.onComplete(false);});
                } else {
                    listener.onComplete(false);
                }
            });
    }

    public static void login(String email, String pw, final Model.LoginListener listener){
        getFBAuth().signInWithEmailAndPassword(email, pw)
                .addOnCompleteListener( task -> {
                    if (task.isSuccessful())
                        listener.onComplete(true);
                    else
                        listener.onComplete(false);

                });
    }

    public static void logout(String id) {
//        FirebaseAuth.getInstance().signOut(); // what about the id?
    }


    // -------------------------------- Diving Sites CRUD ---------------------------------------------
    // ------------------------------------------------------------------------------------------------

     public interface GetAllSitesListener{ public void onComplete(List<SiteAdvice> divingSiteAdvices); }

    // read many
     public static void getAllAdvices(final Model.OnGetAdvicesComplete listener){
         getFBStore().collection(advicesCollection).get().addOnCompleteListener((@NonNull Task<QuerySnapshot> task)
                 -> {
             if (task.isSuccessful()){
                 List<SiteAdvice> list = new LinkedList<>();
                 for (QueryDocumentSnapshot document : task.getResult()){
                     list.add(SiteAdvice.fromMap(document.getData()));
                 }
                 listener.onComplete(new MutableLiveData<>(list));
             }
         });
     }

     // post one
     public static void saveAdvice(SiteAdvice divindSiteAdvice, Model.OnCompleteListener listener){
         FirebaseFirestore db = getFBStore();
         divindSiteAdvice.setOwner(getFBUser().getEmail());
         db.collection(advicesCollection).document(divindSiteAdvice.getId())
                 .set(divindSiteAdvice.jsonify())
                 .addOnSuccessListener((v) ->{
                     listener.onComplete(true);
                     Log.d("ALBUM", "saveAlbum success");
                 })
                 .addOnFailureListener((e) ->{
                     listener.onComplete(false);
                     Log.d("ALBUM", "saveAlbum failed");
                 });

     }

    public static void uploadImage(Bitmap bmImg, String name, Model.UploadImageListener listener) {
// get firebase storage instance (singleton)
        FirebaseStorage storage = FirebaseStorage.getInstance();
        // Create the path
        final StorageReference imagesRef = storage.getReference().child(PHOTOS).child(name);
        //
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        //Compressing
        bmImg.compress(Bitmap.CompressFormat.JPEG, 100, out);
        byte[] data = out.toByteArray();
        // Upload with Task
        UploadTask uploadTask = imagesRef.putBytes(data);
        uploadTask.addOnFailureListener((exception)->listener.onComplete(null))
                .addOnSuccessListener((taskSnapshot)-> {
                    imagesRef.getDownloadUrl().addOnSuccessListener((uri) -> {
                        Uri downloadUrl = uri;
                        listener.onComplete(downloadUrl.toString());
                    });
                });
    }


     // ------------------------------------------------------------------------------------------------
     // ---------------------             Assistance Functions             ------------------------------
     // ------------------------------------------------------------------------------------------------

    public static FirebaseAuth getFBAuth(){
        return FirebaseAuth.getInstance();
    }

    public static FirebaseUser getFBUser(){
        return getFBAuth().getCurrentUser();
    }

    public static FirebaseFirestore getFBStore() {
        return FirebaseFirestore.getInstance();
    }

    public static FirebaseStorage getFBStorage() {
        return FirebaseStorage.getInstance();
    }

    // ------------------------------------------------------------------------------------------------
}
