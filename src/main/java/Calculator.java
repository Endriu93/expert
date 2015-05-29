
public class Calculator {

	public static float DisplayMin;
	public static float DisplayMax;
	public static float CoresMin;
	public static float CoresMax;
	public static float ProcessorMin;
	public static float ProcessorMax;
	public static float CameraMin;
	public static float CameraMax;
	public static float BatteryMin;
	public static float BatteryMax;
	public static float RamMin;
	public static float RamMax;
	public static float StorageMin;
	public static float StorageMax;
	
	//public int Coeficient=100;	// współczynnik
	public static float displayPoints(float num)
	{
		return (num - DisplayMin)/(DisplayMax - DisplayMin);
	}
	public static float batteryPoints(float num)
	{
		return (num - BatteryMin)/(BatteryMax - DisplayMin);
	}
	public static float coresPoints(float num)
	{
		return (num - CoresMin)/(CoresMax - DisplayMin);
	}
	public static float processorPoints(float num)
	{
		return (num - ProcessorMin)/(ProcessorMax - DisplayMin);
	}
	public static float cameraPoints(float num)
	{
		return (num - CameraMin)/(CameraMax - DisplayMin);
	}
	public static float ramPoints(float num)
	{
		return (num - RamMin)/(RamMax - RamMin);
	}
	public static float storagePoints(float num)
	{
		return (num - StorageMin)/(StorageMax - StorageMin);
	}
}
