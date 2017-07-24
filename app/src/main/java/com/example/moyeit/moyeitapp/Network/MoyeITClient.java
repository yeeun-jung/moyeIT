package com.example.moyeit.moyeitapp.Network;

/**
 * Created by yeeun on 2017-07-23.
 */

public interface MoyeITClient<T> {
    public void init();
    T getMoyeITService();
}
