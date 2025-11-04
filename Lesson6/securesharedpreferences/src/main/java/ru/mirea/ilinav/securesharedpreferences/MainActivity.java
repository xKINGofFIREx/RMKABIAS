package ru.mirea.ilinav.securesharedpreferences;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private TextView tVName;
    private ImageView iVPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tVName = findViewById(R.id.tV_name);
        iVPhoto = findViewById(R.id.iV_photo);

        try {
            String mainKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);

            SharedPreferences secureSharedPreferences = EncryptedSharedPreferences.create(
                    "secret_shared_prefs",
                    mainKeyAlias,
                    getBaseContext(),
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );

            String actorName = secureSharedPreferences.getString("secure", "Брэд Питт");
            tVName.setText(actorName);

            SharedPreferences.Editor editor = secureSharedPreferences.edit();
            editor.putString("secure", "Брэд Питт");
            editor.putString("bradpitt", "bradpitt");
            editor.apply();

            String photoName = secureSharedPreferences.getString("bradpitt", "bradpitt");

            int imageResId = getResources().getIdentifier(photoName, "raw", getPackageName());

            Resources res = getResources();
            InputStream inputStream = res.openRawResource(imageResId);

            Drawable drawable = Drawable.createFromStream(inputStream, photoName);

            iVPhoto.setImageDrawable(drawable);

        } catch (Exception e) {
            e.printStackTrace();
            tVName.setText("Ошибка загрузки данных");
        }
    }
}