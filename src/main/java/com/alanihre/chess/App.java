package com.alanihre.chess;

import java.awt.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        GameIOInterface ioInterface = new TerminalGameIOInterface();
        Game game = new ClassicChessGame(ioInterface);
    }
}
