package zigbee;

import com.rapplogic.xbee.api.XBee;
import com.rapplogic.xbee.api.XBeeAddress64;
import com.rapplogic.xbee.api.zigbee.ZNetRxIoSampleResponse;

public class Probe {
	private XBeeAddress64 address64;
	private String nameProbe;
	private ZNetRxIoSampleResponse samples;

	XBee xbee = new XBee();

	public Probe(XBeeAddress64 address64R, String nameProbeR, ZNetRxIoSampleResponse samplesR) {
		address64 = address64R;
		nameProbe = nameProbeR;
		samples = samplesR;
	}

	public ZNetRxIoSampleResponse getPinProbe() {
		return samples;
	}

	public String getNameProbe() {
		return nameProbe;
	}

	public void setNameProbe(String nameSensor) {
		this.nameProbe = nameSensor;
	}

	public XBeeAddress64 getAddress64() {
		return address64;
	}

	public void setAddress64(XBeeAddress64 address64) {
		this.address64 = address64;
	}

	public void update(ZNetRxIoSampleResponse ioSample) {
		samples = ioSample;

	}

	public boolean isButtonPressed() {
		return samples.isD0On();

	}

	public int voltReceived() {
		return samples.getAnalog0();
	}

	public int getLumens() {
		/*
		 * RL=500/lux V0=5*(RL/(RL+R)) V0=LDR_value*ADC_value lux=(250/V0)-50
		 */
		if (voltReceived() != 0) {
			return (250 / voltReceived()) - 50;
		}
		return 0;
	}

}