package com.alanihre.chess;

import com.alanihre.chess.game.ClassicChessGame;
import com.alanihre.chess.game.Game;
import com.alanihre.chess.game_io_interface.GameIOInterface;
import com.alanihre.chess.game_io_interface.TerminalGameIOInterface;

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
