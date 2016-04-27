namespace SkiPlayground
{
    partial class frmPlayground
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.labClickedPosition = new System.Windows.Forms.Label();
            this.lstAllPoints = new System.Windows.Forms.ListBox();
            this.labCurrentDirection = new System.Windows.Forms.Label();
            this.btnReset = new System.Windows.Forms.Button();
            this.labStyle = new System.Windows.Forms.Label();
            this.lblArrow = new System.Windows.Forms.Label();
            this.lblTurnAngle = new System.Windows.Forms.Label();
            this.cBoxOurAlg = new System.Windows.Forms.CheckBox();
            this.cBoxFuturePoints = new System.Windows.Forms.CheckBox();
            this.chkBoxRealPoints = new System.Windows.Forms.CheckBox();
            this.chbSmoothPoints = new System.Windows.Forms.CheckBox();
            this.chbCollectedPoints = new System.Windows.Forms.CheckBox();
            this.cmbSkiSlope = new System.Windows.Forms.ComboBox();
            this.btnSimulateSlope = new System.Windows.Forms.Button();
            this.movAverageBtn = new System.Windows.Forms.CheckBox();
            this.SuspendLayout();
            // 
            // labClickedPosition
            // 
            this.labClickedPosition.AutoSize = true;
            this.labClickedPosition.Location = new System.Drawing.Point(12, 9);
            this.labClickedPosition.Name = "labClickedPosition";
            this.labClickedPosition.Size = new System.Drawing.Size(81, 13);
            this.labClickedPosition.TabIndex = 0;
            this.labClickedPosition.Text = "Clicked position";
            // 
            // lstAllPoints
            // 
            this.lstAllPoints.FormattingEnabled = true;
            this.lstAllPoints.Location = new System.Drawing.Point(12, 25);
            this.lstAllPoints.Name = "lstAllPoints";
            this.lstAllPoints.Size = new System.Drawing.Size(120, 95);
            this.lstAllPoints.TabIndex = 1;
            // 
            // labCurrentDirection
            // 
            this.labCurrentDirection.AutoSize = true;
            this.labCurrentDirection.Location = new System.Drawing.Point(12, 123);
            this.labCurrentDirection.Name = "labCurrentDirection";
            this.labCurrentDirection.Size = new System.Drawing.Size(84, 13);
            this.labCurrentDirection.TabIndex = 2;
            this.labCurrentDirection.Text = "Current direction";
            // 
            // btnReset
            // 
            this.btnReset.Location = new System.Drawing.Point(847, 627);
            this.btnReset.Name = "btnReset";
            this.btnReset.Size = new System.Drawing.Size(75, 23);
            this.btnReset.TabIndex = 3;
            this.btnReset.Text = "Reset";
            this.btnReset.UseVisualStyleBackColor = true;
            this.btnReset.Click += new System.EventHandler(this.btnReset_Click);
            // 
            // labStyle
            // 
            this.labStyle.AutoSize = true;
            this.labStyle.Location = new System.Drawing.Point(12, 148);
            this.labStyle.Name = "labStyle";
            this.labStyle.Size = new System.Drawing.Size(30, 13);
            this.labStyle.TabIndex = 4;
            this.labStyle.Text = "Style";
            // 
            // lblArrow
            // 
            this.lblArrow.AutoSize = true;
            this.lblArrow.Location = new System.Drawing.Point(12, 175);
            this.lblArrow.Name = "lblArrow";
            this.lblArrow.Size = new System.Drawing.Size(34, 13);
            this.lblArrow.TabIndex = 5;
            this.lblArrow.Text = "Arrow";
            // 
            // lblTurnAngle
            // 
            this.lblTurnAngle.AutoSize = true;
            this.lblTurnAngle.Location = new System.Drawing.Point(12, 203);
            this.lblTurnAngle.Name = "lblTurnAngle";
            this.lblTurnAngle.Size = new System.Drawing.Size(56, 13);
            this.lblTurnAngle.TabIndex = 6;
            this.lblTurnAngle.Text = "TurnAngle";
            // 
            // cBoxOurAlg
            // 
            this.cBoxOurAlg.AutoSize = true;
            this.cBoxOurAlg.Checked = true;
            this.cBoxOurAlg.CheckState = System.Windows.Forms.CheckState.Checked;
            this.cBoxOurAlg.ForeColor = System.Drawing.Color.Blue;
            this.cBoxOurAlg.Location = new System.Drawing.Point(157, 50);
            this.cBoxOurAlg.Name = "cBoxOurAlg";
            this.cBoxOurAlg.Size = new System.Drawing.Size(88, 17);
            this.cBoxOurAlg.TabIndex = 13;
            this.cBoxOurAlg.Text = "Our algorithm";
            this.cBoxOurAlg.UseVisualStyleBackColor = true;
            this.cBoxOurAlg.Click += new System.EventHandler(this.cBoxOurAlg_Click);
            // 
            // cBoxFuturePoints
            // 
            this.cBoxFuturePoints.AutoSize = true;
            this.cBoxFuturePoints.ForeColor = System.Drawing.Color.Orange;
            this.cBoxFuturePoints.Location = new System.Drawing.Point(157, 73);
            this.cBoxFuturePoints.Name = "cBoxFuturePoints";
            this.cBoxFuturePoints.Size = new System.Drawing.Size(87, 17);
            this.cBoxFuturePoints.TabIndex = 14;
            this.cBoxFuturePoints.Text = "Future points";
            this.cBoxFuturePoints.UseVisualStyleBackColor = true;
            // 
            // chkBoxRealPoints
            // 
            this.chkBoxRealPoints.AutoSize = true;
            this.chkBoxRealPoints.Checked = true;
            this.chkBoxRealPoints.CheckState = System.Windows.Forms.CheckState.Checked;
            this.chkBoxRealPoints.Location = new System.Drawing.Point(157, 119);
            this.chkBoxRealPoints.Name = "chkBoxRealPoints";
            this.chkBoxRealPoints.Size = new System.Drawing.Size(79, 17);
            this.chkBoxRealPoints.TabIndex = 15;
            this.chkBoxRealPoints.Text = "Real points";
            this.chkBoxRealPoints.UseVisualStyleBackColor = true;
            this.chkBoxRealPoints.Click += new System.EventHandler(this.chkBoxRealPoints_Click);
            // 
            // chbSmoothPoints
            // 
            this.chbSmoothPoints.AutoSize = true;
            this.chbSmoothPoints.Location = new System.Drawing.Point(157, 144);
            this.chbSmoothPoints.Name = "chbSmoothPoints";
            this.chbSmoothPoints.Size = new System.Drawing.Size(93, 17);
            this.chbSmoothPoints.TabIndex = 16;
            this.chbSmoothPoints.Text = "Smooth points";
            this.chbSmoothPoints.UseVisualStyleBackColor = true;
            this.chbSmoothPoints.Click += new System.EventHandler(this.chbSmoothPoints_Click);
            // 
            // chbCollectedPoints
            // 
            this.chbCollectedPoints.AutoSize = true;
            this.chbCollectedPoints.Checked = true;
            this.chbCollectedPoints.CheckState = System.Windows.Forms.CheckState.Checked;
            this.chbCollectedPoints.Location = new System.Drawing.Point(157, 27);
            this.chbCollectedPoints.Name = "chbCollectedPoints";
            this.chbCollectedPoints.Size = new System.Drawing.Size(101, 17);
            this.chbCollectedPoints.TabIndex = 17;
            this.chbCollectedPoints.Text = "Collected points";
            this.chbCollectedPoints.UseVisualStyleBackColor = true;
            // 
            // cmbSkiSlope
            // 
            this.cmbSkiSlope.FormattingEnabled = true;
            this.cmbSkiSlope.Location = new System.Drawing.Point(623, 25);
            this.cmbSkiSlope.Name = "cmbSkiSlope";
            this.cmbSkiSlope.Size = new System.Drawing.Size(121, 21);
            this.cmbSkiSlope.TabIndex = 18;
            // 
            // btnSimulateSlope
            // 
            this.btnSimulateSlope.Location = new System.Drawing.Point(642, 62);
            this.btnSimulateSlope.Name = "btnSimulateSlope";
            this.btnSimulateSlope.Size = new System.Drawing.Size(91, 28);
            this.btnSimulateSlope.TabIndex = 19;
            this.btnSimulateSlope.Text = "Simulate";
            this.btnSimulateSlope.UseVisualStyleBackColor = true;
            this.btnSimulateSlope.Click += new System.EventHandler(this.btnSimulateSlope_Click);
            // 
            // movAverageBtn
            // 
            this.movAverageBtn.AutoSize = true;
            this.movAverageBtn.Location = new System.Drawing.Point(157, 168);
            this.movAverageBtn.Name = "movAverageBtn";
            this.movAverageBtn.Size = new System.Drawing.Size(104, 17);
            this.movAverageBtn.TabIndex = 20;
            this.movAverageBtn.Text = "Moving Average";
            this.movAverageBtn.UseVisualStyleBackColor = true;
            this.movAverageBtn.CheckedChanged += new System.EventHandler(this.movAverageBtn_CheckedChanged);
            // 
            // frmPlayground
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackgroundImage = global::SkiPlayground.Properties.Resources.slope;
            this.ClientSize = new System.Drawing.Size(942, 684);
            this.Controls.Add(this.movAverageBtn);
            this.Controls.Add(this.btnSimulateSlope);
            this.Controls.Add(this.cmbSkiSlope);
            this.Controls.Add(this.chbCollectedPoints);
            this.Controls.Add(this.chbSmoothPoints);
            this.Controls.Add(this.chkBoxRealPoints);
            this.Controls.Add(this.cBoxFuturePoints);
            this.Controls.Add(this.cBoxOurAlg);
            this.Controls.Add(this.lblTurnAngle);
            this.Controls.Add(this.lblArrow);
            this.Controls.Add(this.labStyle);
            this.Controls.Add(this.btnReset);
            this.Controls.Add(this.labCurrentDirection);
            this.Controls.Add(this.lstAllPoints);
            this.Controls.Add(this.labClickedPosition);
            this.Name = "frmPlayground";
            this.Text = "Playground";
            this.MouseUp += new System.Windows.Forms.MouseEventHandler(this.frmPlayground_MouseUp);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label labClickedPosition;
        private System.Windows.Forms.ListBox lstAllPoints;
        private System.Windows.Forms.Label labCurrentDirection;
        private System.Windows.Forms.Button btnReset;
        private System.Windows.Forms.Label labStyle;
        private System.Windows.Forms.Label lblArrow;
        private System.Windows.Forms.Label lblTurnAngle;
        private System.Windows.Forms.CheckBox cBoxOurAlg;
        private System.Windows.Forms.CheckBox cBoxFuturePoints;
        private System.Windows.Forms.CheckBox chkBoxRealPoints;
        private System.Windows.Forms.CheckBox chbSmoothPoints;
        private System.Windows.Forms.CheckBox chbCollectedPoints;
        private System.Windows.Forms.ComboBox cmbSkiSlope;
        private System.Windows.Forms.Button btnSimulateSlope;
        private System.Windows.Forms.CheckBox movAverageBtn;
    }
}

