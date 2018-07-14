package ca.sharipov.serhii.movieinfo.utils;

import android.content.Context;
import android.preference.PreferenceManager;

import ca.sharipov.serhii.movieinfo.Constants;

public class SharedPrefUtil {

    public static String getStoredQuery(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(Constants.PREF_SEARCH_QUERY, null);
    }

    public static void setStoredQuery(Context context, String query) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(Constants.PREF_SEARCH_QUERY, query)
                .apply();
    }

    public static String getLastResultId(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(Constants.PREF_LAST_RESULT_ID, null);
    }

    public static void setLastResult(Context context, String lastResultId) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(Constants.PREF_LAST_RESULT_ID, lastResultId)
                .apply();
    }

}
