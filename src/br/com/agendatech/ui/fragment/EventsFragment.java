package br.com.agendatech.ui.fragment;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import br.com.agendatech.model.Event;
import br.com.agendatech.ui.activity.Main;

import com.google.gson.GsonBuilder;

public class EventsFragment extends ListFragment {
	private Main activity;

	public EventsFragment(Main main) {
		this.activity = main;
		tryToLoad();
	}

	private void tryToLoad() {
		new LoaderTask().execute();
	}

	private static class EventResult {
		public Event evento;
		
		public String toString() {
			return evento.nome;
		}
	}
	
	public class LoaderTask extends AsyncTask<Void, Void, EventResult[]> {
		private static final String BASE_URL = "http://www.agendatech.com.br/mobile";

		protected EventResult[] doInBackground(Void... params) {
			try {
				
				InputStream stream = new URL(BASE_URL + "/eventos").openStream();
				Reader jsonReader = new InputStreamReader(stream); 
				return new GsonBuilder().create().fromJson(jsonReader, EventResult[].class);
			} catch (IOException e) {
				return null;
			}
		}
		
		protected void onPostExecute(EventResult[] result) {
			if (result != null) {
				setListAdapter(new ArrayAdapter<EventResult>(activity, android.R.layout.simple_list_item_1, android.R.id.text1, result));
			} else {
				new AlertDialog.Builder(activity).setMessage("Houve um erro de rede").setPositiveButton("Tentar novamente", new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						tryToLoad();
					}
				}).setNeutralButton("Cancelar", new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						getListView().getEmptyView().setVisibility(View.GONE);
					}
				}).create().show();
			}
		}
	}
}
