package com.androidgear.core.dialog;

import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JavaDialog implements Dialog {

    private Button positiveButton;
    private Button negativeButton;
    private String message;
    private JFrame mainWindow;
    private JDialog d;

    public JavaDialog(JFrame mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public Dialog show() {
        d.setVisible(true);

        return this;
    }

    @Override
    public Dialog cancel() {
        d.dispose();
        return this;
    }

    @Override
    public Dialog setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public Dialog setPositiveButton(String label, DialogButtonListener listener) {
        positiveButton = new Button(label, listener);
        return this;
    }

    @Override
    public Dialog setNegativeButton(String label, DialogButtonListener listener) {
        negativeButton = new Button(label, listener);
        return this;
    }

    @Override
    public Dialog prepare() {

        d = new JDialog();

        d.setUndecorated(true);
        d.setResizable(false);

        d.setSize(300, 150);

        JPanel messagePanel = new JPanel();
        messagePanel.setBackground(Color.LIGHT_GRAY);

        JLabel messageLabel = new JLabel(message);
        messagePanel.add(messageLabel);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.LIGHT_GRAY);

        if (positiveButton != null) {
            JButton bt = new JButton(positiveButton.getLabel());
            bt.setBackground(Color.WHITE);
            bt.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    positiveButton.getListener().execute();
                }
            });
            buttonsPanel.add(bt);
        }

        if (negativeButton != null) {
            JButton bt = new JButton(negativeButton.getLabel());
            bt.setBackground(Color.WHITE);
            bt.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    negativeButton.getListener().execute();
                }
            });
            buttonsPanel.add(bt);
        }

        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        panel.add(messagePanel, "message");
        panel.add(buttonsPanel, "buttons");

        d.add(panel);
        int x = (int) (mainWindow.getX() + mainWindow.getBounds().getWidth()
                / 2 - d.getBounds().getWidth() / 2);
        int y = (int) (mainWindow.getY() + mainWindow.getBounds().getHeight()
                / 2 - d.getBounds().getHeight() / 2);

        d.setLocation(x, y);
        d.setModalityType(ModalityType.APPLICATION_MODAL);

        return this;
    }

    class Button {
        private String label;
        private DialogButtonListener listener;

        public Button(String label, DialogButtonListener listener) {
            this.label = label;
            this.listener = listener;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public DialogButtonListener getListener() {
            return listener;
        }

        public void setListener(DialogButtonListener listener) {
            this.listener = listener;
        }
    }
}
