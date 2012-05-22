package br.com.agendatech.ui.activity;

import roboguice.inject.ContentView;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import br.com.agendatech.R;
import br.com.agendatech.model.Event;
import br.com.agendatech.model.Gadget;

import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;

@ContentView(value = R.layout.event_details)
public class EventDetails extends RoboSherlockFragmentActivity {
	@InjectExtra(value = "event") Event event;

	@InjectView(value = android.R.id.summary) TextView summary;
	@InjectView(value = R.id.who_is_coming) GridView whoIsComing;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(event.nome);

		summary.setText(Html.fromHtml(event.descricao));
		summary.setMovementMethod(LinkMovementMethod.getInstance());

		whoIsComing.setAdapter(new ArrayAdapter<Gadget>(this, android.R.layout.simple_list_item_1, android.R.id.text1, event.gadgets));
	}
}
