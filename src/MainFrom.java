import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.*;
import java.io.IOException;
import java.util.Calendar;

public class MainFrom {

    final static Calendar birthDate = Calendar.getInstance();

    public static void main(String[] args){

        birthDate.set(Calendar.YEAR, 1989);
        birthDate.set(Calendar.MONTH, Calendar.JUNE);
        birthDate.set(Calendar.DAY_OF_MONTH, 27);
        birthDate.set(Calendar.HOUR_OF_DAY, 0);
        birthDate.set(Calendar.MINUTE, 0);
        birthDate.set(Calendar.SECOND, 0);
        birthDate.set(Calendar.MILLISECOND, 1);

        JFrame mainForm = new JFrame("Timer");

        try{
//            mainForm.setIconImage(Toolkit.getDefaultToolkit().getImage(mainForm.getClass().getResource("icon.png")));

            ImageIcon icon = new ImageIcon("icon.png");
            mainForm.setIconImage(icon.getImage());


        }catch (NullPointerException e) {
                System.err.println("Caught NullPointerException: " + e.getMessage());
        }


        mainForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainForm.setSize(270 * 3, 325);
        mainForm.setMinimumSize(new Dimension(260 * 3, 300));

        mainForm.setResizable(false);

        Double xPos = (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - mainForm.getHeight())/2.0;
        Double yPos = (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - mainForm.getWidth())/2.0;
        mainForm.setLocation(xPos.intValue(), yPos.intValue());
        mainForm.setVisible(true);

        mainForm.setLayout(new GridLayout(1,3));

        Double yearProgressAngle = ((-1*
                (birthDate.get(Calendar.DAY_OF_YEAR) * 360.0) / Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_YEAR)*360.0)/365.25) + 86;

        final ProgressPanelLife lifeProgress = new ProgressPanelLife();
        final ProgressPanelYear yearProgress = new ProgressPanelYear(yearProgressAngle);
        final ProgressPanelDay dayProgress = new ProgressPanelDay();

        mainForm.add(dayProgress);
        mainForm.add(yearProgress);
        mainForm.add(lifeProgress);

        new Timer(100, new ActionListener() {

            long milliSecNow;
            long milliSecBirth;
            Long difference;


            Double lifeProgressValue;
            Double yearProgressValue;
            Double timeValue;

            Double percentage;

            final Double daysInYear = 365.25;
            final int secondsInDay = 24*60*60;

            @Override
            public void actionPerformed(ActionEvent e) {

                Calendar calendar = Calendar.getInstance();

                int daysInYear = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
                Long millisInYear = daysInYear * secondsInDay * (long)1000;

                //life progress
                milliSecNow = calendar.getTimeInMillis();
                milliSecBirth = birthDate.getTimeInMillis();
                difference = milliSecNow - milliSecBirth;
                percentage = (difference * 100.0)/(80.0 * millisInYear);
                lifeProgressValue = percentage * 3.6;

                DecimalFormat formatter = new DecimalFormat("#0.000000000");

                lifeProgress.setInfo(lifeProgressValue.intValue(), formatter.format(percentage) + " %");

                //year progress
                Calendar birthDayCalendar = Calendar.getInstance();
                birthDayCalendar.set(Calendar.MONTH, birthDate.get(Calendar.MONTH));
                birthDayCalendar.set(Calendar.DAY_OF_MONTH,  birthDate.get(Calendar.DAY_OF_MONTH));
                birthDayCalendar.set(Calendar.HOUR_OF_DAY, 0);
                birthDayCalendar.set(Calendar.MINUTE, 0);
                birthDayCalendar.set(Calendar.SECOND, 0);
                birthDayCalendar.set(Calendar.MILLISECOND, 0);

                difference = calendar.getTimeInMillis() - birthDayCalendar.getTimeInMillis();

                if (difference < 0) {
                    difference = millisInYear - Math.abs(difference);
                };
                percentage = (difference * 100.0)/((double) millisInYear);
                yearProgressValue = percentage * 3.6;

                formatter.applyPattern("#0.0000000");

                yearProgress.setInfo(yearProgressValue.intValue(), formatter.format(percentage) + " %");

                //day progress
                timeValue = calendar.get(Calendar.HOUR_OF_DAY) * 60.0 + calendar.get(Calendar.MINUTE);//minutes
                timeValue = timeValue * 60 + calendar.get(Calendar.SECOND);//seconds
                timeValue = timeValue * 1000 + calendar.get(Calendar.MILLISECOND);//milliseconds
                timeValue = (timeValue * 360.0 * 2.0)/((long)1000 * secondsInDay);
                dayProgress.setInfo(timeValue.intValue(), timeValue.toString());

            }
        }).start();

    }

}
