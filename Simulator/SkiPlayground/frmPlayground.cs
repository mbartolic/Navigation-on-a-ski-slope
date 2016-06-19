using System;
using System.Collections.Generic;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Windows.Forms;
using System.Windows;



namespace SkiPlayground
{
    public partial class frmPlayground : Form
    {
        enum Directions { Left, Right, NaN };
        Point last;
        List<Point> pointHistory = new List<Point>();
        List<Point> pHist = new List<Point>();
        public frmPlayground()
        {
            InitializeComponent();
            cmbSkiSlope.Items.Add("Slope 1");
            cmbSkiSlope.Items.Add("Slope 2");
        }
        public void DrawPoint(){
            Graphics g = this.CreateGraphics();

            float thickness = 1;
            int size = 8;

            foreach (Point p in pointHistory)
            {
                int x = p.X;
                int y = p.Y;
                g.DrawEllipse(new Pen(Brushes.Green, thickness), new Rectangle(x - (size / 2), y - (size / 2), size, size));
            }
            VunakVunce(pointHistory);
        }
        
        public void VunakVunce(List<Point> pointHistory)
        {
            Graphics g = this.CreateGraphics();
            int size = 8;
            Point pMovAvg = new Point(0,0);
            if ((pointHistory.Count > 2) && (movAverageBtn.Checked == true))
            {
                for (int i = 1; i < pointHistory.Count() - 1; i++)
                {
                    int x0 = pointHistory[i - 1].X;
                    int x1 = pointHistory[i].X;
                    int x2 = pointHistory[i + 1].X;
                    int x = ((x0 + 2 * x1 + x2) / 4);

                    int y0 = pointHistory[i - 1].Y;
                    int y1 = pointHistory[i].Y;
                    int y2 = pointHistory[i + 1].Y;
                    int y = ((y0 + 2 * y1 + y2) / 4);
                    pMovAvg.X = x;
                    pMovAvg.Y = y;
                    pointMoveAvg.Add(pMovAvg);
                    g.DrawEllipse(new Pen(Brushes.Black, 2), new Rectangle(x - (size / 2), y - (size / 2), size, size));
                }
                                    
                    
            }
        }   
            
        public void DrawLine(Point start, Point end)
        {
            Graphics g = this.CreateGraphics();
            float thickness = 2;

        }
        private void frmPlayground_MouseUp(object sender, MouseEventArgs e)
        {
            this.Refresh();

            labClickedPosition.Text = "last.X=" + e.X + "; last.Y=" + e.Y + "; pHist.Add(last);";

            if(last.X != 0 || last.Y != 0)
            DrawLine(last, e.Location);
            

            last = e.Location;
            pointHistory.Add(last);

            // refresh list
            BindingSource bs = new BindingSource();
            bs.DataSource = pointHistory;
            lstAllPoints.DataSource = bs;

         //   labCurrentDirection.Text = "Current direction: " + GetCurrentDirection(e.Location).ToString();
            GetStyle();
            DrawLine(turn, path);
            
           
            if (pointHistory.Count < 10)
            {
                DrawLine(pointHistory.First(), e.Location);
            }
            else
            {
                DrawLine(pointHistory[pointHistory.Count - 9], e.Location);
            }

            double a, b;
            List<Point> bestFit = GenerateLinearBestFit(pointHistory, out a, out b);
            this.Refresh();
            DrawPoint();
            DrawPoints(Brushes.Red, pointHistory);
            DrawingCollectedPoint(extremePoints);
            DrawingCollectedPoint(extremePoints);
            //DrawingHalfPoint(drawHalfPnt);
            //   AvgDirection(pointHistory);
            Calculating(pointHistory);


        }
        private void DrawPoints(Brush b, List<Point> points)
        {
            Graphics g = this.CreateGraphics();
            float thickness = 1;
            try
            {
                if (points.Count > 2)
                    g.DrawLines(new Pen(b, thickness), points.ToArray());

            }catch(Exception e) { }
            }        
        private void GetStyle()
        {
            
            List<int> allX = pointHistory.Select(c => c.X).ToList();

            int[] pointArray = allX.ToArray();
            // poigrati se sa:
            // http://www.imagingshop.com/linear-and-nonlinear-least-squares-with-math-net/
            

            double stdDev = CalculateStdDev(allX);

            if (stdDev > 75)
            {
                labStyle.Text = "Spread out style ";
            }
            else
            {
                labStyle.Text = "Straight style ";
            }
            
            labStyle.Text += " " + (Math.Round(stdDev, 2));

        }
        private Directions GetCurrentDirection(Point currentPoint)
        {
            if (pointHistory.Count < 2)
                return Directions.NaN;

            Point last = pointHistory[pointHistory.Count - 2];
            DrawLine(last, currentPoint);

            if (currentPoint.X > last.X)
            {
                 return Directions.Right;
            }
            else
            {
                return Directions.Left;
            }

        }
        private void chbSmoothPoints_Click(object sender, EventArgs e)
        {
            if (chbSmoothPoints.Checked)
            {
                chkBoxRealPoints.Checked = false;
                cBoxOurAlg.Checked = true;
                movAverageBtn.Checked = false;
            }
           
        }
        private void chkBoxRealPoints_Click(object sender, EventArgs e)
        {
            if (chkBoxRealPoints.Checked)
            {
                chbSmoothPoints.Checked = false;
                cBoxOurAlg.Checked = true;
                movAverageBtn.Checked = false;
            }
        }
        private void movAverageBtn_CheckedChanged(object sender, EventArgs e)
        {

            if (movAverageBtn.Checked)
            {
                chkBoxRealPoints.Checked = false;
                cBoxOurAlg.Checked = true;
                chbSmoothPoints.Checked = false;
            }
        }
        private void cBoxOurAlg_Click(object sender, EventArgs e)
        {
            chbSmoothPoints.Checked = true;
        }

        private void btnSimulateSlope_Click(object sender, EventArgs e)
        {
            extremePoints.Clear();
           // extremePointsY.Clear();
            pointHistory.Clear();
            pointSmooth.Clear();
            point.Clear();
            drawHalfPnt.Clear();
            drawPoint.Clear();
            pHist.Clear();
            last = new Point(0, 0);
            labCurrentDirection.Text = "";
            labClickedPosition.Text = "";

            this.Refresh();
            if (String.Compare(cmbSkiSlope.Text.ToString(), "Slope 1") == 0)
            {
                last.X = 424; last.Y = 45; pHist.Add(last);
                last.X = 405; last.Y = 52; pHist.Add(last);
                last.X = 383; last.Y = 60; pHist.Add(last);
                last.X = 362; last.Y = 69; pHist.Add(last);
                last.X = 347; last.Y = 84; pHist.Add(last);
                last.X = 348; last.Y = 98; pHist.Add(last);
                last.X = 360; last.Y = 109; pHist.Add(last);
                last.X = 380; last.Y = 118; pHist.Add(last);
                last.X = 405; last.Y = 127; pHist.Add(last);
                last.X = 424; last.Y = 132; pHist.Add(last);
                last.X = 438; last.Y = 144; pHist.Add(last);
                last.X = 425; last.Y = 159; pHist.Add(last);
                last.X = 408; last.Y = 169; pHist.Add(last);
                last.X = 387; last.Y = 175; pHist.Add(last);
                last.X = 365; last.Y = 184; pHist.Add(last);
                last.X = 344; last.Y = 196; pHist.Add(last);
                last.X = 334; last.Y = 207; pHist.Add(last);
                last.X = 339; last.Y = 219; pHist.Add(last);
                last.X = 354; last.Y = 228; pHist.Add(last);
                last.X = 377; last.Y = 235; pHist.Add(last);
                last.X = 394; last.Y = 242; pHist.Add(last);
                last.X = 404; last.Y = 252; pHist.Add(last);
                last.X = 400; last.Y = 265; pHist.Add(last);
                last.X = 387; last.Y = 275; pHist.Add(last);
                last.X = 367; last.Y = 283; pHist.Add(last);
                last.X = 346; last.Y = 290; pHist.Add(last);
                last.X = 333; last.Y = 302; pHist.Add(last);
                last.X = 339; last.Y = 316; pHist.Add(last);
                last.X = 353; last.Y = 326; pHist.Add(last);
                last.X = 367; last.Y = 332; pHist.Add(last);
                last.X = 387; last.Y = 341; pHist.Add(last);
                last.X = 411; last.Y = 347; pHist.Add(last);
                last.X = 432; last.Y = 353; pHist.Add(last);
                last.X = 458; last.Y = 357; pHist.Add(last);
                last.X = 479; last.Y = 363; pHist.Add(last);
                last.X = 498; last.Y = 372; pHist.Add(last);
                last.X = 507; last.Y = 386; pHist.Add(last);
                last.X = 499; last.Y = 403; pHist.Add(last);
                last.X = 481; last.Y = 415; pHist.Add(last);
                last.X = 458; last.Y = 428; pHist.Add(last);
                last.X = 439; last.Y = 439; pHist.Add(last);
                last.X = 425; last.Y = 449; pHist.Add(last);
                last.X = 415; last.Y = 466; pHist.Add(last);
                last.X = 425; last.Y = 483; pHist.Add(last);
                last.X = 442; last.Y = 487; pHist.Add(last);
                last.X = 464; last.Y = 494; pHist.Add(last);
                last.X = 486; last.Y = 500; pHist.Add(last);
                last.X = 511; last.Y = 504; pHist.Add(last);
                last.X = 531; last.Y = 498; pHist.Add(last);
                last.X = 552; last.Y = 488; pHist.Add(last);
                last.X = 574; last.Y = 494; pHist.Add(last);
                last.X = 587; last.Y = 510; pHist.Add(last);
                last.X = 584; last.Y = 528; pHist.Add(last);
                last.X = 571; last.Y = 542; pHist.Add(last);
                last.X = 532; last.Y = 557; pHist.Add(last);
                last.X = 505; last.Y = 564; pHist.Add(last);
                last.X = 479; last.Y = 572; pHist.Add(last);
                last.X = 467; last.Y = 584; pHist.Add(last);
                last.X = 470; last.Y = 596; pHist.Add(last);
                last.X = 486; last.Y = 603; pHist.Add(last);
                // last.X = 488; last.Y = 600; pHist.Add(last);
                last.X = 513; last.Y = 610; pHist.Add(last);
                last.X = 536; last.Y = 615; pHist.Add(last);
                last.X = 565; last.Y = 621; pHist.Add(last);
                last.X = 592; last.Y = 628; pHist.Add(last);
                last.X = 618; last.Y = 630; pHist.Add(last);
                last.X = 639; last.Y = 642; pHist.Add(last);
                last.X = 689; last.Y = 626; pHist.Add(last);
                last.X = 715; last.Y = 620; pHist.Add(last);
                last.X = 755; last.Y = 596; pHist.Add(last);
                last.X = 770; last.Y = 573; pHist.Add(last);
                last.X = 775; last.Y = 543; pHist.Add(last);
                last.X = 768; last.Y = 512; pHist.Add(last);
                last.X = 756; last.Y = 488; pHist.Add(last);
                last.X = 744; last.Y = 468; pHist.Add(last);
                last.X = 732; last.Y = 444; pHist.Add(last);
                last.X = 733; last.Y = 422; pHist.Add(last);
                last.X = 748; last.Y = 402; pHist.Add(last);
                last.X = 768; last.Y = 380; pHist.Add(last);
                last.X = 776; last.Y = 351; pHist.Add(last);
                last.X = 767; last.Y = 323; pHist.Add(last);
                last.X = 752; last.Y = 294; pHist.Add(last);
                /*last.X = 740; last.Y = 267; pHist.Add(last);
                last.X = 735; last.Y = 240; pHist.Add(last);
                last.X = 741; last.Y = 212; pHist.Add(last);
                last.X = 757; last.Y = 188; pHist.Add(last);
                last.X = 790; last.Y = 176; pHist.Add(last);
                  last.X = 815; last.Y = 159; pHist.Add(last);
                  last.X = 839; last.Y = 137; pHist.Add(last);
                  last.X = 830; last.Y = 110; pHist.Add(last);
                  last.X = 838; last.Y = 83; pHist.Add(last);
                  last.X = 854; last.Y = 66; pHist.Add(last);
                  last.X = 874; last.Y = 63; pHist.Add(last);*/
            }else if (String.Compare(cmbSkiSlope.Text.ToString(), "Slope 2") == 0)
            {
                last.X = 365; last.Y = 93; pHist.Add(last);
                last.X = 388; last.Y = 86; pHist.Add(last);
                last.X = 409; last.Y = 78; pHist.Add(last);
                last.X = 428; last.Y = 79; pHist.Add(last);
                last.X = 449; last.Y = 82; pHist.Add(last);
                last.X = 468; last.Y = 94; pHist.Add(last);
                last.X = 476; last.Y = 108; pHist.Add(last);
                last.X = 479; last.Y = 124; pHist.Add(last);
                last.X = 474; last.Y = 144; pHist.Add(last);
                last.X = 467; last.Y = 160; pHist.Add(last);
                last.X = 461; last.Y = 177; pHist.Add(last);
                last.X = 455; last.Y = 192; pHist.Add(last);
                last.X = 453; last.Y = 211; pHist.Add(last);
                last.X = 463; last.Y = 227; pHist.Add(last);
                last.X = 483; last.Y = 233; pHist.Add(last);
                last.X = 508; last.Y = 240; pHist.Add(last);
                last.X = 530; last.Y = 238; pHist.Add(last);
                last.X = 551; last.Y = 232; pHist.Add(last);
                last.X = 577; last.Y = 225; pHist.Add(last);
                last.X = 600; last.Y = 217; pHist.Add(last);
                last.X = 623; last.Y = 208; pHist.Add(last);
                last.X = 646; last.Y = 200; pHist.Add(last);
                last.X = 667; last.Y = 202; pHist.Add(last);
                last.X = 683; last.Y = 214; pHist.Add(last);
                last.X = 686; last.Y = 236; pHist.Add(last);
                last.X = 676; last.Y = 258; pHist.Add(last);
                last.X = 663; last.Y = 282; pHist.Add(last);
                last.X = 650; last.Y = 304; pHist.Add(last);
                last.X = 642; last.Y = 324; pHist.Add(last);
                last.X = 646; last.Y = 341; pHist.Add(last);
                last.X = 672; last.Y = 347; pHist.Add(last);
                last.X = 698; last.Y = 339; pHist.Add(last);
                last.X = 719; last.Y = 332; pHist.Add(last);
                last.X = 745; last.Y = 326; pHist.Add(last);
                last.X = 770; last.Y = 325; pHist.Add(last);
                last.X = 795; last.Y = 336; pHist.Add(last);
                last.X = 804; last.Y = 355; pHist.Add(last);
                last.X = 800; last.Y = 377; pHist.Add(last);
                last.X = 795; last.Y = 400; pHist.Add(last);
                last.X = 785; last.Y = 418; pHist.Add(last);
                last.X = 766; last.Y = 438; pHist.Add(last);
                last.X = 744; last.Y = 451; pHist.Add(last);
                last.X = 716; last.Y = 464; pHist.Add(last);
                last.X = 678; last.Y = 474; pHist.Add(last);
                last.X = 650; last.Y = 466; pHist.Add(last);
                last.X = 620; last.Y = 448; pHist.Add(last);
                last.X = 602; last.Y = 437; pHist.Add(last);
                last.X = 583; last.Y = 421; pHist.Add(last);
                last.X = 560; last.Y = 413; pHist.Add(last);
                last.X = 534; last.Y = 431; pHist.Add(last);
                last.X = 518; last.Y = 449; pHist.Add(last);
                last.X = 502; last.Y = 466; pHist.Add(last);
                last.X = 488; last.Y = 482; pHist.Add(last);
                last.X = 450; last.Y = 483; pHist.Add(last);
                last.X = 412; last.Y = 464; pHist.Add(last);
                last.X = 393; last.Y = 442; pHist.Add(last);
                last.X = 383; last.Y = 420; pHist.Add(last);
                last.X = 363; last.Y = 401; pHist.Add(last);
                last.X = 333; last.Y = 404; pHist.Add(last);
                last.X = 310; last.Y = 422; pHist.Add(last);
                last.X = 287; last.Y = 439; pHist.Add(last);
            }else if(selectedFile == true){
                for (int counter = 0; counter < numbersLong.Length - 1; counter = counter + 2)
                {
                    last.X = numbersLong[counter];
                    last.Y = numbersLong[counter + 1];
                    pHist.Add(last);
                }             
            }
            BindingSource bs2 = new BindingSource();
            bs2.DataSource = pHist;
            lstAllPoints.DataSource = bs2;

            for (int i = 0; i < pHist.Count(); i++)
            {
                pointHistory.Add(pHist[i]);
                DrawPoint();
                DrawPoints(Brushes.Red, pointHistory);
                DrawingCollectedPoint(extremePoints);         
            //    DrawingHalfPoint(drawHalfPnt);
                //   AvgDirection(pointHistory);
                Calculating(pointHistory);
            }
        }
        //------------------------------------------------------------------------------------------------
        int x1, y1, x2, y2, rez, x,y;
        Point p, futurePoint, currentLocation, pSmooth, extPoint;
        bool flagXRight = false, flagXLeft = false, flagYRight = false, flagYLeft = false;
        List<bool> flag = new List<bool>();
        List<Point> point = new List<Point>();
        List<Point> extremePoints = new List<System.Drawing.Point>();
      //  List<Point> extremePointsY = new List<System.Drawing.Point>();
        Point turn = new Point(283, 473);
        Point path = new Point(175, 566);
        List<Point> pointSmooth = new List<Point>();
        List<Point> drawHalfPnt = new List<Point>();
        List<Point> pointMoveAvg = new List<Point>();
        private void Calculating(List<Point> pointHistory)
        {            
            int i = pointHistory.Count() - 1;
            int j = pointSmooth.Count() - 1;
            if(pointHistory.Count() > 3)
            {
                if (pointSmooth.Count() > 0)
                {
                    pSmooth = Project(pointSmooth[j], pointHistory[i], pointHistory[i-1]);
                    
                    pointSmooth.Add(pSmooth);
                }
                else
                {
                    pSmooth =  Project(pointHistory[i - 2], pointHistory[i], pointHistory[i - 1]);                    
                    pointSmooth.Add(pSmooth);
                }
                DrawPoints(Brushes.BlueViolet, pointSmooth);
                if (chbSmoothPoints.Checked)
                {
                    AvgDirection(pointSmooth);
                }
                else if (chkBoxRealPoints.Checked)
                {
                    AvgDirection(pointHistory);
                }
                else if (movAverageBtn.Checked)
                {
                    AvgDirection(pointMoveAvg);
                }

          }
        }

        private Point Project(Point line1, Point line2, Point toProject)
        {
            double m = (double)(line2.Y - line1.Y) / (line2.X - line1.X);
            double b = (double)line1.Y - (m * line1.X);

            double x = (m * toProject.Y + toProject.X - m * b) / (m * m + 1);
            double y = (m * m * toProject.Y + m * toProject.X + b) / (m * m + 1);

            return new Point((int)x, (int)y);
        }
        private void AvgDirection(List<Point> pointHistory)
        {
            int i = pointHistory.Count() - 1;
            if (pointHistory.Count() > 5)
            {
                if ((pointHistory[i - 2].X < pointHistory[i - 1].X) && (pointHistory[i - 2].X < pointHistory[i].X) && (pointHistory[i - 2].X < pointHistory[i - 3].X) && (pointHistory[i - 2].X < pointHistory[i - 4].X) && (pointHistory[i - 2].X < pointHistory[i - 5].X))
                {
                    extPoint.X = pointHistory[i - 2].X;
                    extPoint.Y = pointHistory[i - 2].Y;
                    labCurrentDirection.Text = "Left";

                    extremePoints.Add(extPoint);

                    DrawingCollectedPoint(extremePoints);

                    flag.Add(true);
                }
                if ((pointHistory[i - 2].X > pointHistory[i - 1].X) && (pointHistory[i - 2].X > pointHistory[i].X) && (pointHistory[i - 2].X > pointHistory[i - 3].X) && (pointHistory[i - 2].X > pointHistory[i - 4].X) && (pointHistory[i - 2].X > pointHistory[i - 5].X))
                {
                    extPoint.X = pointHistory[i - 2].X;
                    extPoint.Y = pointHistory[i - 2].Y;
                    labCurrentDirection.Text = "Right";

                    extremePoints.Add(extPoint);

                    DrawingCollectedPoint(extremePoints);

                    flag.Add(true);
                }
                if ((pointHistory[i - 2].Y < pointHistory[i - 1].Y) && (pointHistory[i - 2].Y < pointHistory[i].Y) && (pointHistory[i - 2].Y < pointHistory[i - 3].Y) && (pointHistory[i - 2].Y < pointHistory[i - 4].Y) && (pointHistory[i - 2].Y < pointHistory[i - 5].Y))
                {
                    extPoint.X = pointHistory[i - 2].X;
                    extPoint.Y = pointHistory[i - 2].Y;
                    labCurrentDirection.Text = "Left";

                    extremePoints.Add(extPoint);

                    DrawingCollectedPoint(extremePoints);

                    flag.Add(true);
                }
                if ((pointHistory[i - 2].Y > pointHistory[i - 1].Y) && (pointHistory[i - 2].Y > pointHistory[i].Y) && (pointHistory[i - 2].Y > pointHistory[i - 3].Y) && (pointHistory[i - 2].Y > pointHistory[i - 4].Y) && (pointHistory[i - 2].Y > pointHistory[i - 5].Y))
                {
                    extPoint.X = pointHistory[i - 2].X;
                    extPoint.Y = pointHistory[i - 2].Y;
                    labCurrentDirection.Text = "Right";

                    extremePoints.Add(extPoint);

                    DrawingCollectedPoint(extremePoints);

                    flag.Add(true);
                }

                int flagI = flag.Count() - 1;
                if (flagI > 1)
                {
                    if (flag[flagI] && flag[flagI] || flag[flagI] && flag[flagI])
                    {
                        flag.RemoveAt(flagI - 1);
                        int ii = extremePoints.Count() - 1;
                        if (extremePoints.Count() > 1)
                        {
                            p.X = (extremePoints[ii].X + extremePoints[ii - 1].X) / 2;
                            p.Y = (extremePoints[ii].Y + extremePoints[ii - 1].Y) / 2;
                            drawHalfPnt.Add(p);
                            DrawingHalfPoint(drawHalfPnt);


                            flagXRight = false;
                            flagXLeft = false;
                            flagYRight = false;
                            flagYLeft = false;

                            point.Add(p);
                            int j = point.Count();
                            Graphics gg = this.CreateGraphics();
                            float thicks = 2;
                            Point p1 = new Point(x1, y1);
                            Point p2 = new Point(x2, y2);
                            if (extremePoints.Count() > 2)
                            {
                                gg.DrawLine(new Pen(Brushes.Brown, thicks), p1, p2);
                                if (j >= 2)
                                {
                                    Graphics g = this.CreateGraphics();
                                    float thickness = 2;
                                    if (cBoxOurAlg.Checked)
                                    {
                                        try
                                        {
                                            for (int counter = 1; counter < point.Count(); counter++)
                                            {
                                                g.DrawLine(new Pen(Brushes.Blue, thickness), point[counter], point[counter - 1]);
                                            }
                                        } catch (Exception e) { };
                                    }
                                    int z = point[j - 1].X - point[j - 2].X;
                                    int zz = point[j - 1].Y - point[j - 2].Y;
                                    futurePoint.X = point[j - 1].X + z;
                                    futurePoint.Y = point[j - 1].Y + zz;
                                    angleBetweenPoints(futurePoint);
                                    if (cBoxFuturePoints.Checked)
                                    {
                                        g.DrawLine(new Pen(Brushes.Yellow, thickness), point[j - 1], futurePoint);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else if (pointHistory.Count() <= 3 && pointHistory.Count() > 1) {//if( > 3)
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

            lblTurnAngle.Text = Angle.ToString();
            return Angle;
        }
        //----------------------------------------------------------------------------------------
        List<Point> drawPoint = new List<Point>();
        private void DrawingCollectedPoint(List<Point> drawPoint)
        {
            Graphics g = this.CreateGraphics();

            try
            {

                if (chbCollectedPoints.Checked)
                {
                    foreach (Point point in drawPoint)
                    {
                        g.FillRectangle(Brushes.Purple, point.X, point.Y, 10, 10);
                    }
                }
            }catch(Exception e) { }
        }        
        private void DrawingHalfPoint(List<Point> drawHalfPoint)
        {
            Graphics g = this.CreateGraphics();
            drawHalfPoint.Add(p);

            if (chbCollectedPoints.Checked)
            {
                foreach (Point point in drawHalfPoint)
                {
                    g.FillRectangle(Brushes.Chocolate, point.X, point.Y, 10, 10);
                }
            }
        }
        private void btnReset_Click(object sender, EventArgs e)
        {
            extremePoints.Clear();      
            pointHistory.Clear();
            pointSmooth.Clear();
            point.Clear();
            drawHalfPnt.Clear();            
            drawPoint.Clear();
            pHist.Clear();
            last = new Point(0, 0);
            labCurrentDirection.Text = "";
            labClickedPosition.Text = "";
            lstAllPoints.DataSource = null;

            this.Refresh();
        }
        private double CalculateStdDev(IEnumerable<int> values)
        {
            double ret = 0;
            if (values.Count() > 0)
            {
                double avg = values.Average();
                double sum = values.Sum(d => Math.Pow(d - avg, 2));
                ret = Math.Sqrt((sum) / (values.Count() - 1));
            }
            return ret;
        }
        public List<Point> GenerateLinearBestFit(List<Point> points, out double a, out double b)
        {
            int numPoints = points.Count;
            double meanX = points.Average(point => point.X);
            double meanY = points.Average(point => point.Y);

            double sumXSquared = points.Sum(point => point.X * point.X);
            double sumXY = points.Sum(point => point.X * point.Y);

            a = (sumXY / numPoints - meanX * meanY) / (sumXSquared / numPoints - meanX * meanX);
            b = (a * meanX - meanY);

            double a1 = a;
            double b1 = b;

            return points.Select(point => new Point() { X = point.X, Y = (int)(a1 * point.X - b1) }).ToList();
        }
        public Boolean selectedFile;
        public int[] numbersLong;
        private void btnSelect_Click(object sender, EventArgs e)
        {
            OpenFileDialog openFileDialog1 = new OpenFileDialog();
            selectedFile = true;

            // Set filter options and filter index.
            openFileDialog1.Filter = "Text Files (.txt)|*.txt|All Files (*.*)|*.*";
            openFileDialog1.FilterIndex = 1;

            // Process input if the user clicked OK.
            if (openFileDialog1.ShowDialog() == DialogResult.OK)
            {
                // Open the selected file to read.
                System.IO.Stream fileStream = openFileDialog1.OpenFile();

                using (System.IO.StreamReader reader = new System.IO.StreamReader(fileStream))
                {
                    // Read the whole text from the file and write it the textbox.
                    // txtCoordinates.Text = reader.ReadToEnd();
                    string originalFileText = reader.ReadToEnd();
                    //spliting original string into strings which will represent numbers
                    char[] delimiterChars = { ' ', ',', ':', '\t', '\n' };
                    string[] numbers = originalFileText.Split(delimiterChars);
                    //txtCoordinates.Text = numbers[2];

                    //transforming string to long
                    //      int[] numbersLong = Array.ConvertAll(numbers, int.Parse);
                    //      int[] numbersLong = numbers.Select(int.Parse).ToArray();
                    numbersLong = new int[numbers.Length];
                    int i = 0;

                    foreach (String text in numbers)
                    {
                        int.TryParse(text, out numbersLong[i]);
                        ++i;
                    }
                    //txtCoordinates.Text = numbersLong[0].ToString();
                    
                    cmbSkiSlope.Text = "Selected file";  
               }
                fileStream.Close();

            }   
        }

        private void txtCoordinates_TextChanged(object sender, EventArgs e)
        {

        }

               
    }
}

