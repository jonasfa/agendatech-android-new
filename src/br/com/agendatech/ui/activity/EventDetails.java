package br.com.agendatech.ui.activity;

import java.text.SimpleDateFormat;
import java.util.Date;

import roboguice.inject.ContentView;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;
import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.com.agendatech.R;
import br.com.agendatech.model.Event;
import br.com.agendatech.model.Gadget;
import br.com.agendatech.ui.ImageLoader;

import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockActivity;

@ContentView(value = R.layout.event_details)
public class EventDetails extends RoboSherlockActivity {
	@InjectExtra(value = "event") Event event;

	@InjectView(value = android.R.id.summary) TextView summary;
	@InjectView(value = R.id.date) TextView date;
	@InjectView(value = R.id.state) TextView state;
	@InjectView(value = R.id.link) TextView link;
	@InjectView(value = R.id.who_is_coming) LinearLayout whoIsComing;
	@InjectView(value = R.id.who_is_coming_group) View whoIsComingGroup;
	@InjectView(value = R.id.logo) ImageView logo;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(event.nome);

		summary.setText(Html.fromHtml(event.descricao));
		summary.setMovementMethod(LinkMovementMethod.getInstance());
		state.setText(event.estado);
		date.setText(makeDateText(event.data, event.data_termino));
		link.setText(event.site);

		loadLogo();
		setUpWhoIsComing();
	}

	private void setUpWhoIsComing() {
		if (event.gadgets.length == 0) {
			whoIsComingGroup.setVisibility(View.GONE);
		} else {
			for (int i = 0; i < whoIsComing.getChildCount(); i++) {
				ImageView child = (ImageView) whoIsComing.getChildAt(i);

				if (i < event.gadgets.length) {
					new ImageLoader(child).execute("http://api.twitter.com/1/users/profile_image/" + event.gadgets[i].user.nickname + ".png");
				} else {
					child.setVisibility(View.GONE);
				}
			}
		}
	}

	private String makeDateText(Date start, Date end) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		StringBuilder text = new StringBuilder(formatter.format(start));
		if (!start.equals(end)) {
			text.append(" - ");
			text.append(formatter.format(end));
		}
		return text.toString();
	}

	private void loadLogo() {
		if (event.logo_file_name == null) {
			logo.setVisibility(View.GONE);
		} else {
			new ImageLoader(logo).execute("http://s3.amazonaws.com/agendatech_logos/original/" + event.logo_file_name);
		}
	}

	public void showAttendants(View v) {
		ArrayAdapter<Gadget> gadgetsAdapter = new ArrayAdapter<Gadget>(this, android.R.layout.simple_list_item_1, event.gadgets);
		new AlertDialog.Builder(this).setAdapter(gadgetsAdapter, null).setTitle(R.string.who_is_coming).show();
	}

}
