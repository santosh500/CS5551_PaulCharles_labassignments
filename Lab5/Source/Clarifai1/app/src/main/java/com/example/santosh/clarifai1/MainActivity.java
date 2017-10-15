package com.example.santosh.clarifai1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.dto.input.ClarifaiImage;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.prediction.Concept;

public class MainActivity extends AppCompatActivity {


    final ClarifaiClient client = new ClarifaiBuilder("f02d46ee72a740afaaef945c0e2a6deb").buildSync();
    String mainString = "";
    TextView searchView;
    ImageView byteImage;
    Bitmap bitmapPhoto;

    byte[] byteArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        byteImage = (ImageView)findViewById(R.id.imageView);

        searchView = (TextView)findViewById(R.id.textView);



    }

    public void searchItem(View v)
    {
        Intent itemInfo = new Intent(MainActivity.this,ShoppingLocator.class);
        itemInfo.putExtra("mainItem", mainString);
        startActivity(itemInfo);
    }

    //Resource adapted, taken, and referenced from: http://www.javatraineronline.com/java/image-recognition-in-java-using-clarifai-api/
    private class photoSelect extends AsyncTask<String, String,String> {
        protected String doInBackground(String... urls) {


            final List<ClarifaiOutput<Concept>> conceptResults =
                    client.getDefaultModels().generalModel()
                            .predict()
                            .withInputs(
                                    ClarifaiInput.forImage(ClarifaiImage.of(byteArray))
                            )
                            .executeSync()
                            .get();
            ClarifaiOutput<Concept> conceptOutput = conceptResults.get(0);
            List<Concept> concepts = conceptOutput.data();

            mainString =concepts.get(0).name();
            return concepts.get(0).name();
        }

    }



   public void click(View arg0)
   {
       Intent intent = new Intent(Intent.ACTION_PICK,
               android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
       startActivityForResult(intent,0);
   }

   //Referenced and Obtained from location: https://stackoverflow.com/questions/11144783/how-to-access-an-image-from-the-phones-photo-gallery
    //This was used to translate into byteArray and set the image
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            Uri photoURI = data.getData();

            try {
                bitmapPhoto = BitmapFactory.decodeStream(getContentResolver().openInputStream(photoURI));
                ByteArrayOutputStream streamPhoto = new ByteArrayOutputStream();
                bitmapPhoto.compress(Bitmap.CompressFormat.PNG, 100, streamPhoto);
                byteArray = streamPhoto.toByteArray();
                byteImage.setImageBitmap(bitmapPhoto);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


            try {
                new photoSelect().execute().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }



           searchView.setText("Search Word: " + mainString);
        }
    }




}
