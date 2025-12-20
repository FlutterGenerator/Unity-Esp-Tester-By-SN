package com.android.support;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Paint.Align;
import android.util.Log;
import android.graphics.Canvas;
import java.util.Locale;
import android.graphics.Color;
import java.util.Date;
import android.graphics.Paint;
import java.text.SimpleDateFormat;
import android.view.View;

public class ESPView extends View implements Runnable {
  Paint mStrokePaint;
  Paint mFilledPaint;
  Paint mTextPaint;
  Thread mThread;
  int FPS = 60;
  long sleepTime;
  Date time;
  SimpleDateFormat formatter;

  public ESPView(Context context) {
    super(context, null, 0);
    InitializePaints();
    setFocusableInTouchMode(false);
    setBackgroundColor(Color.TRANSPARENT);
    time = new Date();
    formatter = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
    sleepTime = 1000 / FPS;
    mThread = new Thread(this);
    mThread.start();
  }

  @Override
  protected void onDraw(Canvas canvas) {
    if (canvas != null && getVisibility() == VISIBLE) {
      ClearCanvas(canvas);
      time.setTime(System.currentTimeMillis());
      Menu.DrawOn(this, canvas);
    }
  }

  @Override
  public void run() {
    android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
    while (mThread.isAlive() && !mThread.isInterrupted()) {
      try {
        long t1 = System.currentTimeMillis();
        postInvalidate();
        long td = System.currentTimeMillis() - t1;
        Thread.sleep(Math.max(Math.min(0, sleepTime - td), sleepTime));
      } catch (InterruptedException it) {
        Log.e("OverlayThread", it.getMessage());
      }
    }
  }

  public void InitializePaints() {
    this.mStrokePaint = new Paint();
    this.mStrokePaint.setStyle(Style.STROKE);
    this.mStrokePaint.setAntiAlias(true);
    this.mStrokePaint.setColor(Color.rgb(0, 0, 0));
    this.mFilledPaint = new Paint();
    this.mFilledPaint.setStyle(Style.FILL);
    this.mFilledPaint.setAntiAlias(true);
    this.mFilledPaint.setColor(Color.rgb(0, 0, 0));
    this.mTextPaint = new Paint();
    this.mTextPaint.setStyle(Style.FILL_AND_STROKE);
    this.mTextPaint.setAntiAlias(true);
    this.mTextPaint.setColor(Color.rgb(0, 0, 0));
    this.mTextPaint.setTextAlign(Align.CENTER);
    this.mTextPaint.setStrokeWidth(1.1f);
  }

  public void ClearCanvas(Canvas cvs) {
    cvs.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
  }

  public void DrawLine(
      Canvas cvs,
      int a,
      int r,
      int g,
      int b,
      float lineWidth,
      float fromX,
      float fromY,
      float toX,
      float toY) {
    mStrokePaint.setColor(Color.rgb(r, g, b));
    mStrokePaint.setAlpha(a);
    mStrokePaint.setStrokeWidth(lineWidth);
    cvs.drawLine(fromX, fromY, toX, toY, mStrokePaint);
  }

  public void DrawTextInRect(
      Canvas canvas,
      int i,
      int i2,
      int i3,
      int i4,
      int i5,
      int i6,
      int i7,
      int i8,
      String str,
      float f,
      float f2,
      float f3,
      float f4,
      float f5,
      float f6) {
    this.mTextPaint.setColor(Color.rgb(i2, i3, i4));
    this.mTextPaint.setAlpha(i);
    if (((float) getRight()) > f6 || ((float) getBottom()) > f6) {
      this.mTextPaint.setTextSize(((float) 4) + f3);
    } else if (((float) getRight()) == f6 || ((float) getBottom()) == f6) {
      this.mTextPaint.setTextSize(((float) 2) + f3);
    } else {
      this.mTextPaint.setTextSize(f3);
    }
    Rect rect = new Rect();
    this.mTextPaint.getTextBounds(str, 0, str.length(), rect);
    float width = (f - (((float) rect.width()) / 1.7f)) - f5;
    float height = (f2 - (((float) rect.height()) * 1.2f)) - f5;
    float width2 = ((((float) rect.width()) / 1.7f) + f) + f5;
    float height2 = ((((float) rect.height()) / 1.7f) + f2) + f5;
    this.mFilledPaint.setColor(Color.argb(i5, i6, i7, i8));
    canvas.drawRoundRect(width, height, width2, height2, f4, f4, this.mFilledPaint);
    this.mStrokePaint.setStrokeWidth(f5);
    this.mStrokePaint.setStyle(Style.STROKE);
    this.mStrokePaint.setColor(Color.argb(i, i2, i3, i4));
    canvas.drawRoundRect(width, height, width2, height2, f4, f4, this.mStrokePaint);
    canvas.drawText(str, f, f2, this.mTextPaint);
  }

  public void DrawText(
      Canvas canvas, int i, int i2, int i3, int i4, String str, float f, float f2, float f3) {
    this.mTextPaint.setColor(Color.rgb(i2, i3, i4));
    this.mTextPaint.setAlpha(i);
    if (getRight() > 1920 || getBottom() > 1920) {
      this.mTextPaint.setTextSize(((float) 4) + f3);
    } else if (getRight() == 1920 || getBottom() == 1920) {
      this.mTextPaint.setTextSize(((float) 2) + f3);
    } else {
      this.mTextPaint.setTextSize(f3);
    }
    canvas.drawText(str, f, f2, this.mTextPaint);
  }

  public void DrawCircle(
      Canvas cvs, int a, int r, int g, int b, float stroke, float posX, float posY, float radius) {
    mStrokePaint.setColor(Color.rgb(r, g, b));
    mStrokePaint.setAlpha(a);
    mStrokePaint.setStrokeWidth(stroke);
    cvs.drawCircle(posX, posY, radius, mStrokePaint);
  }

  public void DrawHead(
      Canvas cvs, int a, int r, int g, int b, float posX, float posY, float radius) {
    mFilledPaint.setColor(Color.rgb(r, g, b));
    mFilledPaint.setAlpha(a);
    mFilledPaint.setARGB(255, 255, 0, 0);
    cvs.drawCircle(posX, posY, radius, mFilledPaint);
  }

  public void DrawFilledCircle(
      Canvas cvs, int a, int r, int g, int b, float posX, float posY, float radius) {
    mFilledPaint.setColor(Color.rgb(r, g, b));
    mFilledPaint.setAlpha(a);
    cvs.drawCircle(posX, posY, radius, mFilledPaint);
  }

  public void DrawRect(
      Canvas cvs,
      int a,
      int r,
      int g,
      int b,
      int stroke,
      float x,
      float y,
      float width,
      float height) {
    mStrokePaint.setStrokeWidth(stroke);
    mStrokePaint.setColor(Color.rgb(r, g, b));
    mStrokePaint.setAlpha(a);
    cvs.drawRect(x, y, x + width, y + height, mStrokePaint);
  }

  public void DrawFilledRect(
      Canvas cvs, int a, int r, int g, int b, float x, float y, float width, float height) {
    mFilledPaint.setColor(Color.rgb(r, g, b));
    mFilledPaint.setAlpha(a);
    cvs.drawRect(x, y, x + width, y + height, mFilledPaint);
  }
}
