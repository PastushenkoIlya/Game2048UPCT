package org.example;

import javax.swing.*;

public class App 
{
    public static void main(String[] args) {
            SwingUtilities.invokeLater(GameGUI::new);
        }


        /*
        * POSTSCRIPT
        *
        * While archiving a project into .jar file
        * the records.json file stops being read before
        * launching a game, neither records are saved after restarting .jar file
        * but in IDE(intellij idea ) everything works
        *
        * I couldn't fix this bug, so I leave it as it is
        *
        * also, sorry for a lack of comments and explanations in the code, I was doing the deliverable
        * in a bit of  a hurry
        * */
}

