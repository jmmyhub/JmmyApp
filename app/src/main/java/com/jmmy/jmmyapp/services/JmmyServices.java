package com.jmmy.jmmyapp.services;

import android.media.browse.MediaBrowser;
import android.os.Bundle;
import android.service.media.MediaBrowserService;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

/**
 * Created by jmmy on 2017/12/15.
 */

public class JmmyServices extends MediaBrowserService {
    private static final String TAG = "JmmyServices";
    private static final String ROOT_ID = "__ROOT__";
    @Nullable
    @Override
    public BrowserRoot onGetRoot(@NonNull String clientPackageName, int clientUid, @Nullable Bundle rootHints) {
        return new BrowserRoot(ROOT_ID, null);
    }

    @Override
    public void onLoadChildren(@NonNull String parentId, @NonNull Result<List<MediaBrowser.MediaItem>> result) {
        switch (parentId) {
            case ROOT_ID:
                result.detach();
                result.sendResult(null);
                break;
            default:
                break;
        }
    }
}
