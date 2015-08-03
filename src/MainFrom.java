import com.sun.glass.ui.Screen;
import com.sun.org.apache.xpath.internal.functions.FuncFalse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.invoke.MethodHandles;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class MainFrom {

    final static double avDaysInYear = 365.25;


    final static Calendar birthDate = new GregorianCalendar(1995, 6, 27);

    public static void main(String[] args){

        JFrame mainForm = new JFrame("Timer");

        mainForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainForm.setSize(270 * 3, 325);
        mainForm.setMinimumSize(new Dimension(260 * 3, 300));

        mainForm.setResizable(false);

        Double xPos = (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - mainForm.getHeight())/2.0;
        Double yPos = (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - mainForm.getWidth())/2.0;
        mainForm.setLocation(xPos.intValue(), yPos.intValue());
        mainForm.setVisible(true);

        mainForm.setLayout(new GridLayout(1,2));

        final ProgressPanelLife lifeProgress = new ProgressPanelLife();
        final ProgressPanelYear yearProgress = new ProgressPanelYear();
        final ProgressPanelDay dayProgress = new ProgressPanelDay();

        mainForm.add(dayProgress);
        mainForm.add(yearProgress);
        mainForm.add(lifeProgress);

        new Timer(100, new ActionListener() {

            long milliSecNow;
            long milliSecBirth;
            long difference;
            Double lifeProgressValue;
            Double yearProgressValue;
            Double timeValue;



            Calendar calendarDateOnly = Calendar.getInstance();

            final Double milliSecsInYear = 365.5*24*60*60*1000;


            @Override
            public void actionPerformed(ActionEvent e) {

                Calendar calendar = Calendar.getInstance();

//                calendar.get(Calendar.YEAR)

                milliSecNow = calendar.getTimeInMillis();
                milliSecBirth = birthDate.getTimeInMillis();
                difference = (milliSecNow - milliSecBirth);

                calendarDateOnly.set(Calendar.HOUR_OF_DAY, 0);
                calendarDateOnly.set(Calendar.MINUTE, 0);
                calendarDateOnly.set(Calendar.SECOND, 0);
                calendarDateOnly.set(Calendar.MILLISECOND, 0);

                lifeProgressValue = (difference * 360)/(75 * milliSecsInYear);
                yearProgressValue = ((difference % milliSecsInYear) * 360)/(12 * milliSecsInYear);
                timeValue = ((System.currentTimeMillis() - calendarDateOnly.getTimeInMillis()) * 360.0)/(24*60*60*1000);
//
                lifeProgress.setInfo(lifeProgressValue.intValue(), lifeProgressValue.toString());

                yearProgress.setInfo(yearProgressValue.intValue(), yearProgressValue.toString());


                dayProgress.setInfo(timeValue.intValue(), timeValue.toString());




            }
        }).start();

    }

}
