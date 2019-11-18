// This is a program that can be used to test the basic functionality of the
// Guitar37 class.  It does not test all of the functionality.
//
// The main method has an array of 37 frequency values that should be produced
// by a correctly working Guitar37 class.  The actual frequencies don't all end
// in 0.25, but this version of GuitarString converts them to that form so that
// it is easier to understand the output produced by the program.  In
// particular, it is easier to see if it is properly adding new values as notes
// are played.

import java.util.*;

public class Test37 {
    public static final String KEYBOARD =
        "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";  // keyboard layout

    public static void main(String[] args) {
        double[] frequencies = 
            {110.25, 116.25, 123.25, 130.25, 138.25, 146.25, 155.25, 164.25,
             174.25, 184.25, 195.25, 207.25, 220.25, 233.25, 246.25, 261.25,
             277.25, 293.25, 311.25, 329.25, 349.25, 369.25, 391.25, 415.25,
             440.25, 466.25, 493.25, 523.25, 554.25, 587.25, 622.25, 659.25,
             698.25, 739.25, 783.25, 830.25, 880.25};
        
        Guitar g = new Guitar37();
        compare(frequencies, GuitarString.nums);
        for (int i = 0; i < KEYBOARD.length(); i++) {
            int note = i - 24;
            System.out.println("Playing note " + note + " (initially " + 
                               frequencies[i] + ")");
            g.playNote(note);
            advance(g, 4);
            char key = KEYBOARD.charAt(i);
            System.out.println("Plucking string '" + key + "'");
            if (g.hasString(key)) {
                g.pluck(key);
                advance(g, 4);
            } else {
                System.out.println("ERROR: not recognizing key '" + key + "'");
            }
            System.out.println("making an extra call on tic");
            // throw in an extra call on tic without calling time and sample
            g.tic();
            System.out.println();
        }

        // now test a few unsupported values of pitch which should be ignored
        // but should not throw an exception
        int[] unsupportedPitch = {-32, -25, 13, 18};
        for (int n : unsupportedPitch) {
            System.out.println("testing playNote for unsupported pitch " + n);
            g.playNote(n);
            advance(g, 4);
            System.out.println();
        }
    }

    // This method advances the simulation the given number of tics reporting
    // the time reading and sample values from the given guitar.
    public static void advance(Guitar g, int tics) {
        for (int i = 0; i < tics; i++) {
            System.out.println("time " + g.time() + " sample = " + g.sample());
            g.tic();
        }
    }

    // This method compares the array nums with the set of numbers in nums2,
    // suspending program execution if the two sets of numbers differ.
    public static void compare(double[] nums, Set<Integer> nums1) {
        Set<Integer> nums2 = new TreeSet<>();
        for (double n : nums) {
            nums2.add((int) n);
        }
        if (!nums1.equals(nums2)) {
            System.out.println("Wrong frequencies for Guitar37 construction.");
            System.out.println("should be approximately: " + nums2);
            System.out.println("yours are approximately: " + nums1);
            System.exit(1);
        }
    }
}
