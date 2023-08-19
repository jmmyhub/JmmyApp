package com.jmmy.jmmyapp.provides;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;

public class JmmyFileProvider extends FileProvider {
    @Override
    public String getType(@NonNull Uri uri) {
        return super.getType(uri);
    }
}
