package com.bercomic.youngculture.utils.impl;

import com.bercomic.youngculture.utils.SessionManager;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class SessionManagerImpl implements SessionManager {
    protected Session currentSession;
    private Transaction currentTransaction;

    @Override
    public void openCurrentSession(){
        this.currentSession = SessionManager.getSessionFactory().openSession();
    }

    @Override
    public void openCurrentSessionWithTransaction(){
        this.currentSession = SessionManager.getSessionFactory().openSession();
        this.currentTransaction = this.currentSession.beginTransaction();
    }

    @Override
    public void closeCurrentSession(){
        this.currentSession.close();
    }

    @Override
    public void closeCurrentSessionWithTransaction(){
        this.currentTransaction.commit();
        this.currentSession.close();
    }
}
