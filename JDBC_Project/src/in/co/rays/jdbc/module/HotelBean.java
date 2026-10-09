package in.co.rays.jdbc.module;

public class HotelBean {
	private int roomNo;
	private String roomType;
	private int floor;
	private double pricePerNight;
	private boolean availability;

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public double getPricePerNight() {
		return pricePerNight;
	}

	public void setPricePerNight(double pricePerNight) {
		this.pricePerNight = pricePerNight;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

}
