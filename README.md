# About
Social Distancing Simulator is an open-source Minecraft Java Plugin which adds the Coronavirus to Minecraft as an extra challenge!
Social Distancing Simulator (Abbreviated "SDS") runs on Java Multiplayer Servers, and is *NOT* a client-side mod used with Forge or other alternatives.

# Usage
Currently, SDS is only for the (current) latest Minecraft version, 1.18.1 and is using the (current) latest Bukkit library.
Support for older (and newer) Minecraft versions *might* come soon.

# Installation
SDS can be installed like any other normal Minecraft server-side plugin.
Download the latest .jar from the Github releases page ([Here])(https://github.com/Jaeyoon07031/sds/releases)
You can then upload it to a Minecraft server like any other server-side bukkit plugin.

## Getting Started
This section is meant for people who don't know how Minecraft servers work.
If you have previous knowledge dealing with Minecraft servers, feel free to skip this part.

First, you'll need a Minecraft server to install the plugin on.
You could either host one yourself, or use a third-party hosting service (usually costs money)
There are plenty of guides online on setting up your own Minecraft server at home, and I won't give you advice on which hosting service is the best, so I'll skip that part.

Once you have a Minecraft server, you need to go to your server's control panel.
(If it's self-hosted and doesn't have one, go open up a file explorer and go to the root.)

The plugin needs to go to `.\plugins`, upload or copy the plugin to that directory.
Once it is there, restart your server, and go to the Server Console.
You should see a info message saying something along the lines of `Enabling Social Distancing Simulator 2.0`.
If you do see that, great! SDS is up and running on your server.

# Using the Plugin
Most of the next steps require administrator permissions on your minecraft server.
Head over to your Minecraft Server Console again, and type this command: `/op <your_minecraft_username>`.
This makes your account an operator of the server.

To start the simulation, simply type `/sds start`.
Corona will arrive, and all things related to the plugin will start working.
`/sds end` will similarly stop the simulation.

If you want to change the configurations, the `/sds set` command is there for you!
Simply type `/sds set <param> <value>` to change a specific configuration to your liking.
The TabComplete should show you the choices you have, and their names / descriptions are pretty self-explanatory.

If you want to know more about using the Plugin as an administrator, type the command `/shop help admin` to learn more.

# Information for your members
This is some information useful for players playing on a server with SDS on it.
The Help commands are extremely useful for a newcomer to SDS.
Some that you can start with are `/sds help basic`, `/sds help shop`, `/sds help infection`, and `/sds help death`.

# Extra Help
## Browsing the Code
Want to look through the open-source code?
Most of the code for the plugin is located at `./sds/src/main/java/com/tokyocodingclub/jaeyoon/sds`.
From there, you can look through the folders (Once again, names are pretty self-explanatory)

## Help and Support
Stuck with something?
Found a bug?
You can contact me through Discord at `Asterisk#1026`.
Alternatively, you can Email me at `jaeyoonjaylee0602@gmail.com` ([Here](mailto:jaeyoonjaylee0602@gmail.com))
Please make the title of your Email something along the lines of `SDS Help / Support Request` so that it's easier for me to find.
