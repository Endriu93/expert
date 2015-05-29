
public class Calculator {

	public static float DisplayMin=100;
	public static float DisplayMax=0;
	public static float CoresMin=10;
	public static float CoresMax=0;
	public static float ProcessorMin=100000;
	public static float ProcessorMax=0;
	public static float CameraMin=100;
	public static float CameraMax=0;
	public static float BatteryMin=10000;
	public static float BatteryMax=0;
	public static float RamMin=100;
	public static float RamMax=0;
	public static float StorageMin=10000;
	public static float StorageMax=0;
	
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
