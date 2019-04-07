/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PortalJava.Class;

/**
 *
 * @author Miguel Agudo, Alberto Delgado, Óscar Sánchez, Ferran Ases
 */
public class PortalPosition {

    public int x;
    public int y;
    public boolean isBlue;

    public PortalPosition() {

    }

    public PortalPosition(int x, int y, boolean blue) {
        this.x = x;
        this.y = y;
        this.isBlue = blue;
    }
}
