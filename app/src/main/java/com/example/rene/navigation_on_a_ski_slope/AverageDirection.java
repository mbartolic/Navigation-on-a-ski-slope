package com.example.rene.navigation_on_a_ski_slope;

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
        /* int x1, y1, x2, y2, rez;
        Point p, futurePoint;
        bool zastavica1 = false, zastavica2 = false;
        List<Point> point = new List<Point>();
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
                        int z = point[j - 1].X - point[j - 2].X;
                        int zz = point[j - 1].Y - point[j - 2].Y;
                        futurePoint.X = point[j - 1].X + z;
                        futurePoint.Y = point[j - 1].Y + zz;
                    }
                }
            }*/
    }
}
