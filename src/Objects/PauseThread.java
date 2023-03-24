package Objects;

public class PauseThread extends Thread {
    private Thread r = new Thread(this);
    
    @Override
    public void run() {
        while (!r.isInterrupted()) {
            //System.out.println("running");
        }
    }
    
    public Thread getThread() {
        return r;
    }
    
    public boolean isRunning() {
        return r.getState() == Thread.State.RUNNABLE;
    }
}
