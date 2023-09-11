package ui;

import model.Event;
import model.EventLog;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CloseWindowActions extends WindowAdapter {

    public void windowClosing(WindowEvent e) {

        EventLog eventLog = EventLog.getInstance();

        for (Event next: eventLog) {
            System.out.println(next.toString() + "\n\n");
        }

        System.exit(0);
    }
}
