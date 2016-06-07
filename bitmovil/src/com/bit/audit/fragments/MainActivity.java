package com.bit.audit.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bit.adapters.NavDrawerListAdapter;
import com.bit.client.R;
import com.bit.vending.StartActivity;
import com.goycooleainc.ui.base.NavDrawerItem;

import java.util.ArrayList;

public class MainActivity extends Activity {
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private ProgressBar progressbar;

	// nav drawer title
	private CharSequence mDrawerTitle;

	// used to store app title
	private CharSequence mTitle;

	// slide menu items
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;

	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;

	Point p;
	static Boolean miEspacio_popup = Boolean.TRUE;
	static Boolean comprarEvento_popup = Boolean.TRUE;
	static Boolean miMonedero_popup = Boolean.TRUE;
	static Boolean avatares_popup = Boolean.TRUE;
	static Boolean cartola_popup = Boolean.TRUE;
	static Boolean cortesias_popup = Boolean.TRUE;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mTitle = mDrawerTitle = getTitle();

		progressbar= (ProgressBar)findViewById(R.id.pbHeaderProgress);

		// load slide menu items
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

		// nav drawer icons from resources
		navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

		navDrawerItems = new ArrayList<NavDrawerItem>();

		navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));

		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));

		navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));

		navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));

		navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));

		navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));

		navDrawerItems.add(new NavDrawerItem(navMenuTitles[6], navMenuIcons.getResourceId(6, -1)));

		// Recycle the typed array
		navMenuIcons.recycle();

		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

		// setting the nav drawer list adapter
		adapter = new NavDrawerListAdapter(getApplicationContext(), navDrawerItems);

		mDrawerList.setAdapter(adapter);

		// enabling action bar app icon and behaving it as toggle button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.Orange));
		getActionBar().setLogo(R.drawable.icon_bar);
		getActionBar().setDisplayUseLogoEnabled(true);
		getActionBar().setDisplayShowHomeEnabled(true);



		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer1, //nav menu toggle icon
				R.string.app_name, // nav drawer open - description for accessibility
				R.string.app_name // nav drawer close - description for accessibility
		) {
			public void onDrawerClosed(View view) {
				/*getActionBar().setTitle(mTitle);*/
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				/*getActionBar().setTitle(mDrawerTitle);*/
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			// on first time display view for first nav item
			/*displayView(0);*/
			Fragment home = null;
			home = new MisVentasFragment();

			if (home != null) {

				FragmentManager fragmentManager = getFragmentManager();
				fragmentManager.beginTransaction().replace(R.id.frame_container, home).commit();

				// update selected item and title, then close the drawer
				mDrawerList.setItemChecked(0, true);
				mDrawerList.setSelection(0);
				setTitle(navMenuTitles[0]);
				mDrawerLayout.closeDrawer(mDrawerList);
			} else {
				// error in creating fragment
				Log.e("MainActivity", "Error in creating fragment");
			}
		}
	}

	/**
	 * Slide menu item click listener
	 * */
	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// display view for selected nav drawer item
			displayView(position);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action bar actions click
		switch (item.getItemId()) {
			case R.id.action_settings:
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		//menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	private void displayView(int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;
		int desplazamiento_x = 10;
		switch (position) {
			case 0:
				if(miEspacio_popup){
					miEspacio_popup = Boolean.FALSE;
					String info_nav = getString(R.string.navItem1_popup);
					int desplazamiento_y = 30;
					if (p != null){
						showPopup(MainActivity.this, p, desplazamiento_x, desplazamiento_y, info_nav);
					}
				}else{
					fragment = new MisVentasFragment();
				}
				break;
			case 1:
				if(comprarEvento_popup){
					comprarEvento_popup = Boolean.FALSE;
					String info_nav = getString(R.string.navItem2_popup);
					int desplazamiento_y = 80;
					if (p != null){
						showPopup(MainActivity.this, p, desplazamiento_x, desplazamiento_y, info_nav);
					}
				}else{
					fragment = new EventoFragment();
				}
				break;
			case 2:
				if(miMonedero_popup){
					miMonedero_popup = Boolean.FALSE;
					String info_nav = getString(R.string.navItem3_popup);
					int desplazamiento_y = 130;
					if (p != null){
						showPopup(MainActivity.this, p, desplazamiento_x, desplazamiento_y, info_nav);
					}
				}else{
					fragment = new TiendaVirtualFragment();
				}
				break;
			case 3:
				if(avatares_popup){
					avatares_popup = Boolean.FALSE;
					String info_nav = getString(R.string.navItem4_popup);
					int desplazamiento_y = 180;
					if (p != null){
						showPopup(MainActivity.this, p, desplazamiento_x, desplazamiento_y, info_nav);
					}
				}else{
					fragment = new AvatarFragment();
				}
				break;
			case 4:
				if(cartola_popup){
					cartola_popup = Boolean.FALSE;
					String info_nav = getString(R.string.navItem5_popup);
					int desplazamiento_y = 225;
					if (p != null){
						showPopup(MainActivity.this, p, desplazamiento_x, desplazamiento_y, info_nav);
					}
				}else{
					fragment = new TransaccionFragment();
				}
				break;
			case 5:
				if(cortesias_popup){
					cortesias_popup = Boolean.FALSE;
					String info_nav = getString(R.string.navItem6_popup);
					int desplazamiento_y = 275;
					if (p != null){
						showPopup(MainActivity.this, p, desplazamiento_x, desplazamiento_y, info_nav);
					}
				}else{
					fragment = new MisCortesiasFragment();
				}
				break;
			case 6:
				fragment = new WhatsHotFragment();
				break;
			default:
				break;
		}

		if (fragment != null) {

			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();

			// update selected item and title, then close the drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle(navMenuTitles[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
        /*getActionBar().setTitle(mTitle);*/
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	public void onLogout(View paramView) {
		final Dialog dialog = new Dialog(paramView.getContext());
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_new_action);
		/*dialog.setTitle("BITMOVIL : MOBILE PAYMENTS");*/

		TextView texto = (TextView) dialog.findViewById(R.id.dialogText);
		texto.setText("¿Está seguro de Salir y cerrar Sesión?");

		Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
		// if button is clicked, close the custom dialog
		dialogButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					dialog.dismiss();
					Intent mainIntent = new Intent(getBaseContext(), StartActivity.class);
					mainIntent.putExtra("usuario", "");
					mainIntent.putExtra("password", "");
					mainIntent.putExtra("ip", "");
					startActivity(mainIntent);
					finish();
				} catch (Exception ex) {
					ex.toString();
				}
			}
		});

		Button dialogCancelButton = (Button) dialog.findViewById(R.id.dialogButtonCancel);
		// if button is clicked, close the custom dialog
		dialogCancelButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
				//Al no aceptar ...
			}
		});

		dialog.show();
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {

		int[] location = new int[2];
		ListView items_menu = (ListView) findViewById(R.id.list_slidermenu);

		// Get the x, y location and store it in the location[] array
		// location[0] = x, location[1] = y.
		items_menu.getLocationOnScreen(location);

		//Initialize the Point with x, and y positions
		p = new Point();
		p.x = location[0];
		p.y = location[1];

	}

	// The method that displays the popup.
	private void showPopup(final Activity context, Point p, int desplazamiento_x, int desplazamiento_y, String info_item) {
		float d = context.getResources().getDisplayMetrics().density;

		int popupHeight = (int)(210 * d);

		// Inflate the popup_layout.xml
		LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.popup_nav);
		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = layoutInflater.inflate(R.layout.popup_info_nav, viewGroup);

		TextView info_nav = (TextView) layout.findViewById(R.id.nav_info_popup);
		info_nav.setText(info_item);

		// Creating the PopupWindow
		final PopupWindow popup = new PopupWindow(layout, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
		popup.setContentView(layout);
		popup.setHeight(popupHeight);
		popup.setFocusable(true);

		// Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
		int OFFSET_X = (int)(desplazamiento_x * d);
		int OFFSET_Y = (int)(desplazamiento_y * d);

		// Clear the default translucent background
		popup.setBackgroundDrawable(new BitmapDrawable());

		// Displaying the popup at the specified location, + offsets.
		popup.showAtLocation(layout, Gravity.NO_GRAVITY, p.x + OFFSET_X, p.y + OFFSET_Y);

	}

}
