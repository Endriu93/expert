
public class Phone implements Comparable{

	private int ID;
	private float Coefficient;
	Phone(int ID, float coefficient)
	{
		this.ID = ID;
		this.Coefficient = coefficient;
	}
	public int getID()
	{
		return ID;
	}
	public float getCoef()
	{
		return Coefficient;
	}
	@Override
	public int compareTo(Object other) {
		// TODO Auto-generated method stub
		return (new Float(this.Coefficient)).compareTo(((Phone)other).Coefficient);
	}
}
