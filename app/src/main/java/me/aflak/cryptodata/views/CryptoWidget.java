package me.aflak.cryptodata.views;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.Toast;

import javax.inject.Inject;

import me.aflak.cryptodata.api.ApiService;
import me.aflak.cryptodata.app.MyApp;
import me.aflak.cryptodata.R;
import me.aflak.cryptodata.entities.Currency;

/**
 * Implementation of App Widget functionality.
 */
public class CryptoWidget extends AppWidgetProvider {
    private static final String ACTION_UPDATE_CLICK = "me.aflak.cryptodata.entities.action.UPDATE_CLICK";

    @Inject ApiService apiService;

    private void updateAppWidget(Context context, final AppWidgetManager appWidgetManager, final int appWidgetId) {
        final RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.crypto_widget);
        MyApp.app.getApiComponent().inject(this);
        apiService.getEthereum(Currency.EUR, crypto -> {
            boolean isUp = crypto.getPercentChangeWeek()>0;
            int color = ContextCompat.getColor(context, isUp ? R.color.crypto_widget_percent_change_up : R.color.crypto_widget_percent_change_down);
            int img = isUp ? R.drawable.arrow_top_right : R.drawable.arrow_bottom_right;
            String priceText = "(" + crypto.getSymbol() + ") " + (int)crypto.getPrice() + " " + crypto.getCurrency();
            String percentChangeText = (isUp?"+":"") + crypto.getPercentChangeWeek();
            views.setTextViewText(R.id.crypto_widget_price, priceText);
            views.setTextViewText(R.id.crypto_widget_percent_change, percentChangeText);
            views.setTextColor(R.id.crypto_widget_percent_change, color);
            views.setImageViewResource(R.id.crypto_widget_img, img);
            views.setInt(R.id.crypto_widget_img, "setColorFilter", color);

            views.setOnClickPendingIntent(R.id.crypto_widget_parent_layout, getPendingSelfIntent(context, ACTION_UPDATE_CLICK));
            appWidgetManager.updateAppWidget(appWidgetId, views);
            Toast.makeText(context, crypto.getSymbol()+" updated !", Toast.LENGTH_SHORT).show();
        }, s -> appWidgetManager.updateAppWidget(appWidgetId, views));
    }

    private PendingIntent getPendingSelfIntent(Context context, String action) {
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    private void onUpdate(Context context) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        ComponentName thisAppWidgetComponentName = new ComponentName(context.getPackageName(),getClass().getName());
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisAppWidgetComponentName);
        onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (ACTION_UPDATE_CLICK.equals(intent.getAction())) {
            onUpdate(context);
        }
    }
}

