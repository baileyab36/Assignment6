// Assignment #: 6
//         Name: Bailey Bowman
//    StudentID: 1208740698
//      Lecture: 2:00 PM MWF
//  Description: The panel on the second tab of the program.
//          Allows users to add medals to the athletes that were added in the first page.


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class CountPanel extends JPanel {
    private Vector<Athlete> athleteList;

    private JPanel panel;
    private JList<Athlete> list;
    private JLabel topLabel;
    private JPanel buttonPanel;
    private JRadioButton button1;
    private JRadioButton button2;
    private JRadioButton button3;
    private JButton bigButton;
    public int buttonID = 1;


    //Constructor initializes each component and arranges them using certain layouts
    public CountPanel(Vector<Athlete> athleteList) {
        this.athleteList = athleteList;

        //initialize components
        panel = new JPanel();
        topLabel = new JLabel("Choose an athlete and a medal type to increase its count.");
        list = new JList<>(athleteList);
        buttonPanel = new JPanel();
        button1 = new JRadioButton("Gold");
        button2 = new JRadioButton("Silver");
        button3 = new JRadioButton("Bronze");
        bigButton = new JButton("Increase Medal Count");


        //organize components
        setLayout(new BorderLayout());

        add(panel);
        add(bigButton, BorderLayout.SOUTH);

        panel.setLayout(new BorderLayout());
        panel.add(topLabel, BorderLayout.NORTH);
        panel.add(list);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);

        ButtonGroup group = new ButtonGroup();
        group.add(button1);
        group.add(button2);
        group.add(button3);

        button1.addActionListener(new CountListener());
        button2.addActionListener(new CountListener());
        button3.addActionListener(new CountListener());

        bigButton.addActionListener(new ButtonListener());

        button1.setSelected(true);

    }

    //This method  refreshes the JComboBox with updated vector information
    public void updateAthleteList() {
        list.updateUI();
    }


    //CountListener class listens to see the radio buttons was chosen
    private class CountListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            JRadioButton buttonSelected = (JRadioButton)event.getSource();
            if (buttonSelected == button1) {
                buttonID = 1;
            } else if (buttonSelected == button2) {
                buttonID = 2;
            } else {
                buttonID = 3;
            }
        }
    }


    //ButtonListener class listens to see the button "Increase Medal Count" is pushed.
    //A user can choose which athlete to increase medal counts and that will update the
    //medal count of such athlete.
    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            Athlete athlete = list.getSelectedValue();
            if (buttonID == 1) {
                athlete.increaseGold();
            } else if (buttonID == 2) {
                athlete.increaseSilver();
            } else {
                athlete.increaseBronze();
            }
            athleteList.set(list.getSelectedIndex(), athlete);
            list.updateUI();
        }
    } //end of ButtonListener class

    //helper method
    public JList getList() {
        return list;
    }

} //end of CountPanel class