/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

/**
 *
 * @author reds
 */
public class Respuesta {
    private boolean Dibujo;
    private boolean espera;
    private int xpos[];
    private int ypos[];

    public boolean isDibujo() {
        return Dibujo;
    }

    public boolean isEspera() {
        return espera;
    }

    public int[] getXpos() {
        return xpos;
    }

    public int[] getYpos() {
        return ypos;
    }

    public void setDibujo(boolean Dibujo) {
        this.Dibujo = Dibujo;
    }

    public void setEspera(boolean espera) {
        this.espera = espera;
    }

    public void setXpos(int[] xpos) {
        this.xpos = xpos;
    }

    public void setYpos(int[] ypos) {
        this.ypos = ypos;
    }
    
    
}
