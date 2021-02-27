import synthesizer.GuitarString;

import java.util.HashMap;
import java.util.Map;

public class GuitarHero {
    private static final double CONCERT_A = 440.0;
    private static final double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);


    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        Map<Character,GuitarString> keys = new HashMap<>();
        for(int i = 0;i < keyboard.length();i++) {
            GuitarString guitarString = new GuitarString(CONCERT_A * Math.pow(2, (i - 24.0) / 12.0));
            keys.put(keyboard.charAt(i),guitarString);
        }

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if(keys.containsKey(key)) {
                    keys.get(key).pluck();
                }
            }

             //compute the superposition of samples
            double sample = 0.0;
            for(GuitarString s : keys.values()) {
                sample += s.sample();
            }

             //play the sample on standard audio
            StdAudio.play(sample);

             //advance the simulation of each guitar string by one step
            for(GuitarString s : keys.values()) {
                s.tic();
            }
        }
    }
}
