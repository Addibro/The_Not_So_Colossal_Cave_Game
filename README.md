## Step 1: Download source code

1. Choose "Clone or Download"
2. Choose "Download ZIP" and wait for completion
3. Locate the zip file on your computer and unpack the zip file
4. Navigate to the "src" folder.

## Step 2: Compile and run the program

In order to compile and run the GUI, place
yourself in the directory above se/ (src)

In this directory you should have (at least) the following:

cavedatabas.db          <- the database
sqlite-jdbc-3.16.1.jar  <- the JDBC driver
se/                     <- the directory for all the Java files

Issue the following in order to compile and run the GUI in your console/terminal:

javac se/itu/game/main/MainGui.java && java -cp sqlite-jdbc-3.16.1.jar:. se.itu.game.main.MainGui

The game should be running! On you go and play!