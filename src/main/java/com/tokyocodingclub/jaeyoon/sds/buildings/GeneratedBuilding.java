/*
package com.tokyocodingclub.jaeyoon.sds.buildings;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
//import com.sk89q.worldedit.world.World;
import org.bukkit.World;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;

// GeneratedBuilding => Shop, ShoppingStreet, Hospital
// Shop => GeneralStore, Pharmacy

public class GeneratedBuilding
{
    private Clipboard clipboard;
    private ClipboardFormat clipboardFormat;
    private ClipboardHolder clipboardHolder;
    private FileInputStream fileInputStream;
    protected String schemFileName;
    protected File schemFile;
    protected boolean loaded;
    protected boolean generated;
    protected World world;
    protected int x;
    protected int y;
    protected int z;
    protected int type;
    

    public GeneratedBuilding(String fileName, World world)
    {
        type = 0;
        this.world = world;
        schemFileName = fileName;
        schemFile = new File(schemFileName);
        clipboardFormat = ClipboardFormats.findByFile(schemFile);
        loaded = true;
        generated = false;
        try (ClipboardReader clipboardReader = clipboardFormat.getReader(new FileInputStream(schemFile))) 
        {
            clipboard = clipboardReader.read();
        }
        catch (IOException e)
        {
            loaded = false;
            //TODO : do something about this error
        }

    }

    public void generate(int x, int y, int z)
    {
        try (EditSession editSession = WorldEdit.getInstance().newEditSession(BukkitAdapter.adapt(world))) {
            Operation operation = new ClipboardHolder(clipboard)
                    .createPaste(editSession)
                    .to(BlockVector3.at(x, y, z))
                    .copyEntities(true)
                    .build();
            Operations.complete(operation);
            generated = true;
            this.x = x;
            this.y = y;
            this.z = z;
        }
        catch (WorldEditException e)
        {
            //TODO : do something about this error
        }
    }

    public void tick(int time)
    {

    }

    public int getX()
    {
        //retruns x
    }

    public int getY()
    {
        //returns y
    }

    public int getZ()
    {
        //returns z

    }

    public int getType()
    {
        //returns the type
    }
}
*/