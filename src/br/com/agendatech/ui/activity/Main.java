package br.com.agendatech.ui.activity;

import roboguice.inject.ContentView;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import br.com.agendatech.R;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockActivity;

@ContentView(value = R.layout.main)
public class Main extends RoboSherlockActivity {
	private TabListener tabListener = new TabListener() {
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {}
		
		public void onTabSelected(Tab tab, FragmentTransaction ft) {}
		
		public void onTabReselected(Tab tab, FragmentTransaction ft) {}
	};
	
	private Tab eventsTab;
	private Tab groupsTab;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setUpTabs();
	}
	
	private void setUpTabs() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		eventsTab = actionBar.newTab().setText(R.string.events).setTabListener(tabListener);
		groupsTab = actionBar.newTab().setText(R.string.groups).setTabListener(tabListener);
		
		for (Tab tab : new Tab[] { eventsTab, groupsTab }) {
			actionBar.addTab(tab);
		}
	}
}