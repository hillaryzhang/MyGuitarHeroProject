//// WRITTEN BY HILLARY ZHANG, 2019

import java.util.*;

/* The following class can be used to model a vibrating guitar string of 
   a given frequency */

public class GuitarString {
   
   /* the following constant represents the energy decay factor in the 
      Karplus-Strong update, and is not meant to be changed */
   
   public static final double ENERGY_DECAY_FACTOR = 0.996;
   
   /* Queue<Double> ringBuffer - the ring buffer that models the string
      tied down at both ends in which energy moves back and forth, 
      characterized by the frequency */
   /* double ringBufferSize - the size of the ring buffer, which decreases
      with a Karplus-Strong update */
      
   Queue<Double> ringBuffer;
   double ringBufferSize; 
   
   /* pre: the given frequency is greater than zero and the resulting size 
      of the ring buffer is greater than two (throws new 
      IllegalArgumentException if not) */
   /* post: contructs a guitar string inititally at rest, represented by a 
      ring buffer of size based on the given frequency and sampling rate */
   // double frequency - the given frequency that the string if constructed by
   
   public GuitarString(double frequency) {
      ringBuffer = new LinkedList<Double>();
      ringBufferSize = Math.round(StdAudio.SAMPLE_RATE / frequency); 
      if (frequency <= 0 || ringBufferSize < 2) {
         throw new IllegalArgumentException();
      }
      for (int i = 0; i < ringBufferSize; i++) {
         ringBuffer.add(0.0);
      }
   }
   
   /* pre: the length of the given list used to set the values of the ring 
      buffer equal to has at least two elements (throws new 
      IllegalArgumentException if not */
   /* post: constructs a guitar string represented by a ring buffer and sets 
      the contents of the ring buffer to the values in the given list. Used 
      only for testing purposes */
   /* double[] init - the given list of number values to set each element 
      of the ring buffer equal to */
      
   public GuitarString(double[] init) {
      ringBuffer = new LinkedList<Double>(); 
      if (init.length < 2) {
         throw new IllegalArgumentException();
      } else {
         for (int i = 0; i < init.length; i++) {
            ringBuffer.add(init[i]);
         }
      }
   }
   
   /* post: replaces each element in the ring buffer with a random decimal value 
      between -0.5 inclusive and +0.5 exclusive */
      
   public void pluck() {
      for (int i = 0; i < ringBufferSize; i++) {
         double replaceElement = ringBuffer.remove();
         replaceElement = Math.random() - 0.5; 
         ringBuffer.add(replaceElement);
      }
   }
   
   // post: applies the Karplus-Strong update to the ring buffer once 
   
   public void tic() {
      double firstElement = ringBuffer.remove();
      double secondElement = ringBuffer.peek();
      double updateElement = (firstElement + secondElement) * 0.5 
                             * ENERGY_DECAY_FACTOR;
      ringBuffer.add(updateElement);
   }
   
   // post: returns the current first value in the ring buffer
   
   public double sample() {
      return ringBuffer.peek(); 
   }
}