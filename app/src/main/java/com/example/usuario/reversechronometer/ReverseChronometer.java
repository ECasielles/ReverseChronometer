package com.example.usuario.reversechronometer;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.SystemClock;
import android.util.AttributeSet;

/**
 * Ejemplo de control personalizado. Va a tener su propio hilo.
 */
//@SuppressLint("AppCompatCustomView")
public class ReverseChronometer extends android.support.v7.widget.AppCompatTextView implements Runnable {

    /**
     * Estado del componente que deben ser accesibles en el xml.
     * overallTime: tiempo total
     * warningTime: tiempo en el que cambia de color
     */
    private long overallTime, warningTime;
    private long startTime;

    public ReverseChronometer(Context context, AttributeSet attrs) {
        //CUIDAO: no vale sólo pasarle context
        super(context, attrs);
        //Recorre la raíz ReverseChronometer en attrs.xml
        //y guarda los atributos attrs en un array tipado
        TypedArray attributes = getContext().obtainStyledAttributes(attrs, R.styleable.ReverseChronometer);
        overallTime = attributes.getInteger(R.styleable.ReverseChronometer_overall_time, 60);
        warningTime = attributes.getInteger(R.styleable.ReverseChronometer_warning_time, 10);
        //reset();
        startTime = SystemClock.elapsedRealtime();
    }

    public void setOverallTime(long overallTimeMillis) {
        this.overallTime = overallTimeMillis;
    }

    public void setWarningTime(long warningTimeMillis) {
        this.warningTime = warningTimeMillis;
    }

    public void reset() {
        startTime = SystemClock.elapsedRealtime();
        setText("--:--");
        setTextColor(Color.BLACK);
    }

    @Override
    public void run() {
        //Actualizar los tiempos
        long elapsedSeconds = (SystemClock.elapsedRealtime() - startTime) / 1000;
        if (overallTime > elapsedSeconds) {
            long remainingSeconds = overallTime - elapsedSeconds;
            long minutes = remainingSeconds / 60;
            long seconds = remainingSeconds % 60;
            setText(String.format("%d:%02d", minutes, seconds));
            //En caso que nos encontremos en tiempo de warning
            if (remainingSeconds < warningTime)
                setTextColor(Color.RED);

            //postDelayed espera n milisegundos y vuelve a llamar a run
            postDelayed(this, 1000);
        } else {
            setText("00:00");
            setTextColor(Color.BLACK);
        }
    }

    public void stop() {

    }

}
