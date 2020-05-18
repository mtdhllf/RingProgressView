# RingProgressView  

## [介绍博客](https://mtdhllf.github.io/ringview/)

一个简单的环形进度条,支持纯色/渐变,可选线条样式,可配置调整起始/结束角度、最小/最大进度等,具体配置看后面参数介绍  
A simple circular progress bar, supporting pure/gradient, optional line style, configurable adjustment of start/end angle, minimum/maximum progress, etc. See the following parameters for specific configuration  

[![API](https://img.shields.io/badge/API-9%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)
[ ![Download](https://api.bintray.com/packages/mtdhllf/maven/ring-progress/images/download.svg?version=1.1.0) ](https://bintray.com/mtdhllf/maven/ring-progress/1.1.0/link)
[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://github.com/amitshekhariitbhu/Fast-Android-Networking/blob/master/LICENSE)
## Preview

![gif](https://github.com/mtdhllf/RingProgressView/blob/master/shot/shot1.gif)

### Setting up the dependency

Add this dependencies in your build.gradle

```groovy
implementation 'com.mtdhllf.widget:ring-progress:1.1.0'
```

(Please replace the latest version: [ ![Download](https://api.bintray.com/packages/mtdhllf/maven/ring-progress/images/download.svg?version=1.1.0) ](https://bintray.com/mtdhllf/maven/ring-progress/1.1.0/link))  

## Usage  
```xml
<com.mtdhllf.widget.progress.RingProgressView
           android:id="@+id/ring"
           android:layout_width="200dp"
           android:layout_height="200dp"
           android:layout_gravity="center"
           app:f_ringBgColor="#464646"
           app:f_roundWidth="8dp"
           app:f_startBgAngle="0"
           app:f_endBgAngle="360"/>

   <com.mtdhllf.widget.progress.RingProgressView
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                app:f_ringBgColor="#464647"
                app:f_startColor="#6CC6A9"
                app:f_centerColor="#2AF4BC"
                app:f_endColor="#26F28A"
                app:f_roundWidth="8dp"
                app:f_progress="60"/>

    <com.mtdhllf.widget.progress.RingProgressView
            android:layout_gravity="center"
            android:layout_width="140dp"
            android:layout_height="140dp"
            app:f_strokeCap="round"
            app:f_startColor="#6CC6A9"
            app:f_centerColor="#2AF4BC"
            app:f_endColor="#6AAFFD"
            app:f_roundWidth="8dp"
            app:f_progress="50"/>

```
## Attr
```xml
<declare-styleable name="RingProgressView">
  <!--前景环形是否使用渐变-->
  <attr format="boolean" name="f_useGradient"/>
  <!--前景环形渐变起始颜色-->
  <attr format="color" name="f_startColor"/>
  <!--前景环形渐变中间颜色-->
  <attr format="color" name="f_centerColor"/>
  <!--前景环形渐变结束颜色-->
  <attr format="color" name="f_endColor"/>
  <!--前景环形颜色-->
  <attr format="color" name="f_ringColor"/>
  <!--背景环形颜色-->
  <attr format="color" name="f_ringBgColor"/>
  <!--前景环形起始角度-->
  <attr format="float" name="f_startAngle"/>
  <!--前景环形结束角度-->
  <attr format="float" name="f_endAngle"/>
  <!--背景环形起始角度-->
  <attr format="float" name="f_startBgAngle"/>
  <!--背景环形结束角度-->
  <attr format="float" name="f_endBgAngle"/>
  <!--环形线宽-->
  <attr format="dimension" name="f_roundWidth"/>
  <!--进度变化动画时长-->
  <attr format="integer" name="f_duration"/>
  <!--前景环形开始进度-->
  <attr format="float" name="f_startProgress"/>
  <!--前景环形结束进度-->
  <attr format="float" name="f_endProgress"/>
  <!--前景环形目标进度(你想设置的)-->
  <attr format="float" name="f_progress"/>
  <!--环形线帽样式Paint.Cap-->
  <attr format="enum" name="f_strokeCap">
      <!--默认没有-->
      <enum name="butt" value="0"/>
      <!--圆角-->
      <enum name="round" value="1"/>
      <!--直角-->
      <enum name="square" value="2"/>
  </attr>
  <!--环形描边样式,详见Paint.Join-->
  <attr format="enum" name="f_strokeJoin">
      <enum name="miter" value="0"/>
      <enum name="round" value="1"/>
      <enum name="bevel" value="2"/>
  </attr>
</declare-styleable>
```

## About me

An android developer in Shenzhen.

If you want to make friends with me, You can email to me.
my [email](mailto:mtdhllf@qq.com) :smiley:
