package com.etnlgravtnl.common.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by hongtao on 3/16/16.
 *
 * for future user connection data
 */
@Component
public class ConnectionListener implements HttpSessionListener{

    private static final Logger logger = LoggerFactory.getLogger(ConnectionListener.class);
    private static final String TAG = ConnectionListener.class.getName();

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        logger.debug(TAG, "Created session of id " + httpSessionEvent.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        logger.debug(TAG, "Destroyed session of id " + httpSessionEvent.getSession().getId());
    }
}
