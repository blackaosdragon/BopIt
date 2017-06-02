package mx.org.gg.proyect.dragon.juegodireccionesv2;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView tInstruccion;
    TextView numero;
    boolean onPlay=true;
    boolean genNum = true;
    boolean instruccion=false;
    boolean derecha =true;
    boolean izquiera =true;
    boolean atras =true;
    int aleatorio;
    MediaPlayer izq;
    MediaPlayer der;
    MediaPlayer atr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tInstruccion = (TextView) findViewById(R.id.textInstruccion);
        numero = (TextView) findViewById(R.id.numero);
        izq = MediaPlayer.create(getBaseContext(), R.raw.izq);
        der = MediaPlayer.create(getBaseContext(), R.raw.der);
        atr = MediaPlayer.create(getBaseContext(), R.raw.atr);
        SensorManager acelerometro = (SensorManager) getSystemService(SENSOR_SERVICE);
        acelerometro.registerListener(this, acelerometro.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        int x,y,z;
        x = (int) event.values[0];
        y = (int) event.values[1];
        z = (int) event.values[2];
        //giroscopio.setText("X = "+x+"  Y = "+y+"  Z = "+z);

        if (onPlay==true){

            if (genNum==true) {

                aleatorio = (int) (Math.random()*10);
                numero.setText(" "+aleatorio);
                genNum = false;
            }

            if (aleatorio==0 || aleatorio==1 || aleatorio==2){
                tInstruccion.setText("Izquierda!");
                izq.start();

                //
                instruccion = true;
                if (instruccion==true)
                {
                    if (izquiera==true && derecha==false && atras==false)
                    {
                        genNum=true;
                        instruccion = false;
                        tInstruccion.setText("Correcto");
                        izquiera=true;
                        derecha=true;
                        atras=true;
                        try {
                            Thread.sleep(2000);
                        }catch (Exception e){}

                    }
                    if ((izquiera==false && derecha==true && atras==false) ||
                            (izquiera==false && derecha==false && atras==true))
                    {
                        //onPlay = false;
                        //instruccion = false;
                        tInstruccion.setText("Perdiste!!");
                        izq.stop();
                    }
                }

            }
            if (aleatorio==3 || aleatorio==4 || aleatorio==5) {
                tInstruccion.setText("Derecha!");
                der.start();

                //
                instruccion = true;
                if (instruccion == true) {
                    if (izquiera == false && derecha == true && atras == false) {
                        genNum = true;
                        instruccion = false;
                        tInstruccion.setText("Correcto");
                        izquiera=true;
                        derecha=true;
                        atras=true;
                        try {
                            Thread.sleep(2000);
                        }catch (Exception e){}

                    }
                    if ((izquiera==true && derecha==false && atras==false) ||
                            (izquiera==false && derecha==false && atras==true)) {
                        //onPlay = false;
                        //instruccion = false;
                        tInstruccion.setText("Perdiste!!");
                        der.stop();
                    }
                }
            }
             if (aleatorio==6 || aleatorio == 7 || aleatorio == 8 || aleatorio==9){
                 tInstruccion.setText("Hacia atras!");
                 atr.start();

                 //
                 instruccion= true;
                 if(instruccion==true){
                     if(izquiera==false && derecha==false && atras==true){
                         genNum=true;
                         instruccion=false;
                         tInstruccion.setText("Correcto");
                         izquiera=true;
                         derecha=true;
                         atras=true;
                         try {
                             Thread.sleep(1000);
                         }catch (Exception e){}

                     }
                     if ((izquiera==false && derecha==true && atras==false) ||
                             (izquiera==true && derecha==false && atras==false)){
                         //onPlay=false;
                         //instruccion=false;
                         tInstruccion.setText("Perdiste!!");
                         atr.stop();
                     }
                 }

             }


            {
                if ((x <= -4 && x >= -9) && (y <= 8 && y >= 0) && (z <= 3 && z >= 0
                        /*x <= -4 && x >= -9) && (y <= 9 && y >= 0) && (z <= 2 && z >= 0*/)) {
                    derecha = true;
                    izquiera = false;
                    atras = false;
                }
                if ((x < 10 && x > 3) && (y < 10 && y > 0) && (z < 4 && z > 0
                        /*x < 10 && x > 3) && (y < 8 && y > 0) && (z < 4 && z > 0
                        x < 9 && x > 3) && (y < 8 && y > 0) && (z < 4 && z > 0*/)) {
                    izquiera = true;
                    derecha = false;
                    atras = false;
                }
                if ((x < 3 && x > -3) && (y < 7 && y > 0) && (z < 10 && z > 6

                        /*x < 3 && x > -3) && (y < 6 && y > 0) && (z < 10 && z > 2*/)) {
                    atras = true;
                    derecha = false;
                    izquiera = false;
                }
            }
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
