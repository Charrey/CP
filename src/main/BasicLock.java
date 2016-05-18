package main;

public interface BasicLock {

    /**
     * Aqcuires the lock.
     * thread_nr is the number of the requesting thread, thread_nr == 0|1
     */
    void lock (int thread_nr );

    /**
     * Releases the lock.
     * thread_nr is the number of the releasing thread, thread_nr == 0|1
     */
    void unlock(int thread_nr);

}
