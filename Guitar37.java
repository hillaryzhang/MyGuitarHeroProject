// 1/19/19
// CSE143
// TA: Sea Eun Lee
// Section: AV
// Assignment #2 Guitar Hero pt.2 Guitar37

import java.util.*;

/* The following class can be used to keep track of a musical instrument with 
   multiple strings */
   
public class Guitar37 implements Guitar{

   /* the following constant KEYBOARD is the 37 characters used to represent the 
      37 notes on the chromatic scale from 110Hz to 880Hz. The characters correspond
      to the keypad in a way that imitates a piano keyboard */
      
   public static final String KEYBOARD =
        "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";  // keyboard layout
   
   /* GuitarString[] allStrings - the list to keep track of all 37 strings of the
      instrument (say, a guitar) */ 
   /* int tic - keeps track of the number of times the time has advanced forward 
      by one tic */
      
   private GuitarString[] allStrings;
   private int tic;
   
   /* post: constructs a guitar with 37 different strings. Each string is a new
      guitar string of a specified and different frequency, where the frequency is 
      increased with each successive string */
      
   public Guitar37() {
      allStrings = new GuitarString[37];
      for (int i = 0; i < 37; i++) {
         allStrings[i] = new GuitarString(440.0 * Math.pow(2, ((double)i - 24) / 12));
      } 
   }
   
   /* post: allows the user to specify which note to play by a given pitch. Plays the 
      string in the guitar string list that corresponds with the given
      pitch. If a note cannot be played, it is ignored */
   /* int pitch - the given pitch specified by the user that the guitar should 
      play, which is expressed as an integer where the value 0 represents the 
      concert-A pitch and all other notes vary with a chromatic scale relative to 
      concert-A */
      
   public void playNote(int pitch) {
      int index = pitch + 24; // Pitch - 24 = index of array
      if (index >= 0 && index < 37) {
         allStrings[index].pluck();
      }
   }
   
   // post: returns whether or not the guitar string list posseses the given string
   // char string - the given string to be searched for in the guitar
   
   public boolean hasString(char string) {
      for (int i = 0; i < 37; i++) {
         if (KEYBOARD.charAt(i) == string) {
            return true;
         }
      }
      return false;
   }
   
   /* pre: the given string exists in the list of guitar strings (throws new 
      IllegalArgumentException if not) */
   /* post: provides another option for the user in specifying which note to play by 
      basing the note off of a specified string. Plays the note of the given 
      string */
   /* char string - the given character by the user that corresponds to a guitar 
      string and thus specifies which string of the guitar should be played */
   
   public void pluck(char string) {
      int index = -1;
      for (int i = 0; i < 37; i++) {
         if (KEYBOARD.charAt(i) == string) {
            index = i;
         }
      }
      if (index == -1) {
         throw new IllegalArgumentException();
      }
      allStrings[index].pluck();
   }
   
   /* post: returns the sum of the samples of all 37 strings present in the guitar 
      to advance the time forward one tic */
      
   public double sample() {
      double sum = 0.0;
      for (int i = 0; i < 37; i++) {
         sum += allStrings[i].sample();
      }
      return sum;
   }
   
   // post: advances the time forward by one tic 
   
   public void tic() {
      for (int i = 0; i < 37; i++) {
         allStrings[i].tic();
      }
      tic++;  
   }
   
   /* post: returns the number of times that the tic has been increased, and thus 
      the number of times the sample has been taken */
   
   public int time() {
        return tic; 
   }
}

   
   