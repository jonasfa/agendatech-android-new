package br.com.agendatech.app;

import java.io.File;
import java.io.IOException;

import android.app.Application;
import android.net.http.HttpResponseCache;
import android.util.Log;

public class ATApplication extends Application {
	public void onCreate() {
		super.onCreate();
		try {
			File httpCacheDir = new File(getCacheDir(), "http");
			long httpCacheSize = 10 * 1024 * 1024;
			HttpResponseCache.install(httpCacheDir, httpCacheSize);
		} catch (IOException e) {
			Log.i(toString(), "HTTP response cache installation failed:" + e);
		} catch (NoClassDefFoundError e) {
			Log.i(toString(), "Http response cache is not available:" + e);
		}
	}
}
