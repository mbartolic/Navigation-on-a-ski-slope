package com.example.rene.navigation_on_a_ski_slope;

import android.graphics.Point;
import android.support.annotation.NonNull;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Rene on 22.12.2015..
 */
public class AverageDirection {

    private double x,y;

    public AverageDirection(double X, double Y) {
        x = X;
        y = Y;
    }

    private void AvgDirection(){  //algoritam u c# koji treba prevesti u javu a racuna trenutni smjer i predvida buduci smjer
        int x1, y1, x2, y2, rez;
        Point p, futurePoint;
        boolean zastavica1 = false;
        boolean zastavica2 = false;



    private void AvgDirection()
    {
        int i = pointHistory.Count() - 1;
        if (pointHistory.Count() > 3)
        {
            if ((pointHistory[i].X + pointHistory[i].Y) < (pointHistory[i - 1].X + pointHistory[i - 1].Y) && (pointHistory[i - 1].X + pointHistory[i - 1].Y) > (pointHistory[i - 2].X + pointHistory[i - 2].Y))
            {
                x1 = pointHistory[i - 1].X;
                y1 = pointHistory[i - 1].Y;
                labCurrentDirection.Text = "Left";
                zastavica1 = true;
            }
            if ((pointHistory[i].X + pointHistory[i].Y) > (pointHistory[i - 1].X + pointHistory[i - 1].Y) && (pointHistory[i - 1].X + pointHistory[i - 1].Y) < (pointHistory[i - 2].X + pointHistory[i - 2].Y))
            {
                x2 = pointHistory[i - 1].X;
                y2 = pointHistory[i - 1].Y;
                labCurrentDirection.Text = "Right";
                zastavica2 = true;
            }
            if (zastavica1 && zastavica2)
            {
                p.X = (x1 + x2) / 2;
                p.Y = (y1 + y2) / 2;
                zastavica1 = false;
                zastavica2 = false;
                point.Add(p);
                int j = point.Count();

                if (j >= 2)
                {
                    Graphics g = this.CreateGraphics();
                    float thickness = 2;
                    g.DrawLine(new Pen(Brushes.Blue, thickness), point[j - 2], point[j - 1]);
                    int z = point[j - 1].X - point[j - 2].X;
                    int zz = point[j - 1].Y - point[j - 2].Y;
                    futurePoint.X = point[j - 1].X + z;
                    futurePoint.Y = point[j - 1].Y + zz;
                    angleBetweenPoints(futurePoint);
                    g.DrawLine(new Pen(Brushes.Yellow, thickness), point[j - 1], futurePoint);
                }
            }
        }
        else if(pointHistory.Count() <= 3 && pointHistory.Count() > 1) {//if( > 3)
            Graphics g = this.CreateGraphics();
            float thickness = 2;
            g.DrawLine(new Pen(Brushes.Blue, thickness), pointHistory[1], pointHistory[0]);
        }
    }
    Point VectorAfterTurn =  new Point();
    Point VectorBeforeTurn = new Point();
    private Double angleBetweenPoints(Point PointAtTheMoment) {

        VectorBeforeTurn.X = PointAtTheMoment.X - turn.X;
        VectorBeforeTurn.Y = PointAtTheMoment.Y - turn.Y;

        VectorAfterTurn.X = path.X - turn.X;
        VectorAfterTurn.Y = path.Y - turn.Y;

        double VBTA = Math.Sqrt(Math.Pow(VectorBeforeTurn.X, 2) + Math.Pow(VectorBeforeTurn.Y, 2));
        double VATA = Math.Sqrt(Math.Pow(VectorAfterTurn.X, 2) + Math.Pow(VectorAfterTurn.Y, 2));

        double Product = VectorBeforeTurn.X * VectorAfterTurn.X + VectorBeforeTurn.Y * VectorAfterTurn.Y;

        double Angle = (180 / Math.PI) * Math.Acos(Product / (VBTA * VATA));

        Toast.makeText(getApplicationContext(), Angle + "", Toast.LENGTH_LONG).show();
        return Angle;
    }
    }
}
