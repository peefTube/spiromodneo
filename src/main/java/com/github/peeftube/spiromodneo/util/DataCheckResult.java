package com.github.peeftube.spiromodneo.util;

public record DataCheckResult(boolean passed, Object checked)
{
    public boolean getResult()
    { return passed; }

    public Object getCheckedObject()
    { return checked; }
}
