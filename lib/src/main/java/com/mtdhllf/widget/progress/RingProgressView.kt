package com.mtdhllf.widget.progress

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator

/**
 * author: mtdhllf
 * time  : 2019/06/14 18:28
 * desc  :环形进度条
 */
class RingProgressView : View {
    //初始化标记
    private var init = false
    //是否使用渐变
    var useGradient = false
        set(value) {
            field = value
            init()
        }
    //环形颜色
    var ringColor = Color.GREEN
        set(value) {
            field = value
            init()
        }
    //环形背景色
    var ringBgColor = Color.TRANSPARENT
        set(value) {
            field = value
            init()
        }
    //起始颜色
    var startColor = Color.TRANSPARENT
        set(value) {
            field = value
            if (init && value!=Color.TRANSPARENT) {
                useGradient = true
            }
        }
    //中间颜色
    private var centerColor = Color.TRANSPARENT
        set(value) {
            field = value
            if (init && value!=Color.TRANSPARENT) {
                useGradient = true
            }
        }
    //结束颜色
    private var endColor = Color.TRANSPARENT
        set(value) {
            field = value
            if (init && value!=Color.TRANSPARENT) {
                useGradient = true
            }
        }
    //线宽
    var roundWidth = 8
        set(value) {
            field = value
            init()
        }

    //线帽样式
    var strokeCap = Paint.Cap.BUTT
        set(value) {
            field = value
            init()
        }
    //描边样式
    var strokeJoin = Paint.Join.MITER
        set(value) {
            field = value
            init()
        }
    //起始背景角度
    var startBgAngle = -90f
        set(value) {
            field = value
            postInvalidate()
        }
    //结束背景角度
    var endBgAngle = startBgAngle + 360f
        set(value) {
            field = value
            postInvalidate()
        }
    //起始角度
    var startAngle = -90f
        set(value) {
            field = value
            postInvalidate()
        }
    //结束角度
    var endAngle = startAngle + 360f
        set(value) {
            field = value
            postInvalidate()
        }
    //活动角度(绘制缓存)
    private var sweepAngle = 0f
    //更新动画时长
    var duration = 250L
        set(value) {
            field = value
            va.duration = value
            postInvalidate()
        }
    //起始进度(最小进度)
    var startProgress = 0f
        set(value) {
            field = value
            tempProgress = startProgress
            progress = progress
        }
    //结束进度(最大进度)
    var endProgress = 100f
        set(value) {
            field = value
            tempProgress = startProgress
            progress = progress
        }
    //目标进度
    var progress = 50f
        set(value) {
            //合法性检查
            field = when {
                value in startProgress..endProgress -> value

                value > Math.max(startProgress, endProgress) -> Math.max(startProgress, endProgress)

                else -> Math.min(startProgress, endProgress)
            }
            va.setFloatValues(tempProgress, progress)
            va.start()
        }
    //插值器
    private val va = ValueAnimator()
    //画笔
    private val bgPaint = Paint()
    private val forePaint = Paint()
    //绘制实时进度
    private var tempProgress = progress
    //绘制矩形
    private val rectF = RectF()

    constructor(ctx: Context) : super(ctx)

    constructor(ctx: Context, attrs: AttributeSet?) : this(ctx, attrs, 0)

    constructor(ctx: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(ctx, attrs, defStyleAttr) {
        //属性配置
        val ta = context.obtainStyledAttributes(attrs, R.styleable.RingProgressView)
        ringColor = ta.getColor(R.styleable.RingProgressView_f_ringColor, ringColor)
        ringBgColor = ta.getColor(R.styleable.RingProgressView_f_ringBgColor, ringBgColor)
        startColor = ta.getColor(R.styleable.RingProgressView_f_startColor, startColor)
        centerColor = ta.getColor(R.styleable.RingProgressView_f_centerColor, centerColor)
        endColor = ta.getColor(R.styleable.RingProgressView_f_endColor, endColor)
        //渐变色设置检查
        if (startColor != Color.TRANSPARENT || startColor != Color.TRANSPARENT || startColor != Color.TRANSPARENT) {
            useGradient = true
        }
        useGradient = ta.getBoolean(R.styleable.RingProgressView_f_useGradient, useGradient)
        startAngle = ta.getFloat(R.styleable.RingProgressView_f_startAngle, startAngle)
        endAngle = ta.getFloat(R.styleable.RingProgressView_f_endAngle, endAngle)
        startBgAngle = ta.getFloat(R.styleable.RingProgressView_f_startBgAngle, startBgAngle)
        endBgAngle = ta.getFloat(R.styleable.RingProgressView_f_endBgAngle, endBgAngle)
        roundWidth = ta.getDimensionPixelSize(R.styleable.RingProgressView_f_roundWidth, roundWidth)
        strokeCap = when (ta.getInteger(R.styleable.RingProgressView_f_strokeCap, 0)) {
            0 -> Paint.Cap.BUTT
            1 -> Paint.Cap.ROUND
            else -> Paint.Cap.SQUARE
        }
        strokeJoin = when (ta.getInteger(R.styleable.RingProgressView_f_strokeJoin, 0)) {
            0 -> Paint.Join.MITER
            1 -> Paint.Join.ROUND
            else -> Paint.Join.BEVEL
        }
        duration = ta.getInt(R.styleable.RingProgressView_f_duration, duration.toInt()).toLong()
        startProgress = ta.getFloat(R.styleable.RingProgressView_f_startProgress, startProgress)
        endProgress = ta.getFloat(R.styleable.RingProgressView_f_endProgress, endProgress)
        progress = ta.getFloat(R.styleable.RingProgressView_f_progress, progress)
        tempProgress = progress
        ta.recycle()
    }

    init {
        va.interpolator = LinearInterpolator()
        va.setFloatValues(startProgress, progress)
        va.duration = duration
        va.addUpdateListener {
            tempProgress = it.animatedValue as Float
            postInvalidate()
        }
        va.start()
    }

    override fun onDraw(canvas: Canvas) {
        //画背景环形
        canvas.drawArc(rectF, startBgAngle, endBgAngle - startBgAngle, false, bgPaint)
        //计算前景环形角度
        sweepAngle = tempProgress / (endProgress - startProgress) * (endAngle - startAngle)
        //画前景环形
        canvas.drawArc(rectF, startAngle, sweepAngle, false, forePaint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (!init) {
            init = true
        }
        init()
    }

    /**
     * 初始化参数
     */
    private fun init() {
        //设置画笔参数
        bgPaint.strokeWidth = roundWidth.toFloat()
        bgPaint.strokeCap = strokeCap
        bgPaint.strokeJoin = Paint.Join.ROUND
        bgPaint.style = Paint.Style.STROKE
        bgPaint.isAntiAlias = true
        bgPaint.color = ringBgColor
        forePaint.strokeWidth = roundWidth.toFloat()
        forePaint.strokeCap = strokeCap
        forePaint.strokeJoin = Paint.Join.ROUND
        forePaint.style = Paint.Style.STROKE
        forePaint.isAntiAlias = true
        //着色器颜色筛选
        val filter = intArrayOf(startColor, centerColor, endColor).filter { it != Color.TRANSPARENT }
        val colorArray:IntArray = when{
            filter.isEmpty()-> intArrayOf(startColor, centerColor, endColor)
            filter.size>1-> filter.toIntArray()
            else->{
                intArrayOf(filter.first(),filter.first())
            }
        }
        //着色器/颜色设置
        if (useGradient) {
            forePaint.shader = LinearGradient(
                0f,
                0f,
                width.toFloat(),
                height.toFloat(),
                colorArray,
                null,
                Shader.TileMode.CLAMP
            )
        } else {
            forePaint.shader = null
            forePaint.color = ringColor
        }
        //矩形
        rectF.set(
            0f + roundWidth / 2,
            0f + roundWidth / 2,
            width.toFloat() - roundWidth / 2,
            height.toFloat() - roundWidth / 2
        )
        if (init) {
            postInvalidate()
        }
    }


}