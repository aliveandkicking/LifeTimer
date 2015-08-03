import java.awt.*;

public class ProgressPanelYear extends ProgressPanelBase {

    public ProgressPanelYear() {
        super();
        start = ((-1*178.0*360.0)/365.25) + 90;
    }

    @Override
    protected void  onBeforePaint(Graphics2D g){
        g.setColor(new Color(47, 255, 247));
        g.fillArc(margin, infoPanelHeight, arcRadius, arcRadius, 120, -90);

        g.setColor(new Color(67, 255, 82));
        g.fillArc(margin, infoPanelHeight, arcRadius, arcRadius, 30, -90);

        g.setColor(new Color(255, 254, 96));
        g.fillArc(margin, infoPanelHeight, arcRadius, arcRadius, -60, -90);

        g.setColor(new Color(255, 188, 91));
        g.fillArc(margin, infoPanelHeight, arcRadius, arcRadius, -150, -90);
    }

    @Override
    protected void  onPaint(Graphics2D g){

        BasicStroke p2 = new BasicStroke(2);
        g.setStroke(p2);

        Integer i = 0;
        while (i<12){

            Double x = 105* Math.sin(Math.toRadians(15 + (i*30)));
            Double y = 105* Math.sin(Math.toRadians(75 - (i*30)));

            Double x2 = 125* Math.sin(Math.toRadians((i*30)));
            Double y2 = 125* Math.sin(Math.toRadians(90 - (i*30)));

            i++;
            g.drawString(i.toString(), 127 + x.intValue(), 160 - y.intValue());
            g.drawLine(130, 155, 130 + x2.intValue(), 155 - y2.intValue());

        }
    }
}