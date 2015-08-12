import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.*;
import java.util.Calendar;

public class MainFrom {

    final static Calendar birthDate = Calendar.getInstance();
    final static Integer limitAge = 80;
    final static Double approxDaysInYear = 365.25;
    final static Double approxDaysInMonth = 30.4375;
    final static Double daysInLimitAge = limitAge * approxDaysInYear;

    public static void main(String[] args){

        birthDate.set(Calendar.YEAR, 1989);
        birthDate.set(Calendar.MONTH, Calendar.JUNE);
        birthDate.set(Calendar.DAY_OF_MONTH, 27);
        birthDate.set(Calendar.HOUR_OF_DAY, 0);
        birthDate.set(Calendar.MINUTE, 0);
        birthDate.set(Calendar.SECOND, 0);
        birthDate.set(Calendar.MILLISECOND, 1);

        final JFrame mainForm = new JFrame("Timer");
        JButton button = new JButton("");
        JPanel mainPanel = new JPanel(null);
        final AdditionalInfoPanel additionalInfoPanel = new AdditionalInfoPanel();
        additionalInfoPanel.setSize(200, mainPanel.getHeight());
        additionalInfoPanel.setVisible(false);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (additionalInfoPanel.isVisible()) {

                    mainForm.setSize(mainForm.getWidth() - additionalInfoPanel.getWidth(), mainForm.getHeight());
                    additionalInfoPanel.setVisible(false);

                } else {
                    mainForm.setSize(mainForm.getWidth() + additionalInfoPanel.getWidth(), mainForm.getHeight());
                    additionalInfoPanel.setVisible(true);
                }

            }

        });

        try{
            ImageIcon icon = new ImageIcon("icon.png");
            mainForm.setIconImage(icon.getImage());
        }catch (NullPointerException e) {
                System.err.println("Caught NullPointerException: " + e.getMessage());
        }

        mainForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel.setSize(265 * 3, 345);
        mainPanel.setLayout(new GridLayout(1,4));

        mainForm.setSize(mainPanel.getWidth() + 30, mainPanel.getHeight());
        mainForm.setMinimumSize(new Dimension(260 * 3, 300));

        mainForm.setResizable(false);

        Double xPos = (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - mainForm.getHeight())/2.0;
        Double yPos = (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - mainForm.getWidth())/2.0;
        mainForm.setLocation(xPos.intValue(), yPos.intValue());
        mainForm.setLayout(null);

        Double yearProgressAngle = ((-1*
                (birthDate.get(Calendar.DAY_OF_YEAR) * 360.0) / Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_YEAR)*360.0)/365.25) + 86;

        final ProgressPanelLife lifeProgress = new ProgressPanelLife();
        final ProgressPanelYear yearProgress = new ProgressPanelYear(yearProgressAngle);
        final ProgressPanelDay dayProgress = new ProgressPanelDay();

        mainPanel.add(dayProgress);
        mainPanel.add(yearProgress);
        mainPanel.add(lifeProgress);

        mainForm.add(mainPanel);
        mainPanel.setLocation(0, 0);

        mainForm.add(button);
        button.setSize(30, mainPanel.getHeight());
        button.setLocation(mainPanel.getWidth() + 1, 0);

        mainForm.add(additionalInfoPanel);
        additionalInfoPanel.setSize(350, mainPanel.getHeight());
        additionalInfoPanel.setLocation(button.getLocation().x + button.getWidth(), 0);

        mainForm.setVisible(true);

        new Timer(100, new ActionListener() {

            long milliSecNow;
            long milliSecBirth;
            Long difference;

            Double lifeProgressValue;
            Double yearProgressValue;
            Double timeValue;

            Double daysPassed;
            Double yearsPassed;

            Double secondsPassed;
            Double minutesPassed;
            Double hoursPassed;
            Double weeksPassed;
            Double monthsPassed;

            Double monthPassedInYear;
            Double daysPassedInMonth;

            Double percentage;

            final int secondsInDay = 24*60*60;

            @Override
            public void actionPerformed(ActionEvent e) {

                Calendar calendar = Calendar.getInstance();

                int daysInYear = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
                Long millisInYear = daysInYear * secondsInDay * (long)1000;

                milliSecNow = calendar.getTimeInMillis();
                milliSecBirth = birthDate.getTimeInMillis();
                difference = milliSecNow - milliSecBirth;

                secondsPassed = difference/1000.0;
                minutesPassed = secondsPassed/60.0;
                hoursPassed = minutesPassed/60.0;
                daysPassed = hoursPassed/24.0;
                weeksPassed = daysPassed/7.0;
                monthsPassed = daysPassed/approxDaysInMonth;
                yearsPassed =  daysPassed/daysInYear;

                monthPassedInYear = (daysPassed - (yearsPassed.intValue() * approxDaysInYear))/approxDaysInMonth;
                daysPassedInMonth =  1.0 + daysPassed - (yearsPassed.intValue() * approxDaysInYear) - (monthPassedInYear.intValue() * approxDaysInMonth);

                //life progress
                percentage = (difference * 100.0)/(80.0 * millisInYear);
                lifeProgressValue = percentage * 3.6;
                DecimalFormat formatter = new DecimalFormat("#0.000000000");
                lifeProgress.setInfo(lifeProgressValue.intValue(), formatter.format(percentage) +" %",
                        "Age: " + yearsPassed.intValue() + " years " + monthPassedInYear.intValue() + " months " + daysPassedInMonth.intValue() + " days");

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
                yearProgress.setInfo(yearProgressValue.intValue(),
                    formatter.format(percentage) + " %", "Years Passed: " + yearsPassed.intValue() +  " of " + limitAge);

                //day progress
                timeValue = calendar.get(Calendar.HOUR_OF_DAY) * 60.0 + calendar.get(Calendar.MINUTE);//minutes
                timeValue = timeValue * 60 + calendar.get(Calendar.SECOND);//seconds
                timeValue = timeValue * 1000 + calendar.get(Calendar.MILLISECOND);//milliseconds

                percentage = (timeValue * 100.0)/((long)1000 * secondsInDay);
                timeValue = percentage * 3.6 * 2.0;
                formatter.applyPattern("#0.0000");

                dayProgress.setInfo(timeValue.intValue(),
                        formatter.format(percentage) + " %", "Days Passed: " + daysPassed.intValue() + " of " + daysInLimitAge.intValue());

                //additional info
                additionalInfoPanel.setSeconds(secondsPassed.intValue())
                                   .setMinutes(minutesPassed.intValue())
                                   .setHours(hoursPassed.intValue())
                                   .setWeeks(weeksPassed.intValue())
                                   .setMonths(monthsPassed.intValue()).
                                    RefreshData();
            }
        }).start();

    }

}
