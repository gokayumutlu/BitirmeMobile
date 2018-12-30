package com.gokay.bitirmeprojesi;

import android.os.AsyncTask;

public class Task extends AsyncTask<String,Void,String>{
    MainActivity main=new MainActivity();

    @Override
    protected String doInBackground(String... strings) {
        String alici_email=strings[0];

        return main.getIdByEmail(alici_email);
    }

    @Override
    protected void onPostExecute(String s) {
        main.intent();
    }
}
