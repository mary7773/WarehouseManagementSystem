package entity;


// Generated Mar 1, 2014 7:49:42 PM by Hibernate Tools 4.0.0

import java.util.Date;


/**
 * Applicationlog generated by hbm2java
 */
public class Applicationlog
    implements java.io.Serializable
{

    /** field <code>serialVersionUID</code> */
    private static final long serialVersionUID = 1L;
    private int logid;
    private int userId;
    private String log;
    private Date dateLogin;
    private Date dateLogout;


    public Applicationlog()
    {}


    public Applicationlog(int logid, int userId, String log, Date dateLogin, Date dateLogout)
    {
        this.logid = logid;
        this.userId = userId;
        this.log = log;
        this.dateLogin = dateLogin;
        this.dateLogout = dateLogout;
    }


    public int getLogid()
    {
        return this.logid;
    }


    public void setLogid(int logid)
    {
        this.logid = logid;
    }


    public int getUserId()
    {
        return this.userId;
    }


    public void setUserId(int userId)
    {
        this.userId = userId;
    }


    public String getLog()
    {
        return this.log;
    }


    public void setLog(String log)
    {
        this.log = log;
    }


    public Date getDateLogin()
    {
        return this.dateLogin;
    }


    public void setDateLogin(Date dateLogin)
    {
        this.dateLogin = dateLogin;
    }


    public Date getDateLogout()
    {
        return this.dateLogout;
    }


    public void setDateLogout(Date dateLogout)
    {
        this.dateLogout = dateLogout;
    }

}