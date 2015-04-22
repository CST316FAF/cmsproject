/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataCharts;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javafx.scene.canvas.Canvas;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author Davis
 */
public class FailedChart extends Canvas {
    private static Font sanSerifFont = new Font("SanSerif", Font.PLAIN, 12);

    public FailedChart() {
        super();
    }

    public void paint(Graphics g) {
        Graphics2D g2=(Graphics2D)g;
	g2.setBackground(Color.WHITE);
        g2.setFont(sanSerifFont);
        FontMetrics fm = g2.getFontMetrics();
        fm = g2.getFontMetrics();
        int w = fm.stringWidth("No Data Available");
        int h = fm.getAscent();
        g2.drawString("and", 200 - (w / 2), 200 + (h / 4));
    }

}
