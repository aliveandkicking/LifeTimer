import javax.swing.*;
import java.awt.*;


public class AdditionalInfoPanel extends JPanel {

    private class InfoLabel extends JLabel{

        private Integer converter;
        private String prefix;
        private String suffix;

        public InfoLabel(String aPrefix){
            prefix = aPrefix;
            setFont(new Font(null, Font.BOLD, 16));
        }

        public void setText(int value) {
            converter = value;
            super.setText(prefix + converter.toString());
        }

        public void setSuffix(int value) {
            converter = value;
            this.suffix = " of " + converter.toString();
        }
    }

    private InfoLabel seconds = new InfoLabel("Seconds passed: ");
    private InfoLabel minutes = new InfoLabel("Minutes passed: ");
    private InfoLabel hours = new InfoLabel("Hours passed: ");
    private InfoLabel weeks = new InfoLabel("Weeks passed: ");
    private InfoLabel months = new InfoLabel("Months passed: ");

    public AdditionalInfoPanel setMonths(int months) {
        this.months.setText(months);
        return this;
    }

    public AdditionalInfoPanel setWeeks(int weeks) {
        this.weeks.setText(weeks);
        return this;
    }

    public AdditionalInfoPanel setHours(int hours) {
        this.hours.setText(hours);
        return this;
    }

    public AdditionalInfoPanel setMinutes(int minutes) {
        this.minutes.setText(minutes);
        return this;
    }

    public AdditionalInfoPanel setSeconds(int seconds) {
        this.seconds.setText(seconds);
        return this;
    }

    public AdditionalInfoPanel setMaxMonths(int months) {
        this.months.setSuffix(months);
        return this;
    }

    public AdditionalInfoPanel setMaxWeeks(int weeks) {
        this.weeks.setSuffix(weeks);
        return this;
    }

    public AdditionalInfoPanel setMaxHours(int hours) {
        this.hours.setSuffix(hours);
        return this;
    }

    public AdditionalInfoPanel setMaxMinutes(int minutes) {
        this.minutes.setSuffix(minutes);
        return this;
    }

    public AdditionalInfoPanel setMaxSeconds(int seconds) {
        this.seconds.setSuffix(seconds);
        return this;
    }

    public void RefreshData(){
        this.revalidate();
        this.repaint();
    }

    public AdditionalInfoPanel() {

        this.setLayout(new GridLayout(5, 1));
        this.add(seconds);
        this.add(minutes);
        this.add(hours);
        this.add(weeks);
        this.add(months);

    }
}
