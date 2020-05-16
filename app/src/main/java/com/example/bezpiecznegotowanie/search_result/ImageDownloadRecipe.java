package com.example.bezpiecznegotowanie.search_result;



import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.bezpiecznegotowanie.Recipe;

import java.io.BufferedInputStream;
import java.net.URL;


import javax.net.ssl.HttpsURLConnection;

public class ImageDownloadRecipe {

    private Recipe mRecipe;

    ImageDownloadRecipe(Recipe recipe){
        this.mRecipe = recipe;
    }

    Bitmap download() {
        Bitmap bm = null;
        try {
            URL url = new URL(mRecipe.getImageURL());
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.connect();
            BufferedInputStream bis = new BufferedInputStream(urlConnection.getInputStream());
            bm = BitmapFactory.decodeStream(bis);
            mRecipe.setImageBitmap(bm);
            bis.close();
            urlConnection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bm;
    }

}
