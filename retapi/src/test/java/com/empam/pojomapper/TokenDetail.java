package com.empam.pojomapper;

public class TokenDetail
{
    private String token;

    public String getToken ()
    {
        return token;
    }

    public void setToken (String token)
    {
        this.token = token;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [token = "+token+"]";
    }
}
	
