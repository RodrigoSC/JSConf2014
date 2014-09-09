/*
 * OutSystems Project
 *
 * Copyright (C) 2014 OutSystems.
 *
 * This software is proprietary.
 */
package com.outsystems.jsconftimetable;

import org.apache.cordova.CordovaActivity;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.outsystems.jsconftimetable.R;
import com.outsystems.jsconftimetable.widgets.TypefaceSpan;

/**
 * Class Base Activity.
 * 
 * @author <a href="mailto:vmfo@xpand-it.com">vmfo</a>
 * @version $Revision: 666 $
 * 
 */
public class BaseActivity extends CordovaActivity {

    private OnClickListener onClickListenerHyperLink = new OnClickListener() {

        @Override
        public void onClick(View v) {
            String link = getString(R.string.label_about_link);
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + link));
            startActivity(browserIntent);
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
        case android.R.id.home:
            Intent upIntent = NavUtils.getParentActivityIntent(this);
            if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                TaskStackBuilder.create(this).addNextIntentWithParentStack(upIntent).startActivities();
            } else {
                finish();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * About events.
     */
    protected void aboutEvents() {
        TextView textViewLink = (TextView) findViewById(R.id.text_view_about_link);
        textViewLink.setOnClickListener(onClickListenerHyperLink);
    }

    /**
     * Show loading.
     * 
     * @param buttonClick the button click
     */
    protected void showLoading(View buttonClick) {
        ProgressBar progressbar = (ProgressBar) findViewById(R.id.progress_bar);
        buttonClick.setVisibility(View.INVISIBLE);
        progressbar.setVisibility(View.VISIBLE);
    }

    /**
     * Stop loading.
     * 
     * @param buttonClick the button click
     */
    protected void stopLoading(View buttonClick) {
        ProgressBar progressbar = (ProgressBar) findViewById(R.id.progress_bar);
        buttonClick.setVisibility(View.VISIBLE);
        progressbar.setVisibility(View.INVISIBLE);
    }

    /**
     * Show error.
     * 
     * @param viewError the view error
     */
    protected void showError(View viewError) {
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        viewError.startAnimation(shake);
    }
}
