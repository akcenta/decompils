// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ShapeFactory.java

import java.awt.*;
import java.awt.geom.*;

public class ShapeFactory
{

    public ShapeFactory(int shape_type)
    {
        width = 25;
        height = 25;
        stroke = new BasicStroke(3F);
        switch(shape_type / 10)
        {
        case 1: // '\001'
            shape = createStar(3, new Point(0, 0), (double)width / 2D, (double)width / 2D);
            break;

        case 3: // '\003'
            shape = createStar(5, new Point(0, 0), (double)width / 2D, (double)width / 4D);
            break;

        case 5: // '\005'
            shape = new java.awt.geom.Rectangle2D.Double((double)(-width) / 2D, (double)(-height) / 2D, width, height);
            break;

        case 7: // '\007'
            GeneralPath path = new GeneralPath();
            double tmp_height = (Math.sqrt(2D) / 2D) * (double)height;
            path.moveTo(-width / 2, -tmp_height);
            path.lineTo(0.0D, -tmp_height);
            path.lineTo(width / 2, tmp_height);
            path.closePath();
            shape = path;
            break;

        case 9: // '\t'
            shape = new java.awt.geom.Arc2D.Double((double)(-width) / 2D, (double)(-height) / 2D, width, height, 30D, 300D, 2);
            break;

        case 2: // '\002'
        case 4: // '\004'
        case 6: // '\006'
        case 8: // '\b'
        default:
            throw new Error("type is nusupported");
        }
        switch(shape_type % 10)
        {
        case 1: // '\001'
            stroke = new BasicStroke(3F);
            break;

        case 4: // '\004'
            stroke = new BasicStroke(7F);
            break;

        case 7: // '\007'
            paint = new GradientPaint(-width, -height, Color.white, width, height, Color.gray, true);
            break;

        case 8: // '\b'
            paint = Color.red;
            break;

        case 2: // '\002'
        case 5: // '\005'
        case 6: // '\006'
        default:
            throw new Error("type is nusupported");

        case 3: // '\003'
            break;
        }
    }

    private static Shape createStar(int arms, Point center, double rOuter, double rInner)
    {
        double angle = 3.1415926535897931D / (double)arms;
        GeneralPath path = new GeneralPath();
        for(int i = 0; i < 2 * arms; i++)
        {
            double r = (i & 1) != 0 ? rInner : rOuter;
            java.awt.geom.Point2D.Double p = new java.awt.geom.Point2D.Double((double)center.x + Math.cos((double)i * angle) * r, (double)center.y + Math.sin((double)i * angle) * r);
            if(i == 0)
                path.moveTo(p.getX(), p.getY());
            else
                path.lineTo(p.getX(), p.getY());
        }

        path.closePath();
        return path;
    }

    public Shape shape;
    public BasicStroke stroke;
    public Paint paint;
    public int width;
    public int height;
}
