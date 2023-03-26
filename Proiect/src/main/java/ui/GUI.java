package ui;

import functions.OperationType;
import functions.Operations;
import functions.Polynomial;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI {
    private static OperationType operation_selected = OperationType.NONE; //retine ce operatie este selectata in momentul actual, pentru definirea
    //actiunii rezultate prin apasarea butonului "Calculate"
    public static void addPanelDesign( JPanel panel, Color color ){
        panel.setForeground(color);
        panel.setBackground(color);
    }
    public static void makeButtonStyle(JButton btn, Color color1, Color color2) {
        btn.setBackground(color1);
        btn.setForeground(color2);
        btn.setFont(new Font(Font.SERIF, Font.BOLD, 12));
    }

    public static void makeTextStyle(JTextField text){
        text.setFont(new Font(Font.SERIF, Font.BOLD, 12));
    }
    public static void addingAListener1(JLabel operation, String op, JButton btn, JPanel panel,
                                        JLabel lbl1, JLabel lbl2, JTextField text1, JTextField text2) {
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (op) {
                    case "Addition":
                        operation_selected = OperationType.ADDITION;
                        break;
                    case "Subtraction":
                        operation_selected = OperationType.SUBTRACTION;
                        break;
                    case "Multiplication":
                        operation_selected = OperationType.MULTIPLICATION;
                        break;
                    case "  Division":
                        operation_selected = OperationType.DIVISION;
                        break;
                }
                operation.setText(op);
                panel.removeAll();
                panel.setLayout(new GridLayout(2, 2));
                panel.add(lbl1);
                panel.add(text1);
                panel.add(lbl2);
                panel.add(text2);
                panel.revalidate();
            }
        });
    }
    public static void addingAListener2(JLabel operation, String op, JButton btn, JPanel panel, JLabel label,
                                        JTextField text) {
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (op) {
                    case "Derivation":
                        operation_selected = OperationType.DERIVATION;
                        break;
                    case "Integration":
                        operation_selected = OperationType.INTEGRATION;
                        break;
                }
                operation.setText(op);
                panel.removeAll();
                panel.setLayout(new GridLayout(1, 1));
                panel.add(label);
                panel.add(text);
                panel.revalidate();
            }
        });
    }

    public static void selectFromTwoInputs(JTextField text1, JTextField text2, JTextField rezultat, OperationType op) {
        String input1 = text1.getText();
        String input2 = text2.getText();
        Polynomial p1 = new Polynomial(Operations.getPairs(input1));
        Polynomial p2 = new Polynomial(Operations.getPairs(input2));
        try {
        switch (op) {
            case ADDITION:
                rezultat.setText(Operations.addition(p1, p2).toString());
                break;
            case SUBTRACTION:
                rezultat.setText(Operations.subtraction(p1, p2).toString());
                break;
            case MULTIPLICATION:
                rezultat.setText(Operations.multiplication(p1, p2).toString());
                break;
            case DIVISION:
                rezultat.setText(Operations.division(p1, p2).toString());
                break;
        }}
        catch(ArithmeticException e) {
            rezultat.setText(e.getMessage());
        }
    }

    public static void selectFromOneInput(JTextField text1, JTextField rezultat, OperationType op){
        String input = text1.getText();
        Polynomial p = new Polynomial(Operations.getPairs(input));
        switch (op){
            case DERIVATION:
                rezultat.setText(Operations.derivative(p).toString());
                break;
            case INTEGRATION:
                rezultat.setText(Operations.integral(p).toString());
                break;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Polynomial calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 500);
        JTextField text1 = new JTextField(20);
        GUI.makeTextStyle(text1);
        JTextField text2 = new JTextField(20);
        GUI.makeTextStyle(text2);
        JLabel calculator = new JLabel("    Polynomial Calculator");
        JLabel operation = new JLabel("No operation selected.");
        calculator.setFont(new Font("Arial", Font.BOLD, 20));
        JButton adunare = new JButton("Add");
        GUI.makeButtonStyle(adunare, new Color(7, 7, 68), new Color(255, 255, 255));
        JButton scadere = new JButton("Subtract");
        GUI.makeButtonStyle(scadere, new Color(7, 7, 68), new Color(255, 255, 255));
        JButton inmultire = new JButton("Multiply");
        GUI.makeButtonStyle(inmultire, new Color(7, 7, 68), new Color(255, 255, 255));
        JButton impartire = new JButton("Divide");
        GUI.makeButtonStyle(impartire, new Color(7, 7, 68), new Color(255, 255, 255));
        JButton derivare = new JButton("Derivate");
        GUI.makeButtonStyle(derivare, new Color(7, 7, 68), new Color(255, 255, 255));
        JButton integrare = new JButton("Integrate");
        GUI.makeButtonStyle(integrare, new Color(7, 7, 68), new Color(255, 255, 255));
        JTextField rezultat = new JTextField();
        GUI.makeTextStyle(rezultat);
        rezultat.setEditable(false);
        JButton calculeaza = new JButton("CALCULATE");
        GUI.makeButtonStyle(calculeaza, new Color(56, 124, 68), new Color(255, 255, 255));
        JLabel firstpol = new JLabel(" First polynomial:");
        JLabel secondpol = new JLabel(" Second polynomial:");
        JLabel pol = new JLabel(" Insert your polynomial:");

        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        JPanel panel1 = new JPanel();
        GUI.addPanelDesign(panel1, new Color(188, 207, 236));
        panel1.add(calculator);
        panel1.add(operation);
        p.add(panel1);

        JPanel panel_int = new JPanel();
        GUI.addPanelDesign(panel_int, new Color(188, 207, 236));
        panel_int.setSize(300, 30);
        p.add(panel_int);

        JPanel panel2 = new JPanel();
        panel2.setSize(20, 20);
        panel2.setLayout(new GridLayout(3, 2));
        panel2.add(adunare);
        panel2.add(scadere);
        panel2.add(inmultire);
        panel2.add(inmultire);
        panel2.add(impartire);
        panel2.add(derivare);
        panel2.add(integrare);
        p.add(panel2, BorderLayout.CENTER);

        GUI.addingAListener1(operation, "Addition", adunare, panel_int, firstpol, secondpol, text1, text2);
        GUI.addingAListener1(operation, "Subtraction", scadere, panel_int, firstpol, secondpol, text1, text2);
        GUI.addingAListener1(operation, "Multiplication", inmultire, panel_int, firstpol, secondpol, text1, text2);
        GUI.addingAListener1(operation, "  Division", impartire, panel_int, firstpol, secondpol, text1, text2);
        GUI.addingAListener2(operation, "Derivation", derivare, panel_int, pol, text1);
        GUI.addingAListener2(operation, "Integration", integrare, panel_int, pol, text1);

        JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayout(2, 1));
        panel4.add(calculeaza);
        panel4.add(rezultat);
        p.add(panel4, BorderLayout.SOUTH);

        calculeaza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (operation_selected) {
                    case ADDITION:
                        selectFromTwoInputs(text1, text2, rezultat, OperationType.ADDITION);
                        break;
                    case SUBTRACTION:
                        selectFromTwoInputs(text1, text2, rezultat, OperationType.SUBTRACTION);
                        break;
                    case MULTIPLICATION:
                        selectFromTwoInputs(text1, text2, rezultat, OperationType.MULTIPLICATION);
                        break;
                    case DIVISION:
                        selectFromTwoInputs(text1, text2, rezultat, OperationType.DIVISION);
                        break;
                    case DERIVATION:
                        selectFromOneInput(text1, rezultat, OperationType.DERIVATION);
                        break;
                    case INTEGRATION:
                        selectFromOneInput(text1, rezultat, OperationType.INTEGRATION);
                        break;
                }
            }
        });
        frame.setContentPane(p);
        frame.setVisible(true);
    }
    }

