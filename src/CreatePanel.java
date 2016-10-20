// Assignment #: 6
//         Name: Bailey Bowman
//    StudentID: 1208740698
//      Lecture: 2:00 PM MWF
//  Description: A panel that fills the first panel.
//      Allows user to add athletes to the program
//      by inputting first and last name and the sport.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class CreatePanel extends JPanel {
    private Vector<Athlete> athleteList;
    private CountPanel cPanel;
    private JPanel panelL;
    private JScrollPane scrollPane;
    private JPanel panelR;
    private JPanel panelTopL;
    private JPanel panelMidL;
    private JPanel panelBotL;
    private JTextArea areaR;
    private JTextArea areaTop;
    private JTextField field1;
    private JTextField field2;
    private JTextField field3;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JButton button;

    //Constructor initializes components and organize them using certain layouts
    public CreatePanel(Vector<Athlete> athleteList, CountPanel cPanel) {

        //import variables
        this.athleteList = athleteList;
        this.cPanel = cPanel;

        //initialize components
        panelL = new JPanel();
        panelR = new JPanel();
        scrollPane = new JScrollPane(panelR);
        panelTopL = new JPanel();
        panelMidL = new JPanel();
        panelBotL = new JPanel();
        areaR = new JTextArea();
        areaTop = new JTextArea();
        field1 = new JTextField();
        field2 = new JTextField();
        field3 = new JTextField();
        label1 = new JLabel("First Name");
        label2 = new JLabel("Last Name");
        label3 = new JLabel("Sport");
        button = new JButton("Create New athlete");


        //organize components

        setLayout(new GridLayout(1, 2));
        add(panelL);
        add(scrollPane);

        //On panelL
        panelL.setLayout(new GridLayout(3, 1));
        panelL.setSize(250, 400);
        panelL.add(panelTopL);
        panelL.add(panelMidL);
        panelL.add(panelBotL);

        //On panelTopL
        panelTopL.add(areaTop);
        areaTop.setBackground(null);
        areaTop.setEditable(false);
        areaTop.setForeground(Color.red);

        //On panelMidL
        panelMidL.setLayout(new GridLayout(3, 2));
        panelMidL.add(label1);
        panelMidL.add(field1);
        panelMidL.add(label2);
        panelMidL.add(field2);
        panelMidL.add(label3);
        panelMidL.add(field3);

        //On panelBotL
        panelBotL.add(button);
        button.addActionListener(new ButtonListener());

        //On panel R
        panelR.setLayout(new BorderLayout());
        panelR.add(areaR);

    }


    //ButtonListener is a listener class that listens to
    //see if the button "Create an Athlete" is pushed.
    //When the event occurs, it adds an athlete using the information
    //entered by a user.
    //It also performs error checking.
    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            //extract text
            String firstName = field1.getText();
            String lastName = field2.getText();
            String sport = field3.getText();

            //if all fields have text
            if (!firstName.equals("") && !lastName.equals("") && !sport.equals("")) {
                Athlete tempAthlete = new Athlete(firstName, lastName, sport);
                int index = athleteList.size();
                athleteList.add(index, tempAthlete);
                areaTop.setText("Athlete added.");
                areaR.append(tempAthlete.toString());
                cPanel.updateAthleteList();
                (cPanel.getList()).setSelectedIndex(0);

                field1.setText("");
                field2.setText("");
                field3.setText("");


            } else {
                areaTop.setText("Please enter all fields.");
            }



        } //end of actionPerformed method
    } //end of ButtonListener class
} //end of CreatePanel class