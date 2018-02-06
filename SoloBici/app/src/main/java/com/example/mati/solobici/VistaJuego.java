package com.example.chrcuc.solobici;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import java.util.List;
import java.util.Vector;

/**
 * Created by mati on 5/12/16.
 */

public class VistaJuego extends View {
    //	COCHES	//
    private Vector<Grafico> Coches;    //Vector con los Coches
    private int numCoches = 5;        //Numero inicial de Coches
    private int numMotos = 3;        //Fragmentos/Motos en que se dividir un Coche

    // BICI //
    private Grafico bici;
    private int giroBici;    //Incremento direccion de la bici
    private float aceleracionBici;//Aumento de velocidad en la bici
    private static final int PASO_GIRO_BICI = 5;
    private static final float PASO_ACELERACION_BICI = 2.5f;

    // THREAD Y TIEMPO //
    //Hilo encargado de procesar el tiempo
    private HiloJuego hiloJuego;
    //Tiempo que debe transcurrir para procesar cambios (ms)
    private static int PERIODO_PROCESO = 50;
    //Momento en el que se realiza el ultimo proceso
    private long ultimoProceso = 0;

    //PANTALLA TACTIL
    //MX Y MY RECUERDA LAS COORDENADAS DEL ULTIMO EVENTO

    private float mX = 0, mY = 0;
    private boolean disparo = false;

    //RUEDA

    private Grafico rueda;
    private static int VELOCIDAD_RUEDA = 12;
    private boolean ruedaActiva;
    private int distanciaRueda;

    //VARIABLES GLOBALES

    //CONTROLAR SI LA APLICACION ESTÁ EN SEGUNDO PLANO
    private boolean corriendo = false;

    //CONTROLAR SI LA APLICACION ESTÁ EN PAUSA
    private boolean pausa;


    public VistaJuego(Context contexto, AttributeSet atributos) {
        super(contexto, atributos);
        Drawable graficoBici, graficoCoche, graficoRueda;
        //Obtenemos la imagen/recurso del coche
        graficoCoche = contexto.getResources().getDrawable(R.drawable.coche);

        //Creamos un vector para contener todos los coches que se ven en la pantalla
        //y lo rellenamos con graficos de coches
        // con valores aleatorios para su velocidad, direccion y rotacion.
        Coches = new Vector<Grafico>();
        for (int i = 0; i < numCoches; i++) {
            Grafico coche = new Grafico(this, graficoCoche);
            coche.setIncX(Math.random() * 4 - 2);
            coche.setIncY(Math.random() * 4 - 2);
            coche.setAngulo((int) (Math.random() * 360));
            coche.setRotacion((int) (Math.random() * 8 - 4));
            Coches.add(coche);
        }

        //BICI
        graficoBici = contexto.getResources().getDrawable(R.drawable.bici);
        bici = new Grafico(this, graficoBici);
        corriendo = true;

        //HILO QUE CONTROLA EL JUEGO
        hiloJuego = new HiloJuego();
        hiloJuego.start();

        //REGISTRO DE SENSORES
       // SensorManager miSensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
       // List<Sensor> listasensores = miSensorManager.getSensorList(Sensor.TYPE_ORIENTATION);
       // if (!listasensores.isEmpty()) {
       //     Sensor sensorOrientacion = listasensores.get(0);
       //     miSensorManager.registerListener((SensorEventListener) this, sensorOrientacion, SensorManager.SENSOR_DELAY_UI);
       // }

        //DIBUJO RUEDA

        ShapeDrawable dRueda = new ShapeDrawable(new RectShape());
        dRueda.getPaint().setColor(Color.WHITE);
        dRueda.getPaint().setStyle(Paint.Style.STROKE);
        dRueda.setIntrinsicWidth(15);
        dRueda.setIntrinsicHeight(3);

        graficoRueda = dRueda;
        graficoRueda = contexto.getResources().getDrawable(R.drawable.rueda);
        rueda = new Grafico(this, graficoRueda);
        ruedaActiva = false;

    }

    //Al comenzar y dibujar por primera vez la pantalla del juego
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        //Dibujamos los coches en posiciones aleatorias
        for (Grafico coche : Coches) {
            do {
                coche.setPosX(Math.random() * (w - coche.getAncho()));
                coche.setPosY(Math.random() * (h - coche.getAlto()));
            } while (coche.distancia(bici) < (w + h) / 5);
        }


        //PARA PONER LA BICI EN EL CENTRO DE LA PANTALLA
        bici.setPosX((w - bici.getAncho()) / 2);
        bici.setPosY((h - bici.getAlto()) / 2);

        //HILO QUE CONTROLA EL JUEGO

        hiloJuego = new HiloJuego();
        hiloJuego.start();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Dibujamos cada uno de los coches
        for (Grafico coche : Coches) {
            coche.dibujaGrafico(canvas);
        }

        //DIBUJAMOS BICI

        bici.dibujaGrafico(canvas);

        //DIBUJAMOS LA RUEDA SI LO INDICA LA VARIABLE RUEDAACTIVA

        if (ruedaActiva)
            rueda.dibujaGrafico(canvas);
    }

    protected synchronized void actualizaMovimiento() {
        long ahora = System.currentTimeMillis();
        // No hacemos nada si el período de proceso no se ha cumplido.
        if (ultimoProceso + PERIODO_PROCESO > ahora) {
            return;
        }
        // Para una ejecución en tiempo real calculamos retardo
        double retardo = (ahora - ultimoProceso) / PERIODO_PROCESO;
        // Actualizamos la posición de la bici
        bici.setAngulo((int) (bici.getAngulo() + giroBici * retardo));
        double nIncX = bici.getIncX() + aceleracionBici
                * Math.cos(Math.toRadians(bici.getAngulo())) * retardo;
        double nIncY = bici.getIncY() + aceleracionBici
                * Math.sin(Math.toRadians(bici.getAngulo())) * retardo;
        if (Grafico.distanciaE(0, 0, nIncX, nIncY) <= Grafico.getMaxVelocidad()) {
            bici.setIncX(nIncX);
            bici.setIncY(nIncY);
        }
        bici.incrementaPos();

        bici.setIncX(0);
        bici.setIncY(0);


        //Movemos los coches
        for (Grafico coche : Coches) {
            coche.incrementaPos();
        }
        ultimoProceso = ahora;


        if (ruedaActiva) {
            rueda.incrementaPos();
            distanciaRueda--;
            if (distanciaRueda < 0) {
                ruedaActiva = false;
            } else {
                for (int i = 0; i < Coches.size(); i++) {
                    if (rueda.verificaColision(Coches.elementAt(i))) {
                        destruyeCoche(i);
                        i = Coches.size();
                        ruedaActiva = false;
                    }
                }
            }
        }

    }


     class HiloJuego extends Thread {
        private boolean pausa,corriendo;
        public synchronized void pausar(){
            pausa=true;
        }

        public synchronized void reanudar(){
            pausa=false;
            notify();
        }

        public void detener(){
            corriendo=false;
            if (pausa) reanudar();
        }


        @Override
        public void run() {
            corriendo=true;
                while (corriendo) {
                    actualizaMovimiento();
                    synchronized (this){
                        while (pausa){
                            try {
                                wait();
                            }catch (Exception e){

                            }
                        }
                    }
                }
        }
    }
            public boolean onKeyDown(int codigoTecla, KeyEvent evento) {
                super.onKeyDown(codigoTecla, evento);
                boolean pulsacion=true;
//Procesamos la pulsación
                switch (codigoTecla) {
                    case KeyEvent.KEYCODE_DPAD_UP:
                        aceleracionBici=+PASO_ACELERACION_BICI;
                        break;
                    case KeyEvent.KEYCODE_DPAD_LEFT:
                        giroBici=-PASO_GIRO_BICI;
                        break;
                    case KeyEvent.KEYCODE_DPAD_RIGHT:
                        giroBici=+PASO_GIRO_BICI;
                        break;
                    case KeyEvent.KEYCODE_DPAD_CENTER:
                    case KeyEvent.KEYCODE_ENTER:
                        lanzarRueda();
                        break;
                    default:
//Si estamos aquí no hemos pulsado nada que  interese
                        pulsacion=false;
                        break;
                }
                return pulsacion;
            }

    public boolean onKeyUp(int codigoTecla, KeyEvent evento) {
        super.onKeyDown(codigoTecla, evento);
        boolean pulsacion=true;
//Procesamos la pulsación
        switch (codigoTecla) {
            case KeyEvent.KEYCODE_DPAD_UP:
                aceleracionBici=0;
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                giroBici=0;
                break;
            default:
//Si estamos aquí no hemos pulsado nada que  interese
                pulsacion=false;
                break;
        }
        return pulsacion;
    }

    public boolean onTouchEvent(MotionEvent evento) {
        super.onTouchEvent(evento);
//Obtenemos la posición de la pulsación
        float x=evento.getX(); float y=evento.getY();
        switch (evento.getAction()) {
            case MotionEvent.ACTION_DOWN:// se activa la variable disparo
                disparo=true;
                break;
            case MotionEvent.ACTION_MOVE:
                float dx=Math.abs(x-mX);float dy=Math.abs(y-mY);
//Un desplazamiento  horizontal hace girar la bici.
                if (dy<6 && dx>6)
                {giroBici = Math.round((x-mX)/2);
                    disparo = false;
                } else //desplazamiento vertical produce aceleración.
                    if (dx<6 && dy>6)
                    {aceleracionBici = Math.round((mY-y)/25);
                        disparo = false;
                    }
                break;
            case MotionEvent.ACTION_UP://levatar dedo sin despazar =>disparo
                giroBici = 0;
                aceleracionBici = 0;
                if (disparo) lanzarRueda();
                break;
        }
        mX=x; mY=y;
        return true;
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }

    private boolean hayvalorinicial=false;
    private float valorinicial;

    public void onSensorChanged(SensorEvent evento){
        float valor=evento.values[1];
        if(!hayvalorinicial){
            valorinicial=valor;
            hayvalorinicial=true;
        }

        giroBici=(int)(valor-valorinicial)/3;

    }

    private void destruyeCoche(int i){
        Coches.remove(i);
        ruedaActiva=false;
        //ACTIVAMOS EL SONIDO DE LA EXPLOSION
      //  MediaPlayer mimediaplayer=MediaPlayer.create(getContext(),R.raw.explosion);
      //  mimediaplayer.start();
    }

    private void lanzarRueda(){
        rueda.setPosX(bici.getPosX()+bici.getAncho()/2 - rueda.getAncho()/2);
        rueda.setPosY(bici.getPosY()+bici.getAlto()/2 - rueda.getAlto()/2);
        rueda.setAngulo(bici.getAngulo());
        rueda.setIncX(Math.cos(Math.toRadians(rueda.getAngulo()))*VELOCIDAD_RUEDA);
        rueda.setIncX(Math.sin(Math.toRadians(rueda.getAngulo()))*VELOCIDAD_RUEDA);
        distanciaRueda=(int) Math.min(this.getWidth()/Math.abs(rueda.getIncX()),this.getHeight()/Math.abs(rueda.getIncY()))-2;
        ruedaActiva=true;

    }

    public HiloJuego getHilo(){
        return hiloJuego;
    }

    public void setCorriendo(boolean corriendo){
        this.corriendo=corriendo;
    }
    public void setPausa(boolean pausa){
        this.pausa=pausa;
    }

}



