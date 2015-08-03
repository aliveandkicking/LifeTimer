import java.awt.*;

public class ProgressPanelDay extends ProgressPanelBase {
    @Override
    protected void  onPaint(Graphics2D g){

        BasicStroke p2 = new BasicStroke(2);
        g.setStroke(p2);

        Integer i = 1;
        while (i<=12){
            Double x = 105* Math.sin(Math.toRadians((i*30)));
            Double y = 105* Math.sin(Math.toRadians(90- (i*30)));
            g.drawString(i.toString(), 127 + x.intValue(), 160 - y.intValue());
            i++;

        }

    }

}
