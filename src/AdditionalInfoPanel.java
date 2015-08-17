import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Formatter;
import java.util.Locale;


public class AdditionalInfoPanel extends JPanel {

    final private Font labelFont = new Font(null, Font.BOLD, 16);

    private class InfoLabel extends JLabel{

        private NumberFormat nf = NumberFormat.getNumberInstance(Locale.FRANCE);
        private DecimalFormat formatter = (DecimalFormat)nf;
        private String suffix = "";

        public InfoLabel(){
            formatter.applyPattern("###,###,###,###");
            setFont(labelFont);
        }

        public void setText(long value) {
            super.setText(" : " + formatter.format(value) + suffix);
        }

        public void setSuffix(long value) {
            this.suffix = " of " + formatter.format(value);
        }
    }

    private InfoLabel seconds = new InfoLabel();
    private InfoLabel minutes = new InfoLabel();
    private InfoLabel hours = new InfoLabel();
    private InfoLabel weeks = new InfoLabel();
    private InfoLabel months = new InfoLabel();

    public AdditionalInfoPanel setMonths(long months) {
        this.months.setText(months);
        return this;
    }

    public AdditionalInfoPanel setWeeks(long weeks) {
        this.weeks.setText(weeks);
        return this;
    }

    public AdditionalInfoPanel setHours(long hours) {
        this.hours.setText(hours);
        return this;
    }

    public AdditionalInfoPanel setMinutes(long minutes) {
        this.minutes.setText(minutes);
        return this;
    }

    public AdditionalInfoPanel setSeconds(long seconds) {
        this.seconds.setText(seconds);
        return this;
    }

    public AdditionalInfoPanel setMaxMonths(long months) {
        this.months.setSuffix(months);
        return this;
    }

    public AdditionalInfoPanel setMaxWeeks(long weeks) {
        this.weeks.setSuffix(weeks);
        return this;
    }

    public AdditionalInfoPanel setMaxHours(long hours) {
        this.hours.setSuffix(hours);
        return this;
    }

    public AdditionalInfoPanel setMaxMinutes(long minutes) {
        this.minutes.setSuffix(minutes);
        return this;
    }

    public AdditionalInfoPanel setMaxSeconds(long seconds) {
        this.seconds.setSuffix(seconds);
        return this;
    }

    public void RefreshData(){
        this.revalidate();
        this.repaint();
    }

    public AdditionalInfoPanel() {

        class InternalLabel extends JLabel{
            InternalLabel(String text, Font labelFont) {
                super(text);
                setFont(labelFont);
            }
        }

        JPanel leftPanel = new JPanel(new GridLayout(5, 1));
        JPanel rightPanel = new JPanel(new GridLayout(5, 1));

        this.setLayout(null);

        this.add(leftPanel);
        leftPanel.setSize(130, 220);
        leftPanel.setLocation(15, 0);

        this.add(rightPanel);
        rightPanel.setSize(255, 220);
        rightPanel.setLocation(145, 0);

        leftPanel.add(new InternalLabel("Seconds passed", labelFont));
        leftPanel.add(new InternalLabel("Minutes passed", labelFont));
        leftPanel.add(new InternalLabel("Hours passed", labelFont));
        leftPanel.add(new InternalLabel("Weeks passed", labelFont));
        leftPanel.add(new InternalLabel("Months passed", labelFont));

        rightPanel.add(seconds);
        rightPanel.add(minutes);
        rightPanel.add(hours);
        rightPanel.add(weeks);
        rightPanel.add(months);

    }
}
