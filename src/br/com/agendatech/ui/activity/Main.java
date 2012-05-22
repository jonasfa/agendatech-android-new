package br.com.agendatech.ui.activity;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import br.com.agendatech.R;
import br.com.agendatech.ui.fragment.EventsFragment;
import br.com.agendatech.ui.fragment.GroupsFragment;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;

@ContentView(value = R.layout.main)
public class Main extends RoboSherlockFragmentActivity {
	private TabListener tabListener = new TabListener() {
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {}

		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			viewPager.setCurrentItem(tab.getPosition());
		}

		public void onTabReselected(Tab tab, FragmentTransaction ft) {}
	};

	private Tab eventsTab;
	private Tab groupsTab;

	@InjectView(value = R.id.view_pager) ViewPager viewPager;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setUpTabs();
	}

	private void setUpViewPager() {
		final Fragment[] fragments = new Fragment[] { new EventsFragment(), new GroupsFragment() };
		viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
			public int getCount() {
				return fragments.length;
			}

			public Fragment getItem(int position) {
				return fragments[position];
			}
		});
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			public void onPageSelected(int position) {
				getSupportActionBar().setSelectedNavigationItem(position);
			}

			public void onPageScrolled(int arg0, float arg1, int arg2) {}

			public void onPageScrollStateChanged(int arg0) {}
		});
	}

	private void setUpTabs() {
		setUpActionBar();
		setUpViewPager();
	}

	private void setUpActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		eventsTab = actionBar.newTab().setText(R.string.events).setTabListener(tabListener);
		groupsTab = actionBar.newTab().setText(R.string.groups).setTabListener(tabListener);

		for (Tab tab : new Tab[] { eventsTab, groupsTab }) {
			actionBar.addTab(tab);
		}
	}
}