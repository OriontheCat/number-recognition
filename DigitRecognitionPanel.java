/**
 * 
 */

import java.awt.Graphics;

import javax.swing.JPanel;
import java.awt.Color;

/**
 * @author delgadomatac
 *
 */
public class DigitRecognitionPanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 7509557385220349199L;
    /**
     * 
     */

    /**
     * 
     */

    protected double[] m_digit = new double[7 * 5];
    protected int m_panelSize;
    public static final int THUMBNAIL_SIZE = 0;
    public static final int NORMAL_SIZE = 1;

    public DigitRecognitionPanel(int iSize) {
        // TODO Auto-generated constructor stub

        setLayout(new java.awt.BorderLayout());
        m_panelSize = iSize;
        for (int k = 0; k < m_digit.length; k++)
            m_digit[k] = 0.0;
    }

    protected void paintComponent(Graphics g) {
        int w = getWidth();
        int h = getHeight();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, w, h);
        g.setColor(Color.BLACK);

        int pixelSize = m_panelSize == NORMAL_SIZE ? 25 : 5;

        int sx = (w / 2) - (5 * pixelSize) / 2;
        int sy = (h / 2) - (7 * pixelSize) / 2;
        int width = sx + 5 * pixelSize;
        int height = sy + 7 * pixelSize;

        g.setColor(Color.WHITE);
        g.fillRect(sx, sy, (5 * pixelSize), (7 * pixelSize));

        g.setColor(Color.BLACK);
        float grey = 0.0F;
        for (int r = 0; r < 7; r++) {
            for (int c = 0; c < 5; c++) {
                grey = (float) (1 - m_digit[r * 5 + c]);
                g.setColor(new java.awt.Color(grey, grey, grey));
                g.fillRect(sx + c * pixelSize, sy + r * pixelSize, pixelSize, pixelSize);
            }
        }

        if (m_panelSize == NORMAL_SIZE) {

            g.setColor(Color.lightGray);
            for (int c = 1; c < 5; c++)
                g.drawLine(sx + c * pixelSize, sy, sx + c * pixelSize, height);
            for (int r = 1; r < 7; r++)
                g.drawLine(sx, sy + r * pixelSize, width, sy + r * pixelSize);
        }

        g.setColor(Color.black);
        g.drawRect(sx - 1, sy - 1, (5 * pixelSize) + 1, (7 * pixelSize) + 1);
    }

    public void setDigit(double[] digitMap) {
        for (int k = 0; k < m_digit.length; k++)
            m_digit[k] = digitMap[k];
    }

    public double[] getDigit() {
        return m_digit;
    }

    public void clear() {
        for (int k = 0; k < m_digit.length; k++)
            m_digit[k] = 0.0;
    }

    public void addNoise(double rate) {
        for (int k = 0; k < m_digit.length; k++) {
            if (Math.random() < rate) {
                if (m_digit[k] == 0)
                    m_digit[k] += Math.random();
                else
                    m_digit[k] -= (Math.random() * m_digit[k]);
            }
        }
    }
}
