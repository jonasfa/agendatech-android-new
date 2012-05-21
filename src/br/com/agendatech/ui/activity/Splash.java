package br.com.agendatech.ui.activity;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import br.com.agendatech.R;

@ContentView(value = R.layout.splash)
public class Splash extends RoboActivity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		new AsyncTask<Void, Void, Void>() {
			protected Void doInBackground(Void... params) {
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {}
				return null;
			}
			protected void onPostExecute(Void result) {
				startActivity(new Intent(Splash.this, Main.class));
				finish();
			}
		}.execute();
	}
	
	public void onBackPressed() {
	}
}