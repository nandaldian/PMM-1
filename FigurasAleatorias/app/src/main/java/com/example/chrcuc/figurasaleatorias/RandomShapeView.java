package com.example.chrcuc.figurasaleatorias;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by chrcuc on 7/11/17.
 */

public class RandomShapeView extends View {
    private Integer[] mBackgrounds =
            { Color.CYAN, Color.GRAY, Color.LTGRAY,Color.MAGENTA, Color.YELLOW, Color.WHITE };
    private Paint[] mForegrounds =
            { makePaint(Color.BLACK), makePaint(Color.BLUE),makePaint(Color.GREEN), makePaint(Color.RED)};
    private Bitmap[] mPics =
            {makeBitmap(R.drawable.descarga),
                    makeBitmap(R.drawable.descargal),
                    makeBitmap(R.drawable.descargap),
                    makeBitmap(R.drawable.images),
                    makeBitmap(R.drawable.llo) };
    private String mMessage = "NOOO!";

    public RandomShapeView(Context context) {
        super(context);
    }
    public RandomShapeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(RandoUtils.randomElement(mBackgrounds));
        int viewWidth = getWidth();
        int viewHeight = getHeight();
        int avgShapeWidth = viewWidth/5;
        for(int i=0; i<20; i++) {
            drawRandomCircle(canvas, viewWidth, viewHeight, avgShapeWidth);
            drawRandomRect(canvas, viewWidth, viewHeight, avgShapeWidth);
            drawRandomBitmap(canvas, viewWidth, viewHeight);
            drawRandomText(canvas, viewWidth, viewHeight, avgShapeWidth);
        }
    }
    private Paint makePaint(int color) {
        Paint p = new Paint();
        p.setColor(color);
        return(p);
    }
    private Bitmap makeBitmap(int bitmapId) {
        return(BitmapFactory.decodeResource(getResources(), bitmapId));
    }

    private void drawRandomCircle(Canvas canvas, int viewWidth,
                                  int viewHeight, int avgShapeWidth) {
        float x = RandoUtils.randomFloat(viewWidth);
        float y = RandoUtils.randomFloat(viewHeight);
        float radius = RandoUtils.randomFloat(avgShapeWidth/2);
        Paint circleColor = RandoUtils.randomElement(mForegrounds);
        canvas.drawCircle(x, y, radius, circleColor);
    }

    private void drawRandomRect(Canvas canvas, int viewWidth,
                                int viewHeight, int avgShapeWidth) {
        float left = RandoUtils.randomFloat(viewWidth);
        float top = RandoUtils.randomFloat(viewHeight);
        float width = RandoUtils.randomFloat(avgShapeWidth);
        float right = left + width;
        float bottom = top + width;
        Paint squareColor = RandoUtils.randomElement(mForegrounds);
        canvas.drawRect(left, top, right, bottom, squareColor);
    }
    private void drawRandomBitmap(Canvas canvas, int viewWidth,
                                  int viewHeight) {
        float left = RandoUtils.randomFloat(viewWidth);
        float top = RandoUtils.randomFloat(viewHeight);
        Bitmap pic = RandoUtils.randomElement(mPics);
        // Last arg is the Paint: you can use null for opaque images
        canvas.drawBitmap(pic, left, top, null);
    }
    private void drawRandomText(Canvas canvas, int viewWidth,
                                int viewHeight, int avgShapeWidth) {
        float x = RandoUtils.randomFloat(viewWidth);
        float y = RandoUtils.randomFloat(viewHeight);
        float textSize = RandoUtils.randomFloat(avgShapeWidth);
        Paint textPaint = RandoUtils.randomElement(mForegrounds);
        textPaint.setTextSize(textSize);
        canvas.drawText(mMessage, x, y, textPaint);
    }
}

