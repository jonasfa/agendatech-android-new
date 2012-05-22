package br.com.agendatech.ui.activity;

import roboguice.inject.ContentView;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import br.com.agendatech.R;
import br.com.agendatech.model.Event;

import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;

@ContentView(value = R.layout.event_details)
public class EventDetails extends RoboSherlockFragmentActivity {
	@InjectExtra(value = "event") Event event;
	
	@InjectView(value = android.R.id.summary) TextView summary;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(event.nome);
		summary.setText(Html.fromHtml(event.descricao));
		summary.setMovementMethod(LinkMovementMethod.getInstance());
	}
}
