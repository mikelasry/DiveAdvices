package com.example.diveadvices.model;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

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
                            .addOnSuccessListener((v)->{listener.onComplete(true, "User added succcessfully!");})
                            .addOnFailureListener((@NonNull Exception e) -> {listener.onComplete(false, "Failed to store user");});
                } else {
                    listener.onComplete(false, "Somthing went wrong");
                }
            });
    }

    public static void login(String id, String pw, final Model.LoginListener listener){

    }





    // -------------------------------- Diving Sites CRUD ---------------------------------------------
    // ------------------------------------------------------------------------------------------------

     public interface GetAllSitesListener{ public void onComplete(List<SiteAdvice> divingSiteAdvices); }

    // read many
     public void getAllSitesListener(GetAllSitesListener listener){

     }

     // post one
     public void saveSite(SiteAdvice divindSiteAdvice){

     }
}
