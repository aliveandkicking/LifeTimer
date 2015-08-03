import java.awt.*;

public class ProgressPanelDay extends ProgressPanelBase {

    Color firstLapColor = new Color(0, 175, 0);

    @Override
    protected void onBeforePaint(Graphics2D g) {
        super.onBeforePaint(g);

        if (getAngle() > 360) {
            setAngle(getAngle() - 360);
            g.setColor(firstLapColor);
            g.fillOval(margin, infoPanelHeight, arcRadius, arcRadius);
        }
    }

    @Override
    protected Color getMainProgressColor() {
        if (getAngle() <= 360 ){
            return firstLapColor;
        }
        else{
            return super.getMainProgressColor();
        }
    }

    @Override
    protected void  onPaint(Graphics2D g){

        BasicStroke p2 = new BasicStroke(2);
        g.setStroke(p2);

        Integer i = 1;
        while (i<=12){
            Double x = 105* Math.sin(Math.toRadians((i*30)));
            Double y = 105* Math.sin(Math.toRadians(90- (i*30)));

            if (i == 12) {
                g.drawString(i.toString(), 124 + x.intValue(), 160 - y.intValue());
            }else{
                g.drawString(i.toString(), 127 + x.intValue(), 160 - y.intValue());
            }
            i++;

        }

    }

}
