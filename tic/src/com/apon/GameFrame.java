package com.apon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameFrame extends JFrame {
    private JButton[][] buttons = new JButton[3][3];
    private boolean isXTurn = true;

    public GameFrame(String username) {
        setTitle("Tic Tac Toe - Welcome " + username);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        initializeBoard();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeBoard() {
        Font font = new Font("Arial", Font.BOLD, 40);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton btn = new JButton("");
                btn.setFont(font);
                btn.setFocusPainted(false);
                buttons[i][j] = btn;

                btn.addActionListener(e -> {
                    if (btn.getText().equals("")) {
                        btn.setText(isXTurn ? "X" : "O");
                        btn.setForeground(isXTurn ? Color.BLUE : Color.RED);
                        isXTurn = !isXTurn;
                        checkWinner();
                    }
                });

                add(btn);
            }
        }
    }

    private void checkWinner() {
        String winner = "";

        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (checkLine(buttons[i][0], buttons[i][1], buttons[i][2]))
                winner = buttons[i][0].getText();
            if (checkLine(buttons[0][i], buttons[1][i], buttons[2][i]))
                winner = buttons[0][i].getText();
        }

        // Check diagonals
        if (checkLine(buttons[0][0], buttons[1][1], buttons[2][2]))
            winner = buttons[0][0].getText();
        if (checkLine(buttons[0][2], buttons[1][1], buttons[2][0]))
            winner = buttons[0][2].getText();

        if (!winner.equals("")) {
            JOptionPane.showMessageDialog(this, "Player " + winner + " wins!");
            resetBoard();
        } else if (isBoardFull()) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetBoard();
        }
    }

    private boolean checkLine(JButton b1, JButton b2, JButton b3) {
        return !b1.getText().equals("") &&
                b1.getText().equals(b2.getText()) &&
                b2.getText().equals(b3.getText());
    }

    private boolean isBoardFull() {
        for (JButton[] row : buttons)
            for (JButton btn : row)
                if (btn.getText().equals("")) return false;
        return true;
    }

    private void resetBoard() {
        for (JButton[] row : buttons)
            for (JButton btn : row)
                btn.setText("");
        isXTurn = true;
    }
}
