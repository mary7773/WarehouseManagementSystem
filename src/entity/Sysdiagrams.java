package entity;


// Generated Mar 1, 2014 7:49:42 PM by Hibernate Tools 4.0.0

import java.io.Serializable;


/**
 * Sysdiagrams generated by hbm2java
 */
public class Sysdiagrams
    implements Serializable
{

    /** field <code>serialVersionUID</code> */
    private static final long serialVersionUID = 1L;
    private int diagramId;
    private Integer version;
    private String name;
    private int principalId;
    private byte[] definition;


    public Sysdiagrams()
    {}


    public Sysdiagrams(int diagramId, String name, int principalId)
    {
        this.diagramId = diagramId;
        this.name = name;
        this.principalId = principalId;
    }


    public Sysdiagrams(int diagramId, String name, int principalId, byte[] definition)
    {
        this.diagramId = diagramId;
        this.name = name;
        this.principalId = principalId;
        this.definition = definition;
    }


    public int getDiagramId()
    {
        return this.diagramId;
    }


    public void setDiagramId(int diagramId)
    {
        this.diagramId = diagramId;
    }


    public Integer getVersion()
    {
        return this.version;
    }


    public void setVersion(Integer version)
    {
        this.version = version;
    }


    public String getName()
    {
        return this.name;
    }


    public void setName(String name)
    {
        this.name = name;
    }


    public int getPrincipalId()
    {
        return this.principalId;
    }


    public void setPrincipalId(int principalId)
    {
        this.principalId = principalId;
    }


    public byte[] getDefinition()
    {
        return this.definition;
    }


    public void setDefinition(byte[] definition)
    {
        this.definition = definition;
    }

}
