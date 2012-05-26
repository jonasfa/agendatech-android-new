package br.com.agendatech.ui;

import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.ImageView;
import br.com.agendatech.BuildConfig;

import com.actionbarsherlock.internal.nineoldandroids.animation.ObjectAnimator;

public class ImageLoader extends AsyncTask<Object, Void, Drawable> {
	volatile ImageView logo;
	private boolean animated;

	public ImageLoader(ImageView logo, boolean animated) {
		this.logo = logo;
		this.animated = animated;
	}

	protected void onPreExecute() {
		if (this.animated)
			this.logo.setRotationY(90);
	}

	protected Drawable doInBackground(Object... params) {
		String fileName = (String) params[0];

		HttpURLConnection connection = null;
		try {
			connection = (HttpURLConnection) new URL(fileName).openConnection();
			return Drawable.createFromStream(connection.getInputStream(), fileName);
		} catch (Exception e) {
			if (BuildConfig.DEBUG)
				e.printStackTrace();
		} finally {
			if (connection != null)
				connection.disconnect();
		}
		return null;
	}

	protected void onPostExecute(Drawable result) {
		this.logo.setImageDrawable(result);
		if (this.animated)
			ObjectAnimator.ofFloat(this.logo, "rotationY", 0).start();
	}
}