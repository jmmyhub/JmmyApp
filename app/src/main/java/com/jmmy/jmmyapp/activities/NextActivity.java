package com.jmmy.jmmyapp.activities;

import android.content.ComponentName;
import android.media.MediaMetadata;
import android.media.browse.MediaBrowser;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jmmy.jmmyapp.R;
import com.jmmy.jmmyapp.services.JmmyServices;
import com.jmmy.jmmyapp.utils.LogUtils;

import java.util.List;

/**
 * Created by jmmy on 2017/12/10.
 */

public class NextActivity extends AppCompatActivity{
    private String TAG = "NextActiviyty";

    private MediaBrowser mMediaBrowser;

    private MediaController mMediaController;

    private MediaBrowser.ItemCallback itemCallback = new MediaBrowser.ItemCallback() {
        @Override
        public void onItemLoaded(MediaBrowser.MediaItem item) {
            super.onItemLoaded(item);
        }

        @Override
        public void onError(@NonNull String mediaId) {
            super.onError(mediaId);
        }
    };

    private MediaBrowser.SubscriptionCallback subscriptionCallback = new MediaBrowser.SubscriptionCallback() {
        @Override
        public void onChildrenLoaded(@NonNull String parentId, @NonNull List<MediaBrowser.MediaItem> children) {
            super.onChildrenLoaded(parentId, children);
        }

        @Override
        public void onChildrenLoaded(@NonNull String parentId, @NonNull List<MediaBrowser.MediaItem> children, @NonNull Bundle options) {
            super.onChildrenLoaded(parentId, children, options);
        }

        @Override
        public void onError(@NonNull String parentId) {
            super.onError(parentId);
        }

        @Override
        public void onError(@NonNull String parentId, @NonNull Bundle options) {
            super.onError(parentId, options);
        }
    };

    private MediaController.Callback callback = new MediaController.Callback() {
        @Override
        public void onSessionDestroyed() {
            super.onSessionDestroyed();
        }

        @Override
        public void onSessionEvent(@NonNull String event, @Nullable Bundle extras) {
            super.onSessionEvent(event, extras);
        }

        @Override
        public void onPlaybackStateChanged(@Nullable PlaybackState state) {
            super.onPlaybackStateChanged(state);
        }

        @Override
        public void onMetadataChanged(@Nullable MediaMetadata metadata) {
            super.onMetadataChanged(metadata);
        }

        @Override
        public void onQueueChanged(@Nullable List<MediaSession.QueueItem> queue) {
            super.onQueueChanged(queue);
        }

        @Override
        public void onQueueTitleChanged(@Nullable CharSequence title) {
            super.onQueueTitleChanged(title);
        }

        @Override
        public void onExtrasChanged(@Nullable Bundle extras) {
            super.onExtrasChanged(extras);
        }

        @Override
        public void onAudioInfoChanged(MediaController.PlaybackInfo info) {
            super.onAudioInfoChanged(info);
        }
    };

    private MediaBrowser.ConnectionCallback connectionCallback
        = new MediaBrowser.ConnectionCallback(){
        @Override
        public void onConnected() {
            super.onConnected();
            if (mMediaBrowser.isConnected()) {
                String mediaId = mMediaBrowser.getRoot();
                mMediaBrowser.unsubscribe(mediaId);
                mMediaBrowser.unsubscribe(mediaId, subscriptionCallback);
                mMediaBrowser.getItem(mediaId, itemCallback);

                MediaSession.Token token = mMediaBrowser.getSessionToken();
                mMediaController = new MediaController(getApplicationContext(), token);
                mMediaController.registerCallback(callback);
                MediaController.PlaybackInfo playbackInfo = mMediaController.getPlaybackInfo();
                mMediaController.getTransportControls().play();
            }
        }

        @Override
        public void onConnectionFailed() {
            super.onConnectionFailed();
        }

        @Override
        public void onConnectionSuspended() {
            super.onConnectionSuspended();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        LogUtils.i(TAG, "onCreate");
        ComponentName componentName = new ComponentName(this, JmmyServices.class);
        mMediaBrowser = new MediaBrowser(this, componentName, connectionCallback, null);
        mMediaBrowser.connect();
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.i(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.i(TAG, "onStop");

    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.i(TAG, "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.i(TAG, "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.i(TAG, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.i(TAG, "onResume");
    }
}
