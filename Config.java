// 170101003 -- Ahmet Kaan Cig

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

public class Config implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private long t1;
	private long t2;
	private long t3;
	private long t4;
	private long delay;
	private long offset;
	private double o;
	private double d;

	public Config() {
	
	}
	public long getDelay() {
		return delay;
	}
	public void setDelay(long delay) {
		this.delay = delay;
	}
	public long getOffset() {
		return offset;
	}
	public void setOffset(long offset) {
		this.offset = offset;
	}
	public long getT1() {
		return t1;
	}
	public void setT1(long t1) {
		this.t1 = t1;
	}
	public long getT2() {
		return t2;
	}
	public void setT2(long t2) {
		this.t2 = t2;
	}
	public long getT3() {
		return t3;
	}
	public void setT3(long t3) {
		this.t3 = t3;
	}
	public long getT4() {
		return t4;
	}
	public void setT4(long t4) {
		this.t4 = t4;
	}
	public double getD() {
		return d;
	}
	public double getO() {
		return o;
	}
	public void getResultsOandD() {
		d = 0.5 * (t2 - t1 + t4 - t3);
		o = t3+d-t4;	
	}
	public long getRandomDelay(){
		return ThreadLocalRandom.current().nextLong(10, 1000 + 1);
	}
	public void sleepThread(long duration){
		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}