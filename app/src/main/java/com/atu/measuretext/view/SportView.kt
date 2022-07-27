package com.atu.measuretext.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import com.atu.measuretext.dp

private val CIRCLE_COLOR = Color.parseColor("#90A4AE")
private val HIGHLIGHT_COLOR = Color.parseColor("#FF4081")
private val RING_WIDHT = 20.dp
private val RADIUS = 150.dp
private var TEXT = "agag"

class SportView constructor(context: Context,attributeSet: AttributeSet? = null)
    :View(context,attributeSet){

    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 100.dp
        textAlign = Paint.Align.CENTER
    }

    private val bounds = Rect()
    private val fontmetrics = Paint.FontMetrics()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //绘制圆环
        paint.style = Paint.Style.STROKE
        paint.color = CIRCLE_COLOR
        paint.strokeWidth = RING_WIDHT
        canvas.drawCircle(width/ 2f ,height / 2f , RADIUS,paint)

        //绘制进度条
        paint.color = HIGHLIGHT_COLOR
        paint.strokeCap = Paint.Cap.ROUND //设置进度条两端的弧度
        canvas.drawArc(
            width / 2f - RADIUS,
            height / 2f - RADIUS,
            width / 2f + RADIUS,
            height / 2f + RADIUS,
            -90f,
            225f,
            false,
            paint
        )

        //绘制文字
        paint.style = Paint.Style.FILL

        //适合静态显示 思路 获取文字的边界 然后计算出纵向的中心点  距离baseline的距离就是要偏移的距离
        paint.textAlign = Paint.Align.CENTER
        paint.getTextBounds(TEXT,0, TEXT.length,bounds)
        canvas.drawText(TEXT,width / 2f,height / 2f - (bounds.bottom + bounds.top) / 2f,paint)

        //适合动态显示 跟文字无关  根据两个重要的基线ascent descent 计算出中心点
//        paint.getFontMetrics(fontmetrics)
//        canvas.drawText(TEXT,width / 2f,height / 2f - (fontmetrics.ascent + fontmetrics.descent) / 2f,paint)

        //文字上贴边 做贴边
        paint.textAlign = Paint.Align.LEFT
        paint.getTextBounds(TEXT,0, TEXT.length,bounds)
        canvas.drawText(TEXT,-bounds.left.toFloat(),-bounds.top.toFloat(),paint)

        //下贴边 右贴边
        paint.textAlign = Paint.Align.RIGHT
        paint.getTextBounds(TEXT,0, TEXT.length,bounds)
        paint.getFontMetrics(fontmetrics)
        canvas.drawText(TEXT,width.toFloat() + bounds.left,height - fontmetrics.descent,paint)

    }

    fun setText(content: String){
        TEXT = content
        invalidate()
    }
}