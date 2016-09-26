package com.alanihre.chessui;

import java.awt.*;

import javax.swing.*;

class Window extends JFrame {

  private static final int WINDOW_SIZE_WIDTH = 500;
  private static final int WINDOW_SIZE_HEIGHT = 500;

  Window() {
    super("Chess");
  }

  void createAndShowGUI() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Dimension windowSize = new Dimension(WINDOW_SIZE_WIDTH, WINDOW_SIZE_HEIGHT);
    setPreferredSize(windowSize);

    pack();
    setVisible(true);
  }
}
