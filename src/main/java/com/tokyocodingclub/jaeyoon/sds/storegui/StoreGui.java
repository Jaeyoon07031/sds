package com.tokyocodingclub.jaeyoon.sds.storegui;

import com.tokyocodingclub.jaeyoon.sds.Simulator;
import com.tokyocodingclub.jaeyoon.sds.gui.ChestGui;


public class StoreGui
{
    protected ChestGui gui;
    protected Simulator simulator;

    public StoreGui()
    {
        
    }

    public void setupGui()
    {
        
    }

    public ChestGui getChestGui()
    {
        return gui;
    }

    public void showGui()
    {
        gui.show();
    }

    public void close()
    {
        gui.close();
    }
}

//TODO: make general store gui you idot