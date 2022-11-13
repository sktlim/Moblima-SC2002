package Controllers;

import java.io.*;

/**
 * This class attempts to erase characters echoed to the console. For use during password entry in login process.
 */

class MaskingThread extends Thread {

    /**
     * Semaphore to signal for the masking to stop when user has finished inputting password
     */
    private volatile boolean stop;

    /**
     * Char to replace and mask each password char input by the user
     */
    private final char echochar = '*';

    /**
     *@param prompt The prompt displayed to the user
     */
    public MaskingThread(String prompt) {
        System.out.print(prompt);
    }

    /**
     * Begin masking until asked to stop.
     */
    public void run() {

        int priority = Thread.currentThread().getPriority();
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

        try {
            stop = true;
            while(stop) {
                System.out.print("\010" + echochar);
                try {
                    // attempt masking at this rate
                    Thread.currentThread().sleep(1);
                }catch (InterruptedException iex) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        } finally { // restore the original priority
            Thread.currentThread().setPriority(priority);
        }
    }

    /**
     * Instruct the thread to stop masking.
     */
    public void stopMasking() {
        this.stop = false;
    }
}