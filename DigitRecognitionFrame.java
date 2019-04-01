
/**
 * 
 */

import java.awt.HeadlessException;

import javax.swing.JFrame;

/**
 * @author delgadomatac
 *
 */

public class DigitRecognitionFrame extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -8688901065711771599L;
    /**
     * 
     */

    private javax.swing.JButton m_addNoiseButton;
    private javax.swing.JButton m_classifyButton;
    private javax.swing.JButton m_clearButton;
    private javax.swing.JButton m_trainButton;
    private javax.swing.JButton m_generateButton;
    private DigitRecognitionPanel m_outputPanel;
    private javax.swing.JPanel m_digitsPanel;

    private javax.swing.JPanel m_mainPanel;
    private DigitRecognitionPanel m_inputPanel;
    private javax.swing.JPanel m_toolbarPanel;
    private DigitRecognitionPanel[] digitPanels;

    public static final int NUM_DIGITS = 10;
    /**
     * @throws HeadlessException
     */
    static final DigitRecognitionService digitRecognitionService = new DigitRecognitionService();
    static final double[] ONE = { 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0,
            0, 1, 1, 1, 1, 1 };
    static final double[] TWO = { 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0,
            0, 1, 1, 1, 1, 1 };
    static final double[] THREE = { 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0,
            0, 1, 0, 1, 1, 1, 0 };
    static final double[] FOUR = { 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0,
            1, 0, 0, 0, 0, 1, 0 };
    static final double[] FIVE = { 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0,
            0, 1, 0, 1, 1, 1, 0 };
    static final double[] SIX = { 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0,
            1, 0, 1, 1, 1, 0 };
    static final double[] SEVEN = { 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0,
            0, 0, 0, 1, 0, 0, 0 };
    static final double[] EIGHT = { 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0,
            0, 1, 0, 1, 1, 1, 0 };
    static final double[] NINE = { 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0,
            1, 0, 0, 1, 1, 0, 0 };
    static final double[] ZERO = { 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0,
            0, 1, 0, 1, 1, 1, 0 };
    static final double[] ERROR = { 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0,
            0, 0, 0, 1, 1, 1, 1 };
    static final public double[][] DIGITS = { ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE };

    public DigitRecognitionFrame() throws HeadlessException {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param arg0
     * @throws HeadlessException
     */

    public DigitRecognitionFrame(String arg0) throws HeadlessException {
        super(arg0);
        // TODO Auto-generated constructor stub
        initComponents();
    }

    public void initComponents() {

        m_classifyButton = new javax.swing.JButton();
        m_clearButton = new javax.swing.JButton();
        m_addNoiseButton = new javax.swing.JButton();
        m_trainButton = new javax.swing.JButton();
        m_generateButton = new javax.swing.JButton();
        m_outputPanel = new DigitRecognitionPanel(DigitRecognitionPanel.NORMAL_SIZE);
        m_digitsPanel = new javax.swing.JPanel();

        m_mainPanel = new javax.swing.JPanel();
        m_inputPanel = new DigitRecognitionPanel(DigitRecognitionPanel.NORMAL_SIZE);
        m_toolbarPanel = new javax.swing.JPanel();

        digitPanels = new DigitRecognitionPanel[NUM_DIGITS];
        for (int k = 0; k < digitPanels.length; k++) {
            digitPanels[k] = new DigitRecognitionPanel(DigitRecognitionPanel.THUMBNAIL_SIZE);
            digitPanels[k].setDigit(DIGITS[k]);
        }

        m_digitsPanel.setPreferredSize(new java.awt.Dimension(200, 60));
        getContentPane().add(m_digitsPanel, java.awt.BorderLayout.SOUTH);

        m_mainPanel.setLayout(new java.awt.BorderLayout());

        m_inputPanel.setLayout(null);

        m_inputPanel.setPreferredSize(new java.awt.Dimension((5 + 1) * 25, (7 + 1) * 25));
        m_mainPanel.add(m_inputPanel, java.awt.BorderLayout.WEST);

        m_toolbarPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 10));

        m_toolbarPanel.setMinimumSize(new java.awt.Dimension(100, 500));
        m_toolbarPanel.setPreferredSize(new java.awt.Dimension(100, 300));

        m_generateButton.setText("Generate");
        m_generateButton.setMaximumSize(new java.awt.Dimension(85, 23));
        m_generateButton.setPreferredSize(new java.awt.Dimension(85, 23));
        m_generateButton.setMargin(new java.awt.Insets(2, 10, 2, 10));
        m_generateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateButtonActionPerformed(evt);
            }
        });
        m_toolbarPanel.add(m_generateButton);
        m_trainButton.setText("Train");
        m_trainButton.setMaximumSize(new java.awt.Dimension(85, 23));
        m_trainButton.setPreferredSize(new java.awt.Dimension(85, 23));
        m_trainButton.setMargin(new java.awt.Insets(2, 10, 2, 10));
        m_trainButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trainButtonActionPerformed(evt);
            }
        });
        m_toolbarPanel.add(m_trainButton);

        m_classifyButton.setText("Classify");
        m_classifyButton.setMargin(new java.awt.Insets(2, 10, 2, 10));
        m_classifyButton.setPreferredSize(new java.awt.Dimension(85, 23));
        m_classifyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classifyButtonActionPerformed(evt);
            }
        });

        m_toolbarPanel.add(m_classifyButton);

        m_addNoiseButton.setText("Add Noise");
        m_addNoiseButton.setMargin(new java.awt.Insets(2, 10, 2, 10));
        m_addNoiseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNoiseButtonActionPerformed(evt);
            }
        });
        m_toolbarPanel.add(m_addNoiseButton);

        m_clearButton.setText("Clear");
        m_clearButton.setMaximumSize(new java.awt.Dimension(85, 23));
        m_clearButton.setPreferredSize(new java.awt.Dimension(85, 23));
        m_clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        m_toolbarPanel.add(m_clearButton);

        // The digit Panels
        for (int k = 0; k < digitPanels.length; k++) {
            digitPanels[k].setLayout(null);

            digitPanels[k].setPreferredSize(new java.awt.Dimension(35, 50));
            digitPanels[k].addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    digitsPanelMouseClicked(evt);
                }
            });

            m_digitsPanel.add(digitPanels[k]);
        }

        m_mainPanel.add(m_toolbarPanel, java.awt.BorderLayout.CENTER);

        m_outputPanel.setLayout(new java.awt.BorderLayout());

        m_outputPanel.setPreferredSize(new java.awt.Dimension((5 + 1) * 25, (7 + 1) * 25));
        m_mainPanel.add(m_outputPanel, java.awt.BorderLayout.EAST);

        getContentPane().add(m_mainPanel, java.awt.BorderLayout.CENTER);

        pack();
    }

    private void generateButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_classifyButtonActionPerformed
        digitRecognitionService.generateSamples();
        m_outputPanel.clear();
    }

    private void classifyButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_classifyButtonActionPerformed

        // Here add your code to classify.
        // Run the input through all ten perceptrons.
        // Choose the one selected

        // Next line just for testing sake.
        double[] oneHotVector = digitRecognitionService.classify(m_inputPanel.getDigit());
        m_outputPanel.setDigit(ERROR);
        boolean set = false;
        for (int i = 0; i <= 9 && !set; i++) {
            if (oneHotVector[i] > 0) {
                set = true;
                m_outputPanel.setDigit(DIGITS[i]);
            }
        }
        m_outputPanel.repaint();
        // m_outputPanel.clear();
    }

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_clearButtonActionPerformed
        m_inputPanel.clear();
        m_inputPanel.repaint();
        m_outputPanel.clear();
        m_outputPanel.repaint();
    }

    private void addNoiseButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_addNoiseButtonActionPerformed
        m_inputPanel.addNoise(0.10);
        m_inputPanel.repaint();
    }

    private void trainButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_addNoiseButtonActionPerformed
        // Here add your code to Train the 10 perceptrons OK.
        digitRecognitionService.train();
        // javax.swing.JOptionPane.showMessageDialog(this, "Not yet implemented.. that
        // is part of your project!");
    }

    private void digitsPanelMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_templateMouseClicked
        DigitRecognitionPanel src = (DigitRecognitionPanel) evt.getSource();

        m_inputPanel.setDigit(src.getDigit());
        m_inputPanel.repaint();
    }
}
