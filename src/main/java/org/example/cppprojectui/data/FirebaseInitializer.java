package org.example.cppprojectui.data;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;


import java.io.FileInputStream;
import java.io.IOException;


public class FirebaseInitializer {
    private static boolean isInitialized = false;

    public static void initializeFirebase() {
        if (!isInitialized) {
            try {
                FileInputStream serviceAccount = new FileInputStream("serviceAccount.json");
                FirebaseOptions options = new FirebaseOptions.Builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .setDatabaseUrl("https://pizzeriaapp-e7b25-default-rtdb.europe-west1.firebasedatabase.app/")
                        .build();

                FirebaseApp.initializeApp(options);
                isInitialized = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}