package com.jmmy.jmmyapp.provides;

import java.security.Provider;

/**
 * Created by jmmy on 2017/12/15.
 */

public class jmmyProvider extends Provider {
    protected jmmyProvider(String name, double version, String info) {
        super(name, version, info);
    }
}
