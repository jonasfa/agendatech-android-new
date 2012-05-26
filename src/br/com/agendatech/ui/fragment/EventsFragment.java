package br.com.agendatech.ui.fragment;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.com.agendatech.model.Event;
import br.com.agendatech.ui.activity.EventDetails;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class EventsFragment extends ListFragment {

	public EventsFragment() {
		tryToLoad();
	}

	public void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent(getActivity(), EventDetails.class);
		intent.putExtra("event", ((EventResult) l.getItemAtPosition(position)).evento);
		startActivity(intent);
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
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssz").create();
				return gson.fromJson(jsonReader, EventResult[].class);
			} catch (IOException e) {
				return null;
			}
		}

		protected void onPostExecute(EventResult[] result) {
			FragmentActivity activity = getActivity();

			if (activity == null)
				return;

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
