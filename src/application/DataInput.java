package application;

public class DataInput {
	private double doubleValue;
	private String stringValue;

	public DataInput(String month, double value) {
		this.setDoubleValue(value);
		this.setStringValue(month);
	}

	public double getDoubleValue() {
		return doubleValue;
	}

	public void setDoubleValue(double doubleValue) {
		this.doubleValue = doubleValue;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}
	

}
